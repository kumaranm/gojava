package com.mk.programiz;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianMaintain {

	public static void printMedian(int[] a) {

		double med = a[0];

		PriorityQueue<Integer> smaller = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> greater = new PriorityQueue<>();

		smaller.add(a[0]);
		System.out.println(med);

		for (int i = 1; i < a.length; i++) {

			int x = a[i];

			// case 1 left side has more elements
			if (smaller.size() > greater.size()) {
				if (x < med) {
					greater.add(smaller.remove());
					smaller.add(x);
				} else {
					greater.add(x);
				}
				med = (double) (smaller.peek() + greater.peek()) / 2;
			}
			// case 2 both are equal
			else if (smaller.size() == greater.size()) {
				if (x < med) {
					smaller.add(x);
					med = (double) smaller.peek();
				} else {
					greater.add(x);
					med = (double) greater.peek();
				}
				// case 3 right side has more elements
			} else {
				if (x < med) {
					smaller.add(x);
				} else {
					smaller.add(greater.remove());
					greater.add(x);
				}
				med = (double) (smaller.peek() + greater.peek()) / 2;
			}
			System.out.println(med);
		}
	}

	public static void main(String[] args) {

		int[] a = { 5, 10, 15, 20 };
		printMedian(a);

	}

}
