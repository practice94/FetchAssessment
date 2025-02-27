import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenWeatherApiClient {
    private static final String API_KEY = "f897a99d971b5eef57be6fafa0d83239";
    private static final String BASE_URL = "http://api.openweathermap.org/geo/1.0/";
    private final LocationParser locationParser = new LocationParser(); // Create an instance of LocationParser

    //http://api.openweathermap.org/geo/1.0/direct?q={city name},{state code},{country code}&limit={limit}&appid={API key}
    //http://api.openweathermap.org/geo/1.0/zip?zip={zip code},{country code}&appid={API key}

    public Map<String, Map<String, Object>> getLocationData(List<String> locations) {
        Map<String, Map<String, Object>> results = new HashMap<>();

        for (String location : locations) {
            Response response;

            if (location.matches("\\d{5}")) { // Check if input is a ZIP code
                response = getLocationByZip(location);
            } else { // Otherwise, it's assumed to be a city/state
                response = getLocationByCityState(location);
            }

            if (response != null && response.statusCode() == 200) {
                // Convert response to JsonElement
                JsonElement jsonResponse = locationParser.convertStringToJsonElement(response.asString());

                // Handle both JSON Object and Array
                if (jsonResponse.isJsonArray() && !jsonResponse.getAsJsonArray().isEmpty()) {
                    JsonObject firstLocation = jsonResponse.getAsJsonArray().get(0).getAsJsonObject();
                    results.put(location, locationParser.retrieveLocationData(firstLocation)); // Use LocationParser
                }
                else if (jsonResponse.isJsonObject()) {
                    JsonObject locationObject = jsonResponse.getAsJsonObject();
                    results.put(location, locationParser.retrieveLocationData(locationObject)); // Use LocationParser
                }
            }
        }
        return results;
    }

    private Response getLocationByZip(String zip) {
        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .basePath("zip")
                .queryParam("zip", zip + ",US")
                .queryParam("appid", API_KEY)
                .when()
                .get();
    }

    private Response getLocationByCityState(String location) {
        String[] parts = location.split(",");
        if (parts.length < 2) {
            System.out.println("Invalid input format: " + location);
            return null; // Skip invalid inputs
        }

        String formattedLocation = parts[0].trim() + "," + parts[1].trim() + ",US"; // Format correctly

        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .basePath("direct")
                .queryParam("q", formattedLocation)
                .queryParam("limit", "1")
                .queryParam("appid", API_KEY)
                .when()
                .get();
    }

}
