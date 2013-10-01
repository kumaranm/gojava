package com.mk.solutions;

public class ReflectionNewInstance
{
	public static void main(String[] args)
	{
		// create instance of "Class"
		Class<?> c = null;
		try
		{
			c = Class.forName("com.mk.solutions.Foo2");
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		// create instance of "Foo"
		Foo2 f = null;

		try
		{
			f = (Foo2) c.newInstance();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		f.print();
	}
}

class Foo2
{
	public void print()
	{
		System.out.println("abc");
	}
}