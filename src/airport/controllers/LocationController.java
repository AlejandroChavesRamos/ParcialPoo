/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers;

import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.models.Location;
import airport.models.storage.LocationStorage;
import java.util.ArrayList;
import java.util.Comparator;


/**
 *
 * @author alejo
 */
public class LocationController {
    public static Response showAllLocations(){
        LocationStorage storage = LocationStorage.getInstance();
        ArrayList<Location> locations = storage.getLocation();
        
        locations.sort(Comparator.comparing(Location::getAirportId));
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
    
    public static Response createLocation(String id, String name, String city, String country, String latitude, String longitude){
        try{
            double latitudeDouble;
            double longitudeDouble;
            if(id == null || name == null || city == null || country == null || latitude == null || longitude == null){
                Response r = new Response("All fields must be filled", Status.Bad_Request);
                return r.clone();
            }
            
            if(id.length()!= 3){
                Response r = new Response("The id must be 3 digits long", Status.Bad_Request);
                return r.clone();
            }
            
            for(int i = 0; i<3; i++){
                char letra = id.charAt(i);
                if(letra < 'A' || letra > 'Z'){
                    Response r = new Response("The id must start with 3 capital letters", Status.Bad_Request);
                    return r.clone();
                }
            }
            try{
                latitudeDouble = Double.parseDouble(latitude);
                if(latitudeDouble < -90 || latitudeDouble > 90){
                    Response r = new Response("Latitude value must be between -90 and 90", Status.Bad_Request);
                    return r.clone();
                }
                int i = latitude.indexOf(".");
                if(i!= -1){
                    if(latitude.substring(i+1).length() > 4){
                        Response r = new Response("Latitude value must have a maximum of 4 decimal places", Status.Bad_Request);
                        return r.clone();
                    }
                }
            }catch (Exception ex) {
                Response r = new Response("Latitude must be numeric", Status.Bad_Request);
                return r.clone();
            }
            
            try{
                longitudeDouble = Double.parseDouble(longitude);
                if(longitudeDouble < -90 || longitudeDouble > 90){
                    Response r = new Response("Longitude value must be between -90 and 90", Status.Bad_Request);
                    return r.clone();
                }
                int i = longitude.indexOf(".");
                if(i!= -1){
                    if(longitude.substring(i+1).length() > 4){
                        Response r = new Response("Longitude value must have a maximum of 4 decimal places", Status.Bad_Request);
                        return r.clone();
                    }
                }
            }catch (Exception ex) {
                Response r = new Response("Longitude must be numeric", Status.Bad_Request);
                return r.clone();
            }
            
            
            LocationStorage storage = LocationStorage.getInstance();
            if(!storage.addLocation(new Location(id, name, city, country, latitudeDouble, longitudeDouble))){
                Response r = new Response("A location with that id already exist", Status.Bad_Request);
                return r.clone();
            }
            Response r = new Response("Location created", Status.Created);
            return r.clone();
        }catch (Exception ex) {
            Response r = new Response("Unexpected error", Status.Internal_Server_Error);
            return r.clone();
        }
        
        
    }
}
