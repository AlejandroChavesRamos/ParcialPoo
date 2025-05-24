/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers;

import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.models.Flight;
import airport.models.storage.FlightStorage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author alejo
 */
public class FlightController {
    public ArrayList<String> updateJsonComponent() throws IOException{
        FlightStorage storage = FlightStorage.getInstance();
        
        ArrayList<Flight> flights = storage.getFlights();
        ArrayList<String> ids = new ArrayList<>();
        
        for(Flight f : flights){
            ids.add(f.getId()+"");
        }
        
        return ids;
        
    }
    
     public static Response showAllFlights(){
        FlightStorage storage = FlightStorage.getInstance();
        ArrayList<Flight> flights = storage.getFlights();
        
        
        flights.sort(Comparator.comparing(Flight::getId));
        ArrayList<Object[]> data = new ArrayList<>();
        for (Flight f : flights) {
            data.add(new Object[]{
                f.getId(),
                f.getDepartureLocation().getAirportId(),
                f.getArrivalLocation().getAirportId(),
                (f.hasScale() ? f.getScaleLocation().getAirportId() : "Null"),
                
                
                f.getDepartureDate().toString(),
                f.calculateArrivalDate().toString(),
                f.getPlane().getId(),
                f.getNumPassengers()
                
            });
        }
        
        Response r = new Response("Vuelos actualizados", Status.Ok, data);
        return r.clone();

        
        
    }
}
