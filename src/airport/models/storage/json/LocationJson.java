/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.storage.json;

import airport.models.location.Location;
import airport.models.storage.LocationStorage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author alejo
 */
public class LocationJson {
    public static void readLocation(String path){
        try{
            LocationStorage storage = LocationStorage.getInstance();
            String content = Files.readString(Paths.get(path), StandardCharsets.UTF_8);
            JSONArray array  = new JSONArray(content);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                
                String airportId = obj.getString("airportId");
                String airportName = obj.getString("airportName");
                String airportCity = obj.getString("airportCity");
                String airportCountry = obj.getString("airportCountry");
                double airportLatitude = obj.getDouble("airportLatitude");
                double airportLongitude = obj.getDouble("airportLongitude");
                
                Location location = new Location(airportId, airportName, airportCity, airportCountry, airportLatitude, airportLongitude);
                storage.addLocation(location);
            }
                
        }catch (IOException | JSONException e) {
            e.printStackTrace();
        }     
    }
}
