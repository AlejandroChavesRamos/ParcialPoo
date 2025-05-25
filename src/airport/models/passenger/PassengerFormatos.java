/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.passenger;

import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author alejo
 */
public class PassengerFormatos {
    public static String getFullname(Passenger passenger) {
        return passenger.getFirstname() + " " + passenger.getLastname();
    }
    
    public static String generateFullPhone(Passenger passenger) {
        return "+" + passenger.getCountryPhoneCode() + " " + passenger.getPhone();
    }
    
    public static int calculateAge(Passenger passenger) {
        return Period.between(passenger.getBirthDate(), LocalDate.now()).getYears();
    }
}
