package de.timo.netrobot.ui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Window {

	public void create(Stage primaryStage) {
		primaryStage.setTitle("NetRobot Server!");

		try {
			GridPane root = FXMLLoader.load(getClass().getResource("/window.fxml"));
			primaryStage.setScene(new Scene(root, 200, 100));
			primaryStage.show();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
