/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.flights;

import airport.models.flights.Flight;
import airport.models.storage.FlightStorage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author alejo
 */
public class FlightControllerUpdateJson {
    public ArrayList<String> updateJsonComponent() throws IOException{
        FlightStorage storage = FlightStorage.getInstance();
        
        ArrayList<Flight> flights = storage.getFlights();
        
        ArrayList<String> ids = new ArrayList<>();
        
        for(Flight f : flights){
            ids.add(f.getId()+"");
        }
        
        return ids;
        
    }
}
