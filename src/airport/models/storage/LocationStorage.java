/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.storage;

import airport.models.Location;
import static airport.models.storage.json.LocationJson.readLocation;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author alejo
 */
public class LocationStorage {
    private static ArrayList<Location> locations = new ArrayList();

    public LocationStorage() throws IOException {
        locations = readLocation("json/locations.json");
    }
    
    public static ArrayList<Location> getLocation() {
        return locations;
    }
}
