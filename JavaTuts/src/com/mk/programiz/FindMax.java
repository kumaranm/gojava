package com.mk.programiz;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.PriorityQueue;

/*
 * int[] arr = new int[]{12, 34, 56, 789, 567, 345, 234 ,123};
 *  - Array of integers ordered as increasing and starts decreasing. Find the maximum element.
 * 
 */

public class FindMax {

	public static void findMaxByIteration(int arr[]) {

		if(arr == null || arr.length == 0){
			System.out.println("Max number using Heap is nothing");
			return;
		}
		int maxIndex = -1;
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] > arr[i + 1]) {
				maxIndex = i;
				break;
			}
		}
		if (maxIndex != -1) {
			System.out.println("Max number using iteration is " + arr[maxIndex]);
		} else {
			System.out.println("Max number using iteration is " + arr[arr.length - 1]);
		}
	}

	public static void findMaxByHeap(int arr[]) {

		if(arr == null || arr.length == 0){
			System.out.println("Max number using Heap is nothing");
			return;
		}
		PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());

		max.add(arr[0]);

		for (int i = 1; i < arr.length; i++) {
			if (max.peek() > arr[i]) {
				break;
			} else {
				max.add(arr[i]);
			}
		}
		System.out.println("Max number using Heap is " + max.peek());
	}

	public static void findMaxByStack(int [] arr){
		if(arr == null || arr.length == 0){
			System.out.println("Max number using Stack is nothing");
			return;
		}
		
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		
		stack.push(arr[0]);
		for (int i = 1; i < arr.length; i++) {
			
			if(stack.peek() > arr[i]){
				break;
			}
			else{
				stack.push(arr[i]);
			}
		}
		System.out.println("Max number using Satck is " + stack.peek());
	}
	
	public static void main(String[] args) {
		int[][] array = new int[][] { 
			    { 12}, 
			    { 792, 567, 345, 234, 123 }, 
			    { 12, 793, 567, 345, 234, 123 },
				{ 12, 34, 56, 789, 567, 345, 234, 123 }, 
				{ 12, 34, 56, 123, 234, 567, 790, 590 },
				{ 12, 34, 56, 123, 234, 567, 791 } };

		// iteration
		// binary search?
		// use max-heap? log n

		for (int[] arr : array) {
			FindMax.findMaxByIteration(arr);
			FindMax.findMaxByHeap(arr);
			FindMax.findMaxByStack(arr);
		}

	}

}
