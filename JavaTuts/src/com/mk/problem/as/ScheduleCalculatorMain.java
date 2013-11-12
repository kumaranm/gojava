package com.mk.problem.as;

import java.io.IOException;

import com.mk.problem.as.common.PrintUtility;
import com.mk.problem.as.impl.AmortizationScheduleCalculator;

/**
 * Main class to run the schedule calculator
 * 
 * @author kmuruganandham
 *
 */
public class ScheduleCalculatorMain
{
	public static void main(String[] args)
	{
		try
		{
			Schedule scheduleCalc = new AmortizationScheduleCalculator();
			scheduleCalc.printSchedule();
		} catch (IllegalArgumentException e)
		{
			PrintUtility.print("Unable to process the values entered. Terminating program.\n");
		} catch (IOException e)
		{
			PrintUtility.print("Unable to read the values entered. Terminating program.\n");
		}
	}
}
