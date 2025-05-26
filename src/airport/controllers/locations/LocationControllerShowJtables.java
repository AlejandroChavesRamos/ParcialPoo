/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.locations;

import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.models.location.Location;
import airport.models.storage.LocationStorage;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author alejo
 */
public class LocationControllerShowJtables {
    public static Response showAllLocations(){
        LocationStorage storage = LocationStorage.getInstance();
        ArrayList<Location> locations = storage.getLocation();
        
        
        ArrayList<Object[]> data = new ArrayList<>();
        for (Location l : locations) {
            data.add(new Object[]{
                l.getAirportId(),
                l.getAirportName(),
                l.getAirportCity(),
                l.getAirportCountry()
                
            });
        }
        Response r = new Response("Locations Updated", Status.Ok, data);
        return r.clone();
    }
}
