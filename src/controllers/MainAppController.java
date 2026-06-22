package controllers;

import javafx.scene.Scene;
import javafx.stage.Stage;
import view.CitySelectionView;

public class MainAppController {

    public Scene getScene(Stage stage) {
        CitySelectionView view = new CitySelectionView();
        return view.getScene(stage);
    }
}
