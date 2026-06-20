package models;

public class WeatherData {

	private double temperature;
	private String description;
	private String icon;
	
	public WeatherData(double temperature, String description, String icon) {
		this.temperature = temperature;
		this.description = description;
		this.icon = icon;
	}

	/**
	 * @return the temperature
	 */
	public double getTemperature() {
		return temperature;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}
	
	
}
