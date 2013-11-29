package com.mk.problem.problem2;

public class Time
{

	private int hour;
	private int min;
	private TOD tod;
	private int minutes;

	private static final int MINUTES_IN_HOUR = 60;

	public Time(int hour, int min, TOD tod) {
		if (hour < 1 || hour > 12 || min < 0 || min > 59 || tod == null)
		{
			throw new RuntimeException("invalid time");
		}
		this.hour = hour;
		this.min = min;
		this.tod = tod;
		this.minutes = calcMinutes(this);
	}

	public int getHour()
	{
		return hour;
	}

	public int getMin()
	{
		return min;
	}

	public TOD getTod()
	{
		return tod;
	}

	public int getMinutes()
	{
		return minutes;
	}

	enum TOD {
		AM, PM;
	}

	public static Time getNextSlot(Time t, int minutes)
	{
		return getTime(t.getMinutes() + minutes);
	}

	private int calcMinutes(Time t)
	{
		if (t.hour == 12 && t.tod == TOD.AM)
		{
			return t.min;
		}
		else if (t.hour == 12 && t.tod == TOD.PM)
		{
			return t.hour * MINUTES_IN_HOUR + t.min;
		}
		return t.hour * MINUTES_IN_HOUR + t.min + (t.tod == TOD.AM ? 0 : 12 * MINUTES_IN_HOUR);
	}

	public int difference(Time t)
	{
		if (this.isAfter(t))
		{
			return minutes - calcMinutes(t);
		}
		else if (this.isBefore(t))
		{
			return calcMinutes(t) - minutes;
		}
		return 0;
	}

	public boolean isBefore(Time t2)
	{
		return calcMinutes(this) < calcMinutes(t2);
	}

	public boolean isAfter(Time t2)
	{
		return calcMinutes(this) > calcMinutes(t2);
	}

	public boolean isEqual(Time t2)
	{
		return calcMinutes(this) == calcMinutes(t2);
	}

	public static Time getTime(int minutes)
	{
		int hours = 0;
		int min = 0;
		TOD tod = TOD.AM;
		if (minutes < MINUTES_IN_HOUR)
		{
			tod = TOD.AM;
			hours = 12;
			min = minutes;
		}
		else if (minutes < 12 * MINUTES_IN_HOUR)
		{
			tod = TOD.AM;
			hours = minutes / MINUTES_IN_HOUR;
			min = minutes % MINUTES_IN_HOUR;
		}
		else if (minutes > 12 * MINUTES_IN_HOUR)
		{
			tod = TOD.PM;
			minutes = minutes - 12 * MINUTES_IN_HOUR;
			if (minutes < MINUTES_IN_HOUR)
			{
				tod = TOD.PM;
				hours = 12;
				min = minutes;
			}
			else
			{
				hours = minutes / MINUTES_IN_HOUR;
				min = minutes % MINUTES_IN_HOUR;
			}
		}
		else
		{
			tod = TOD.PM;
			hours = 12;
			minutes = 0;
		}
		return new Time(hours, min, tod);
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		if (hour < 10)
		{
			sb.append("0");
		}
		sb.append(hour);
		sb.append(":");
		if (min < 10)
		{
			sb.append("0");
		}
		sb.append(min);
		sb.append(tod.name());
		return sb.toString();
	}

	public static Time getNextTimeSlot(Time t, int minutes)
	{
		int h = 0;
		int m = 0;
		TOD d = t.getTod();
		if (minutes < 0 || minutes > 60)
		{
			throw new RuntimeException("");
		}
		if (minutes == 60)
		{
			m = t.min;
			h = t.hour + 1;
			if (h == 12)
			{
				d = TOD.PM;
			}
			else if (h == 13)
			{
				h = 1;
				d = TOD.PM;
			}
		}
		else
		{
			m = (t.min + minutes) % 60;
			int r = (t.min + minutes) / 60;
			h = t.hour + r;
			if (h == 12)
			{
				d = TOD.PM;
			}
			else if (h == 13)
			{
				h = 1;
				d = TOD.PM;
			}
		}
		return new Time(h, m, d);
	}

	public static boolean isBefore(Time t1, Time t2)
	{
		if (t2.tod.ordinal() > t1.tod.ordinal())
		{
			return true;
		}
		else if (t2.getHour() > t1.getHour())
		{
			return true;
		}
		else if (t2.getMin() > t1.getMin())
		{
			return true;
		}
		return false;
	}

	public static boolean isAfter(Time t1, Time t2)
	{
		if (t1.tod.ordinal() < t2.tod.ordinal())
		{
			return false;
		}
		else if (t1.getHour() < t2.getHour())
		{
			return false;
		}
		else if (t1.getMin() < t2.getMin())
		{
			return false;
		}
		return true;
	}

	public static boolean equals(Time t1, Time t2)
	{
		if (t2.tod.equals(t1.tod) && t2.getHour() == t1.getHour() && t2.getMin() == t1.getMin())
		{
			return true;
		}
		return false;
	}
}
