package utils;

import java.util.Map;

public class WeatherIconMapper {

    private static final Map<String, String> ICON_MAP = Map.of(
        "01", "sunnyWeather.png",
        "02", "cloudyWeather.png",
        "03", "cloudyWeather.png",
        "04", "cloudyWeather.png",
        "09", "rainyWeather.png",
        "10", "sunnyRainyWeather.png",
        "11", "stormyWeather.png",
        "13", "snowyWeather.png",
        "50", "variableWeather.png"
    );

    private static final Map<String, String> EMOJI_MAP = Map.of(
        "01", "☀️",
        "02", "⛅",
        "03", "☁️",
        "04", "☁️",
        "09", "🌧️",
        "10", "🌦️",
        "11", "⛈️",
        "13", "❄️",
        "50", "🌫️"
    );

    public static String getIconFile(String iconCode) {

        if (iconCode == null || iconCode.length() < 2)
            return "sunnyWeather.png";

        String key = iconCode.substring(0, 2);

        return ICON_MAP.getOrDefault(key, "sunnyWeather.png");
    }

    public static String getEmojiForIcon(String iconCode) {

        if (iconCode == null || iconCode.length() < 2)
            return "❓";

        String key = iconCode.substring(0, 2);

        return EMOJI_MAP.getOrDefault(key, "❓");
    }
}
