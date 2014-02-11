package com.robot.main;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

public class NewRobotDrive extends RobotDrive
{
	public NewRobotDrive(SpeedController ldm, SpeedController rdm)
	{
		super(ldm,rdm);
	}
}
