package com.robot.out;

import com.robot.main.Constants;
import com.robot.main.ConstantsImpl;

import edu.wpi.first.wpilibj.Jaguar;

public class Arm
{
	protected Jaguar arm;
	protected boolean initialized;

	public Arm(int portNum)
	{
		if(portNum >= 1 && portNum <= 12)
		{
			arm = new Jaguar(portNum);
			initialized = true;
		}
		else
		{
			new ConstantsImpl().errorPort(null);
			initialized = false;
		}
	}

	public void load(double armAngle,double winchAngle, boolean trigger)
	{
		if(initialized)
		{
			while(winchAngle > Constants.MIN_WINCH_ANGLE)
			{
				; //<--- Most efficient line of code EVER!!
			}
			while(winchAngle < Constants.MAX_WINCH_ANGLE && !trigger)
			{
				;
			}
		}
		else
		{
			new ConstantsImpl().errorArmInit(null);
		}
	}
	public void fire(double armAngle,double winchAngle, boolean trigger)
	{
		if(initialized)
		{
			
		}
		else
		{
			new ConstantsImpl().errorArmInit(null);
		}
	}
}