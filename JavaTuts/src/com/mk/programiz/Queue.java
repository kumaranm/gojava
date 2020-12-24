package com.mk.programiz;

public class Queue {

	int SIZE = 5;
	int items[] = new int[5];
	int front, rear;

	Queue() {
		front = -1;
		rear = -1;
	}

	public boolean isEmpty() {
		if (front == -1) {
			return true;
		}
		return false;
	}

	public boolean isFull() {
		if (rear == SIZE - 1) {
			return true;
		}
		return false;
	}

	public void enqueue(int element) {
		if (isFull()) {
			System.out.println("Queue full");
		} else {
			if (front == -1) {
				front = 0;
			}
			rear++;
			items[rear] = element;
			System.out.println("Added " + element);
		}
	}

	public int dequeue() {
		int element;
		if (isEmpty()) {
			System.out.println("Queue empty");
			return -1;
		} else {
			element = items[front];
			if (front >= rear) {
				front = -1;
				rear = -1;
			} else {
				front++;
			}
			System.out.println("Deleted " + element);
			return element;
		}
	}

	public void display() {

		if (isEmpty()) {
			System.out.println("Queue empty");
		} else {

			System.out.println("Front " + front);
			for (int i = front; i <= rear; i++) {
				System.out.print(items[i] + " ");
			}
			System.out.println("\nRear " + rear);
		}
	}

	public static void main(String[] args) {

		Queue q = new Queue();

		q.dequeue();
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		q.enqueue(4);
		q.enqueue(5);

		q.display();

		q.dequeue();
		q.display();
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.display();
		q.enqueue(6);
		q.enqueue(7);
		q.enqueue(8);
		q.enqueue(9);
		q.enqueue(10);
		q.enqueue(11);
		q.display();
	}
}
