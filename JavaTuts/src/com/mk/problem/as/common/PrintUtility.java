package com.mk.problem.as.common;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.IllegalFormatException;

/**
 * Utility for outputting to the console
 * 
 * @author kmuruganandham
 * 
 */
public class PrintUtility
{
	private static Console console = System.console();

	public static void formatAndPrint(String formatString, Object... args)
	{

		try
		{
			if (console != null)
			{
				console.printf(formatString, args);
			}
			else
			{
				// System.out.print(String.format(formatString, args));
				System.out.format(formatString, args);
			}
		} catch (IllegalFormatException e)
		{
			System.err.print("Error printing...\n");
		}
	}

	public static void print(String s)
	{
		formatAndPrint("%s", s);
	}

	public static String readLine(String userInput) throws IOException
	{
		String line = "";

		if (console != null)
		{
			line = console.readLine(userInput);
		}
		else
		{
			print(userInput);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			line = bufferedReader.readLine();
		}
		if (null != line)
		{
			line.trim();
		}
		return line;
	}
}
