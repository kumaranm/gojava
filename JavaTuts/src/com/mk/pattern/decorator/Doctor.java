package com.mk.pattern.decorator;

public class Doctor extends GirlDecorator {
	
	public Doctor(Girl girl) {
		super(girl);
	}

	@Override
	public String getDescription() {
		return girl.getDescription() + "+Like Doctor";
	}

	public void calculateStuff() {
		System.out.println("doctor calculation!");
	}

	public void doctorTitle() {
		System.out.println("doctor title");
	}
}