/**
 * 
 */
package com.mk.solutions;

/*
 a = a*b; //now a is 18 and b is 3
 b = a/b; //now a is 18 but b is 6 (original value of a)
 a = a/b; //now a is 3 and b is 6, numbers are swapped

 a = a^b; //now a is 6 and b is 4
 b = a^b; //now a is 6 but b is 2 (original value of a)
 a = a^b; //now a is 4 and b is 2, numbers are swapped

 a = a+ b; //now a is 30 and b is 20
 b = a -b; //now a is 30 but b is 10 (original value of a)
 a = a -b; //now a is 20 and b is 10, numbers are swapped
 *
 */
public class SwapWithoutTempVariable
{

	static void swap(int a, int b)
	{
		System.out.println("Before swap: a-" + a + ";b-" + b);
		a = a * b;
		b = a / b;
		a = a / b;
		System.out.println("After swap: a-" + a + ";b-" + b);
	}

	public static void main(String[] args)
	{
		swap(3, 5);
	}
}
