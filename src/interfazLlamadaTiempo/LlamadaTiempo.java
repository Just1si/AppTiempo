package interfazLlamadaTiempo;

import models.WeatherData;

public interface LlamadaTiempo {

	void onSuccess(WeatherData data);

	void onError();

}
