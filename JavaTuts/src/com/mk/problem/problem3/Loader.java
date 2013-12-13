package com.mk.problem.problem3;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mk.problem.problem2.Time.TOD;

public class Loader
{
	final Time startofDay = new Time(9, 0, TOD.AM);
	final Time lunchTimeStart = new Time(1, 0, TOD.PM);
	final Time lunchTimeEnd = new Time(2, 0, TOD.PM);
	final Time optionalEndOfDay = new Time(4, 0, TOD.PM);
	final Time endOfDay = new Time(5, 0, TOD.PM);
	int index = 1;

	public void loadFromTalksPlannedFile(String pathname)
	{
		try (Scanner br = new Scanner(new InputStreamReader(Loader.class.getResourceAsStream(pathname))))
		{
			String input = br.nextLine();

			while (input != null)
			{
				input = input.trim();
				if (input.startsWith("#"))
				{
					if (br.hasNext())
					{
						input = br.nextLine();
						continue;
					}
					else
					{
						break;
					}
				}
				String title = null;
				String duration = null;
				if (input.endsWith("min"))
				{
					title = new String(input.substring(0, input.lastIndexOf(" ")));
					duration = new String(input.substring(input.lastIndexOf(" ") + 1, input.lastIndexOf("min")));
					// System.out.println(title +" - " + duration);
					Conference.addTalk(new Talk(title, Integer.valueOf(duration)));
				}
				else if (input.endsWith("lightning"))
				{
					title = new String(input.substring(0, input.lastIndexOf(" ")));
					duration = new String("5");
					// System.out.println(title +" - " + duration);
					Conference.addTalk(new Talk(title, Integer.valueOf(duration)));
				}
				else
				{
					// ignore invalid entries
					// throw new
					// RuntimeException("Invalid mapping record in mapping file");
				}
				if (br.hasNext())
				{
					input = br.nextLine();
				}
				else
				{
					break;
				}
			}
		}
	}

	public void organizeTracks(List<Talk> unassignedTalks)
	{
		// long timeAllotted = 0;
		List<Talk> assignedTalks = new ArrayList<>();
		FirstSession fs = new FirstSession();
		SecondSession ss = new SecondSession();
		for (Talk talk : unassignedTalks)
		{
			if (fs.getAllotedTime() < FirstSession.getDuration())
			{
				if ((fs.getAllotedTime() + talk.getDuration()) <= FirstSession.getDuration())
				{
					fs.setAllotedTime(fs.getAllotedTime() + talk.getDuration());
					fs.addTalk(talk);
					assignedTalks.add(talk);
					// timeAllotted += talk.getDuration();
					continue;
				}
			}
			if (ss.getAllotedTime() < SecondSession.getDuration())
			{
				if ((ss.getAllotedTime() + talk.getDuration()) <= SecondSession.getDuration())
				{
					ss.setAllotedTime(ss.getAllotedTime() + talk.getDuration());
					ss.addTalk(talk);
					assignedTalks.add(talk);
					// timeAllotted += talk.getDuration();
					continue;
				}
			}
			if (fs.getAllotedTime() == FirstSession.getDuration() && ss.getAllotedTime() == SecondSession.getDuration())
			{
				break;
			}
		}
		// System.out.println(timeAllotted);
		Conference.addTrack(new Track("Track " + index, fs, ss));
		index++;
		unassignedTalks.removeAll(assignedTalks);
		if (unassignedTalks.size() > 0)
		{
			organizeTracks(unassignedTalks);
		}
	}

}
