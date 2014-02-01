package com.robot.out;

import com.robot.main.ConstantsImpl;

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
			new ConstantsImpl().errorPort(null);
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
			new ConstantsImpl().errorPort(null);
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
			new ConstantsImpl().errorPort(null);
	}
	
	public void setValue(int portNum,double value)
	{
		if(portNum >= alphaPort && portNum <= omegaPort)
			values[portNum-alphaPort] = value;
		else
			new ConstantsImpl().errorPort(null);
	}
	
	public double getValue(int portNum)
	{
		if(portNum >= alphaPort && portNum <= omegaPort)
			return values[portNum-alphaPort];
		else
		{
			new ConstantsImpl().errorPort(null);
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