package view;

import controllers.MainMenuController;
import controllers.WeatherController;
import interfazLlamadaTiempo.LlamadaTiempo;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.WeatherData;
import utils.WeatherAnimations;
import utils.WeatherBackgroundMapper;
import utils.WeatherIconMapper;

import java.util.List;

public class WeatherView implements LlamadaTiempo {

	private final WeatherController controller = new WeatherController();

	private final String currentCity;
	private final List<String> allCities;

	private Label cityLabel;
	private Label temperatureLabel;
	private Label descriptionLabel;
	private ImageView weatherIcon;
	private StackPane rootReference;

	public WeatherView(String city, List<String> allCities) {
		this.currentCity = city;
		this.allCities = allCities;
	}

	public Scene getScene(Stage stage) {

		cityLabel = new Label(currentCity);
		cityLabel.getStyleClass().add("city-title");

		temperatureLabel = new Label("Cargando...");
		temperatureLabel.getStyleClass().add("temperature");

		descriptionLabel = new Label("Obteniendo datos...");
		descriptionLabel.getStyleClass().add("description");

		weatherIcon = new ImageView(new Image(getClass().getResource("/icons/sunnyWeather.png").toExternalForm()));
		weatherIcon.setFitWidth(120);
		weatherIcon.setPreserveRatio(true);

		WeatherAnimations.applyBounce(weatherIcon);
		WeatherAnimations.applyPulse(weatherIcon);

		Button backButton = new Button("Volver");
		backButton.getStyleClass().add("back-button");
		
		backButton.setOnAction(e -> {
			WeatherDashboardView dashboard = new WeatherDashboardView(allCities);
			stage.setScene(dashboard.getScene(stage));
		});

		Button resetButton = new Button("Reiniciar");
		resetButton.getStyleClass().add("reset-button");

		resetButton.setOnAction(e -> {
			MainMenuController controller = new MainMenuController();
			controller.show(stage);
		});

		VBox content = new VBox(cityLabel, weatherIcon, temperatureLabel, descriptionLabel, backButton, resetButton);
		content.setAlignment(Pos.CENTER);
		content.setSpacing(20);

		StackPane root = new StackPane(content);
		this.rootReference = root;
		root.getStyleClass().add("weather-background");

		WeatherAnimations.applyIntro(root);

		loadWeather(currentCity);

		Scene scene = new Scene(root, 800, 600);
		scene.getStylesheets().add(getClass().getResource("/styles/weather.css").toExternalForm());

		return scene;
	}

	protected void loadWeather(String city) {
		temperatureLabel.setText("Cargando...");
		descriptionLabel.setText("Obteniendo datos...");
		controller.loadWeather(city, this);
	}

	@Override
	public void onSuccess(WeatherData data) {
		temperatureLabel.setText((int) data.getTemperature() + "°C");
		descriptionLabel.setText(data.getDescription());
		applyDynamicBackground(rootReference, data.getDescription());
		updateWeatherIcon(data.getIcon());
	}

	@Override
	public void onError() {
		temperatureLabel.setText("Error");
		descriptionLabel.setText("No se pudo cargar el clima");
	}

	private void applyDynamicBackground(StackPane root, String weather) {
		String imageName = WeatherBackgroundMapper.getBackground(weather);
		root.setStyle("-fx-background-image: url('/backgrounds/" + imageName + "');" + "-fx-background-size: cover;"
				+ "-fx-background-position: center;");
	}

	private void updateWeatherIcon(String iconCode) {
		String file = WeatherIconMapper.getIconFile(iconCode);
		weatherIcon.setImage(new Image(getClass().getResource("/icons/" + file).toExternalForm()));
	}
}
