package com.mk.programiz;

import java.util.ArrayDeque;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class RegExpMatching {

	public boolean isMatch1(String s, String p) {
		// . -> any character
		// * -> 0 or more
		int index = 0;
		int i = 0;
		for (i = 0; i < p.length();) {

			if (index >= s.length()) {
				if (p.contains("*") && s.charAt(index - 1) == p.charAt(i)) {
					i++;
					continue;
				} else {
					return false;
				}
			}

			char t = p.charAt(i);
			char t1 = i + 1 == p.length() ? ' ' : p.charAt(i + 1);
			if (t == '.') {
				if (t1 == '*') {
					if (i + 2 < p.length()) {
						boolean isMatched = true;
						while (isMatched) {
							if (index >= s.length() || s.charAt(index) == p.charAt(i + 2)) {
								isMatched = false;
								i++;
							} else {
								index++;
							}
						}
					} else {
						return true;
					}
				} else {
					index++; // move to next position in string
				}
				i++;
			} else if ((t != '.' && t != '*')) {
				if (t1 == '*') {
					// str and pattern should match
					boolean isMatched = true;
					while (isMatched) {
						// since 0 or more occurrence
						if (index >= s.length() || !(s.charAt(index) == p.charAt(i))) {
							isMatched = false;
						} else {
							index++;
							// return true;
						}
					}
					i++;
					i++;
				} else {
					// single char match
					if (!(s.charAt(index) == p.charAt(i))) {
						return false;
					}
					index++;
					i++;
				}
			}
		}
		if (index < s.length() || i < p.length()) {
			return false;
		}
		return true;
	}

	public boolean isMatch2(String s, String p) {

		// first break pattern to find characters that must occur vs 0 or more
		// vs 1 char
		Map<Integer, String> rmap = new LinkedHashMap<>();
		Map<Integer, String> nrmap = new LinkedHashMap<>();
		String newp = "";
		for (int i = 0; i < p.length(); i++) {
			char t = p.charAt(i);
			if (p.charAt(i) == '.') {
				if (i + 1 < p.length() && p.charAt(i + 1) == '*') {
					newp += String.valueOf(0) + " ";
					rmap.put(i, String.valueOf(t));
					i++;
				} else {
					newp += String.valueOf(1);
					nrmap.put(i, String.valueOf(t));
				}
			} else {
				// not .
				if (i + 1 < p.length() && p.charAt(i + 1) == '*') {
					newp += String.valueOf(0) + " ";
					rmap.put(i, String.valueOf(t));
					i++;
				} else {
					nrmap.put(i, String.valueOf(t));
					newp += String.valueOf(t);
				}
			}
		}
		// System.out.print("\nnew pattern - " + newp);
		// System.out.print(" -- repeated map" + rmap);

		int index = 0;
		int i = 0;
		for (i = 0; i < newp.length();) {

			if (index >= s.length()) {
				if (nrmap.size() > 0) {
					return false;
				} else if (newp.substring(i).contains("1")) {
					return false;
				}
				return true;
			}
			char t = newp.charAt(i);

			if (t == ' ') {
				continue;
			} else if (t == '1') {
				// single occurrence any char
				nrmap.remove(i);
				index++;
				i++;
			} else if (t == '0') {
				// multiple occurrences till next static char
				int start = index;
				int end = i; // get from pattern

				char rchar = rmap.get(i).charAt(0);
				rmap.remove(i);

				if (!rmap.isEmpty()) {
					Entry<Integer, String> next = rmap.entrySet().iterator().next();
					end = next.getKey();
					// int tend = newp.indexOf(' ', i);
					// end = tend == -1? end : end > tend + 1 ? tend + 1 : end ;

				} else {
					// find last occurence of char
					// end = newp.indexOf(' ', i) == -1? newp.length() :
					// newp.indexOf(' ', i) + 1;
					end = newp.length();
				}

				if (!nrmap.isEmpty()) {
					// Entry<Integer, String> next =
					// nrmap.entrySet().iterator().next();
					end = s.length() - nrmap.size();
				}
				boolean isMatched = true;

				if (s.substring(index).length() == nrmap.size()) {
					isMatched = false;
				}

				while (index < end && isMatched) {

					if (rchar != '.' && !(s.charAt(index) == rchar)) {
						isMatched = false;
					} else {
						index++;
					}
				}
				i++;
				i++;
			} else {
				// match char
				if (!(s.charAt(index) == t)) {
					return false;
				}
				nrmap.remove(i);
				index++;
				i++;
			}
		}

		if (index < s.length() && !p.equals(".*")) {
			return false;
		}
		return true;
	}

	public boolean isMatch(String text, String pattern) {
		if (pattern.isEmpty())
			return text.isEmpty();
		boolean first_match = (!text.isEmpty() && (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

		if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
			return (isMatch(text, pattern.substring(2)) || (first_match && isMatch(text.substring(1), pattern)));
		} else {
			return first_match && isMatch(text.substring(1), pattern.substring(1));
		}
	}

	public void checkMatch(String s, String p) {
		System.out.printf("%s matches %s -> %b", s, p, isMatch(s, p));
	}

	public static void main(String[] args) {
		RegExpMatching matcher = new RegExpMatching();

		String s = "aa";
		String p = "a";
		System.out.printf("%s matches %s -> %b vs %b", s, p, matcher.isMatch(s, p), s.matches(p));

		s = "aa";
		p = "a*";
		System.out.printf("\n%s matches %s -> %b vs %b", s, p, matcher.isMatch(s, p), s.matches(p));

		s = "a";
		p = "ab*";
		System.out.printf("\n%s matches %s -> %b vs %b", s, p, matcher.isMatch(s, p), s.matches(p));

		s = "aaa";
		p = ".*";
		System.out.printf("\n%s matches %s -> %b vs %b", s, p, matcher.isMatch(s, p), s.matches(p));

		s = "aab";
		p = "c*a*b";
		System.out.printf("\n%s matches %s -> %b vs %b", s, p, matcher.isMatch(s, p), s.matches(p));

		s = "mississippi";
		p = "mis*is*p*."; // mi0i001
		System.out.printf("\n%s matches %s -> %b vs %b", s, p, matcher.isMatch(s, p), s.matches(p));

		s = "abc";
		p = ".*c";
		System.out.printf("\n%s matches %s -> %b vs %b", s, p, matcher.isMatch(s, p), s.matches(p));

		s = "aaa";
		p = "a*a";
		System.out.printf("\n%s matches %s -> %b vs %b", s, p, matcher.isMatch(s, p), s.matches(p));

		s = "aaa";
		p = "aaaa";
		System.out.printf("\n%s matches %s -> %b vs %b", s, p, matcher.isMatch(s, p), s.matches(p));

		s = "aaa";
		p = "ab*a*c*a"; // a000a
		System.out.printf("\n%s matches %s -> %b vs %b", s, p, matcher.isMatch(s, p), s.matches(p));

		s = "ab";
		p = ".*..";
		System.out.printf("\n%s matches %s -> %b vs %b", s, p, matcher.isMatch(s, p), s.matches(p));

		s = "aasdfasdfasdfasdfas";
		p = "aasdf.*asdf.*asdf.*asdf.*s"; // true
		System.out.printf("\n%s matches %s -> %b vs %b", s, p, matcher.isMatch(s, p), s.matches(p));
	}

}
