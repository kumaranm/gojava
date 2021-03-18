package com.mk.programiz;

import java.util.ArrayDeque;

/*
 * String str = #A@FT%S#GHU; Reverse the string and maintain the index of the special characters
 */

public class ReverseString {

	public static void main(String[] args) {
		String[] input = new String[] { "#A@FT%S#GHU;", "#A%B", "KUMARAN", "829JUKS*II)(JS@#KKS" };

		for (String str : input) {
			System.out.println("String - " + str);
			System.out.println("Reversed String - " + ReverseString.reverse(str));
			ReverseString.reverse(str.toCharArray());
			System.out.println("Reversed String - " + String.valueOf(ReverseString.reverse(str.toCharArray())));
		}
	}

	private static char[] reverse(char[] c) {
		//single iteration
		int l = 0;
		int r = c.length - 1;

		while (l < r) {
			if (! (Character.isAlphabetic(c[l]) || Character.isDigit(c[l]))) {
				l++;
			} else if (!Character.isAlphabetic(c[r]) || Character.isDigit(c[r])) {
				r--;
			} else {
				char tmp = c[l];
				c[l] = c[r];
				c[r] = tmp;
				l++;
				r--;
			}
		}
		return c;
	}

	private static String reverse(String string) {
		ArrayDeque<Character> stack = new ArrayDeque<>();
		StringBuilder newString = new StringBuilder();

		for (Character str : string.toCharArray()) {
			if (Character.isLetter(str) || Character.isDigit(str)) {
				stack.push(str);
				newString.append(" ");
			} else {
				newString.append(str);
			}
		}
		String str = newString.toString();
		for (Character character : stack) {
			str = str.replaceFirst(" ", String.valueOf(character));
		}
		return str;
	}
}
