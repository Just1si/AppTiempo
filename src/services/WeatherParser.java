package services;

import java.io.IOException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import models.WeatherData;

public class WeatherParser {

    public static WeatherData parse(String jsonString) throws Exception {

        JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();

        if (!json.has("cod")) {
            throw new IllegalStateException("Respuesta inesperada de la API");
        }

        int code = json.get("cod").getAsInt();

        if (code != 200) {
            String msg = json.has("message") ? json.get("message").getAsString() : "Error desconocido";
            throw new IOException("API error (" + code + "): " + msg);
        }

        JsonObject main = json.getAsJsonObject("main");
        JsonObject weatherObj = json.getAsJsonArray("weather").get(0).getAsJsonObject();

        double temp = main.get("temp").getAsDouble();
        String description = weatherObj.get("description").getAsString();
        String icon = weatherObj.get("icon").getAsString();

        return new WeatherData(temp, description, icon);
    }
}
