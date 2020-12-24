package com.mk.programiz;

public class Stack {

	private int arr[];
	private int capacity;
	private int top;

	Stack(int size) {
		arr = new int[size];
		capacity = size;
		top = -1;
	}

	public int size() {
		return top + 1;
	}

	public boolean isEmpty() {
		return top == -1;
	}

	public boolean isFull() {
		return top == capacity - 1;
	}

	public void push(int x) {
		if (isFull()) {
			System.out.println("Stack is full");
			System.exit(1);
		}

		System.out.println("Adding " + x);
		arr[++top] = x;
	}

	public int pop() {
		if (isEmpty()) {
			System.out.println("Stack is empty");
			System.exit(0);
		}

		return arr[top--];
	}

	public void printStack() {
		for (int i = 0; i <= top; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.print("\n");
	}

	public static void main(String[] args) {

		Stack stack = new Stack(5);

		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.printStack();

		stack.push(5);
		stack.printStack();

		// stack.push(6);
		stack.pop();
		stack.printStack();
		System.out.println(stack.size());

		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();

		System.out.println(stack.size());
	}

}
