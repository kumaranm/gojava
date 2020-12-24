package com.mk.arrays;

import java.util.Arrays;

public class QuickSort implements SortingAlgorithm<Integer> {

	private Integer[] numbers;
	private int iteration;

	@Override
	public <T> void sort(final T[] t) {
		numbers = (Integer[]) t;
		iteration = 1;
		quickSort1(0, t.length - 1);
	}

	private void quickSort(int low, int high) {
		int i = low;
		int j = high;

		int pivot = numbers[low + (high - low) / 2];
		System.out.println("Iteration: " + iteration++ + ", using pivot: " + pivot);

		while (i <= j) {

			while (numbers[i] < pivot) {
				i++;
			}
			while (numbers[j] > pivot) {
				j--;
			}

			if (i <= j) {
				System.out.println("Swapping:" + numbers[i] + " and " + numbers[j]);
				int tmp = numbers[i];
				numbers[i] = numbers[j];
				numbers[j] = tmp;
				i++;
				j--;
			}
		}
		System.out.println(Arrays.toString(numbers));
		if (low < j) {
			quickSort(low, j);
		}
		if (i < high) {
			quickSort(i, high);
		}
	}

	private void quickSort1(int low, int high) {
		int i = low;
		int j = high;
		// pivot is middle index
		int pivot = numbers[low + (high - low) / 2];
		System.out.println("Iteration: " + iteration++ + ", using pivot: " + pivot);
		// Divide into two arrays
		while (i <= j) {
			/**
			 * * As shown in above image, In each iteration, we will identify a
			 * * number from left side which is greater then the pivot value,
			 * and * a number from right side which is less then the pivot
			 * value. Once * search is complete, we can swap both numbers.
			 */
			while (numbers[i] < pivot) {
				i++;
			}
			while (numbers[j] > pivot) {
				j--;
			}
			if (i <= j) {
				swap(i, j);
				// move index to next position on both sides
				i++;
				j--;
			}
		}
		// calls quickSort() method recursively
		System.out.println(Arrays.toString(numbers));
		if (low < j) {
			quickSort(low, j);
		}
		if (i < high) {
			quickSort(i, high);
		}
	}

	private void swap(int i, int j) {
		System.out.println("Swapping:" + numbers[i] + " and " + numbers[j]);
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}
}
