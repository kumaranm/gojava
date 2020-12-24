package com.mk.programiz;

import java.util.Stack;

public class SStack {

	public static void main(String[] args) {

		Stack<Integer> st = new Stack<>();

		st.push(1);
		st.push(2);
		st.push(3);
		st.push(4);
		st.push(5);

		System.out.println(st.toString());
		st.pop();
		System.out.println(st.toString());
	}
}
