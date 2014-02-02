package com.robot.main;

// import com.robot.in.DigitalIOSet;
// import com.robot.out.Arm;
import com.robot.out.MotorSet;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.IterativeRobot;

public class BasicBot extends IterativeRobot
{
	MotorSet drv = new MotorSet(1,2)/*,motors = new MotorSet(6,12)*/;
	// DigitalIOSet dio = new DigitalIOSet(1,12);
	Joystick j1/*,j2*/;
	// Jaguar leftDriveMotor,rightDriveMotor;
	// double mag,theta,rotation;
	// Arm arm;
	
	public void robotInit()
	{
		{ // Motor initialization
			// arm = new Arm(5);
		}
		j1 = new Joystick(1);
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
		drv.update();
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
		    
	        double leftMotorSpeed,rightMotorSpeed,moveValue = j1.getY(),
	        rotateValue = j1.getX();
	        boolean squaredInputs = false;
	        if(Math.abs(moveValue) > 1)
	        	moveValue = moveValue/Math.abs(moveValue);
	        if(Math.abs(rotateValue) > 1)
	        	rotateValue = rotateValue/Math.abs(rotateValue);
	        

	        if (squaredInputs) {
	            // square the inputs (while preserving the sign) to increase fine control while permitting full power
	            if (moveValue >= 0.0) {
	                moveValue = (moveValue * moveValue);
	            } else {
	                moveValue = -(moveValue * moveValue);
	            }
	            if (rotateValue >= 0.0) {
	                rotateValue = (rotateValue * rotateValue);
	            } else {
	                rotateValue = -(rotateValue * rotateValue);
	            }
	        }

	        if (moveValue > 0.0) {
	            if (rotateValue > 0.0) {
	                leftMotorSpeed = moveValue - rotateValue;
	                rightMotorSpeed = Math.max(moveValue, rotateValue);
	            } else {
	                leftMotorSpeed = Math.max(moveValue, -rotateValue);
	                rightMotorSpeed = moveValue + rotateValue;
	            }
	        } else {
	            if (rotateValue > 0.0) {
	                leftMotorSpeed = -Math.max(-moveValue, rotateValue);
	                rightMotorSpeed = moveValue + rotateValue;
	            } else {
	                leftMotorSpeed = moveValue - rotateValue;
	                rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
	            }
	        }

	        drv.setValue(1,leftMotorSpeed);
	        drv.setValue(2,rightMotorSpeed);
	        drv.update();
	        Timer.delay(0.01d);
	        
	}
}