/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers;

import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.models.Flight.Flight;
import airport.models.Flight.FlightCalculateTimes;
import airport.models.location.Location;
import airport.models.passenger.Passenger;
import airport.models.plane.Plane;
import airport.models.storage.FlightStorage;
import airport.models.storage.LocationStorage;
import airport.models.storage.PassengerStorage;
import airport.models.storage.PlaneStorage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        flights.sort(Comparator.comparing(Flight::getId));
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
                FlightCalculateTimes.calculateArrivalDate(f).toString(),
                f.getPlane().getId(),
                f.getNumPassengers()
                
            });
        }
        
        Response r = new Response("Flights updated", Status.Ok, data);
        return r.clone();    
    }
    
    public static Response createFlight(String id, String planeId, String departureLocationId, String arrivalLocationId, String scaleLocationId, String year, String month, String day, String hour, String minute, String hoursDurationArrival, String minutesDurationArraival,String hoursDurationScale, String minutesDurationScale ){
        FlightStorage storageF = FlightStorage.getInstance();
        PlaneStorage storageP = PlaneStorage.getInstance();
        LocationStorage storageL = LocationStorage.getInstance();
        LocalDateTime departureDate;
            
        int hoursDurationArrivalInt, hoursDurationScaleInt, minutesDurationArrivalInt, minutesDurationScaleInt;
        int yearInt, monthInt, dayInt, hourInt, minuteInt;
        try{
            if(id == null){
                Response r = new Response("", Status.Bad_Request);
                return r.clone();
            }
            if(id.length() != 6){
                Response r = new Response("The id must be 6 digist long", Status.Bad_Request);
                return r.clone();
            }
            for(int i = 0; i<3; i++){
                char letra = id.charAt(i);
                if(letra< 'A' || letra > 'Z'){
                    Response r = new Response("The id must start with 3 capital letters", Status.Bad_Request);
                    return r.clone();
                }
            }
            for(int i = 3; i<6; i++){
                char n = id.charAt(i);
                if(n < '0' || n > '9'){
                    Response r = new Response("Invalid Id", Status.Bad_Request);
                    return r.clone();
                }
            }
            
            Plane plane;
            if(storageP.findById(planeId)== null){
                Response r = new Response("Plane not found", Status.Not_Found);
                return r.clone();
            }else{
                plane = storageP.findById(planeId);
            }
            
            Location departureLocation, arrivalLocation, scaleLocation;
            if(storageL.findById(departureLocationId) == null){
                Response r = new Response("Departure airport not found", Status.Bad_Request);
                return r.clone();
            }else{
                departureLocation = storageL.findById(departureLocationId);
            }
            
            if(storageL.findById(arrivalLocationId) == null){
                Response r = new Response("Arraival airport nor found", Status.Not_Found);
                return r.clone();
            }else{
                arrivalLocation = storageL.findById(arrivalLocationId);
            }
            
            try{
                yearInt = Integer.parseInt(year);
            }catch (NumberFormatException ex) {
                Response r = new Response("Invalid year", Status.Bad_Request);
                return r.clone();
            }
            try{
                monthInt = Integer.parseInt(month);
            }catch (NumberFormatException ex) {
                Response r = new Response("Invalid month", Status.Bad_Request);
                return r.clone();
            }
            try{
                dayInt = Integer.parseInt(day);
            }catch (NumberFormatException ex) {
                Response r = new Response("Invalid day", Status.Bad_Request);
                return r.clone();
            }
            try{
                hourInt = Integer.parseInt(hour);
            }catch (NumberFormatException ex) {
                Response r = new Response("Invalid hour", Status.Bad_Request);
                return r.clone();
            }
            try{
                minuteInt = Integer.parseInt(minute);
            }catch (NumberFormatException ex) {
                Response r = new Response("Invalid minute", Status.Bad_Request);
                return r.clone();
            }
            try{
                departureDate = LocalDateTime.of(yearInt, monthInt, dayInt, hourInt, minuteInt);
            }catch (Exception ex) {
                Response r = new Response("Invalid date", Status.Bad_Request);
                return r.clone();
            }
            LocalDateTime actualDate = LocalDateTime.now();
            if(departureDate.isBefore(actualDate)){
                Response r = new Response("Invalid date", Status.Bad_Request);
                return r.clone();
            }
            
            try{
                hoursDurationArrivalInt = Integer.parseInt(hoursDurationArrival);
            }catch (NumberFormatException ex) {
                Response r = new Response("Invalid arraival hour", Status.Bad_Request);
                return r.clone();
            }
            try{
                minutesDurationArrivalInt = Integer.parseInt(minutesDurationArraival);
            }catch (NumberFormatException ex) {
                Response r = new Response("Invalid arraival minute", Status.Bad_Request);
                return r.clone();
            }
            
            if(minutesDurationArrivalInt == 0 && hoursDurationArrivalInt == 0){
                Response r = new Response("Duration must be different from 0", Status.Bad_Request);
                return r.clone();
            }
            
            boolean hasScale = !(scaleLocationId.equals("Location") || scaleLocationId.equals(""));
            
            if(hasScale){
                if(storageL.findById(scaleLocationId)== null){
                    Response r = new Response("Scale airport nor found", Status.Not_Found);
                    return r.clone();
                }else{
                    scaleLocation = storageL.findById(scaleLocationId);
                }
                
                try{
                    hoursDurationScaleInt = Integer.parseInt(hoursDurationScale);
                }catch (NumberFormatException ex) {
                    Response r = new Response("Invalid scale hour", Status.Bad_Request);
                    return r.clone();
                }
                try{
                    minutesDurationScaleInt = Integer.parseInt(minutesDurationScale);
                }catch (NumberFormatException ex) {
                    Response r = new Response("Invalid scale minute", Status.Bad_Request);
                    return r.clone();
                }

                if(minutesDurationScaleInt == 0 && hoursDurationScaleInt == 0){
                    Response r = new Response("Scale duration must be different from 0", Status.Bad_Request);
                    return r.clone();
                }
                
                Flight flight = new Flight(id, plane, departureLocation, scaleLocation, arrivalLocation, departureDate, hoursDurationArrivalInt, minutesDurationArrivalInt, hoursDurationScaleInt, hoursDurationScaleInt);
                if(!storageF.AddFlight(flight)){
                    Response r = new Response("A flight with that id already exist", Status.Bad_Request);
                    return r.clone();
                }
            }else{
                Flight flight = new Flight(id, plane, departureLocation, arrivalLocation, departureDate, hoursDurationArrivalInt, minutesDurationArrivalInt);
                if(!storageF.AddFlight(flight)){
                    Response r = new Response("A flight with that id already exist", Status.Bad_Request);
                    return r.clone();
                }
            }
            
            Response r = new Response("Flight created", Status.Created);
            return r.clone();
        }catch (Exception ex) {
            Response r = new Response("Unexpected error", Status.Internal_Server_Error);
            return r.clone();
        }
        
        
    }
    
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
    
    public static Response delayFlight(String flightId, String hours, String minutes){
        FlightStorage storage = FlightStorage.getInstance();
        int hoursInt, minutesInt;
        Flight flight;
        try{
            flight = storage.findById(flightId);
            if(flightId.contentEquals("ID") || hours.equals("Hour") || minutes.equals("Minute")){
                Response r = new Response("All fields must be filled", Status.Bad_Request);
                return r.clone();
            }

            if(flight == null){
                Response r = new Response("Flight not found", Status.Not_Found);
                return r.clone();
            }else{
                try{
                    hoursInt = Integer.parseInt(hours);
                }catch (NumberFormatException ex) {
                    Response r = new Response("Invalid hour", Status.Bad_Request);
                    return r.clone();
                }
                try{
                    minutesInt = Integer.parseInt(minutes);
                }catch (NumberFormatException ex) {
                    Response r = new Response("Invalid arraival minute", Status.Bad_Request);
                    return r.clone();
                }

                if(minutesInt == 0 && hoursInt == 0){
                    Response r = new Response("The duration of the delay must be different from 0", Status.Bad_Request);
                    return r.clone();
                }
                FlightCalculateTimes.delay(flight, hoursInt, minutesInt);
            }
            Response r = new Response("The flight has been delayed", Status.Ok);
            return r.clone();
        }catch (Exception ex) {
            Response r = new Response("Unexpected error", Status.Internal_Server_Error);
            return r.clone();
        } 
        
        
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
