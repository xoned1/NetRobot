package de.timo.netrobot.ui;

import de.timo.netrobot.net.NetworkService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class WindowController {

	private NetworkService network = NetworkService.getInstance();

	@FXML
	private Button btnStartServer;

	@FXML
	private Canvas canvasConStatus;

	private GraphicsContext gc;

	@FXML
	void initialize() {
		this.gc = canvasConStatus.getGraphicsContext2D();
		drawCircle(Color.BLACK);
		startStopServer();
	}

	public void btnStartServer_onclick(ActionEvent event) {
		startStopServer();
	}

	private void startStopServer() {
		if (network.getServer().isRunning()) {
			stopServer();
		} else {
			startServer();
		}
	}

	private void startServer() {
		network.setOn();
		btnStartServer.textProperty().set("Stop Server..");
		drawCircle(javafx.scene.paint.Color.GREEN);
	}

	private void stopServer() {
		network.disconnect();
		drawCircle(javafx.scene.paint.Color.RED);
		btnStartServer.textProperty().set("Start Server..");
	}

	private void drawCircle(Color color) {
		gc.setFill(color);
		gc.fillOval(0, 0, 20, 20);
	}

}
