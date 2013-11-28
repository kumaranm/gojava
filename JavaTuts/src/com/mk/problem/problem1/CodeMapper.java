package com.mk.problem.problem1;

import java.util.HashMap;
import java.util.Map;

public class CodeMapper
{
	private static Map<String, RomanNumeral> map = new HashMap<>(6);

	public static void add(String code, String numeral)
	{
		if (code == null || code.trim().equals("") || numeral == null || numeral.trim().equals("")
				|| RomanNumeral.valueOf(numeral) == null)
		{
			throw new RuntimeException("Invalid Mapping specified. Please check content");
		}
		else
		{
			map.put(code, RomanNumeral.valueOf(numeral));
		}
	}

	public static RomanNumeral get(String code)
	{
		return map.get(code);
	}

	public static Map<String, RomanNumeral> getMap()
	{
		return map;
	}
}
