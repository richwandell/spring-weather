package com.wandell.springweather.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wandell.springweather.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceImpl implements WeatherService {

    private static final String MAP_QUEST_ENDPOINT = "http://open.mapquestapi.com/geocoding/v1/address?key=%s&location=%s";
    private static final String DARK_SKY_ENDPOINT = "https://api.darksky.net/forecast/%s/%s,%s";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${darksky.apikey}")
    private String darkSkyApiKey;

    @Value("${mapquest.apikey}")
    private String mapQuestApiKey;
    
    public Weather getCityWeather(String city) {
        MapDataReturnValue mapData = getMapData(city);
        double[] latLng = mapData.getLatLng();
        String darkSkyUri = String.format(DARK_SKY_ENDPOINT, darkSkyApiKey, latLng[0], latLng[1]);
        ResponseEntity<String> response = restTemplate.getForEntity(darkSkyUri, String.class);
        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            JsonNode currently = root.path("currently");
            String summary = currently.get("summary").asText();
            String icon = currently.get("icon").asText();
            double temperature = currently.get("temperature").asDouble();
            double apparentTemperature = currently.get("apparentTemperature").asDouble();
            double humidity = currently.get("humidity").asDouble();
            double pressure = currently.get("pressure").asDouble();

            return new Weather(
                    mapData.getCity(), mapData.getState(), summary, icon, temperature, apparentTemperature, humidity, pressure
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class MapDataReturnValue {
        private final double[] latLng;
        private final String city;
        private final String state;

        private MapDataReturnValue(double[] latLng, String city, String state) {
            this.latLng = latLng;
            this.city = city;
            this.state = state;
        }

        public double[] getLatLng() {
            return latLng;
        }

        public String getCity() {
            return city;
        }

        public String getState() {
            return state;
        }
    }

    private MapDataReturnValue getMapData(String city) {
        String mapQuestUri = String.format(MAP_QUEST_ENDPOINT, mapQuestApiKey, city);
        ResponseEntity<String> response = restTemplate.getForEntity(mapQuestUri, String.class);
        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            JsonNode results = root.path("results");
            JsonNode result = results.get(0);
            JsonNode locations = result.path("locations");
            JsonNode location = locations.get(0);
            JsonNode latLng = location.path("latLng");
            double lat = latLng.get("lat").asDouble();
            double lng = latLng.get("lng").asDouble();

            String[] adminTypeStrings = new String[]{
                    location.path("adminArea1Type").asText(), location.path("adminArea1").asText(),
                    location.path("adminArea2Type").asText(), location.path("adminArea2").asText(),
                    location.path("adminArea3Type").asText(), location.path("adminArea3").asText(),
                    location.path("adminArea4Type").asText(), location.path("adminArea4").asText(),
                    location.path("adminArea5Type").asText(), location.path("adminArea5").asText(),
                    location.path("adminArea6Type").asText(), location.path("adminArea6").asText()};

            city = getAdminAreaType(adminTypeStrings, "City");
            String state = getAdminAreaType(adminTypeStrings, "State");
            return new MapDataReturnValue(new double[]{lat, lng}, city, state);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getAdminAreaType(String[] strings, String whichType) {
        if (strings[0].equals(whichType)) {
            return strings[1];
        } else if (strings[2].equals(whichType)) {
            return strings[3];
        } else if (strings[4].equals(whichType)) {
            return strings[5];
        } else if (strings[6].equals(whichType)) {
            return strings[7];
        } else if (strings[8].equals(whichType)) {
            return strings[9];
        } else if (strings[10].equals(whichType)) {
            return strings[11];
        }
        return "";
    }
}
