package com.mk.solutions;

import java.lang.reflect.Constructor;

public class ReflectionConstructor
{
	public static void main(String[] args)
	{
		// create instance of "Class"
		Class<?> c = null;
		try
		{
			c = Class.forName("com.mk.solutions.Foo3");
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		// create instance of "Foo"
		Foo3 f1 = null;
		Foo3 f2 = null;

		// get all constructors
		Constructor<?> cons[] = c.getConstructors();

		try
		{
			f1 = (Foo3) cons[0].newInstance();
			f2 = (Foo3) cons[1].newInstance("abc");
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		f1.print();
		f2.print();
	}
}

class Foo3
{
	String s;

	public Foo3() {
	}

	public Foo3(String s) {
		this.s = s;
	}

	public void print()
	{
		System.out.println(s);
	}
}