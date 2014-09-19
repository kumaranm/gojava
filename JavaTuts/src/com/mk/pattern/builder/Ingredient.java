package com.mk.pattern.builder;

//1. EXAMPLE: PART TO CUSTOMIZATE "INGREDIENTS"
public interface Ingredient {
	// INGREDIENTS WILL HAVE...
	void printName();

	String getUnitPrice();

	void printCalories();
}