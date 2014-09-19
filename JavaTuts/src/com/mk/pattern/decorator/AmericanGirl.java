package com.mk.pattern.decorator;

public class AmericanGirl implements Girl {

	private String description = "";

	// NORMAL AMERICAN GIRL
	public AmericanGirl() {
		super();
		description = "+American";
	}

	@Override
	public String getDescription() {
		return description;
	}
}