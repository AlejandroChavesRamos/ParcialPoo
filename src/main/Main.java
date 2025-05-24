/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import airport.models.storage.json.FlightJson;
import airport.models.storage.json.LocationJson;
import airport.models.storage.json.PassengerJson;
import airport.models.storage.json.PlaneJson;
import airport.views.AirportFrame;
import com.formdev.flatlaf.FlatDarkLaf;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;

/**
 *
 * @author alejo
 */
public class Main {
    public static void main(String[] args){

        PassengerJson.readPassengers("json/passengers.json");
        LocationJson.readLocation("json/locations.json");
        PlaneJson.readPlanes("json/Planes.json");
        FlightJson.readFlights("json/flights.json");
                
        System.setProperty("flatlaf.useNativeLibrary", "false");

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new AirportFrame().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
    }
}
