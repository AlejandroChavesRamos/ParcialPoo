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
    private static ArrayList<Passenger> passengers = new ArrayList();

    public PassengerStorage() throws IOException {
        passengers = readPassengers("json/passengers.json");
    }
    
    public static ArrayList<Passenger> getPassengers() {
        return passengers;
    }

   

    
}
