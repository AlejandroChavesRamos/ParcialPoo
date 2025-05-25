/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.passangers;

import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.models.passenger.Passenger;
import airport.models.passenger.PassengerFormatos;
import airport.models.storage.PassengerStorage;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author alejo
 */
public class PassengerControllerShowJtables {
    public static Response showAllPassengers(){
        PassengerStorage storage = PassengerStorage.getInstance();
        ArrayList<Passenger> passengers = storage.getPassengers();
        
        
        passengers.sort(Comparator.comparing(Passenger::getId));
        ArrayList<Object[]> data = new ArrayList<>();
        for (Passenger p : passengers) {
            data.add(new Object[]{
                p.getId(),
                PassengerFormatos.getFullname(p),
                p.getBirthDate(),
                PassengerFormatos.calculateAge(p),
                p.getPhone(),
                p.getCountry(),
                p.getNumFlights()
            });
        }
        
        Response r = new Response("Passenger updated", Status.Ok, data);
        return r.clone();

        
        
    }
}
