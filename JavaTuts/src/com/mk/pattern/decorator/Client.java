package com.mk.pattern.decorator;

public class Client {
	
	public static void main(String[] args) {
		// COMMOM GIRL
		Girl girl;

		// CREATING NORMAL AMERICAN GIRL
		girl = new AmericanGirl();
		System.out.println(girl.getDescription());

		// DECORANTING AMERICANA GIRL WITH SCIENCE'S DEGREE
		girl = new Science(girl);
		System.out.println(girl.getDescription());

		// DECORANTING AMERICANA GIRL WITH ART'S DEGREE
		girl = new Art(girl);
		System.out.println(girl.getDescription());

		// EUROPEAN GIRL HAS ALREADY ALL DEGREES
		Girl europeia = new Science(new Art(new EuropeanGirl()));
		System.out.println(europeia.getDescription());

		// DOCTOR HAS NEW FUNCTIONS
		girl = new Doctor(girl);
		System.out.println(girl.getDescription());
		// BECAUSE DOCTOR EXTENDS FROM COMMON GIRL, IT CAN DO A DOWNCAST
		((Doctor) girl).doctorTitle();
		((Doctor) girl).calculateStuff();

		// PAY ATTENTION THAT WE USE THE SAME INSTANCE, BUT THEY BEHAVIOR
		// DIFFERENT
		// AT DIFFERENT TIME SLOTS. THE CLIENT HAS THE IMPRESSION THAT WE HAVE
		// CHANGED THE IMPLEMENTATION, BUT IN FACT NOT.
	}
}