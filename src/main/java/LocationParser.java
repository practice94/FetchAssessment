import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;

public class LocationParser {

    /*
    Retrieve all the required location details from the response body
     */
    public Map<String, Object> retrieveLocationData(JsonObject jsonResponse) {
        Map<String, Object> locationData = new HashMap<>();

        // Store the relevant data in a map
        locationData.put("name", jsonResponse.get("name").getAsString());
        locationData.put("latitude", jsonResponse.get("lat").getAsDouble());
        locationData.put("longitude", jsonResponse.get("lon").getAsDouble());
        try {
            locationData.put("state", jsonResponse.get("state").getAsString());
        }
        catch (Exception e){
            locationData.put("zip", jsonResponse.get("zip").getAsString());
        }

        return locationData;
    }

    /*
    convert the response string into json elements
     */
    public JsonElement convertStringToJsonElement(String jsonResponse) {
        return JsonParser.parseString(jsonResponse);
    }

}
