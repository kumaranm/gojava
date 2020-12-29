package com.mk.programiz;

import java.util.Arrays;

public class BinarySearch {

	int binarySearch(int[] array, int key, int low, int high) {
		// recursive

		if (low > high) {
			return -1;
		}

		int mid = (low + high) / 2;

		if (key == array[mid]) {
			return mid;
		} else if (key < array[mid]) {
			// key is lower than mid
			return binarySearch(array, key, low, mid - 1);
		} else {
			// key is higher than mid
			return binarySearch(array, key, mid + 1, high);
		}

	}

	int binarySearch(int[] array, int key) {
		// iterative
		int low = 0;
		int high = array.length;

		while (low <= high) {

			int mid = (low + high) / 2;

			if (key == array[mid]) {
				return mid;
			} else if (key < array[mid]) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		BinarySearch bs = new BinarySearch();

		int[] data = { 2, 4, 5, 7, 8, 10, 11, 14, 15, 19, 20, 23, 25 };
		int key = 25;

		System.out.println(Arrays.toString(data));
		System.out.println("Key " + key + " found at index " + bs.binarySearch(data, key, 0, data.length));
		System.out.println("Key " + key + " found at index " + bs.binarySearch(data, key));
	}

}
