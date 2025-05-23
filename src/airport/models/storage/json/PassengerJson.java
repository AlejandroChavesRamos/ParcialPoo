/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.storage.json;

import airport.models.Passenger;
import airport.models.storage.PassengerStorage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author alejo
 */
public class PassengerJson {
    
    public static void readPassengers(String path) {
        try{
            PassengerStorage storage = PassengerStorage.getInstance();
            String content = Files.readString(Paths.get(path), StandardCharsets.UTF_8);
            JSONArray array  = new JSONArray(content);


            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                long id = obj.getLong("id");
                String firstname = obj.getString("firstname");
                String lastname = obj.getString("lastname");
                LocalDate birthDate = LocalDate.parse(obj.getString("birthDate"));
                int countryPhoneCode = obj.getInt("countryPhoneCode");
                int phone = obj.getInt("phone");
                String country = obj.getString("country");


                Passenger p = new Passenger(id, firstname, lastname, birthDate, countryPhoneCode, phone, country);
                storage.AddPassenger(p);
            }
        }catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        
        
    }
}
