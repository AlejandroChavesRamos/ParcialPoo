/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers;

import airport.models.Passenger;
import airport.models.storage.PassengerStorage;
import java.io.IOException;
import java.time.LocalDate;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author alejo
 */
public class PassengerController {
    private static PassengerStorage passengerS;
    
    public PassengerController() throws IOException {
        passengerS = new PassengerStorage();
        
    }
    
    public void createPassenger(long id, String firstname, String lastname, LocalDate birthDate, int countryPhoneCode, long phone, String country){
       Passenger passenger = new Passenger(id, firstname, lastname, birthDate, countryPhoneCode, phone, country);
       passengerS.getPassengers().add(passenger);
    }
    
    
    public DefaultTableModel toPassengersJTable() {
        String[] columnas = {"ID", "Name", "Birthdate", "Age", "Phone", "Country", "Num Flight"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0); 

        if (passengerS.getPassengers().isEmpty()) {
            System.out.println("Lista de pasajeros vacia");
        } else {
            for (Passenger p : passengerS.getPassengers()) {
                Object[] fila = new Object[] { 
                  p.getId(), p.getFirstname(), String.valueOf(p.getBirthDate()), p.calculateAge(), p.getPhone(), p.getCountry(), p.getNumFlights()
                };
                model.addRow(fila);
            } 
        }
        return model;
    }
}
