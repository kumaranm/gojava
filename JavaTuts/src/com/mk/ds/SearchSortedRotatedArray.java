package com.mk.ds;

public class SearchSortedRotatedArray {

	public static void main(String[] args) {

		int arr[] = { 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 1, 2 };

		int n = arr.length;
		int key = 1;

		System.out.println("Index of key is: " + pivotedBinarySearch(arr, n, key));
		System.out.println("Index of key is: " + search(arr, 0, n, key));
	}

	static int pivotedBinarySearch(int[] arr, int n, int key) {

		// find the pivot point where array is rotated
		int pivot = findPivot(arr, 0, n - 1);

		// not pivot, ie not rotated, normal binary search
		if (pivot == -1) {
			return binarySearch(arr, 0, n - 1, key);
		}

		// if pivot point is same as the key
		if (arr[pivot] == key) {
			return pivot;
		}

		// if 0th element is more than the key, search left of pivot
		if (arr[0] <= key) {
			return binarySearch(arr, 0, pivot - 1, key);
		}
		// else search right of pivot
		return binarySearch(arr, pivot + 1, n - 1, key);
	}

	private static int binarySearch(int[] arr, int low, int high, int key) {

		// element not found
		if (high < low) {
			return -1;
		}

		int mid = (low + high) / 2;

		if (key == arr[mid]) {
			return mid;
		}

		if (key > arr[mid]) {
			return binarySearch(arr, mid + 1, high, key);
		}

		return binarySearch(arr, low, mid - 1, key);
	}

	private static int findPivot(int[] arr, int low, int high) {

		if (high < low) {
			return -1;
		}

		if (high == low) {
			return low;
		}

		int mid = (low + high) / 2;

		// if mid element is more than next element, this is the pivot
		if (mid < high && arr[mid] > arr[mid + 1]) {
			return mid;
		}

		if (mid > low && arr[mid] < arr[mid - 1]) {
			return mid - 1;
		}

		if (arr[low] >= arr[mid]) {
			return findPivot(arr, low, mid - 1);
		}

		return findPivot(arr, mid + 1, high);
	}

	static int search(int[] arr, int low, int high, int key) {

		if (low > high) {
			return -1;
		}

		int mid = (low + high) / 2;
		if (arr[mid] == key) {
			return mid;
		}

		// is first half is sorted?
		if (arr[low] <= arr[mid]) {
			if (key >= arr[low] && key <= arr[mid]) {
				// key is in first sorted half
				return search(arr, low, mid - 1, key);
			}
			// key is in second half
			return search(arr, mid + 1, high, key);
		}

		if (key >= arr[mid] && key <= arr[high]) {
			return search(arr, mid + 1, high, key);
		}

		return search(arr, low, mid - 1, key);
	}

}
