/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.storage.json;

import airport.controllers.locations.LocationController;
import airport.controllers.planes.PlaneController;
import airport.models.flights.Flight;
import airport.models.location.Location;
import airport.models.passenger.Passenger;
import airport.models.plane.Plane;
import airport.models.storage.FlightStorage;
import airport.models.storage.LocationStorage;
import airport.models.storage.PlaneStorage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author alejo
 */
public class FlightJson {
    public static void readFlights(String path){
        try{
            FlightStorage storage = FlightStorage.getInstance();
            LocationStorage locationstorage = LocationStorage.getInstance();
            String content = Files.readString(Paths.get(path), StandardCharsets.UTF_8);
            JSONArray array  = new JSONArray(content);
            
            for(int i = 0;i < array.length(); i++){
                JSONObject obj = array.getJSONObject(i);

                String id = obj.getString("id");

                String planeId = obj.getString("plane");


                Plane plane = PlaneStorage.getInstance().findById(planeId);

                String departureId = obj.getString("departureLocation");
                Location departure = locationstorage.findById(departureId);
                
                String arrivalId = obj.getString("arrivalLocation");
                Location arrival = locationstorage.findById(arrivalId);
                
                Location scale = null;
                if (!obj.isNull("scaleLocation")) {
                
                    String scaleId = obj.getString("scaleLocation");
                    scale = locationstorage.findById(scaleId);
                }

                LocalDateTime departureDate = LocalDateTime.parse(obj.getString("departureDate"));
                int hArr = obj.getInt("hoursDurationArrival");
                int mArr = obj.getInt("minutesDurationArrival");

                Flight flight;
                if (scale == null) {
                    flight = new Flight(id, plane, departure, arrival, departureDate, hArr, mArr);
                } else {
                    int hScale = obj.getInt("hoursDurationScale");
                    int mScale = obj.getInt("minutesDurationScale");
                    flight = new Flight(id, plane, departure, scale, arrival, departureDate, hArr, mArr, hScale, mScale);
                }

                storage.AddFlight(flight);
                
            }
            
        }catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
    

}
