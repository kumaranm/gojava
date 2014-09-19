package com.mk.pattern.builder;

//2. THE BUILDER METHOD WILL ADD 
//INGREDIENTS RETURNING THE BUILDER ITSELF
public interface Recipe < B > {
    B addIngredient(Ingredient ingredient);
}