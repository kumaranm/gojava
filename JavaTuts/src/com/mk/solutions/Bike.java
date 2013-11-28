package com.mk.solutions;

class Bike extends Vehicle
{
	int speed;

	Bike()
	{
		this(23);
		System.out.println("in no arg constructor");
	}
	
	Bike(int speed) {
		super();
		this.speed = speed;
		System.out.println(speed);
	}
	
	void vargs(int... i)
	{
		
	}

	public static void main(String args[])
	{
		Bike b = new Bike(10);
		Bike b1 = new Bike();
	}
}