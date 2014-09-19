package com.mk.pattern.builder;

import java.util.List;

public class Cake {
	public Cake(List<Ingredient> ingredients) {
		String muffin = "";
		if (ingredients == null) {
			System.out.println(" zero cake " + muffin);
			return;
		}
		// PRINT OUT MUFFIN INGREDIENTS
		System.out.printf(" Cake with: ");
		for (Ingredient ingredient : ingredients) {
			ingredient.printName();
		}
		// PRINT OUT PART PRICES
		for (Ingredient ingredient : ingredients) {
			muffin += " " + ingredient.getUnitPrice();// NOPMD
		}
		System.out.println(" - Price: " + muffin);
	}

	public void printResult() {
		System.out.println(" Cake is ready!");
	}
}