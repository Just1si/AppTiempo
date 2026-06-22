package controllers;

import javafx.animation.FadeTransition;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.CitySelectionView;
import view.MainMenuView;


public class MainMenuController {

	public void show(Stage stage) {

		MainMenuView view = new MainMenuView();
		Scene scene = view.getScene(stage);
		stage.setScene(scene);
		stage.show();

		view.getStartButton().setOnAction(e -> {

			FadeTransition fadeOut = new FadeTransition(Duration.millis(600), stage.getScene().getRoot());
			fadeOut.setFromValue(1.0);
			fadeOut.setToValue(0.0);

			fadeOut.setOnFinished(ev -> {

				CitySelectionView cityView = new CitySelectionView();
				Scene mainScene = cityView.getScene(stage);
				stage.setScene(mainScene);
				stage.show();

				FadeTransition fadeIn = new FadeTransition(Duration.millis(600), mainScene.getRoot());
				fadeIn.setFromValue(0.0);
				fadeIn.setToValue(1.0);
				fadeIn.play();
			});

			fadeOut.play();
		});

	}

}
