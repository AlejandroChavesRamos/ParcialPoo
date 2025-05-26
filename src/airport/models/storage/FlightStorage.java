/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.storage;

import airport.models.flights.Flight;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author alejo
 */
public class FlightStorage extends Observable{
    private ArrayList<Flight> flights = new ArrayList<>();

    private static FlightStorage instance;

    public ArrayList<Flight> getFlights(){
        flights.sort(Comparator.comparing(Flight::getId));
        return flights;
    }

     public static FlightStorage getInstance(){
        if (instance == null) {
            instance = new FlightStorage();
        }
        return instance;
    }

    public void notifyA(){
        notifyObservers();
    }
    public boolean AddFlight(Flight flight){
        for (Flight f : this.flights) {
            if (f.getId().equals(flight.getId())) {
                return false;
            }
        }
        notifyObservers();
        this.flights.add(flight);
        return true;

    }
    public Flight findById(String id) {
        for (Flight flight : flights) {
            if (flight.getId().equals(id)) {
                return flight;
            }
        }
        return null; 
    }
    
}
