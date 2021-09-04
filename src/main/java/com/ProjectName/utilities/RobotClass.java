package com.ProjectName.utilities;

import java.awt.Robot;

public class RobotClass {

	public static ThreadLocal<Robot> dr = new ThreadLocal<Robot>();

	public static Robot getRobotClassObject() {

		return dr.get();

	}

	public static void setRobotClassObject(Robot robot) {

		dr.set(robot);
	}
	
}