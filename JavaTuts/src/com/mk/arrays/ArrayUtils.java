package com.mk.arrays;

import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Java program to find missing elements in a Integer array containing numbers
 * from 1 to 100.
 *
 * Read more:
 * https://javarevisited.blogspot.com/2014/11/how-to-find-missing-number-on-
 * integer-array-java.html#ixzz6TTlCCQHI
 */

public class ArrayUtils {

	public static void main(String args[]) {

		int[] arr = findMissingNumber(new Integer[] { 1, 2, 3, 5, 6, 7, 8, 9 }, 10);
		System.out.println("Missing Elements: " + Arrays.toString(arr));

		arr = removeDuplicates(new Integer[] { 1, 2, 3, 5, 6, 7, 8, 9, 9, 5,6,8 });
		System.out.println("Duplicates Removed: " + Arrays.toString(arr));
		
		largestAndSmallest((new Integer[] { 1, 2, 3, 5, 6, 7, 8, 9, 9, 5,6,8 }));
		findTwoElementsMatchingGivenSum(removeDuplicates(new Integer[] { 1, 2, 3, 5, 6, 7, 8, 9, 9, 5,6,8,7 }), 11);

		
		Integer[] unsorted = new Integer[] {10, 30, 80, 90, 40, 50, 70 };
//		Integer[] unsorted = new Integer[] { 1, 8, 3, 5, 6, 7, 4, 9, 2 };
		System.out.println("\nUnSorted: " + Arrays.toString(unsorted));
		SortingAlgorithm<Integer> qs = new QuickSort();
		qs.sort(unsorted);
		System.out.println("Sorted: " + Arrays.toString(unsorted));
		
		reverseArray(unsorted);
		System.out.println("Reversed: " + Arrays.toString(unsorted));
	}

	private static void reverseArray(Integer[] unsorted) {

		for (int i = 0, j = unsorted.length - 1; i < unsorted.length / 2; i++, j--) {
			Integer tmp = unsorted[i];
			unsorted[i] = unsorted[j];
			unsorted[j] = tmp;
		}
	}

	private static int[] findMissingNumber(Integer[] input, int totalLength) {
		int noOfMissingElements = totalLength - input.length;
		int[] missingNumbers = new int[noOfMissingElements];
		int count = 0;
		if (noOfMissingElements == 1) {
			int expectedSum = totalLength * (totalLength + 1) / 2;
			Integer actualSum = Arrays.asList(input).stream().reduce(0, Integer::sum);

			missingNumbers[count] = expectedSum - actualSum;
		} else if (noOfMissingElements > 1) {
			BitSet bs = new BitSet(totalLength);

			Arrays.asList(input).stream().forEach(x -> bs.set(x));
			System.out.println("Input Array:" + bs);

			for (int i = 1; i < totalLength + 1; i++) {
				i = bs.nextClearBit(i);
				missingNumbers[count++] = i;
				if (count == missingNumbers.length) {
					break;
				}
			}
		} else {
			throw new RuntimeException();
		}
		return missingNumbers;
	}
	
	private static int[] removeDuplicates(Integer[] input){
		HashSet<Integer> set = new HashSet<>(); 
		Arrays.asList(input).stream().forEach(x -> {
				if(set.contains(x)) {
					System.out.println("Duplicate: " + x);
				}
				set.add(x); 
			}
		);
		int[] arr = new int[set.size()];
		int i=0;
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			Integer integer = (Integer) iterator.next();
			arr[i++] = integer; 
		}
		return arr;
	}

	
	private static void largestAndSmallest(Integer[] input){
		 
		Integer largest= Arrays.asList(input).stream().reduce(Integer.MIN_VALUE,(x, res) -> x > res? x : res);
		System.out.println("Largest: " + largest);
		Integer smallest= Arrays.asList(input).stream().reduce(Integer.MAX_VALUE, (x, res) -> x < res? x : res);
		System.out.println("Smallest: " + smallest);
	}
	
	private static void findTwoElementsMatchingGivenSum(int[] input, int sum){
		
		HashSet<Integer> set = new HashSet<>();
		
		for (int number : input) {
			int target = sum - number;
			
			if(set.contains(target)){
				System.out.printf("Pair:{%d,%d} ", number,target);
			}
			set.add(number);
		}
	}
	
	
}
