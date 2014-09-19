package com.mk.pattern.decorator;

public class Science extends GirlDecorator {

	public Science(Girl girl) {
		super(girl);
	}

	@Override
	public String getDescription() {
		// DECORATES WITH A SCIENCE'S DEGREE
		return girl.getDescription() + "+ Like Science";
	}

	public void calculateStuff() {
		// ADDS NEW FEATURES (METHOD) TO IT
		System.out.println("scientific calculation!");
	}
}