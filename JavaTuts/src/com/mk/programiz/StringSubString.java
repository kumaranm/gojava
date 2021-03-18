package com.mk.programiz;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StringSubString {

	/**
	 * Working
	 * 
	 * @param s
	 * @return
	 */
	public static int lengthOfLongestSubstring1(String s) {

		char[] array = s.toCharArray();
		Map<Character, Integer> map = new HashMap<Character, Integer>();

		if (array.length <= 0) {
			return 0;
		}

		int[] l = new int[array.length];

		map.put(array[0], 0);
		l[0] = 1;
		int max = 1;

		for (int i = 1; i < array.length; i++) {

			if (array[i] == array[i - 1]) {
				// successive char
				l[i] = 1;
				map.clear();
				map.put(array[i], i);
			} else if (!map.containsKey(array[i])) {
				// distinct char
				l[i] = l[i - 1] + 1;
				map.put(array[i], i);
			} else if (map.containsKey(array[i])) {
				// non distinct
				int tmp = i - map.get(array[i]);
				l[i] = tmp >= l[i - 1] + 1 ? l[i - 1] + 1 : tmp;
				map.put(array[i], i);
			} else {
				System.out.println("unhandled usecase");
			}
			max = l[i] > max ? l[i] : max;
		}
		System.out.println(s + " - " + Arrays.toString(l));
		return max;
	}

	public static void main(String[] args) {

		System.out.println(StringSubString.lengthOfLongestSubstring1("abcabcbb"));
		System.out.println(StringSubString.lengthOfLongestSubstring1("pwwkew"));
		System.out.println(StringSubString.lengthOfLongestSubstring1("ckilbkd"));
		System.out.println(StringSubString.lengthOfLongestSubstring1("dvdf"));
		System.out.println(StringSubString.lengthOfLongestSubstring1("abba"));
		System.out.println(StringSubString.lengthOfLongestSubstring1("abbbbbbbbbbbbba"));
		System.out.println(StringSubString.lengthOfLongestSubstring1("abbbbbdddbbccbbba"));
		System.out.println(StringSubString.lengthOfLongestSubstring1("abbbbbbbbbbbbbca"));
		System.out.println(StringSubString.lengthOfLongestSubstring1("wobgrovw"));
		System.out.println(StringSubString.lengthOfLongestSubstring1("eeydgwdykpv"));//

	}
}
