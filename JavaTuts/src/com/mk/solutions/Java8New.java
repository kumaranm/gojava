package com.mk.solutions;

import java.math.BigInteger;
import static java.lang.System.out;

public class Java8New
{

	public static void main(String[] args)
	{
		final BigInteger byteMax = new BigInteger(String.valueOf(Byte.MAX_VALUE));
		out.println("Byte Max: " + byteMax.byteValue());
	}
}
