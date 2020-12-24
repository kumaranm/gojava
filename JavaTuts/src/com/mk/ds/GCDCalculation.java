package com.mk.ds;

public class GCDCalculation {

	public static void main(String[] args) {

		GCDCalculation gcd = new GCDCalculation();

		gcd.calcGCD(9, 727);
	}

	private void calcGCD(int i, int j) {
		System.out.println(gcd(i,j));
	}

	private int gcd(int a, int b) {

		if (b == 0) {
			return a;
		} else
			return gcd(b, a % b);
	}
}
