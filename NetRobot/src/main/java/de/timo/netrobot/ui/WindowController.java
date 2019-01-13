package de.timo.netrobot.ui;

import de.timo.netrobot.net.NetworkService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class WindowController {

	private NetworkService network = NetworkService.GetIntsance();

	@FXML
	private Button btnStartServer;

	@FXML
	private Canvas canvasConStatus;

	private GraphicsContext gc;

	@FXML
	void initialize() {
		this.gc = canvasConStatus.getGraphicsContext2D();
		drawCircle(Color.BLACK);
	}

	public void btnStartServer_onclick(ActionEvent event) {

		if (network.getServer().isRunning()) { // network.running
			network.disconnect();
			drawCircle(Color.RED);
			btnStartServer.textProperty().set("Starting Server..");
		} else {
			network.setOn();
			btnStartServer.textProperty().set("Stop Server..");
			drawCircle(Color.GREEN);
		}

	}

	private void drawCircle(Color color) {
		// gc.clearRect(0, 0, 20, 20);

		gc.setFill(color);
		gc.fillOval(0, 0, 20, 20);
	}

}
