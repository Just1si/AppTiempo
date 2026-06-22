package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class MainAppView {

    public Scene getScene(Stage stage) {

        FlowPane root = new FlowPane();
        root.setHgap(20);
        root.setVgap(20);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(
            getClass().getResource("/styles/mainApp.css").toExternalForm()
        );

        return scene;
    }
}
