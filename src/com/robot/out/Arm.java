package com.robot.out;

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
			System.out.println("Error: Invalid Port Number");
			System.out.println("\"*sigh*\"\t-Midna, Legend of Zelda: Twilight Princess");
			initialized = false;
		}
	}

	public void load(double armAngle,double winchAngle, boolean trigger)
	{
		if(initialized)
		{
			while(winchAngle > 0.0)
			{
				; //<--- Most efficient line of code EVER!!
			}
			while(winchAngle < 5.0 && !trigger)
			{
				;
			}
		}
		else
		{
			System.out.println("Error: Arm object not initialized.");
			System.out.println("\"Duster, you moron! *whack* You moron!\"\t-Wess, Mother 3");

		}
	}
	public void fire(double armAngle,double winchAngle, boolean trigger)
	{
		if(initialized)
		{
			
		}
		else
		{
			System.out.println("Error: Arm object not initialized.");
			System.out.println("\"Duster, you moron! *whack* You moron!\"\t-Wess, Mother 3");
		}
	}
}