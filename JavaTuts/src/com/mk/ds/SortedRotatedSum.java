package com.mk.ds;

public class SortedRotatedSum {

	public static void main(String[] args) {
		int[] arr = { 11, 15, 6, 8, 9, 10 };
		int sum = 26;

		int n = arr.length;

		System.out.println(pairInSortedRotated(arr, n, sum));
	}

	static boolean pairInSortedRotated(int[] arr, int n, int sum) {

		// find pivot
		int i = 0;
		for (i = 0; i < n - 1; i++) {
			if (arr[i] > arr[i + 1]) {
				break;
			}
		}

		// smallest index
		int l = (i + 1) % n;
		int r = i;

		while (l != r) {

			if (arr[l] + arr[r] == sum) {
				return true;
			}

			if (arr[l] + arr[r] < sum) {
				l = (l + 1) % n;
			} else {
				r = (n + r - 1) % n;
			}
		}

		return false;
	}
}
