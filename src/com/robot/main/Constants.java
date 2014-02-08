package com.robot.main;

/**
 * 
 * This file contains the constants for all files.
 * @author CtrlFreak
 *
 */
public interface Constants {
	/**
	 * This contains the <b>Winch Angle</b> constants.
	 */
	static final double MAX_WINCH_ANGLE = 5.0;
	static final double MIN_WINCH_ANGLE = 0.0;
	/**
	 * This contains error message constants, to save us typing :P
	 * @param errorMsg
	 */
	void errorArmInit(String errorMsg);
	void errorPort(String errorMsg);
	void errorGeneric(String errorMsg);
	void errorVacInit(String errorMsg);
}
