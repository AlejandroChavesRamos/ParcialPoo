/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers;

import airport.models.Observer;

/**
 *
 * @author alejo
 */
public class JtablesObserverController implements Observer {
    private Runnable update;

    public JtablesObserverController(Runnable update) {
        this.update = update;
    }
    
    
    @Override
    public void update() {
        update.run();
    }
    
}
