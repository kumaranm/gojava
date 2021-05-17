package com.mk.programiz;

import java.util.Arrays;

public class FirstLastIndex {

	public int binarySearch(int[] arr, int low, int high, int key) {

		if (low > high) {
			return -1;
		}

		int mid = low + (high - low) / 2;

		if (key == arr[mid]) {
			return mid;
		}

		if (key < arr[mid]) {
			return binarySearch(arr, low, mid - 1, key);
		} else {
			return binarySearch(arr, mid + 1, high, key);
		}
	}

	public int binarySearchLow(int[] arr, int low, int high, int key, boolean isLower) {

		if (low > high) {
			return -1;
		}

		int mid = low + (high - low) / 2;

		if (key == arr[mid] && low == high) {
			return mid;
		}

		if (key == arr[mid] && ((mid - 1 >= 0 && key != arr[mid - 1] && isLower)
				|| (mid + 1 < arr.length && key != arr[mid + 1] && !isLower))) {
			return mid;
		}

		if (key < arr[mid] || (mid - 1 >= 0 && key == arr[mid - 1] && isLower)) {
			return binarySearchLow(arr, low, mid - 1, key, isLower);
		} else {
			return binarySearchLow(arr, mid + 1, high, key, isLower);
		}
	}

	public int[] startEndIndex(int[] arr, int key) {
		int[] res = new int[2];
		res[0] = binarySearchLow(arr, 0, arr.length - 1, key, true);
		if (res[0] != -1) {
			res[1] = binarySearchLow(arr, res[0], arr.length - 1, key, false);
		} else {
			res[1] = -1;
		}
		return res;
	}

	public static void main(String[] args) {

		FirstLastIndex fli = new FirstLastIndex();
		int[] arr = { 3, 5, 6, 7, 9, 10, 13, 15, 18, 20, 23, 25, 27, 29, 30 };

		System.out.println(fli.binarySearch(arr, 0, arr.length - 1, 23));
		System.out.println(fli.binarySearch(arr, 0, arr.length - 1, 31));
		System.out.println(fli.binarySearch(arr, 0, arr.length - 1, 3));
		System.out.println(fli.binarySearch(arr, 0, arr.length - 1, 30));
		System.out.println(fli.binarySearch(arr, 0, arr.length - 1, 13));

		arr = new int[] { 5, 6, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 10, 12, 18 };
		System.out.println(Arrays.toString(fli.startEndIndex(arr, 8)));
		System.out.println(Arrays.toString(fli.startEndIndex(arr, 9)));
		System.out.println(Arrays.toString(fli.startEndIndex(arr, 10)));
		System.out.println(Arrays.toString(fli.startEndIndex(arr, 1)));
		System.out.println(Arrays.toString(fli.startEndIndex(arr, 18)));
		System.out.println(Arrays.toString(fli.startEndIndex(arr, 5)));
	}

}
