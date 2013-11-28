package com.mk.problem.problem1;

public class Interpretor
{
	public static void createExpression(String[] split)
	{
		Metal a = null;
		StringBuilder xRoman = new StringBuilder();
		long y = 0L;
		boolean allowCode = true;
		boolean allowMetal = true;
		boolean isCredit = false;
		for (int i = 0; i < split.length; i++)
		{
			if (allowCode && CodeMapper.getMap().containsKey(split[i]))
			{
				xRoman.append(CodeMapper.getMap().get(split[i]).name());
			}
			else if (allowMetal && Metal.valueOf(split[i].toUpperCase()) != null)
			{
				allowCode = false;
				a = Metal.valueOf(split[i].toUpperCase());
				allowMetal = false;
			}
			else if (!allowCode && !allowMetal && split[i].equals("is"))
			{
				isCredit = true;
				continue;
			}
			else if (isCredit)
			{
				y = Long.valueOf(split[i]);
				break;
			}
		}
//		System.out.println(xRoman);
//		System.out.println(a);
//		System.out.println(y);

		Long romanValue = convertRomanToLong(xRoman);
//		System.out.println(romanValue);
		double cost = (double) y / romanValue;
//		System.out.println(cost);
		MetalCreditMapper.add(a, cost);
	}

	public static Long convertRomanToLong(StringBuilder xRoman)
	{
		Long l = 0L;
		validate(xRoman);
		for (int i = 0; i < xRoman.length(); i++)
		{
			int s1 = RomanNumeral.valueOf("" + xRoman.charAt(i)).value();
			String i1 = RomanNumeral.valueOf("" + xRoman.charAt(i)).name();
			int s2 = 0;
			String i2 = "";
			if (i != xRoman.length() - 1)
			{
				s2 = RomanNumeral.valueOf("" + xRoman.charAt(i + 1)).value();
				i2 = RomanNumeral.valueOf("" + xRoman.charAt(i + 1)).name();
			}
			if (i == xRoman.length() - 1)
			{
				l += s1;
			}
			else if (s1 >= s2)
			{
				l += s1;
			}
			else if (s1 < s2)
			{
				validate(i1, i2);
				l += s2 - s1;
				i++;
			}
			else
			{
				throw new RuntimeException("invalid");
			}
		}
		return l;
	}

	public static void validate(String i1, String i2)
	{
		if (i1.equals("I") && !(i2.equals("V") || i2.equals("X")))
		{
			throw new RuntimeException("I can only be subtracted from V,X");
		}
		if (i1.equals("X") && !(i2.equals("L") || i2.equals("C")))
		{
			throw new RuntimeException("X can only be subtracted from L,C");
		}
		if (i1.equals("C") && !(i2.equals("D") || i2.equals("M")))
		{
			throw new RuntimeException("C can only be subtracted from D,M");
		}	
		if (i1.equals("V") || i1.equals("L") || i1.equals("D"))
		{
			throw new RuntimeException("V, L, D can never be subtracted");
		}
	}

	public static void validate(StringBuilder xRoman)
	{
		if (xRoman.indexOf("III") > 0 || xRoman.indexOf("XXX") > 0 || xRoman.indexOf("CCC") > 0
				|| xRoman.indexOf("MMM") > 0 || xRoman.indexOf("DD") > 0 || xRoman.indexOf("LL") > 0
				|| xRoman.indexOf("VV") > 0)
		{
			throw new RuntimeException("Roman Numeral validation failed");
		}
	}
}
