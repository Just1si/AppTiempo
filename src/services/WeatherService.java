package services;

import models.WeatherData;

/**
 * Servicio encargado de obtener información meteorológica desde la API de OpenWeather.
 * <p>
 * Esta clase actúa como fachada: delega la construcción de la URL y la llamada HTTP
 * en {@link WeatherHttpClient}, y el parseo del JSON en {@link WeatherParser}.
 * </p>
 *
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     WeatherService service = new WeatherService();
 *     WeatherData data = service.getWeather("Málaga");
 * </pre>
 * </p>
 */
public class WeatherService {

    /**
     * Obtiene los datos meteorológicos de una ciudad utilizando la API de OpenWeather.
     *
     * @param city Nombre de la ciudad (se recomienda usar nombres válidos para la API).
     * @return Un objeto {@link WeatherData} con temperatura, descripción e icono.
     * @throws Exception Si ocurre un error de red, si la API devuelve un código distinto de 200,
     *                   o si la respuesta no puede ser parseada correctamente.
     */
    public WeatherData getWeather(String city) throws Exception {
        String json = WeatherHttpClient.fetchWeatherJson(city);
        return WeatherParser.parse(json);
    }
}
