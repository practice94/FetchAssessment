import Utils.BaseClass;
import Utils.ValidationUtil;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GeoLocationTests extends BaseClass {
    OpenWeatherApiClient apiClient = new OpenWeatherApiClient();

    @Test
    public void test_Valid_City_And_State() {
        List<String> locations = List.of("Madison, WI");

        // Fetch location data
        Map<String, Map<String, Object>> locationData = apiClient.getLocationData(locations);

        ValidationUtil.validate("Validating data is received for Madison,WI:",locationData.isEmpty(),false);
        ValidationUtil.validate("Validating name matches:",locationData.get("Madison, WI").get("name"),"Madison");
        ValidationUtil.validate("Validating state matches:",locationData.get("Madison, WI").get("state"),"Wisconsin");
        ValidationUtil.validate("Validating latitude is retrieved:",locationData.get("Madison, WI").get("latitude").toString().isEmpty(), false);
        ValidationUtil.validate("Validating longitude is retrieved:",locationData.get("Madison, WI").get("longitude").toString().isEmpty(),false);

    }

    @Test
    public void test_Valid_ZipCode() {
        String zip1 = "10001";
        String zip2 = "33024";
        List<String> locations = Arrays.asList(zip1, zip2);

        // Fetch location data
        Map<String, Map<String, Object>> locationData = apiClient.getLocationData(locations);

        ValidationUtil.validate("Validating data is received for multiple zipcodes:",locationData.size()==2);

        ValidationUtil.validate("Validating name matches for 10001:",locationData.get(zip1).get("name"),"New York");
        ValidationUtil.validate("Validating name matches:",locationData.get(zip1).get("zip"),zip1);
        ValidationUtil.validate("Validating latitude is retrieved:",locationData.get(zip1).get("latitude").toString().isEmpty(), false);
        ValidationUtil.validate("Validating longitude is retrieved:",locationData.get(zip1).get("longitude").toString().isEmpty(),false);

        ValidationUtil.validate("Validating name matches for 90210:",locationData.get(zip2).get("name"),"Pembroke Pines");
        ValidationUtil.validate("Validating name matches:",locationData.get(zip2).get("zip"),zip2);
        ValidationUtil.validate("Validating latitude is retrieved:",locationData.get(zip2).get("latitude").toString().isEmpty(), false);
        ValidationUtil.validate("Validating longitude is retrieved:",locationData.get(zip2).get("longitude").toString().isEmpty(),false);

    }

    @Test
    public void test_Multiple_Valid_Locations() {
        String city1 = "Orlando, FL";
        String zip1 = "Chicago, IL";
        String city2 = "33004";
        String zip2 = "90210";
        List<String> locations = Arrays.asList(city1,zip1,city2,zip2);

        // Fetch location data
        Map<String, Map<String, Object>> locationData = apiClient.getLocationData(locations);

        ValidationUtil.validate("Validating data is received for multiple inputs:",locationData.size()==4);

    }

    @Test
    public void test_Invalid_City_And_State() {
        List<String> locations = List.of("Miami, NY");

        // Fetch location data
        Map<String, Map<String, Object>> locationData = apiClient.getLocationData(locations);

        ValidationUtil.validate("Validating no data is received for invalid city-state combination:", locationData.isEmpty());

    }

    @Test
    public void test_Invalid_ZipCode() {
        List<String> locations = Arrays.asList("00000");

        // Fetch location data
        Map<String, Map<String, Object>> locationData = apiClient.getLocationData(locations);

        ValidationUtil.validate("Validating no data is received for invalid zipcode:", locationData.isEmpty());

    }

    @Test
    public void test_Valid_And_Invalid_Combination_Input() {
        String city1 = "Orlando, FL";
        String city2 = "Fake@City, IL";
        String zip1 = "33004";
        String zip2 = "00000";
        List<String> locations = Arrays.asList(city1,zip1,city2,zip2);

        // Fetch location data
        Map<String, Map<String, Object>> locationData = apiClient.getLocationData(locations);

        ValidationUtil.validate("Validating data is received for multiple inputs:",locationData.size()==2);
        ValidationUtil.validate(city1 + " is retrieved:",locationData.containsKey(city1));
        ValidationUtil.validate(zip1 + " is retrieved:",locationData.containsKey(zip1));
    }
}
