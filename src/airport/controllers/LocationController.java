/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers;

import airport.models.Location;
import airport.models.storage.LocationStorage;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alejo
 */
public class LocationController {
    private static LocationStorage locationS;
    
    public LocationController() throws IOException{
        locationS = new LocationStorage();
    }
    
    public void createLocation(String airportId, String airportName, String airportCity, String airportCountry, double airportLatitude, double airportLongitude){
        Location location = new Location(airportId, airportName, airportCity, airportCountry, airportLatitude, airportLongitude);
        locationS.getLocation().add(location);
    }
    
    public Location getIdLocation(String id){
        for(Location location: locationS.getLocation()){
            if(location.getAirportId().equals(id)){
                return location;
            }
        }
        return null;
    }
    
    public DefaultTableModel toLocationsJTable(){
        String[] columnas = {"Airport ID", "Airport Name", "City", "Country"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0); 
        
        if(locationS.getLocation().isEmpty()){
            System.out.println("Lista Vacia");
        } else{
            for(Location location : locationS.getLocation()){
                Object[] fila = new Object[] {
                    location.getAirportId(), location.getAirportName(), location.getAirportCity(), location.getAirportCountry()
                };
                model.addRow(fila);
            }
        }
        return model;
    }
}
