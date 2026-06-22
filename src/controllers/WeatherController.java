package controllers;

import interfazLlamadaTiempo.LlamadaTiempo;
import javafx.application.Platform;
import models.WeatherData;
import services.WeatherService;

public class WeatherController implements LlamadaTiempo {

    private final WeatherService service = new WeatherService();

    public void loadWeather(String city, LlamadaTiempo callback) {

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

	@Override
	public void onSuccess(WeatherData data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError() {
		// TODO Auto-generated method stub
		
	}

  
}
