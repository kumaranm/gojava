package com.mk.programiz;

import java.util.Arrays;

public class LongestCommonPrefix {

	/**
	 * Write a function to find the longest common prefix string amongst an
	 * array of strings.
	 * 
	 * If there is no common prefix, return an empty string "".
	 * 
	 * Input: strs = ["flower","flow","flight"] Output: "fl"
	 * 
	 * @param strs
	 * @return
	 */
	public String longestCommonPrefix(String[] strs) {

		if (strs.length == 0) {
			return "";
		}

		// use sliding window approach with widow 1
		// set first string as prefix
		// for every string compare with prefix and make prefix smaller
		// if prefix becomes 0, quit loop return ""
		// if prefix matches move to next string
		int k = 1;
		String prefix = strs[0];

		int prefixLen = prefix.length();
		boolean isMatch = true;
		for (int i = k; i < strs.length; i++) {

			while (!strs[i].startsWith(prefix) && isMatch) {
				if (--prefixLen < 0) {
					isMatch = false;
					break;
				}
				prefix = prefix.substring(0, prefixLen);
			}
			if (!isMatch) {
				break;
			}
		}
		if (prefixLen < 0) {
			prefix = "";
		}
		return prefix;
	}

	public static void main(String[] args) {
		LongestCommonPrefix p = new LongestCommonPrefix();

		String[] str = new String[] { "flower", "flow", "flight" };
		System.out.printf("\n %s -> %s", p.longestCommonPrefix(str), Arrays.toString(str));

		str = new String[] {"", ""};
		System.out.printf("\n %s -> %s", p.longestCommonPrefix(str), Arrays.toString(str));
	}

}
