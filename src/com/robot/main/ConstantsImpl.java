package com.robot.main;

public class ConstantsImpl implements Constants {

	@Override
	public void errorArmInit(String errorMsg) {
		System.out.println("Error: Arm object not initialized.");
		System.out.println("\"Duster, you moron! *whack* You moron!\"\t-Wess, Mother 3");
		if(errorMsg != null)
			System.out.println(errorMsg);
	}

	@Override
	public void errorPort(String errorMsg) {
		System.out.println("Error: Invalid Port Number.");
		System.out.println("\"*sigh*\"\t-Midna, Legend of Zelda: Twilight Princess");
		if(errorMsg != null)
			System.out.println(errorMsg);
	}

}