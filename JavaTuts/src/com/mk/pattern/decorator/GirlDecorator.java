package com.mk.pattern.decorator;

public abstract class GirlDecorator implements Girl {
	protected Girl girl;

	public GirlDecorator(Girl girl) {
		super();
		this.girl = girl;
	}
}