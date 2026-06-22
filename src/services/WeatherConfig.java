package services;

import java.util.Properties;

public class WeatherConfig {
	
    private static final String API_KEY;

    static {
        try {
            Properties props = new Properties();
            props.load(WeatherConfig.class.getResourceAsStream("/config.properties"));
            API_KEY = props.getProperty("API_KEY");

            if (API_KEY == null || API_KEY.isBlank()) {
                throw new IllegalStateException("API_KEY no encontrada");
            }

        } catch (Exception e) {
            throw new RuntimeException("No se pudo cargar config.properties", e);
        }
    }

    public static String getApiKey() {
        return API_KEY;
    }
}
