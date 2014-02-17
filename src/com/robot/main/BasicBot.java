package com.robot.main;

//import com.robot.in.DigitalIOSet;
import com.robot.out.Arm;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GearTooth;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class BasicBot extends IterativeRobot
{
	// DigitalIOSet dio = new DigitalIOSet(1,12);
	Joystick j1/*,j2*/;
	Talon ldm,rdm;
	RobotDrive chassis;
	ADXL345_I2C acl;
	AnalogChannel pot;
	GearTooth winch;
	DigitalInput load,ldme,rdme;
 	Arm arm;
	Talon armVacuum,armPull;
	
	Solenoid rpn,lpn,armLock;
	
	
	
	public void robotInit()
	{
		{ // Motor initialization
			arm = new Arm(1);
		}
		j1 = new Joystick(1);
		
		ldm = new Talon(2);
		rdm = new Talon(1);
		armVacuum = new Talon(4);
		armPull = new Talon(3);
		
		
		rpn = new Solenoid(1);
		lpn = new Solenoid(2);
		armLock = new Solenoid(3);
		
		load = new DigitalInput(1);
		ldme = new DigitalInput(2);
		rdme = new DigitalInput(3);
		
		winch = new GearTooth(6);
		
		chassis = new RobotDrive(ldm,rdm);
		chassis.setInvertedMotor(RobotDrive.MotorType.kFrontRight,true);
		chassis.setInvertedMotor(RobotDrive.MotorType.kFrontLeft,true);
		// acl = new ADXL345_I2C(1,ADXL345_I2C.DataFormat_Range.k2G);
		
		pot = new AnalogChannel(1);
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
		// SmartDashboard.putData("Potentiometer", pot);
		// System.out.println(pot.getValue());
		
		// chassis.arcadeDrive(acl.getAcceleration(ADXL345_I2C.Axes.kY),acl.getAcceleration(ADXL345_I2C.Axes.kX),false);
		chassis.arcadeDrive(j1);
		// chassis.arcadeDrive(pot.getValue()-5,0,false);
		
		
		// arm.load(winch.getValue(), load.get());
		// arm.fire(j1.getRawButton(1));
		// arm.vacuum(j1.getRawButton(2));
		arm.execute(j1,load,armLock,armVacuum,armPull,winch);
	}
}