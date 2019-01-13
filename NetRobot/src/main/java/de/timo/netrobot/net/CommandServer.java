package de.timo.netrobot.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import de.timo.netrobot.commands.AudioControl;
import de.timo.netrobot.commands.Commands;
import de.timo.netrobot.commands.NetRobot;

public class CommandServer {

	private ServerSocket server;

	public CommandServer() {
	}

	public boolean listen() {

		Thread thread = new Thread(() -> {
			try {
				if (server == null || server.isClosed()) {
					System.out.println("Server starting..");
					this.server = new ServerSocket(1337);
				}

				while (!server.isClosed()) {
					Socket socket = server.accept();
					System.out.println("Connection accepted");
					BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

					String command = br.readLine();
					System.out.println("Received command: " + command);
					if (Commands.CLICK_RIGHT.toString().equals(command)) {
						NetRobot.invokeRightClick();
					}
					if (Commands.CLICK_LEFT.toString().equals(command)) {
						NetRobot.invokeLeftClick();
					}
					if (Commands.MOUSE_UP.toString().equals(command)) {
						NetRobot.moveMouseUp();
					}
					if (Commands.MOUSE_RIGHT.toString().equals(command)) {
						NetRobot.moveMouseRight();
					}
					if (Commands.MOUSE_DOWN.toString().equals(command)) {
						NetRobot.moveMouseDown();
					}
					if (Commands.MOUSE_LEFT.toString().equals(command)) {
						NetRobot.moveMouseLeft();
					}
					if (Commands.VOLUME_SET.toString().equals(command)) {
						AudioControl.setVolume(50); //TODO 50?
					}
					if (Commands.VOLUME_UP.toString().equals(command)) {
						AudioControl.increaseVolume(5); //TODO 
					}
					if (Commands.VOLUME_DOWN.toString().equals(command)) {
						AudioControl.decreaseVolume(5); //TODO
					}
					if (Commands.VOLUME_MUTE.toString().equals(command)) {
						AudioControl.switchMute();
					}
					if (Commands.EXIT.toString().equals(command)) {
						socket.close();

					}
				}

			} catch (BindException e) {
				if (e.getMessage().contains("already in use")) {
					System.out.println("Port bereits genutzt");
				}
			} catch (SocketException e) {
				e.printStackTrace();
				// nothing
			} catch (IOException e) {
				e.printStackTrace();
			} finally {

			}
		});

		thread.start();
		return false;
	}

	public void disconnect() {
		try {
			System.out.println("Server stopping..");
			this.server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isRunning() {
		return this.server != null && !this.server.isClosed();
	}

}
