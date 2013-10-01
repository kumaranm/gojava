package com.mk.solutions;

import java.lang.reflect.Method;

public class ReflectionInvokeMethod
{
	public static void main(String[] args)
	{
		Foo1 f = new Foo1();

		Method method;
		try
		{
			method = f.getClass().getMethod("print", new Class<?>[0]);
			method.invoke(f);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

class Foo1
{
	public void print()
	{
		System.out.println("abc");
	}
}