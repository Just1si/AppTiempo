package view;

import controllers.WeatherController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

public class WeatherView {

    private final WeatherController controller = new WeatherController();

    private Label temperatureLabel;
    private Label descriptionLabel;
    private ImageView weatherIcon;
    private Button backButton;

    public Scene getScene(Stage stage) {

        // Etiquetas
        temperatureLabel = new Label("Cargando...");
        temperatureLabel.getStyleClass().add("temperature");

        descriptionLabel = new Label("Obteniendo datos...");
        descriptionLabel.getStyleClass().add("description");

        // Icono
        weatherIcon = new ImageView(new Image(
                getClass().getResource("/icons/sunnyWeather.png").toExternalForm()));
        weatherIcon.setFitWidth(120);
        weatherIcon.setPreserveRatio(true);

        // Animaciones externas
        WeatherAnimations.applyBounce(weatherIcon);
        WeatherAnimations.applyPulse(weatherIcon);

        // Botón volver
        backButton = new Button("Volver");
        backButton.getStyleClass().add("back-button");
        backButton.setOnAction(e -> {
            MainMenuView menu = new MainMenuView();
            stage.setScene(menu.getScene(stage));
        });

        // Contenedor principal
        VBox content = new VBox();
        content.setAlignment(Pos.CENTER);
        content.setSpacing(20);

        StackPane root = new StackPane(content);
        root.getStyleClass().add("weather-background");

        // Selector de ciudad
        ComboBox<String> citySelector = new ComboBox<>();
        citySelector.getItems().addAll("Málaga", "Madrid", "Barcelona", "Londres", "Nueva York");
        citySelector.setValue("Málaga");
        citySelector.getStyleClass().add("city-selector");

        citySelector.setOnAction(e -> {
            String city = citySelector.getValue();
            loadWeather(city, root);
        });

        // Añadir nodos
        content.getChildren().addAll(citySelector, weatherIcon, temperatureLabel, descriptionLabel, backButton);

        // Animación de entrada
        WeatherAnimations.applyIntro(root);

        // Cargar clima inicial
        loadWeather("Málaga", root);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/styles/weather.css").toExternalForm());

        return scene;
    }

    private void loadWeather(String city, StackPane root) {

        temperatureLabel.setText("Cargando...");
        descriptionLabel.setText("Obteniendo datos...");

        controller.loadWeather(city, new WeatherController.WeatherCallback() {

            @Override
            public void onSuccess(WeatherData data) {
                temperatureLabel.setText((int) data.getTemperature() + "°C");
                descriptionLabel.setText(data.getDescription());
                applyDynamicBackground(root, data.getDescription());
                updateWeatherIcon(data.getIcon());
            }

            @Override
            public void onError() {
                temperatureLabel.setText("Error");
                descriptionLabel.setText("No se pudo cargar el clima");
            }
        });
    }

    private void applyDynamicBackground(StackPane root, String weather) {
        String imageName = WeatherBackgroundMapper.getBackground(weather);
        root.setStyle("-fx-background-image: url('/backgrounds/" + imageName + "');"
                + "-fx-background-size: cover;"
                + "-fx-background-position: center;");
    }

    private void updateWeatherIcon(String iconCode) {
        String file = WeatherIconMapper.getIconFile(iconCode);
        weatherIcon.setImage(new Image(
                getClass().getResource("/icons/" + file).toExternalForm()));
    }
}
