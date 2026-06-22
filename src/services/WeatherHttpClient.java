package services;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class WeatherHttpClient {

    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final String URL_TEMPLATE =
        "https://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&lang=es&appid=%s";

    public static String fetchWeatherJson(String city) throws Exception {
        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
        String url = String.format(URL_TEMPLATE, encodedCity, WeatherConfig.getApiKey());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response =
                CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}

