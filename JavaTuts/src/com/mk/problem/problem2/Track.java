package com.mk.problem.problem2;

public class Track
{
	private String trackName;
	private FirstSession firstSession;
	private SecondSession secondSession;

	public Track(String name, FirstSession fs, SecondSession ss) {
		this.trackName = name;
		this.firstSession = fs;
		this.secondSession = ss;
	}

	public String getTrackName()
	{
		return trackName;
	}

	public void setTrackName(String trackName)
	{
		this.trackName = trackName;
	}

	public FirstSession getFirstSession()
	{
		return firstSession;
	}

	public void setFirstSession(FirstSession firstSession)
	{
		this.firstSession = firstSession;
	}

	public SecondSession getSecondSession()
	{
		return secondSession;
	}

	public void setSecondSession(SecondSession secondSession)
	{
		this.secondSession = secondSession;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(getTrackName());
		sb.append(": \n");
		sb.append(getFirstSession());
		sb.append(getSecondSession());
		sb.append("\n");
		return sb.toString();
	}
}
