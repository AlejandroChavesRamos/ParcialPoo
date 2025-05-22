/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers;

import airport.models.Plane;
import airport.models.storage.PlaneStorage;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alejo
 */
public class PlaneController {
    private static PlaneStorage planeS;
    
    public PlaneController() throws IOException {
        planeS = new PlaneStorage();
    }
    
    public void createPlane(String id, String brand, String model, int maxCapacity, String airline){
        Plane plane = new Plane(id, brand, model, maxCapacity, airline);
        planeS.getPlane().add(plane);
    }
    
    public Plane getIdPlane(String id){
        for(Plane plane: planeS.getPlane()){
            if(plane.getId().equals(id)){
                return plane;
            }
        }
        return null;
    }
    
    public DefaultTableModel toPlanesJTable(){
        String[] columnas = {"ID", "Brand", "Model", "Max Capacity", "Airline", "Number Flights"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0); 
        
        if(planeS.getPlane().isEmpty()){
            System.out.println("Lista Vacia");
        } else{
            for(Plane plane : planeS.getPlane()){
                Object[] fila = new Object[] {
                    plane.getId(), plane.getBrand(), plane.getModel(), plane.getMaxCapacity(), plane.getAirline(), plane.getNumFlights()
                };
                model.addRow(fila);
            }
        }
        return model;
    }
}
