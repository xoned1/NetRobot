package de.timo.netrobot.commands;

import java.io.File;
import java.io.IOException;


public class AudioControl {

	// TODO Dran denken, wenn jar muss ausgepackt werden

	private static final String EXE_NAME = "nircmdc.exe";

	public static String getExecutablePath() {
		return new File("C:\\Users\\itimo\\IdeaProjects\\NetRobot\\NetRobot\\nircmd.exe").getAbsolutePath();
	}

	//public static String getExecutablePath() {
	//	return new File("C:\Users\itimo\IdeaProjects\NetRobot\NetRobot").getAbsolutePath() + File.separator + EXE_NAME;
	//}

	public static void shutdown(String message, int timeoutSeconds, boolean force) {
		try {
			String strforce = force ? "force" : "";
			Runtime.getRuntime().exec(getExecutablePath() + " initshutdown \"" + message +  "\" " + String.valueOf(timeoutSeconds) + " " + strforce);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void setVolume(int value) {
		try {
			Runtime.getRuntime().exec(getExecutablePath() + " setsysvolume " + String.valueOf(calcAbsValue(value)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 

	public static void increaseVolume(int value) {
		try {
			Runtime.getRuntime().exec(getExecutablePath() + " changesysvolume " + String.valueOf(calcAbsValue(value)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void decreaseVolume(int value) {
		try {
			Runtime.getRuntime().exec(getExecutablePath() + " changesysvolume -" + String.valueOf(calcAbsValue(value)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void switchMute() {
		try {
			Runtime.getRuntime().exec(getExecutablePath() + " mutesysvolume " + 2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

	private static int calcAbsValue(int procent) {
		int MAX = 65535;
		return (MAX / 100) * procent;
	}

}
