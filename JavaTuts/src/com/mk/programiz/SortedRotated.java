package com.mk.programiz;

import java.util.Arrays;

public class SortedRotated {

	public static int countRotations(int arr[]) {

		int count = 0;
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] > arr[i + 1]) {
				count = i + 1;
				break;
			}
		}
		return count;
	}

	public static int countRotations(int arr[], int low, int high) {

		if (low > high) {
			return 0;
		}

		if (low == high) {
			if (low == (arr.length - 1) && arr[high] > arr[high - 1]) {
				// if last element, return rotation count as 0
				// since array is already sorted
				return 0;
			}
			return low;
		}

		int mid = low + (high - low) / 2;

		if (mid < high && arr[mid] > arr[mid + 1]) {
			// right side check
			return mid + 1;
		}
		if (mid > low && arr[mid] < arr[mid - 1]) {
			// left side check
			return mid;
		}

		if (arr[low] > arr[mid]) {
			// search left
			return countRotations(arr, low, mid - 1);
		}
		return countRotations(arr, mid + 1, high);
	}

	public static void main(String[] args) {

		int arr[] = { 10, 20, 30, 40, 50, 60, 70, 80, 90 };
		for (int i = 0; i < arr.length; i++) {
			System.out.println("---------Iteration: " + i);
			arr = rotateArray(arr, 1);
			System.out.println("Input Array: " + Arrays.toString(arr));

			System.out.println("No of rotations: " + SortedRotated.countRotations(arr));
			System.out.println("No of rotations: " + SortedRotated.countRotations(arr, 0, arr.length - 1));
		}
	}

	private static int[] rotateArray(int[] arr, int rotateBy) {

		int newarr[] = new int[arr.length];
		for (int j = 0; j < arr.length; j++) {
			//clockwise - left shift
			newarr[j] = (j + rotateBy >= arr.length) ? arr[(j + rotateBy) % arr.length] : arr[j + rotateBy];
		}
		
		for (int j = 0; j < arr.length; j++) {
			//anticlockwise - right shift
			newarr[(j + rotateBy >= arr.length) ? (j + rotateBy) % arr.length : (j + rotateBy)] = arr[j];
		}
		
		return newarr;
	}

}
