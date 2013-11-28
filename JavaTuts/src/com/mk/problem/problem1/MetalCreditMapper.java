package com.mk.problem.problem1;

import java.util.HashMap;
import java.util.Map;

public class MetalCreditMapper
{
	private static Map<Metal, Long> map = new HashMap<>(3);

	public static void add(Metal metal, double cost)
	{
		if (metal == null || cost <= 0)
		{
			throw new RuntimeException("Invalid Mapping specified. Please check content");
		}
		else
		{
			map.put(metal, (long) (cost * 100));
		}
	}

	public static Long get(Metal metal)
	{
		return map.get(metal);
	}

	public static Map<Metal, Long> getMap()
	{
		return map;
	}
}
