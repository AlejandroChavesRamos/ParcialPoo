/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.storage.json;

import airport.models.plane.Plane;
import airport.models.storage.PlaneStorage;
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
public class PlaneJson {
    public static void readPlanes(String path) {
        try{
            PlaneStorage storage = PlaneStorage.getInstance();
            String content = Files.readString(Paths.get(path), StandardCharsets.UTF_8);
            JSONArray array  = new JSONArray(content);


            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                String id = obj.getString("id");
                String brand = obj.getString("brand");
                String model = obj.getString("model");
                int maxCapacity = obj.getInt("maxCapacity");
                String airline = obj.getString("airline");


                Plane p = new Plane(id, brand, model, maxCapacity, airline);
                storage.AddPlane(p);
            }
        }catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        
        
    }
}
