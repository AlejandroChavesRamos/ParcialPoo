/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.storage.json;

import airport.controllers.LocationController;
import airport.controllers.PlaneController;
import airport.models.Flight;
import airport.models.Location;
import airport.models.Passenger;
import airport.models.Plane;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author alejo
 */
public class FlightJson {
    public static ArrayList<Flight> readFlights(String path) throws IOException {
        String content = Files.readString(Paths.get(path), StandardCharsets.UTF_8);
        JSONArray array  = new JSONArray(content);

        ArrayList<Flight> list = new ArrayList<>();
        
        PlaneController PlaneC = new PlaneController();
        LocationController LocationC = new LocationController();
        
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);

            String id = obj.getString("id");
            Plane plane = PlaneC.getIdPlane(obj.getString("plane"));
            Location departureLocation = LocationC.getIdLocation(obj.getString("departureLocation"));
            Location arrivalLocation = LocationC.getIdLocation(obj.getString("arrivalLocation"));
            Location scaleLocation = LocationC.getIdLocation(obj.getString("scaleLocation"));
            LocalDate arrivalDate = LocalDate.parse(obj.getString("arrivalDate"));
            int hoursDurationArraival = obj.getInt("hoursDurationArraival");
            int minutesDurationArrival = obj.getInt("minutesDurationArrival");
            int hoursDurationScale = obj.getInt("hoursDurationScale");
            int minutesDurationScale = obj.getInt("minutesDurationScale");




            Flight flight = new Flight(id, plane, departureLocation, arrivalLocation, scaleLocation, arrivalDate, hoursDurationArraival, minutesDurationArrival, hoursDurationScale, minutesDurationScale);
            list.add(flight);
        }

            return list;
        }
}
