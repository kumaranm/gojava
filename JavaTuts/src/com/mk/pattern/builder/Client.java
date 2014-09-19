package com.mk.pattern.builder;

public class Client {
	
	public static void main(String[] args) {
		Builder<Cake> chocoMuffinBuilder = new CakeBuilder();
		chocoMuffinBuilder.addIngredient(new Choco(10, 23, "3.39"));
		chocoMuffinBuilder.addIngredient(new Milk(34, 67, "1.57"));
		chocoMuffinBuilder.addIngredient(new Sugar(34, 67, "2.00"));
		final Cake chocoMuffin = chocoMuffinBuilder.build();
		chocoMuffin.printResult();
	}
}