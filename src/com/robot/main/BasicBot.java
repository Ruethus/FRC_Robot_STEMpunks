package com.robot.main;

import com.robot.in.DigitalIOSet;
import com.robot.out.MotorSet;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;

public class BasicBot extends SimpleRobot
{
	MotorSet drv = new MotorSet(1,4),arm = new MotorSet(5,12);
	DigitalIOSet dio = new DigitalIOSet(1,12);
	Joystick j1,j2;
	RobotDrive chassis;
	Jaguar leftDriveMotor,rightDriveMotor;
	double mag,theta,rotation;

	public void robotInit()
	{
		{ // MotorSet initialization
			chassis = new RobotDrive(drv.getObject(0),drv.getObject(1),drv.getObject(2),drv.getObject(3));

		}

		{ // DigitalIOSet initialization
			dio.setName(1,"frontLeftCollider");
			dio.setName(2,"frontRightCollider");
			dio.setName(3,"backLeftCollider");
			dio.setName(4,"backRightCollider");
		}
	}

	public void autonomous()
	{
		// dio.update();
		{
			// Autonomous code goes here
		}
		drv.update();
		arm.update();
	}

	public void operatorControl()
	{
		// dio.update();
		// double[] j1axes = {j1.getX(),j1.getY(),j1.getThrottle()};
		// double[] j2axes = {j2.getX(),j2.getY(),j2.getThrottle()};

		; // <-- Most efficient line of code EVER!!!

		/*
		 * Expected Mecanum Arrangement
		 * 
		 *  \\\\\        /////
		 *    |            |
		 * 
		 *    |            |
		 *  /////        \\\\\
		 */
		/*
		 * mag = Math.sqrt(Math.pow(j1axes[0],2)+Math.pow(j1axes[1],2)*j1axes[2]);
		 * theta = Math.atan(j1axes[1]/j1axes[0]);
		 * rotation = j1axes[2];
		 * chassis.mecanumDrive_Polar(mag,theta,rotation);
		 */
		chassis.arcadeDrive(j1);
		if (j1.getRawButton(1))
			arm.setValue(5,1);
		else if (dio.getValue(1))
			arm.setValue(5,0);
		else
			arm.setValue(6,-1);
		if (j1.getRawButton(2))
			arm.setValue(6,1);
		else
			arm.setValue(6,-1);
		arm.update();
	}
}