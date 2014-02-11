package com.robot.main;

//import com.robot.in.DigitalIOSet;
// import com.robot.out.Arm;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj.DigitalInput;

public class BasicBot extends IterativeRobot
{
	// MotorSet drv = new MotorSet(1,2)/*,motors = new MotorSet(6,12)*/;
	// DigitalIOSet dio = new DigitalIOSet(1,12);
	Joystick j1/*,j2*/;
	Talon ldm,rdm;
	RobotDrive chassis;
	ADXL345_I2C acl;
	AnalogChannel pot,winch;
	// DigitalInput load,ldme,rdme;
	// double mag,theta,rotation;
 	// Arm arm;
	Talon armVacuum;
	

	public void robotInit()
	{
		{ // Motor initialization
			// arm = new Arm(5);
		}
		j1 = new Joystick(1);
		
		ldm = new Talon(2);
		rdm = new Talon(1);
		// armVacuum = new Jaguar(3);
		
		// load = new DigitalInput(1);
		// ldme = new DigitalInput(2);
		// rdme = new DigitalInput(3);
		
		chassis = new RobotDrive(ldm,rdm);
		chassis.setInvertedMotor(RobotDrive.MotorType.kFrontRight,true);
		chassis.setInvertedMotor(RobotDrive.MotorType.kFrontLeft,true);
		// acl = new ADXL345_I2C(1,ADXL345_I2C.DataFormat_Range.k2G);
		
		pot = new AnalogChannel(1);
		// winch = new AnalogChannel(2);
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
		SmartDashboard.putData("Potentiometer", pot);
		// System.out.println(pot.getValue());
		
		// chassis.arcadeDrive(acl.getAcceleration(ADXL345_I2C.Axes.kY),acl.getAcceleration(ADXL345_I2C.Axes.kX),false);
		// System.out.println("Joystick X: " + j1.getX() + "\nJoystick Y: " + j1.getY());
		// System.out.println("Left Motor Value: " + ldm.get() + "\nRight Motor Value: " + rdm.get());
		chassis.arcadeDrive(j1);
		// chassis.arcadeDrive(pot.getValue()-5,0,false);
		
		
		// arm.load(winch.getValue(), load.get());
		// arm.fire(j1.getRawButton(1));
		
		
	}
}