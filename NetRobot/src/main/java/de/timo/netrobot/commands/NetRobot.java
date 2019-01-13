package de.timo.netrobot.commands;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class NetRobot {

	public static void invokeRightClick() {
		try {
			Robot robot = new Robot();
			int event = InputEvent.BUTTON3_DOWN_MASK;
			robot.mousePress(event);
			robot.mouseRelease(event);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public static void invokeLeftClick() {
		try {
			Robot robot = new Robot();
			int event = InputEvent.BUTTON1_DOWN_MASK;
			robot.mousePress(event);
			robot.mouseRelease(event);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public static void moveMouseRight() {
		try {
			Robot robot = new Robot();
			Point location = MouseInfo.getPointerInfo().getLocation();
			robot.mouseMove((int) location.getX() + 10, (int) location.getY());
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public static void moveMouseLeft() {
		try {
			Robot robot = new Robot();
			Point location = MouseInfo.getPointerInfo().getLocation();
			robot.mouseMove((int) location.getX() - 10, (int) location.getY());
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public static void moveMouseUp() {
		try {
			Robot robot = new Robot();
			Point location = MouseInfo.getPointerInfo().getLocation();
			robot.mouseMove((int) location.getX(), (int) location.getY() + 10);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public static void moveMouseDown() {
		try {
			Robot robot = new Robot();
			Point location = MouseInfo.getPointerInfo().getLocation();
			robot.mouseMove((int) location.getX(), (int) location.getY() - 10);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

}
