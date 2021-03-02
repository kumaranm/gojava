package com.mk.programiz;

class StarBucks {

	private String size;
	private String drink;

	public void setSize(String size) {
		this.size = size;
	}

	public void setDrink(String drink) {
		this.drink = drink;
	}

	public String getDrink() {
		return this.drink;
	}

	public String getSize() {
		return this.size;
	}
}

abstract class StarbucksBuilder {
	protected StarBucks starBucks;

	public StarBucks getStarBucks() {
		return starBucks;
	}

	public void createStarBucks() {
		starBucks = new StarBucks();
		System.out.println("A drink is created");
	}

	public abstract void buildSize();

	public abstract void buildDrink();
}

class CoffeeBuilder extends StarbucksBuilder {

	@Override
	public void buildSize() {
		starBucks.setSize("large");
		System.out.println("build large size");
	}

	@Override
	public void buildDrink() {
		starBucks.setDrink("coffee");
		System.out.println("build coffee");
	}

}

class TeaBuilder extends StarbucksBuilder {

	@Override
	public void buildSize() {
		starBucks.setSize("medium");
		System.out.println("build medium size");
	}

	@Override
	public void buildDrink() {
		starBucks.setDrink("tea");
		System.out.println("build tea");
	}

}

class Waiter {
	private StarbucksBuilder builder;

	public void setStarBucksBuilder(StarbucksBuilder builder) {
		this.builder = builder;
	}

	public StarBucks getStarBucksDrink() {
		return builder.getStarBucks();
	}

	public void constructStarBucks() {
		builder.createStarBucks();
		builder.buildDrink();
		builder.buildSize();
	}
}

public class CreateDrinkBuilder {

	public static void main(String[] args) {
		Waiter w = new Waiter();
		w.setStarBucksBuilder(new CoffeeBuilder());
		w.constructStarBucks();

		StarBucks drink = w.getStarBucksDrink();
		System.out.println(drink.getDrink());
	}
}
