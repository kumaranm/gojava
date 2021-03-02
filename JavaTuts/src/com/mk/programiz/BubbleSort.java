package com.mk.programiz;

import java.util.Arrays;

public class BubbleSort {

	/**
	 * Compare Adjacent elements and swap if not in order Best case - O(n) -
	 * array already sorted Avg/ Worst Case - O(n squared)
	 * Largest element in correct position at end of array
	 * 
	 * @param array
	 */
	void bubbleSort(int[] array) {

		int size = array.length;

		for (int i = 0; i < size - 1; i++) {
			boolean swapped = false;
			for (int j = 0; j < size - i - 1; j++) {
				if (array[j] > array[j + 1]) {
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
					swapped = true;
				}
				System.out.println("Round - " + (i + 1) + " - " + Arrays.toString(array));
			}
			// optimized version, if no swaps done in prev loop, end
			if (!swapped) {
				break;
			}
		}
	}

	public static void main(String[] args) {

		BubbleSort bs = new BubbleSort();
		// int[] a = { -2, 45, 0, 11, -9 };
		int[] a = { -9, -2, 0, 45, 11 };

		System.out.println("Before BubbleSort: " + Arrays.toString(a));
		bs.bubbleSort(a);
		System.out.println("After BubbleSort: " + Arrays.toString(a));

	}

}
