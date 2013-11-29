package com.mk.problem.problem2;

import java.util.ArrayList;
import java.util.List;

public class Conference
{

	private static List<Talk> talks = new ArrayList<>(20);
	private static List<Track> tracks = new ArrayList<>(3);

	private static long totalMinutes = 0;

	public static void addTalk(final Talk talk)
	{
		talks.add(talk);
		totalMinutes += talk.getDuration();
	}

	public static void addTrack(Track track)
	{
		tracks.add(track);
	}

	public static List<Talk> getTalks()
	{
		return talks;
	}

	public static List<Track> getTracks()
	{
		return tracks;
	}

	public static long getTotalMinutes()
	{
		return totalMinutes;
	}

}
