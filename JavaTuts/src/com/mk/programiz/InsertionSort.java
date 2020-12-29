package com.mk.programiz;

import java.util.Arrays;

public class InsertionSort {

	/**
	 * Place an unsorted element in the right position in each iteration
	 * 
	 * Worst/Avg Case: O(n square) Best: O(n)
	 * 
	 * @param array
	 */
	void insertionSort(int[] array) {

		int size = array.length;

		for (int step = 1; step < size; step++) {
			int key = array[step];
			int j = step - 1;

			while (j >= 0 && key < array[j]) {
				array[j + 1] = array[j];
				--j;
			}

			array[j + 1] = key;
			System.out.println("Round - " + (step) + " - " + Arrays.toString(array));
		}

	}

	public static void main(String[] args) {
		InsertionSort is = new InsertionSort();

		int[] data = { 20, 12, 10, 15, 2 };
		// int[] data = { -9, -2, 0, 45, 11 };

		System.out.println("Before InsertionSort - " + Arrays.toString(data));
		is.insertionSort(data);
		System.out.println("After InsertionSort - " + Arrays.toString(data));

	}

}
