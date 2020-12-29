package com.mk.programiz;

import java.util.Arrays;

public class CountingSort {

	/**
	 * O(n + k)
	 * 
	 * @param array
	 * @param size
	 */
	void countingSort(int array[], int size) {

		// create output array
		int[] output = new int[size + 1];

		int max = array[0];
		// find max element
		for (int i = 1; i < size; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}

		// create new array with max elements and init with 0
		int[] count = new int[max + 1];
		for (int i = 0; i < max; i++) {
			count[i] = 0;
		}

		// update count array with unique element counts
		for (int i = 0; i < size; i++) {
			count[array[i]]++;
		}

		// find cumulative sum
		for (int i = 1; i <= max; i++) {
			count[i] += count[i - 1];
		}

		// start from last, find index pos, subtract 1 and add to index pos
		for (int i = size - 1; i >= 0; i--) {
			output[count[array[i]] - 1] = array[i];
			count[array[i]]--;
		}

		// replace in original array
		for (int i = 0; i < size; i++) {
			array[i] = output[i];
		}
	}

	public static void main(String[] args) {

		CountingSort cs = new CountingSort();
		int[] data = { 20, 12, 10, 15, 2 };
		int size = data.length;

		System.out.println("Before CountingSort - " + Arrays.toString(data));
		cs.countingSort(data, size);
		System.out.println("After CountingSort - " + Arrays.toString(data));
	}

}
