/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers;

import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.models.Location;
import airport.models.storage.LocationStorage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alejo
 */
public class LocationController {
    public static Response showAllLocations(){
        LocationStorage storage = LocationStorage.getInstance();
        ArrayList<Location> locations = storage.getLocation();
        
        locations.sort(Comparator.comparing(Location::getAirportId));
        return new Response("Localizaciones actualizadas", Status.Ok, locations);
    }
}
