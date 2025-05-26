/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.flights;

import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.models.flights.Flight;
import airport.models.flights.FlightCalculateTimes;
import airport.models.passenger.Passenger;
import airport.models.storage.FlightStorage;
import airport.models.storage.PassengerStorage;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author alejo
 */
public class FlightControllerShowJtables {
    
    public static Response showAllFlights(){
        FlightStorage storage = FlightStorage.getInstance();
        ArrayList<Flight> flights = storage.getFlights();
        
        
        
        ArrayList<Object[]> data = new ArrayList<>();
        for (Flight f : flights) {
            data.add(new Object[]{
                f.getId(),
                f.getDepartureLocation().getAirportId(),
                f.getArrivalLocation().getAirportId(),
                (f.hasScale() ? f.getScaleLocation().getAirportId() : "Null"),
                
                
                f.getDepartureDate().toString(),
                FlightCalculateTimes.calculateArrivalDate(f).toString(),
                f.getPlane().getId(),
                f.getNumPassengers()
                
            });
        }
        
        Response r = new Response("Flights updated", Status.Ok, data);
        return r.clone();    
    }
    
    public static Response showAllMyFlights(String id){
        Long idLong;
        PassengerStorage storageP = PassengerStorage.getInstance();
        FlightStorage storgaF = FlightStorage.getInstance();
        
        ArrayList<Object[]> data = new ArrayList<>();
        try{
            if(id.equals("Select User")){
                Response r = new Response("You must select a id", Status.Bad_Request);
                return r.clone();
            }
            try{
                idLong = Long.valueOf(id);   
            }catch (Exception ex) {
                Response r = new Response("Id must be numeric", Status.Internal_Server_Error);
                return r.clone();
            } 
            Passenger passenger = storageP.findById(idLong);
            if(passenger == null){
                Response r = new Response("Passenger not found", Status.Not_Found);
                return r.clone();
            }else{
                ArrayList<Flight> flights = (ArrayList<Flight>) passenger.getFlights();
                flights.sort(Comparator.comparing(Flight::getId));
                if(flights.isEmpty()){
                    Response r = new Response("This user dosent have flights", Status.Not_Found);
                    return r.clone();
                }
                
                for (Flight f : flights) {
                    data.add(new Object[]{
                        f.getId(),
                        f.getDepartureDate(),
                        FlightCalculateTimes.calculateArrivalDate(f),
                        

                    });
                }
            }
            Response r = new Response("Flights updated", Status.Ok, data);
            return r.clone(); 
        }catch (Exception ex) {
            Response r = new Response("Unexpected error", Status.Internal_Server_Error);
            return r.clone();
        } 
    }
}
