package view;

import java.util.ArrayList;
import java.util.List;

import controllers.WeatherDashboardController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class WeatherDashboardView {

    private final List<String> cities;
    private final WeatherDashboardController controller = new WeatherDashboardController();

    public WeatherDashboardView(List<String> cities) {
        this.cities = cities;
    }

    public Scene getScene(Stage stage) {

        FlowPane root = new FlowPane();
        root.setHgap(25);
        root.setVgap(25);
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("dashboard-background");

        List<WeatherCard> cards = new ArrayList<>();

        for (String city : cities) {
            WeatherCard card = new WeatherCard(city, cities);
            cards.add(card);
            root.getChildren().add(card);
        }

        controller.loadCities(cities, cards, stage);

        Scene scene = new Scene(root, 900, 650);
        scene.getStylesheets().add(
                getClass().getResource("/styles/dashboard.css").toExternalForm()
        );

        return scene;
    }
}
