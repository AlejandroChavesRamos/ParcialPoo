/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers;

import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.models.Passenger;
import airport.models.storage.PassengerStorage;
import static airport.models.storage.json.PassengerJson.readPassengers;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author alejo
 */
public class PassengerController {
    
    
    public void createPassenger(String id, String firstname, String lastname, String birthDate, String countryPhoneCode, String phone, String country){
//       Passenger passenger = new Passenger(id, firstname, lastname, birthDate, countryPhoneCode, phone, country);
//       passengerS.getPassengers().add(passenger);
       
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
