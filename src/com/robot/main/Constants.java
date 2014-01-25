package com.robot.main;

public interface Constants {
	static final double MAX_WINCH_ANGLE = 5.0;
	static final double MIN_WINCH_ANGLE = 0.0;
	
	void errorArmInit(String errorMsg);
	void errorPort(String errorMsg);
}
