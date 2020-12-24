package com.mk.ds;

import java.util.Arrays;

public class RotateArray {

	public static void main(String args[]) {

		int[] array = { 1, 2, 3, 4, 5, 6 };
		int rotateBy = 2;

		rotateArray1(array, rotateBy, array.length);
		rotateArray(array, 3, array.length);
	}

	private static void rotateArray(int[] array, int rotateBy, int length) {

		System.out.println("\nArray before rotation: " + Arrays.toString(array));

		int[] temp = new int[rotateBy];

		for (int i = 0; i < length; i++) {

			if (i < rotateBy) {
				temp[i] = array[i];
				continue;
			}
			array[i - rotateBy] = array[i];
		}

		for (int i = 0; i < temp.length; i++) {
			array[length - rotateBy + i] = temp[i];
		}

		System.out.printf("\nArray after rotation by %d is %s", rotateBy, Arrays.toString(array));
	}

	private static void rotateArray1(int[] array, int rotateBy, int length) {

		System.out.println("\nArray before rotation: " + Arrays.toString(array));

		int[] temp = new int[rotateBy];

		for (int i = 0, j = 0; i < length + rotateBy; i++) {

			if (i < rotateBy) {
				temp[i] = array[i];
				continue;
			} else if (i >= length) {
				array[length - rotateBy + j] = temp[j++];
			} else {
				array[i - rotateBy] = array[i];
			}
		}

		System.out.printf("\nArray after rotation by %d is %s", rotateBy, Arrays.toString(array));
	}
}
