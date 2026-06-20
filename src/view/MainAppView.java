package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainAppView {

    public Scene getScene(Stage stage) {

        FlowPane root = new FlowPane();
        root.setHgap(20);
        root.setVgap(20);
        root.setAlignment(Pos.CENTER);

        // Tarjetas de ejemplo
        root.getChildren().add(crearTarjeta("Madrid", "27°C", "☀️"));
        root.getChildren().add(crearTarjeta("Barcelona", "24°C", "⛅"));
        root.getChildren().add(crearTarjeta("Málaga", "29°C", "☀️"));
        root.getChildren().add(crearTarjeta("Sevilla", "31°C", "☀️"));
        root.getChildren().add(crearTarjeta("Bilbao", "20°C", "🌧️"));
        root.getChildren().add(crearTarjeta("Valencia", "26°C", "🌤️"));

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(
            getClass().getResource("/styles/mainApp.css").toExternalForm()
        );

        return scene;
    }

    private VBox crearTarjeta(String ciudad, String temp, String icono) {
        Label cityLabel = new Label(ciudad);
        cityLabel.getStyleClass().add("city");

        Label tempLabel = new Label(temp);
        tempLabel.getStyleClass().add("temp");

        Label iconLabel = new Label(icono);
        iconLabel.getStyleClass().add("icon");

        VBox card = new VBox(cityLabel, tempLabel, iconLabel);
        card.getStyleClass().add("card");
        card.setAlignment(Pos.CENTER);

        return card;
    }
}
