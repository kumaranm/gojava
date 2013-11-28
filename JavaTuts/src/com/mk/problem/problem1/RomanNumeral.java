package com.mk.problem.problem1;

public enum RomanNumeral {
	I(1), V(5), X(10), L(50), C(100), D(500), M(1000);

	private int value;

	private RomanNumeral(int i) {
		this.value = i;
	}

	public int value()
	{
		return this.value;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder(this.name());
		sb.append("(").append(this.value).append(")");
		return sb.toString();
	}
}
