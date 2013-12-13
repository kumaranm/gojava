package com.mk.problem.problem3;


public class P3Main
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Loader l = new Loader();
		l.loadFromTalksPlannedFile("talks_planned.txt");
		l.organizeTracks(Conference.getTalks());
	}

}
