package com.mk.programiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PhoneNode {
	
	String alphabet;
	List<PhoneNode> child;
	
	PhoneNode(String str){
		alphabet = str;
		child = null;
	}
	
	PhoneNode(PhoneNode parent, String str){
		if(parent == null){
			child = new ArrayList<>();
			for (int i = 0; i < str.length(); i++) {
				child.add(new PhoneNode("" + str.charAt(i)));
			}
		}else {
			if(child != null) {
				for (PhoneNode phoneNode : child) {
					for (int i = 0; i < str.length(); i++) {
						phoneNode.child.add(new PhoneNode("" + str.charAt(i)));
					}
				}
			}
		}
	}
}

public class PhoneLetterCombination {

	/**
	 * 
	 * Input: digits = "23" Output:
	 * ["ad","ae","af","bd","be","bf","cd","ce","cf"]
	 * 
	 * @param digits
	 * @return
	 */
	

	
	public List<String> letterCombinations(String digits) {

		List<String> combos = new ArrayList<String>();
		Map<Integer, String> dict = new HashMap<Integer, String>();
		dict.put(2, "abc");
		dict.put(3, "def");
		dict.put(4, "ghi");
		dict.put(5, "jkl");
		dict.put(6, "mno");
		dict.put(7, "pqrs");
		dict.put(8, "tuv");
		dict.put(9, "wxyz");

		int len = digits.length();
		int firstlen = dict.get(digits.charAt(0)).length();
		
		
		PhoneNode head = new PhoneNode("");
		for (int i = 0; i < digits.length(); i++) {
			String str = dict.get(digits.charAt(i));
			PhoneNode node =  new PhoneNode(head,str);
			
		}
		
		
		return combos;
	}
	

	public static void main(String[] args) {

	}

}
