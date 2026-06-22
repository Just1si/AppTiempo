package controllers;

import java.util.List;
import interfazLlamadaTiempo.LlamadaTiempo;
import javafx.stage.Stage;
import models.WeatherData;
import view.WeatherCard;

public class WeatherDashboardController {

    private final WeatherController weatherController = new WeatherController();

    public void loadCities(List<String> cities, List<WeatherCard> cards, Stage stage) {

        for (int i = 0; i < cities.size(); i++) {
            String city = cities.get(i);
            WeatherCard card = cards.get(i);

            weatherController.loadWeather(city, new LlamadaTiempo() {

                @Override
                public void onSuccess(WeatherData data) {
                    javafx.application.Platform.runLater(() -> card.update(data, stage));
                }

                @Override
                public void onError() {
                    javafx.application.Platform.runLater(card::showError);
                }
            });
        }
    }
}
