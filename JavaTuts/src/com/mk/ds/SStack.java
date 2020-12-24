package com.mk.ds;

public class SStack {

	class Node {
		Node prev;
		Node next;
		int data;

		Node(int d) {
			data = d;
		}
	}

	class Stack {

		Node head;
		Node mid;
		int count;
	}

	Stack createStack() {
		Stack stack = new Stack();
		stack.count = 0;
		return stack;
	}

	public static void main(String[] args) {

		SStack st = new SStack();

		Stack stack = st.createStack();
		st.push(stack, 2);
		st.printList(stack);
		st.push(stack, 5);
		st.printList(stack);
		st.push(stack, 7);
		st.printList(stack);
		st.push(stack, 1);
		st.printList(stack);
		st.push(stack, 8);
		st.printList(stack);
		st.push(stack, 10);
		st.printList(stack);
		st.push(stack, 11);
		st.printList(stack);

		st.pop(stack);
		st.printList(stack);

		st.pop(stack);
		st.printList(stack);
	}

	void printList(Stack st) {
		Node h = st.head;
		while (h != null) {
			System.out.print(h.data + " -> ");
			h = h.next;
		}
		if (h == null) {
			System.out.print("NULL         ");
		}

		if (st.mid != null) {
			System.out.println("Mid: " + st.mid.data);
		}
	}

	void push(Stack st, int d) {

		Node n = new Node(d);

		n.prev = null;
		n.next = st.head;
		st.count++;

		if (st.count > 1) {
			st.head.prev = n;
		}

		if (st.count < 2) {
			st.mid = n;
		} else {
			if (st.head.next != null) {
				st.mid = st.head.next;
			}
			/*
			 * if (st.count % 2 != 0) { st.mid = st.mid.prev; }
			 */
		}

		/*
		 * int i = 0; if (st.count <= 2) { st.mid = n; } else { i = st.count /
		 * 2; while (i > 0) { st.mid = n.next; st.mid = st.mid.next; --i; } }
		 */

		/*
		 * if (st.count == 1) { st.mid = n; } else { st.mid.prev = n; if
		 * (st.count % 2 != 0) { st.mid = st.mid.prev; } }
		 */
		st.head = n;
	}

	int pop(Stack st) {
		if (st.count == 0) {
			return -1;
		}

		int d = st.head.data;
		st.head = st.head.next;

		return d;
	}

	int findMiddle(Stack st) {
		return 0;
	}
}
