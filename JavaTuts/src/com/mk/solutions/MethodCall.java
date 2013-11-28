package com.mk.solutions;

import java.lang.reflect.Method;

public class MethodCall
{
	public static void main(String[] args) throws Exception
	{
		Class c = Class.forName("com.mk.solutions.A");
		Object o = c.newInstance();
		Method m = c.getDeclaredMethod("message", null);
		m.setAccessible(true);
		m.invoke(o, null);
	}
}