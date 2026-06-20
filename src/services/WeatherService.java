package services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import models.WeatherData;

public class WeatherService {

    private static final String API_KEY;
    
    static {
        try {
            Properties props = new Properties();
            props.load(WeatherService.class.getResourceAsStream("/config.properties"));
            API_KEY = props.getProperty("API_KEY");
        } catch (Exception e) {
            throw new RuntimeException("No se pudo cargar la API key", e);
        }
    }
    
    private static final String URL_TEMPLATE =
    	    "https://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&lang=es&appid=%s";


    public WeatherData getWeather(String city) throws Exception {

        String urlString = String.format(URL_TEMPLATE, city, API_KEY);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

        // --- VALIDACIONES IMPORTANTES ---
        if (!json.has("cod")) {
            throw new Exception("Respuesta inesperada de la API");
        }

        int code = json.get("cod").getAsInt();

        if (code != 200) {
            String msg = json.has("message") ? json.get("message").getAsString() : "Error desconocido";
            throw new Exception("API error (" + code + "): " + msg);
        }

        // --- AHORA SÍ: datos garantizados ---
        JsonObject main = json.getAsJsonObject("main");
        JsonObject weatherObj = json.getAsJsonArray("weather").get(0).getAsJsonObject();

        double temp = main.get("temp").getAsDouble();
        String description = weatherObj.get("description").getAsString();
        String icon = weatherObj.get("icon").getAsString();

        return new WeatherData(temp, description, icon);
    }

}

