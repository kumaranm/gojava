package com.mk.problem.problem2;


public class P2Main
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Loader l = new Loader();
		l.loadFromTalksPlannedFile("talks_planned.txt");
		l.organizeTracks(Conference.getTalks());
//		System.out.println(Conference.getTalks());
		System.out.println(Conference.getTracks());
//		System.out.println(Conference.getTotalMinutes());
//		System.out.println(Conference.getTracks());
		
//		int numberOfDays = 785 / (FirstSession.getDuration() + SecondSession.getDuration());
//		System.out.println(numberOfDays);
	}

}
