package com.mk.programiz;

public class ReverseNumber {

	/**
	 * Working Submitted
	 * 
	 * @param x
	 * @return
	 */
	public int reverse(int x) {

		int res = 0;
		while (x != 0) {
			if (res < Integer.MIN_VALUE / 10 || res > Integer.MAX_VALUE / 10) {
				res = 0;
				break;
			}
			res = (res * 10) + x % 10;
			x = x / 10;
		}

		return res;
	}

	public boolean isPalindrome(int x) {

		int res = 0;
		int actual = x;
		while (x != 0) {
			res = (res * 10) + x % 10;
			x = x / 10;
		}

		if (actual >= 0 && res == actual) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {

		ReverseNumber n = new ReverseNumber();

		int s = 123;
		System.out.printf("%d -> %d ", s, n.reverse(s));

		s = 1534236489;
		System.out.printf("\n%d -> %d ", s, n.reverse(s));

		s = -153423646;
		System.out.printf("\n%d -> %d ", s, n.reverse(s));

		s = 121;
		System.out.printf("\n%d -> %b ", s, n.isPalindrome(s));

		s = -121;
		System.out.printf("\n%d -> %b ", s, n.isPalindrome(s));
	}

}
