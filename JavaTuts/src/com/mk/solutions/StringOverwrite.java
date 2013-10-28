package com.mk.solutions;

import java.lang.reflect.Field;

public class StringOverwrite
{
	public static void main(String[] args)
	{
		System.out.println("Hello World");
		System.out.println("Hello World1");
		
		int i = (byte) + (char) - (int) + (long) - 1;
		System.out.println(i);
		
		i = (byte) + (char) + (int) + (long) - 1;
		System.out.println(i);
	}

	static
	{
		try
		{
			Field value = String.class.getDeclaredField("value");
			value.setAccessible(true);
			value.set("Hello World", value.get("Hello Kumaran, I changed the string value"));
			value.set("Hello World1", value.get("Hello Kumaran, I changed the string"));
		} catch (Exception e)
		{
			throw new AssertionError(e);
		}
	}
}
