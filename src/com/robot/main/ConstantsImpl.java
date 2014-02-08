package com.robot.main;

public class ConstantsImpl implements Constants {

	public void errorArmInit(String errorMsg) {
		System.out.println("Error: Arm object not initialized.");
		System.out.println("\"Duster, you moron! *whack* You moron!\"\t-Wess, Mother 3");
		if(errorMsg != null)
			System.out.println(errorMsg);
	}

	public void errorPort(String errorMsg) {
		System.out.println("Error: Invalid Port Number.");
		System.out.println("\"*sigh*\"\t-Midna, Legend of Zelda: Twilight Princess");
		if(errorMsg != null)
			System.out.println(errorMsg);
	}
	public void errorVacInit(String errorMsg)
	{
		System.out.println("Error: Vacuum object not initialized.");
		System.out.println("\"You suck!\"\t-Deadpool, Deadpool");
	}
	public void errorGeneric(String errorMsg)
	{
		System.out.println("Error 404: Creativity not found.");
		System.out.println("\"I'm disinclined to acquiesce to your request. Means no.\"\t-Hector Barbossa, Pirates of the Caribbean");
	}
}
