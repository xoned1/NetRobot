package de.timo.netrobot.app;

import de.timo.netrobot.ui.Window;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

	public static void main(String[] args) {

		boolean start = false;
		if (args.length >= 2) {
			if (args[0].equals("--start")) {
				start = Boolean.getBoolean(args[1]);
			}
		}

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Window window = new Window();
		window.create(primaryStage);
	}

}
