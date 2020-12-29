package com.mk.programiz;

import java.util.Arrays;

public class MergeSort {

	/**
	 * Split into sub arrays and merge
	 * 
	 * Time Complexity: O (n log n)
	 * 
	 * 
	 * @param array
	 * @param p
	 * @param r
	 */
	void mergeSort(int[] array, int p, int r) {

		if (p >= r) {
			return;
		}

		int q = (p + r) / 2;

		mergeSort(array, p, q);
		mergeSort(array, q + 1, r);

		merge(array, p, q, r);
	}

	/**
	 * while not end of array compare elements on sub array insert min element
	 * in sorted array move relevant pointer if end, copy remaining elements
	 * 
	 * 
	 * @param array
	 * @param p
	 * @param q
	 * @param r
	 */
	private void merge(int[] array, int p, int q, int r) {

		//find sub array lengths
		int na1 = q - p + 1;
		int na2 = r - q;
		// create sub arrays
		int[] a1 = new int[na1];
		int[] a2 = new int[na2];

		// populate sub array data
		for (int i = 0; i < na1; i++) {
			a1[i] = array[p + i];
		}

		for (int i = 0; i < na2; i++) {
			a2[i] = array[q + 1 + i];
		}

		int a1Index = 0;
		int a2Index = 0;
		int index = p;

		//compare sub array indexes
		while (a1Index < na1 && a2Index < na2) {
			if (a1[a1Index] <= a2[a2Index]) {
				array[index] = a1[a1Index];
				a1Index++;
			} else {
				array[index] = a2[a2Index];
				a2Index++;
			}
			index++;
		}

		//copy remaining elements
		while (a1Index < na1) {
			array[index] = a1[a1Index];
			a1Index++;
			index++;
		}

		while (a2Index < na2) {
			array[index] = a2[a2Index];
			a2Index++;
			index++;
		}
	}

	public static void main(String[] args) {
		MergeSort ms = new MergeSort();

		int[] data = { 20, 12, 10, 15, 2 };

		System.out.println("Before MergeSort - " + Arrays.toString(data));
		ms.mergeSort(data, 0, data.length-1);
		System.out.println("After MergeSort - " + Arrays.toString(data));
	}

}
