/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.planes;

import airport.models.plane.Plane;
import airport.models.storage.PlaneStorage;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author alejo
 */
public class PlaneControllerUpdateJson {
    public ArrayList<String> updateJsonComponent(){
        PlaneStorage storage = PlaneStorage.getInstance();
        
        ArrayList<Plane> planes = storage.getPlanes();
        
        ArrayList<String> ids = new ArrayList<>();
        
        for(Plane p : planes){
            ids.add(p.getId()+"");
        }
        
        return ids;
        
    }
}
