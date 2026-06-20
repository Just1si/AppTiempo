package controllers;

import javafx.application.Platform;
import models.WeatherData;
import services.WeatherService;

public class WeatherController {

    private final WeatherService service = new WeatherService();

    public void loadWeather(String city, WeatherCallback callback) {

        new Thread(() -> {
            try {
                WeatherData data = service.getWeather(city);

                Platform.runLater(() -> callback.onSuccess(data));

            } catch (Exception ex) {
                ex.printStackTrace();
                Platform.runLater(callback::onError);
            }
        }).start();
    }

    public interface WeatherCallback {
        void onSuccess(WeatherData data);
        void onError();
    }
}
