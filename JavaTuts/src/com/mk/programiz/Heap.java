package com.mk.programiz;

import java.util.ArrayList;

public class Heap {

	void heapify(ArrayList<Integer> hp, int i) {

		int size = hp.size();
		int largest = i;
		int l = 2 * i + 1;
		int r = 2 * i + 2;

		if (l < size && hp.get(l) > hp.get(largest)) {
			largest = l;
		}
		if (r < size && hp.get(r) > hp.get(largest)) {
			largest = r;
		}

		if (largest != i) {
			int temp = hp.get(largest);
			hp.set(largest, hp.get(i));
			hp.set(i, temp);
			heapify(hp, largest);
		}
	}

	void print(ArrayList<Integer> hp) {
		for (Integer i : hp) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	void insert(ArrayList<Integer> hp, int num) {
		int size = hp.size();
		
		//add element to child node
		if (size == 0) {
			hp.add(num);
		} else {
			hp.add(num);
			
			//heapify
			for (int i = size % 2 == 0? (size / 2) - 1 : size /2; i >= 0; i--) {
				heapify(hp, i);
			}
		}
	}

	void delete(ArrayList<Integer> hp, int num) {
		int size = hp.size();
		int i = 0;
		//find index of element to be deleted
		for (i = 0; i < size; i++) {
			if (num == hp.get(i)) {
				break;
			}
		}

		if (i >= size ) {
			return;
		}
		//swap element to be deleted to last child node
		int temp = hp.get(i);
		hp.set(i, hp.get(size - 1));
		hp.set(size - 1, temp);
		
		//remove element
		hp.remove(size - 1);

		//heapify
//		for (int j = (size / 2) - 1; j >= 0; j--) {
		for (int j = size % 2 == 0? (size / 2) - 1 : size /2; j >= 0; j--) {
			heapify(hp, j);
		}
	}

	public static void main(String[] args) {

		ArrayList<Integer> array = new ArrayList<>();
		int size = array.size();

		// array.add(1);
		// array.add(2);
		// array.add(3);
		// array.add(4);

		Heap h = new Heap();
		h.insert(array, 1);
		h.insert(array, 2);
		h.insert(array, 3);
		h.insert(array, 4);
//		h.insert(array, 5);

		h.print(array);
		
		h.delete(array, 1);
		h.print(array);
		
		h.delete(array, 3);
		h.print(array);
		
		h.delete(array, 5);
		h.insert(array, 1);
		h.insert(array, 9);
		h.insert(array, 10);
		h.insert(array, 6);
		h.print(array);
	}

}
