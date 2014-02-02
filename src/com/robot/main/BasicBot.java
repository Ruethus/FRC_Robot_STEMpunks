package com.robot.main;

//import com.robot.in.DigitalIOSet;
//import com.robot.out.Arm;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.ADXL345_I2C;

public class BasicBot extends IterativeRobot
{
	// MotorSet drv = new MotorSet(1,2)/*,motors = new MotorSet(6,12)*/;
	// DigitalIOSet dio = new DigitalIOSet(1,12);
	Joystick j1/*,j2*/;
	Jaguar ldm,rdm;
	RobotDrive chassis;
	ADXL345_I2C acl;
	// double mag,theta,rotation;
	// Arm arm;

	public void robotInit()
	{
		{ // Motor initialization
			// arm = new Arm(5);
		}
		j1 = new Joystick(1);
		ldm = new Jaguar(1);
		rdm = new Jaguar(2);
		chassis = new RobotDrive(rdm,ldm);
		acl = new ADXL345_I2C(1,ADXL345_I2C.DataFormat_Range.k2G);
		// j2 = new Joystick(2);

		{ // DigitalIOSet initialization
			// dio.setName(1,"frontLeftCollider");
			// dio.setName(2,"frontRightCollider");
			// dio.setName(3,"backLeftCollider");
			// dio.setName(4,"backRightCollider");
		}
	}

	public void autonomous()
	{
		// dio.update();
		{
			// Autonomous code goes here
		}
		// drv.update();
		// motors.update();
	}

	public void teleopInit()
	{

	}

	public void teleopPeriodic()
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

		chassis.arcadeDrive(acl.getAcceleration(ADXL345_I2C.Axes.kY),acl.getAcceleration(ADXL345_I2C.Axes.kX),false);
		System.out.println("Joystick X: " + j1.getX() + "\nJoystick Y: " + j1.getY());
		System.out.println("Left Motor Value: " + ldm.get() + "\nRight Motor Value: " + rdm.get());
	}
}