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
 * @author CtrlFreak1337
 * @author Ruethus
 * 
 */
public class Arm
{
	protected Jaguar winchMotor;
	protected Solenoid armLock;
	protected boolean initialized;
	protected Jaguar armVacuum;
	public boolean armBack;
	private double winchDiff;
	

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
	 * @param armBack Tests to see if the launcher arm is all the way loaded.
	 */
	public void load(double winchAngle,boolean armBack)
	{
		if(initialized)
		{
			while(!armBack)
			{
				armVacuum.set(1.0);
				winchMotor.set(-1.0);
			}
			while(armBack)
			{
				winchDiff = winchAngle;
				winchMotor.set(0.0);
				armLock.set(true);
			}
			while(armBack)
			{
				if(armLock.get())
				{
					if(winchAngle+winchDiff < Constants.MAX_WINCH_ANGLE)
					{
						winchMotor.set(1.0);
					}
					else
					{
					winchMotor.set(0.0);	
					}
				}
				else
				{
					armLock.set(true);
					if(winchAngle+winchDiff < Constants.MAX_WINCH_ANGLE)
					{
						winchMotor.set(1.0);
					}
					else
					{
						winchMotor.set(0.0);
					}
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
	public void fire(boolean trigger)
	{
		if(initialized)
		{
			while(armBack)
			{
				armVacuum.set(0);
				if(trigger)
				{
					armLock.set(false);
					System.out.println("FIRE!!!");
				}	
			}
			while(!armBack)
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
	public void Test()
	{
		if(armBack)
		{
			System.out.println("Red-y for firing.");
		}
	}
}