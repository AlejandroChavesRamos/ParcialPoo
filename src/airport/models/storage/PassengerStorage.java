/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.storage;

import airport.models.Passenger;
import static airport.models.storage.json.PassengerJson.readPassengers;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author alejo
 */
public class PassengerStorage {
    //Creo lista
    private ArrayList<Passenger> passengers = new ArrayList();
    
    private static PassengerStorage instance;
    
    
    
    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public static PassengerStorage getInstance(){
        if (instance == null) {
            instance = new PassengerStorage();
        }
        return instance;
    }
    
    
    public boolean AddPassenger(Passenger passenger){
        for (Passenger p : this.passengers) {
            if (p.getId() == passenger.getId()) {
                return false;
            }
        }
        this.passengers.add(passenger);
        return true;
        
    }
    
    public Passenger findById(Long id) {
        for (Passenger passenger : passengers) {
            if (passenger.getId()  == id) {
                return passenger;
            }
        }
        return null; 
    }

   

    
}
