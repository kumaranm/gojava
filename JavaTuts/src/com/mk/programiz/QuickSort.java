package com.mk.programiz;

import java.util.Arrays;

public class QuickSort {

	/**
	 * Time :O (n log n), worst O(n square)
	 * Space: O (log n)
	 * 
	 * @param array
	 * @param low
	 * @param high
	 */
	void quickSort(int array[], int low, int high) {

		if (low < high) {

			// select pivot and put lower elements to left and higher to right
			int pivot = partition(array, low, high);

			// sort elements on left of pivot
			quickSort(array, low, pivot - 1);

			// sort elements on right of pivot
			quickSort(array, pivot + 1, high);
		}
	}

	private int partition(int[] array, int low, int high) {

		// last element is considered pivot
		int pivot = array[high];

		int i = low - 1;

		//lower on left of pivot
		for (int j = low; j < high; j++) {
			if (array[j] <= pivot) {
				i++;
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			}
		}

		int temp = array[i + 1];
		array[i + 1] = array[high];
		array[high] = temp;

		return i + 1;
	}

	public static void main(String[] args) {
		QuickSort qs = new QuickSort();

		int[] data = { 20, 12, 10, 15, 2 };

		System.out.println("Before QuickSort - " + Arrays.toString(data));
		qs.quickSort(data, 0, data.length - 1);
		System.out.println("After QuickSort - " + Arrays.toString(data));

	}

}
