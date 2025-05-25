/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers;

import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.models.Passenger;
import airport.models.storage.PassengerStorage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;



/**
 *
 * @author alejo
 */
public class PassengerController {
    
    
    public static Response createPassenger(String id, String firstname, String lastname, String year, String month, String day, String countryPhoneCode, String phone, String country){
        long idLong, phoneLong;
        int countryPhoneCodeInt, monthInt, dayInt, yearInt;
        LocalDate birthDate;
        try{
            if(id == null || firstname == null || lastname == null || year == null || month.trim().equals("Month") || day.trim().equals("Day") || countryPhoneCode == null || phone == null || country == null){
                Response r = new Response("Todos los campos deben estar llenos", Status.Bad_Request);
                return r.clone();
            }
            
            try{
                idLong = Long.parseLong(id);
                if(idLong < 0){
                    Response r = new Response("Id debe ser positivo o 0", Status.Bad_Request);
                    return r.clone();
                }
                if(id.length() > 15){
                    Response r = new Response("Id debe ser de menor de 15 digítos", Status.Bad_Request);
                    return r.clone();
                }
            }catch (Exception ex) {
                Response r = new Response("Id debe ser numerico", Status.Bad_Request);
                return r.clone();
            }
            try{
                yearInt = Integer.parseInt(year);
                if(yearInt < 0){
                    Response r = new Response("year debe ser positivo o 0", Status.Bad_Request);
                    return r.clone();
                }
            }catch (Exception ex) {
                Response r = new Response("Year debe ser numerico", Status.Bad_Request);
                return r.clone();
            }
            try{
                monthInt = Integer.parseInt(month);
                if(monthInt < 0){
                    Response r = new Response("Month debe ser positivo o 0", Status.Bad_Request);
                    return r.clone();
                }
            }catch (Exception ex) {
                Response r = new Response("Month debe ser numerico", Status.Bad_Request);
                return r.clone();
            }
            try{
                dayInt = Integer.parseInt(day);
                if(dayInt < 0){
                    Response r = new Response("Day debe ser positivo o 0", Status.Bad_Request);
                    return r.clone();
                }
            }catch (Exception ex) {
                Response r = new Response("Day debe ser numerico", Status.Bad_Request);
                return r.clone();
            }
            try{
                birthDate = LocalDate.of(yearInt, monthInt, dayInt);
            }catch (Exception ex) {
                Response r = new Response("Fecha no valida", Status.Bad_Request);
                return r.clone();
            }
            
            LocalDate actualDate = LocalDate.now();
            if (!birthDate.isBefore(actualDate)) {
                Response r = new Response("Fecha no valida a", Status.Bad_Request);
                return r.clone();
            }
            try{
                countryPhoneCodeInt = Integer.parseInt(countryPhoneCode);
                if(countryPhoneCode.length() > 3){
                    Response r = new Response("El Country phone code no debe exceder los 3 dígitos", Status.Bad_Request);
                    return r.clone();
                }
                if(countryPhoneCodeInt < 0){
                    Response r = new Response("El Country phone code debe ser mayor o igual a 0", Status.Bad_Request);
                    return r.clone();
                }
            }catch (Exception ex) {
                Response r = new Response("Country phone code debe ser numerico", Status.Bad_Request);
                return r.clone();
            }
            
            try{
                phoneLong = Long.parseLong(phone);
                if(phone.length() > 11){
                    Response r = new Response("El phone number no debe exceder los 11 dígitos", Status.Bad_Request);
                    return r.clone();
                }
                if(phoneLong< 0){
                    Response r = new Response("El phone number debe ser mayor o igual a 0", Status.Bad_Request);
                    return r.clone();
                }
            }catch (Exception ex) {
                Response r = new Response("Phone debe ser numerico", Status.Bad_Request);
                return r.clone();
            }
            PassengerStorage storage = PassengerStorage.getInstance();
            if(!storage.AddPassenger(new Passenger( idLong,  firstname,  lastname,  birthDate,  countryPhoneCodeInt,  phoneLong,  country))){
                Response r = new Response("Un pasajero con ese id ya existe", Status.Bad_Request);
                return r.clone();
            }
            
            
            
            Response r = new Response("Pasajero creado", Status.Created);
            return r.clone();
        }catch (Exception ex) {
            Response r = new Response("Unexpected error", Status.Internal_Server_Error);
            return r.clone();
        }
    }   
    
    
    public ArrayList<String> updateJsonComponent() throws IOException{
        PassengerStorage passengerS = PassengerStorage.getInstance();
        
        ArrayList<Passenger> passengers = passengerS.getPassengers();
        ArrayList<String> ids = new ArrayList<>();
        
        for(Passenger p : passengers){
            ids.add(p.getId()+"");
        }
        
        return ids;
        
    }
    
    public static Response showAllPassengers(){
        PassengerStorage storage = PassengerStorage.getInstance();
        ArrayList<Passenger> passengers = storage.getPassengers();
        
        
        passengers.sort(Comparator.comparing(Passenger::getId));
        ArrayList<Object[]> data = new ArrayList<>();
        for (Passenger p : passengers) {
            data.add(new Object[]{
                p.getId(),
                p.getFullname(),
                p.getBirthDate(),
                p.calculateAge(),
                p.getPhone(),
                p.getCountry(),
                p.getNumFlights()
            });
        }
        
        Response r = new Response("Pasajeros actualizados", Status.Ok, data);
        return r.clone();

        
        
    }
    

}
