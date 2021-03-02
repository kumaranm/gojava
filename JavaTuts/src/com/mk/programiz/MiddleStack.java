package com.mk.programiz;

public class MiddleStack {

	class DLLNode {
		DLLNode prev;
		int data;
		DLLNode next;

		DLLNode(int data) {
			this.data = data;
		}
	}

	class MStack {
		DLLNode head;
		DLLNode mid;
		int count;
	}

	MStack createStack() {
		MStack ms = new MStack();
		ms.count = 0;
		return ms;
	}

	void push(MStack ms, int newData) {

		// create node
		DLLNode newNode = new DLLNode(newData);

		// add new node at front ie Stack
		newNode.prev = null;

		// link existing nodes
		newNode.next = ms.head;

		// increment count
		ms.count++;

		if (ms.count == 1) {
			ms.mid = newNode;
		} else {

			ms.head.prev = newNode;
			// if odd update mid
			if (ms.count % 2 != 0) {
				ms.mid = ms.mid.prev;
			}
		}

		ms.head = newNode;
	}

	int pop(MStack ms) {

		if (ms.count == 0) {
			System.out.println("No elemements");
			return -1;
		}

		DLLNode head = ms.head;
		int item = head.data;
		ms.head = head.next;

		if (ms.head != null) {
			ms.head.prev = null;
		}

		ms.count--;

		if (ms.count % 2 == 0) {
			ms.mid = ms.mid.next;
		}
		return item;
	}

	int findMiddle(MStack ms) {
		if (ms.count == 0) {
			return -1;
		}

		return ms.mid.data;
	}

	public static void main(String[] args) {

		MiddleStack ms = new MiddleStack();

		MStack mstack = ms.createStack();

		ms.push(mstack, 11);
		ms.push(mstack, 22);
		ms.push(mstack, 33);
		ms.push(mstack, 44);
		ms.push(mstack, 55);
		ms.push(mstack, 66);
		ms.push(mstack, 77);

		System.out.println("Pop " + ms.pop(mstack));
		System.out.println("Pop " + ms.pop(mstack));

		System.out.println("Middle " + ms.findMiddle(mstack));
		ms.push(mstack, 88);
		System.out.println("Middle " + ms.findMiddle(mstack));
		ms.push(mstack, 99);
		System.out.println("Middle " + ms.findMiddle(mstack));
	}

}
