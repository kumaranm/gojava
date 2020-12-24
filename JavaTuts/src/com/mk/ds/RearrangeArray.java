package com.mk.ds;

import java.util.Arrays;

public class RearrangeArray {

	public static void main(String[] args) {

//		int[] arr = { 1, 2, 3, 4, 5, 6, 7 };

		int[] arr = { 1, 2, 1, 4, 5, 6, 8,8 };

		System.out.println("Input Array: " + Arrays.toString(arr));

		arr = rearrangeArray(arr);

		System.out.println("Rearranged Array: " + Arrays.toString(arr));
	}

	static int[] rearrangeArray(int[] arr) {

		Arrays.sort(arr);

		int mid = (arr.length / 2) + (arr.length % 2);
		int a = mid - 1;
		int b = mid;
		
		int[] newArray = new int[arr.length]; 
		
		for (int i = 1; i <= arr.length; i++) {

			// 0-a -> odd numbers
			// b - length -> even numbers
			if (i % 2 == 0) {
				newArray[i-1] = arr[b++];
			} else {
				newArray[i-1] = arr[a--];
			}
		}
		return newArray;
	}

}
