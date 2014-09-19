package com.mk.pattern.decorator;

public abstract class Decorator implements Decorable {
	protected Decorable component;

	public Decorator(Decorable component) {
		super();
		this.component = component;
	}
}