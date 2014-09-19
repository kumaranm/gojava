package com.mk.pattern.builder;

import java.util.ArrayList;
import java.util.List;

//4. IMPLEMENT THE BUILDER ACC. TO YOUR NEE
public class CakeBuilder implements Builder<Cake> {
	// IN THIS CASE THE PARTS ARE THE INGREDIENTS
	private List<Ingredient> ingredients = new ArrayList<Ingredient>();

	@Override
	public Cake build() {
		if (!ingredients.isEmpty()) {
			// THE FINAL PRODUCT IS A CHOCO-MUFFIN
			return new Cake(ingredients);
		}
		return new Cake(null);
	}

	@Override
	// BECAUSE I ALWAYS GET A BUILDER BACK, I'M ABLE TO
	// ADD A LOT OF PARTS BEFORE I CALL "BUILD()"
	public Builder<Cake> addIngredient(Ingredient ingredient) {
		if (ingredient != null) {
			ingredients.add(ingredient);
		}
		return this;
	}
}