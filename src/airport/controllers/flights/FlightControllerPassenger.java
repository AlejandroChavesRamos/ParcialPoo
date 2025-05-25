/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.flights;

import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.models.flights.Flight;
import airport.models.passenger.Passenger;
import airport.models.storage.FlightStorage;
import airport.models.storage.PassengerStorage;

/**
 *
 * @author alejo
 */
public class FlightControllerPassenger {
    public static Response addPassenger(String passengerId, String flightId){
        FlightStorage storageF = FlightStorage.getInstance();
        PassengerStorage storageP = PassengerStorage.getInstance();
        try{
            Long passengerIdLong;
            if(passengerId.equals("")){
                Response r = new Response("Passenger id cannot be empty", Status.Bad_Request);
                return r.clone();
            }
            try{
                passengerIdLong = Long.valueOf(passengerId);
                if(passengerIdLong < 0){
                    Response r = new Response("Passenger id must be positive or 0", Status.Bad_Request);
                    return r.clone();
                }
                if(passengerId.length() > 15){
                    Response r = new Response("The passenger id can have a maximum of 15 digits", Status.Bad_Request);
                    return r.clone();
                }
            }catch (NumberFormatException ex) {
                Response r = new Response("Passenger id must be numeric", Status.Bad_Request);
                return r.clone();
            }
            if(flightId.equals("Flight")){
                Response r = new Response("You must select a flight id", Status.Bad_Request);
                return r.clone();
            }
            
            Flight flight = storageF.findById(flightId);
            Passenger passenger = storageP.findById(passengerIdLong);
            if(passenger == null){
                Response r = new Response("Passenger not found", Status.Not_Found);
                return r.clone();
            }
            if(flight == null){
                Response r = new Response("Flight not found", Status.Not_Found);
                return r.clone();
            }
            if(passenger.getFlights().contains(flight)){
                Response r = new Response("The passenger is already assigned to this flight", Status.Bad_Request);
                return r.clone();
            }
            passenger.addFlight(flight);
            flight.addPassenger(passenger);
            
            Response r = new Response("Passenger added", Status.Ok);
            return r.clone();
        }catch (Exception ex) {
            Response r = new Response("Unexpected error", Status.Internal_Server_Error);
            return r.clone();
        }    
    }
    
}
