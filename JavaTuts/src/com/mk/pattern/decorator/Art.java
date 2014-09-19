package com.mk.pattern.decorator;

public class Art extends GirlDecorator {
	
	public Art(Girl girl) {
		super(girl);
	}

	@Override
	public String getDescription() {
		return girl.getDescription() + "+Like Art";
	}

	public void draw() {
		System.out.println("draw pictures!");
	}
}