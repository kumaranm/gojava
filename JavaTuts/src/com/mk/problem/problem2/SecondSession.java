package com.mk.problem.problem2;

import com.mk.problem.problem2.Time.TOD;

public class SecondSession extends AbstractSession
{
	private static Time start = new Time(2, 0, TOD.PM);
	private static Time tempEnd = new Time(4, 0, TOD.PM);
	private static Time end = new Time(5, 0, TOD.PM);
	private static int duration = end.difference(start);

	public static Time getStart()
	{
		return start;
	}

	public static Time getEnd()
	{
		return end;
	}

	public static int getDuration()
	{
		return duration;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder("\n");
		Time temp = start;
		for (Talk talk : getTalks())
		{
			sb.append(temp).append(" ").append(talk.getTitle()).append(" ").append(talk.getDuration()).append("min");
			sb.append("\n");
			temp = Time.getNextSlot(temp, talk.getDuration());
		}
		if (temp.isBefore(tempEnd))
		{
			sb.append(tempEnd).append(" Networking Event");
		}
		else
		{
			sb.append(temp).append(" Networking Event");
		}
		return sb.toString();
	}

}
