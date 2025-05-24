/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers;

import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.models.Plane;
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
        
        Response r = new Response("Aviones actualizados", Status.Ok, data);
        return r.clone();
    }
}
