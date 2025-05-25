/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers;

import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.models.plane.Plane;
import airport.models.storage.PlaneStorage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alejo
 */
public class PlaneController {
    public static Response showAllPlane(){
        PlaneStorage storage = PlaneStorage.getInstance();
        ArrayList<Plane> planes = storage.getPlanes();
        
        planes.sort(Comparator.comparing(Plane::getId));
        ArrayList<Object[]> data = new ArrayList<>();
        for(Plane p: planes){
            data.add(new Object[]{
               p.getId(),
               p.getBrand(),
               p.getModel(),
               p.getMaxCapacity(),
               p.getAirline(),
               p.getNumFlights()
                    
            });
        }
        
        Response r = new Response("Planes updated", Status.Ok, data);
        return r.clone();
    }
    
    public ArrayList<String> updateJsonComponent(){
        PlaneStorage storage = PlaneStorage.getInstance();
        
        ArrayList<Plane> planes = storage.getPlanes();
        planes.sort(Comparator.comparing(Plane::getId));
        ArrayList<String> ids = new ArrayList<>();
        
        for(Plane p : planes){
            ids.add(p.getId()+"");
        }
        
        return ids;
        
    }
    
    public static Response createPlane(String id, String brand, String model, String maxCapacity, String airline){
        try{
            int maxCapacityInt;
            if(id == null || brand == null || model == null || maxCapacity == null || airline == null){
                Response r = new Response("All fields must be filled", Status.Bad_Request);
                return r.clone();
            }
            if(id.length()!= 7){
                Response r = new Response("Id must be 7 digits long", Status.Bad_Request);
                return r.clone();
            }
            for(int i = 0; i<2; i++){
                char letra = id.charAt(i);
                if(letra < 'A' || letra > 'Z'){
                    Response r = new Response("The id must start with 2 capital letters", Status.Bad_Request);
                    return r.clone();
                }
            }
            for(int i = 2; i<7; i++){
                char n = id.charAt(i);
                if(n < '0' || n > '9'){
                    Response r = new Response("Invalid id", Status.Bad_Request);
                    return r.clone();
                }
            }
            try{
                maxCapacityInt = Integer.parseInt(maxCapacity);
            }catch (NumberFormatException ex) {
                Response r = new Response("Max capacity must be numeric", Status.Bad_Request);
                return r.clone();
            }
            
            PlaneStorage storage = PlaneStorage.getInstance();
            if(!storage.AddPlane(new Plane(id, brand, model, maxCapacityInt, airline))){
                Response r = new Response("A airplane with that id already exist", Status.Bad_Request);
                return r.clone();
            }
            Response r = new Response("Plane created", Status.Created);
            return r.clone();
        }catch (Exception ex) {
            Response r = new Response("Unexpected error", Status.Internal_Server_Error);
            return r.clone();
        }
    }
}
