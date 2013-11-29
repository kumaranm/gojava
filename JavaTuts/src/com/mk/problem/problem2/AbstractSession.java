package com.mk.problem.problem2;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSession
{
	private List<Talk> talks = new ArrayList<>(5);
	private int allotedTime;

	public void addTalk(Talk talk)
	{
		talks.add(talk);
	}

	public List<Talk> getTalks()
	{
		return talks;
	}

	public int getAllotedTime()
	{
		return allotedTime;
	}

	public void setAllotedTime(int allotedTime)
	{
		this.allotedTime = allotedTime;
	}
}
