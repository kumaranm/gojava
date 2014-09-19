package com.mk.pattern.decorator;

public class EuropeanGirl implements Girl {
	
	private String description = "";

	public EuropeanGirl() {
		super();
		description = "+European";
	}

	@Override
	public String getDescription() {
		return description;
	}
}