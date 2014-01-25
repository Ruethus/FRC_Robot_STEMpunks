package com.robot.out;

import edu.wpi.first.wpilibj.Jaguar;

public class MotorSet
{
	int alphaPort,omegaPort;
	String[] names;
	double[] values;
	Jaguar[] motors;

	public MotorSet(int alphaPort,int omegaPort)
	{
		if((!(alphaPort > 0) || !(omegaPort > 0)) || !(omegaPort <= 12))
		{
			System.out.println("ERROR: com.robot.out.MotorSet: Negative, zero, or overflow integer port address received. Please use integers from 1 to 12 for port addresses. Values: (" + alphaPort + "," + omegaPort + "");
		}
		this.alphaPort = alphaPort;
		this.omegaPort = omegaPort;
		names = new String[(omegaPort-alphaPort)];
		values = new double[(omegaPort-alphaPort)];
		motors = new Jaguar[(omegaPort-alphaPort)];
		for(int x = 0;x < (omegaPort-alphaPort);x++)
		{
			motors[x] = new Jaguar(x+alphaPort);
			values[x] = 0;
			names[x] = "";
		}
			
	}

	public void setName(int portNum,String name)
	{
		if(portNum >= alphaPort && portNum <= omegaPort)
			names[portNum-alphaPort] = name;
		else
			System.out.println("ERROR: Invalid Port number.");
	}

	public void printSpecs()
	{
		for(int x = alphaPort;x < omegaPort;x++)
			System.out.println("Name: " + names[x-alphaPort] + "; Port: " + x + "; Value: " + values[x-alphaPort]);
	}

	public void appendValue(int portNum,int arg0)
	{
		if(portNum >= alphaPort && portNum <= omegaPort)
			values[portNum-alphaPort] += arg0;
		else
			System.out.println("ERROR: Invalid Port number.");
	}
	
	public void setValue(int portNum,double value)
	{
		if(portNum >= alphaPort && portNum <= omegaPort)
			values[portNum-alphaPort] = value;
		else
			System.out.println("ERROR: Invalid Port number.");
	}
	
	public double getValue(int portNum)
	{
		if(portNum >= alphaPort && portNum <= omegaPort)
			return values[portNum-alphaPort];
		else
		{
			System.out.println("ERROR: Invalid Port number.");
			return 0.0;
		}
	}

	public void update()
	{
		// System.out.println("\nMotors updated.\n");
		for(int x = alphaPort;x < omegaPort;x++)
			motors[x-alphaPort].set(values[x-alphaPort]);
		// printSpecs();
	}
	
	public Jaguar getObject(int portnum)
	{
		return ((Jaguar) motors[portnum-alphaPort]);
	}
}