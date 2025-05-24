/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.storage;

import airport.models.Plane;
import static airport.models.storage.json.PlaneJson.readPlanes;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author alejo
 */
public class PlaneStorage {
     //Creo lista
    private ArrayList<Plane> planes = new ArrayList();
    private static PlaneStorage instance;
    //Aplico singleton
    public static PlaneStorage getInstance(){
        if (instance == null) {
            instance = new PlaneStorage();
        }
        return instance;
    }

    public ArrayList<Plane> getPlanes() {
        return planes;
    }
    
    public boolean AddPlane(Plane plane){
        for (Plane p : this.planes) {
            if (p.getId().equals(plane.getId())) {
                return false;
            }
        }
        this.planes.add(plane);
        return true;  
    }
    
    public Plane findById(String id) {
        for (Plane plane : planes) {
            if (plane.getId().equals(id)) {
                return plane;
            }
        }
        return null; 
    }
    
    
    
   
}
