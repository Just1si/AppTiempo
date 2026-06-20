package controllers;

import javafx.scene.Scene;
import javafx.stage.Stage;
import view.MainAppView;

public class MainAppController {

    public Scene getScene(Stage stage) {
        MainAppView view = new MainAppView();
        return view.getScene(stage);
    }
}
