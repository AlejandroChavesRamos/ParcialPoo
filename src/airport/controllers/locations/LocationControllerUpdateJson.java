/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.locations;

import airport.models.location.Location;
import airport.models.storage.LocationStorage;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author alejo
 */
public class LocationControllerUpdateJson {
    public ArrayList<String> updateJsonComponent(){
        LocationStorage storage = LocationStorage.getInstance();
        
        ArrayList<Location> locations = storage.getLocation();
       
        ArrayList<String> ids = new ArrayList<>();
        
        for(Location l : locations){
            ids.add(l.getAirportId()+"");
        }
        
        return ids;
        
    }
}
