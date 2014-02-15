package com.robot.out;

import com.robot.main.Constants;
import com.robot.main.ConstantsImpl;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GearTooth;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;

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
	protected Talon winchMotor;
	protected Solenoid armLock;
	protected boolean initialized;
	protected Talon armVacuum;
	protected DigitalInput armBack;
	protected int winchDiff;
	protected Joystick src;
	protected int button;
	protected GearTooth winchAngle;

	public Arm(int armPortNum, int vacuumPortNum, Joystick src, int button, DigitalInput armBack, GearTooth winchAngle)
	{
		if((armPortNum >= 1 && armPortNum <= 12) && (vacuumPortNum >= 1 && vacuumPortNum<= 12) && (armPortNum != vacuumPortNum))
		{
			armVacuum = new Talon(vacuumPortNum);
			winchMotor = new Talon(armPortNum);
			initialized = true;
			this.src = src;
			this.button = button;

		}
		else
		{
			new ConstantsImpl().errorPort(null);
			new ConstantsImpl().errorArmInit(null);
			new ConstantsImpl().errorVacInit(null);
			initialized = false;
		}
	}

	/**
	 * This readies the launcher for firing.
	 * @param armAngle This specifies the angle of the arm.
	 * @param winchAngle Default min value is 0. Default max value is 5.
	 * @param armBack Tests to see if the launcher arm is all the way loaded.
	 */
	public void load()
	{
		if(initialized)
		{
			while(armBack.get())
			{
				armVacuum.set(1.0);
				winchMotor.set(-1.0);
			}
			while(!armBack.get())
			{
				winchDiff = winchAngle.get();
				winchMotor.set(0.0);
				armLock.set(true);
			}
			while(!armBack.get())
			{
				if(armLock.get())
				{
					if(winchAngle.get()+winchDiff < Constants.MAX_WINCH_ANGLE)
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
					if(winchAngle.get()+winchDiff < Constants.MAX_WINCH_ANGLE)
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
	public void fire()
	{
		if(initialized)
		{
			while(armBack.get())
			{
				armVacuum.set(0);
				if(src.getRawButton(button))
				{
					armLock.set(false);
					System.out.println("FIRE!!!");
				}	
			}
			while(!armBack.get())
			{
				if(src.getRawButton(button))
				{
					System.out.println("\"Patience, young Padawan.\"\t-Every Jedi Master, ever.");
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
		if(armBack.get())
		{
			System.out.println("Red-y for firing.");
		}
	}
	public void execute(Joystick src)
	{
		if(src.getRawButton(button))
			fire();
		else
			load();
	}
	public void Vacuum(boolean vac)
	{
		while(vac)
			{
				armVacuum.set(1);
			}
	}
}