package com.mk.solutions;

public class BitWise
{
	public static void main(String[] args)
	{
		Integer aa = -3;//Integer.MAX_VALUE;
		
		System.out.println(aa);
		System.out.println(Integer.toBinaryString(aa));
		
		int no = 2; //0010
	    
		//example of bitwise unary complement operator (~)
		System.out.println(" value of number before: " + no);
		System.out.println(" value of number after negation: " + ~no);

		int a = 2; //0010
		int b = 4; //0100

		//example of bitwise AND operator &
		System.out.println("Result of a&b  is " + (a&b));  //should be zero
		System.out.println(" value of A bitwise OR B in Java : " + (a|b) );
		System.out.println(" value of a XOR B in Java : " + (a^b) );

		
		int number = 8; //0000 1000
	     System.out.println("Original number : " + number);
	   
	     //left shifting bytes with 1 position
	     number = number<<1; //should be 16 i.e. 0001 0000

	     //equivalent of multiplication of 2
	     System.out.println("value of number after left shift: " + number);
	   
	     number = -8;
	     //right shifting bytes with sign 1 position
	     number = number>>1; //should be 16 i.e. 0001 0000

	     //equivalent of division of 2
	     System.out.println("value of number after right shift with sign: " + number);
	   
	     number = -8;
	     //right shifting bytes without sign 1 position
	     number = number>>>1; //should be 16 i.e. 0001 0000

	     System.out.println("value of number after right shift without sign: " + number);

	}
}
