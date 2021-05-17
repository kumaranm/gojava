package com.mk.programiz;

public class GenericExample {
	public static void main(String[] args) {
		String eejits = get();
		System.out.println(eejits);

		Integer knobends = get(); // Throws a ClassCastException.
	}

	private static <T> T get() {
		return (T) "I love to downvote without explaining why";
	}
}