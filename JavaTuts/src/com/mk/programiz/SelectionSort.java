package com.mk.programiz;

import java.util.Arrays;

public class SelectionSort {

	/**
	 * Select smallest element and place at first unsorted postition
	 * 
	 * Best/Avg/Worst - O(n square)
	 * 
	 * @param array
	 */
	void selectionSort(int[] array) {

		int size = array.length;

		for (int step = 0; step < size; step++) {
			int minIndex = step;
			for (int i = step + 1; i < size; i++) {

				if (array[i] < array[minIndex]) {
					minIndex = i;
				}
			}

			int temp = array[step];
			array[step] = array[minIndex];
			array[minIndex] = temp;
			System.out.println("Round - " + (step + 1) + " - " + Arrays.toString(array));
		}

	}

	public static void main(String[] args) {

		SelectionSort ss = new SelectionSort();
		int[] data = { 20, 12, 10, 15, 2 };
//		int[] data = { -9, -2, 0, 45, 11 };

		System.out.println("Before SelectionSort - " + Arrays.toString(data));
		ss.selectionSort(data);
		System.out.println("After SelectionSort - " + Arrays.toString(data));
	}

}
