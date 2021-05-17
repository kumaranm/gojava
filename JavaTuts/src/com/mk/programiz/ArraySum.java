package com.mk.programiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArraySum {

	/**
	 * Given an array nums of n integers, are there elements a, b, c in nums
	 * such that a + b + c = 0? Find all unique triplets in the array which
	 * gives the sum of zero.
	 * 
	 * Notice that the solution set must not contain duplicate triplets.
	 * 
	 * Input: nums = [-1,0,1,2,-1,-4] Output: [[-1,-1,2],[-1,0,1]]
	 * 
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> threeSum(int[] nums) {

		return null;
	}

	public int threeSumClosest(int[] nums, int target) {
		int diff = Integer.MAX_VALUE;
		int sz = nums.length;
		Arrays.sort(nums);

		for (int i = 0; i < sz && diff != 0; ++i) {
			int lo = i + 1, hi = sz - 1;
			while (lo < hi) {
				int sum = nums[i] + nums[lo] + nums[hi];
				if (Math.abs(target - sum) < Math.abs(diff))
					diff = target - sum;
				if (sum < target)
					++lo;
				else
					--hi;
			}
		}
		return target - diff;
	}

	public List<List<Integer>> anySum(int[] nums, int k, int sum) {

		List<List<Integer>> res = new ArrayList<List<Integer>>();

		int arrlen = nums.length;

		if (k >= arrlen) {
			return res;
		}

		Arrays.sort(nums);
		List<Integer> set = new ArrayList<>();
		int windowSum = 0;
		for (int i = 0; i < k - 1; i++) {
			windowSum += nums[i];
			set.add(nums[i]);
		}
		/*
		 * if(windowSum == sum){ res.add(set); windowSum = 0; }
		 */

		for (int i = k - 1; i < arrlen; i++) {

			if (nums[i] > sum) {
				// since sorted list, if number is created, it cannot form the
				// sum
				break;
			}

			windowSum += nums[i];
			if (windowSum < sum && set.size() == k) {
				windowSum -= set.get(0);
				set.remove(0);
				set.add(nums[i]);
			} else if (windowSum >= sum && set.size() == k) {
				int diff = windowSum - sum;
				if (set.contains(diff)) {
					set.remove(set.indexOf(diff));
					set.add(nums[i]);
					res.add(set);
				} else if (diff == 0) {
					set.add(nums[i]);
					res.add(set);
				} else {
					windowSum -= diff;
				}
			} /*
				 * else if (windowSum == sum && set.size() == k){ res.add(set);
				 * windowSum = 0; set.remove(0); }
				 */else {
				set.add(nums[i]);
			}
		}

		return res;
	}

	public static void main(String[] args) {

		ArraySum as = new ArraySum();

		// System.out.println(as.anySum(new int[] { 1, 2, 5, 4, 3, 1, 4, 0, 7 },
		// 2, 9));

		System.out.println(as.threeSumClosest(new int[] { 1, 2, 4, 3, 1, 4, 0, 7 }, 10));
	}

}
