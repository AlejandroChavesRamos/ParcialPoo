/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.storage.json;

import airport.models.Plane;
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
public class PlaneJson {
    public static ArrayList<Plane> readPlanes(String path) throws IOException {
        String content = Files.readString(Paths.get(path), StandardCharsets.UTF_8);
        JSONArray array  = new JSONArray(content);

        ArrayList<Plane> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
        JSONObject obj = array.getJSONObject(i);

       
        String id = obj.getString("id");
        String brand = obj.getString("brand");
        String model = obj.getString("model");
        String airline = obj.getString("airline");
        int maxCapacity = obj.getInt("maxCapacity");
        


        Plane plane = new Plane(id, brand, model, maxCapacity, airline);
        list.add(plane);
    }

        return list;
    }
}
