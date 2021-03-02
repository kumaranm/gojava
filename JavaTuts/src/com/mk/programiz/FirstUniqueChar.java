package com.mk.programiz;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class FirstUniqueChar {

	// find unique first character from a stream of characters

	// a b h 9 = # h c z 9 a b # c =

	// add the char to a set/map
	// on next char check if present in set/map
	// if present, char is not unique

	// order should be maintained

	// primaryHashMap-> a b h 9 = # c

	// uniqueHashMap-> a b c

	private HashMap<Character, Character> allCharsHashMap = new HashMap<>();
	private HashMap<Character, Character> uniqueCharsHashMap = new LinkedHashMap<>();

	void addToMap(Character val) {
		if (allCharsHashMap.containsKey(val)) {
			uniqueCharsHashMap.remove(val);
		} else {
			allCharsHashMap.put(val, val);
			uniqueCharsHashMap.put(val, val);
		}
	}

	Character getFirstUniqueChar() {
		for (Entry<Character, Character> data : uniqueCharsHashMap.entrySet()) {
			return data.getKey();
		}
		return new Character(' ');
	}

	public static void main(String[] args) {

		FirstUniqueChar fuis = new FirstUniqueChar();
		char[] charArray = { 'a', 'b', 'h', 'a', '=', '#', 'h', 'c', 'z', '9', 'a', 'b', '#', 'c', '=' };

		for (char a : charArray) {
			fuis.addToMap(a);
			System.out.print(fuis.getFirstUniqueChar() + " - ");
		}

	}

}
