package com.mk.programiz;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class MedianSortedArrays {

	public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
	    int[] newArr = new int[nums1.length + nums2.length];
	    System.arraycopy(nums1, 0, newArr, 0, nums1.length);
	    System.arraycopy(nums2, 0, newArr, nums1.length, nums2.length);
	    Arrays.sort(newArr);
	    int medianIndex = 0;
	    double medianNum = 0.0d;
	    medianIndex = newArr.length / 2;
	    if(newArr.length % 2 == 0) {
	       medianNum = (newArr[medianIndex] + newArr[medianIndex - 1]) / 2.0;
	    } else {
	        medianNum = newArr[medianIndex];
	    }
	    
	    return medianNum;
	}
	
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {

		PriorityQueue<Integer> smaller = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> greater = new PriorityQueue<>();

		double median = 0d;

		int[] mArray = merge(nums1, nums2);

		for (int i = 0; i < mArray.length; i++) {

			if (i == 0) {
				smaller.add(nums1[i]);
				median = smaller.peek();
				continue;
			}
			if (smaller.size() > greater.size()) {
				greater.add(mArray[i]);
				median = (smaller.peek() + greater.peek()) / 2.0;
			} else if (smaller.size() < greater.size()) {
				smaller.add(mArray[i]);
				median = (smaller.peek() + greater.peek()) / 2.0;
			} else {
				if (mArray[i] < smaller.peek()) {
					greater.add(smaller.poll());
					smaller.add(mArray[i]);
					median = greater.peek();
				}
				if (mArray[i] > smaller.peek()) {
					smaller.add(greater.poll());
					greater.add(mArray[i]);
					median = smaller.peek();
				}
			}
		}

		return median;
	}

	private int[] merge(int[] nums1, int[] nums2) {

		if ((nums1 == null && nums2 == null) || (nums1.length == 0 && nums2.length == 0)) {
			return new int[0];
		} else if (nums1 != null && nums1.length > 0 && (nums2 == null || nums2.length == 0)) {
			return new int[] { nums1[0] };
		} else if (nums2 != null && nums2.length > 0 && (nums1 == null || nums1.length == 0)) {
			return new int[] { nums2[0] };
		}

		int[] newArray = new int[nums1.length + nums2.length];
		int l = 0;
		int r = 0;
		int k = 0;

		while (l < nums1.length && r < nums2.length) {
			if (nums1[l] > nums2[r]) {
				newArray[k] = nums2[r];
				k++;
				r++;
			} else if (nums1[l] < nums2[r]) {
				newArray[k] = nums1[l];
				k++;
				l++;
			}
		}
		while (l < nums1.length) {
			newArray[k++] = nums1[l++];
		}
		while (r < nums2.length) {
			newArray[k++] = nums1[r++];
		}
		return newArray;
	}

	public static void main(String[] args) {

		MedianSortedArrays m = new MedianSortedArrays();

		int[] a1 = { 1, 2, 8, 9 };
		int[] a2 = { 3, 6, 7 };

		System.out.println(m.findMedianSortedArrays(a1, a2));
		System.out.println(m.findMedianSortedArrays1(a1, a2));
	}

}
