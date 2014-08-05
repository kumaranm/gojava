package com.mk.utility;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ReadFileFromResources
{

	private String getFile(String fileName)
	{
		StringBuilder sb = new StringBuilder();
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		try (Scanner scanner = new Scanner(file))
		{
			while (scanner.hasNextLine())
			{
				String line = scanner.nextLine();
				sb.append(line).append("\n");
			}
		} catch (IOException e)
		{
			System.err.println(e);
		}
		return sb.toString();
	}

	public static void main(String[] arg)
	{
		ReadFileFromResources obj = new ReadFileFromResources();
		System.out.println(obj.getFile("file/test.txt"));

	}
}
