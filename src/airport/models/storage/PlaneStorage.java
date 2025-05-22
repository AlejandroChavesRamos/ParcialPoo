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
    private static ArrayList<Plane> planes = new ArrayList();

    public PlaneStorage() throws IOException {
        planes = readPlanes("json/Planes.json");
    }
    
    public static ArrayList<Plane> getPlane() {
        return planes;
    }

   
}
