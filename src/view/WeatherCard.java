package view;

import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.WeatherData;
import utils.WeatherIconMapper;

public class WeatherCard extends VBox {

	private final String city;
	private final Label cityLabel;
	private final Label tempLabel;
	private final ImageView iconView;
	private final List<String> allCities;

	public WeatherCard(String city, List<String> allCities) {
		this.city = city;
		this.allCities = allCities;

		cityLabel = new Label(city);
		cityLabel.getStyleClass().add("city");

		tempLabel = new Label("Cargando...");
		tempLabel.getStyleClass().add("temp");

		iconView = new ImageView();
		iconView.setFitWidth(40);
		iconView.setPreserveRatio(true);
		iconView.getStyleClass().add("icon");

		setAlignment(Pos.CENTER);
		setSpacing(10);
		getStyleClass().add("weather-card");
		getChildren().addAll(iconView, tempLabel, cityLabel);
	}

	public void update(WeatherData data, Stage stage) {

		tempLabel.setText((int) data.getTemperature() + "°C");

		String file = WeatherIconMapper.getIconFile(data.getIcon());
		iconView.setImage(new Image(getClass().getResource("/icons/" + file).toExternalForm()));

		setOnMouseClicked(e -> {

			WeatherView view = new WeatherView(city, allCities);
			stage.setScene(view.getScene(stage));

		});

		animate();
	}

	public void showError() {
		tempLabel.setText("Error");
		iconView.setImage(null);
	}

	private void animate() {
		setOpacity(0);
		setScaleX(0.8);
		setScaleY(0.8);

		new Thread(() -> {
			try {
				for (double i = 0; i <= 1; i += 0.05) {
					double scale = 0.8 + (0.2 * i);
					double opacity = i;

					javafx.application.Platform.runLater(() -> {
						setScaleX(scale);
						setScaleY(scale);
						setOpacity(opacity);
					});

					Thread.sleep(15);
				}
			} catch (InterruptedException ignored) {
			}
		}).start();
	}
}
