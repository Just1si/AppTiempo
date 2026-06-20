package appTiempo;
import controllers.MainMenuController;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
	
	
	@Override
	public void start(Stage escenario) throws Exception {

		MainMenuController controller = new MainMenuController();
		controller.show(escenario);
	}
	
	public static void main(String[] args) {
		launch(args);
		
		
	}
	
}
