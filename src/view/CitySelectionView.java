package view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CitySelectionView {

    public Scene getScene(Stage stage) {

        Label title = new Label("Introduce las ciudades que quieres ver:");
        title.getStyleClass().add("title");

        TextArea input = new TextArea();
        input.setPromptText("Ejemplo:\nMálaga\nMadrid\nLondres\nNueva York");
        input.setPrefRowCount(8);
        input.setWrapText(true);
        input.getStyleClass().add("city-input");
        
        Button btn = new Button("Ver clima");
        btn.getStyleClass().add("button");
        
        btn.setOnAction(e -> {
        	
        	String raw = input.getText();
        	
            List<String> cities = Arrays.stream(raw.split("[,;\n]"))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toList());

            if (!cities.isEmpty()) {
                WeatherDashboardView dashboard = new WeatherDashboardView(cities);
                stage.setScene(dashboard.getScene(stage));
            }
        });

        VBox root = new VBox(15, title, input, btn);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(
            getClass().getResource("/styles/citySelection.css").toExternalForm()
        );

        return scene;
    }
}
