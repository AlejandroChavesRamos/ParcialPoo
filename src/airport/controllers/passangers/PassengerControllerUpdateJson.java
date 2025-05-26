/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.passangers;

import airport.models.passenger.Passenger;
import airport.models.storage.PassengerStorage;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author alejo
 */
public class PassengerControllerUpdateJson {
    public ArrayList<String> updateJsonComponent(){
        PassengerStorage passengerS = PassengerStorage.getInstance();
        
        ArrayList<Passenger> passengers = passengerS.getPassengers();
        
        ArrayList<String> ids = new ArrayList<>();
        
        for(Passenger p : passengers){
            ids.add(p.getId()+"");
        }
        
        return ids;
        
    }
}
