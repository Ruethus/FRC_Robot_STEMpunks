package com.robot.in;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.AnalogTrigger;

public class DigitalIOSet
{
	int alphaPort,omegaPort;
	String[] names;
	boolean[] values;
	ArrayList<AnalogTrigger> dio;

	public DigitalIOSet(int alphaPort,int omegaPort)
	{
		if((!(alphaPort > 0) || !(alphaPort <= 12) || !(omegaPort > 0)) || !(omegaPort <= 12))
		{
			System.out.println("ERROR: com.robot.out.MotorSet: Negative, zero, or overflow integer port address received. Please use integers from 1 to 12 for port addresses. Values: (" + alphaPort + "," + omegaPort + "");
		}
		else
		{
			this.alphaPort = alphaPort;
			this.omegaPort = omegaPort;
			names = new String[(omegaPort-alphaPort)];
			values = new boolean[(omegaPort-alphaPort)];
			dio = new ArrayList<AnalogTrigger>();
			for(int x = alphaPort;x < omegaPort;x++)
			{
				dio.add(new AnalogTrigger(x));
				dio.get(x-alphaPort).setLimitsVoltage(1.0, 10.0);
				values[x-alphaPort] = false;
				names[x-alphaPort] = "";
			}
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

	public boolean getValue(int portNum)
	{
		if(portNum >= alphaPort && portNum <= omegaPort)
			return (values[portNum-alphaPort] == false);
		else
		{
			System.out.println("ERROR: Invalid Port number.");
			return false;
		}
	}

	public void setTriggerRange(int portNum,double min,double max)
	{
		if(portNum >= alphaPort && portNum <= omegaPort)
			dio.get(portNum-alphaPort).setLimitsVoltage(min,max);
		else
		{
			System.out.println("ERROR: Invalid Port number.");
		}
	}
	
	public void update()
	{
		for(int x = alphaPort;x < omegaPort;x++)
			values[x-alphaPort] = dio.get(x-alphaPort).getTriggerState();
	}
}