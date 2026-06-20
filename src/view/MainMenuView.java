package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.animation.ScaleTransition;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

public class MainMenuView {

    private Button startButton;

    public Scene getScene(Stage stage) {

        startButton = new Button("Start");
        startButton.getStyleClass().add("start-button");
        
     // Animación hover estilo GBA

        ScaleTransition hoverGrow = new ScaleTransition(Duration.millis(120), startButton);
        hoverGrow.setToX(1.05);
        hoverGrow.setToY(1.05);

        ScaleTransition hoverShrink = new ScaleTransition(Duration.millis(120), startButton);
        hoverShrink.setToX(1.0);
        hoverShrink.setToY(1.0);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(120), startButton);
        fadeIn.setToValue(1.0);

        FadeTransition fadeOut = new FadeTransition(Duration.millis(120), startButton);
        fadeOut.setToValue(0.92);

        // Eventos del ratón
        startButton.setOnMouseEntered(e -> {
            hoverGrow.playFromStart();
            fadeIn.playFromStart();
        });

        startButton.setOnMouseExited(e -> {
            hoverShrink.playFromStart();
            fadeOut.playFromStart();
        });
        
        startButton.setOnAction(e -> {
            WeatherView weatherView = new WeatherView();
            stage.setScene(weatherView.getScene(stage));
        });

     // Animación de rebote al hacer clic
        startButton.setOnMousePressed(e -> {
            ScaleTransition press = new ScaleTransition(Duration.millis(80), startButton);
            press.setToX(0.92);
            press.setToY(0.92);
            press.playFromStart();
        });

        startButton.setOnMouseReleased(e -> {
            ScaleTransition release = new ScaleTransition(Duration.millis(120), startButton);
            release.setToX(1.05);  // pequeño rebote
            release.setToY(1.05);
            release.setOnFinished(ev -> {
                // vuelve al tamaño normal
                ScaleTransition settle = new ScaleTransition(Duration.millis(80), startButton);
                settle.setToX(1.0);
                settle.setToY(1.0);
                settle.play();
            });
            release.play();
        });


        VBox root = new VBox(startButton);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(
            getClass().getResource("/styles/mainMenu.css").toExternalForm()
        );
      
        return scene;
    }

    public Button getStartButton() {
        return startButton;
    }
}
