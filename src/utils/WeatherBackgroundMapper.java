package utils;

import java.util.Map;

public class WeatherBackgroundMapper {

    private static final Map<String, String> BACKGROUND_MAP = Map.of(
        "cloud", "cloudyWeather.png",
        "rain", "rainyWeather.png",
        "sun+rain", "sunnyRainyWeather.png",
        "storm", "stormyWeather.png",
        "snow", "snowyWeather.png",
        "sun+snow", "snowySunnyWeather.png",
        "variable", "variableWeather.png",
        "mixed", "variableWeather.png"
    );

    public static String getBackground(String description) {

        if (description == null)
            return "sunnyWeather.png";

        String w = description.toLowerCase();

        // Casos especiales primero
        if (w.contains("rain") && w.contains("sun"))
            return BACKGROUND_MAP.get("sun+rain");

        if (w.contains("snow") && w.contains("sun"))
            return BACKGROUND_MAP.get("sun+snow");

        // Casos simples
        if (w.contains("cloud")) return BACKGROUND_MAP.get("cloud");
        if (w.contains("rain")) return BACKGROUND_MAP.get("rain");
        if (w.contains("storm") || w.contains("thunder")) return BACKGROUND_MAP.get("storm");
        if (w.contains("snow")) return BACKGROUND_MAP.get("snow");
        if (w.contains("variable") || w.contains("mixed")) return BACKGROUND_MAP.get("variable");

        // Por defecto
        return "sunnyWeather.png";
    }
}
