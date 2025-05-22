/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.storage.json;

import airport.models.Location;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author alejo
 */
public class LocationJson {
    public static ArrayList<Location> readLocation(String path) throws IOException {
        String content = Files.readString(Paths.get(path), StandardCharsets.UTF_8);
        JSONArray array  = new JSONArray(content);

        ArrayList<Location> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
        JSONObject obj = array.getJSONObject(i);

       
        String airportId = obj.getString("airportId");
        String airportName = obj.getString("airportName");
        String airportCity = obj.getString("airportCity");
        String airportCountry = obj.getString("airportCountry");
        double airportLatitude = obj.getDouble("airportLatitude");
        double airportLongitude = obj.getDouble("airportLongitude");
        


        Location location = new Location(airportId, airportName, airportCity, airportCountry, airportLatitude, airportLongitude);
        list.add(location);
    }

        return list;
    }
}
