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
    private ArrayList<Location> locations = new ArrayList();
    private static LocationStorage instance;
    
    
    public ArrayList<Location> getLocation() {
        return locations;
    }
    
    public static LocationStorage getInstance(){
        if (instance == null) {
            instance = new LocationStorage();
        }
        return instance;
    }
    
    public boolean addLocation(Location location){
        for(Location l : this.locations){
            if (l.getAirportId().equals(location.getAirportId())) {
                return false;
            }
        }
        this.locations.add(location);
        return true;
    }
}
