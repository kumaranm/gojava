package com.mk.programiz;

import java.util.Arrays;

public class LongIncreaseSubSequence {

	public static void main(String[] args) {

		
//		int arr[] = { 3, 10, 2, 11 };
		int arr[] = { 3, 10, 2, 11, 6, 13, 4, 1, 19, 5, 25 };
		
		lis(arr);
	}

	private static void lis(int[] arr) {

		//dynamic programming Time Complexity is O(n*n)	
		int[] lisarr = new int[arr.length];

		for (int i = 0; i < arr.length; i++) {
				lisarr[i] = max(lisarr, arr, arr[i]) + 1; 
		}
		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(lisarr));
		Arrays.sort(lisarr);
		System.out.println(lisarr[lisarr.length-1]);
		
		
		java.util.Deque<Integer> ll = new java.util.ArrayDeque();
		ll.add(1);
		ll.add(2);
		System.out.println(ll);
		ll.remove(1);
		System.out.println(ll);
	}

	private static int max(int[] lisarr,int[] arr, int s) {
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] < s){
				 max = max > lisarr[i] ? max : lisarr[i];
			}
		}
		return max;
	}

}
