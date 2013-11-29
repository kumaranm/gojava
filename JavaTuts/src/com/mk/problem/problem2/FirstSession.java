package com.mk.problem.problem2;

import com.mk.problem.problem2.Time.TOD;

public class FirstSession extends AbstractSession
{
	private static Time start = new Time(9, 0, TOD.AM);
	private static Time end = new Time(1, 0, TOD.PM);
	private static int duration =  end.difference(start);

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
		StringBuilder sb = new StringBuilder();
		Time temp = start;
		for (Talk talk : getTalks())
		{
			sb.append(temp).append(" ").append(talk.getTitle()).append(" ").append(talk.getDuration()).append("min");
			sb.append("\n");
			temp = Time.getNextSlot(temp, talk.getDuration());
		}
		sb.append(end).append(" Lunch");
		return sb.toString();
	}
}
