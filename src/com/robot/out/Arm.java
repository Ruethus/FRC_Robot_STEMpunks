package com.robot.out;

import com.robot.main.Constants;
import com.robot.main.ConstantsImpl;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * 
 * This class is the implementation of the Arm and Launcher.
 * @version 0.1
 * @see edu.wpi.first.wpilibj.Jaguar
 * @author CtrlFreak
 *
 */
public class Arm
{
	protected Jaguar winchMotor;
	protected Solenoid armLock;
	protected boolean initialized;

	public Arm(int portNum)
	{
		if(portNum >= 1 && portNum <= 12)
		{
			winchMotor = new Jaguar(portNum);
			initialized = true;
		}
		else
		{
			new ConstantsImpl().errorPort(null);
			initialized = false;
		}
	}

	/**
	 * This readies the launcher for firing.
	 * @param armAngle This specifies the angle of the arm.
	 * @param winchAngle Default min value is 0. Default max value is 5.
	 * @param trigger Tests to see if the trigger is pressed.
	 */
	public void load(double armAngle,double winchAngle, boolean trigger)
	{
		if(initialized)
		{
			while(winchAngle > Constants.MIN_WINCH_ANGLE)
			{
				winchMotor.set(-1.0);
			}
			while(winchAngle <= Constants.MIN_WINCH_ANGLE)
			{
				winchMotor.set(0.0);
				armLock.set(true);
			}
			while(winchAngle < Constants.MAX_WINCH_ANGLE)
			{
				if(armLock.get())
				{
					winchMotor.set(1.0);
				}
				else
				{
					armLock.set(true);
					winchMotor.set(1.0);
				}
			}
		}
		else
		{
			new ConstantsImpl().errorArmInit(null);
		}
	}
	/**
	 * @param armAngle
	 * @param winchAngle
	 * @param trigger
	 */
	public void fire(double armAngle,double winchAngle, boolean trigger)
	{
		if(initialized)
		{
			while(winchAngle >= Constants.MAX_WINCH_ANGLE)
			{
				if(trigger)
				{
					armLock.set(false);
					System.out.println("FIRE!!!");
				}	
			}
			while(winchAngle < Constants.MAX_WINCH_ANGLE)
			{
				if(trigger)
				{
					System.out.println("Launcher is loading. Please wait.");
				}
			}
		}
		else
		{
			new ConstantsImpl().errorArmInit(null);
		}
	}
}