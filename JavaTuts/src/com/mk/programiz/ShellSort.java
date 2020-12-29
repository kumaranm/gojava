package com.mk.programiz;

import java.util.Arrays;

public class ShellSort {

	/**
	 * O (n log n)
	 * 
	 * @param array
	 * @param n
	 */
	void shellSort(int[] array, int n) {

		for (int interval = n / 2 - 1; interval > 0; interval--) {
			for (int i = interval; i < n; i++) {

				int temp = array[i];
				int j;
				for (j = i; j >= interval && array[j - interval] > temp; j -= interval) {
					array[j] = array[j - interval];
				}
				array[j] = temp;
			}

		}
	}

	public static void main(String[] args) {
		ShellSort ss = new ShellSort();
		int[] data = { 20, 12, 10, 15, 2 };

		System.out.println("Before ShellSort - " + Arrays.toString(data));
		ss.shellSort(data, data.length);
		System.out.println("After ShellSort - " + Arrays.toString(data));
	}

}
