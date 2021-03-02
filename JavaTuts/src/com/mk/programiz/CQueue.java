package com.mk.programiz;

public class CQueue {

	int SIZE = 5;
	int items[] = new int[5];
	int front, rear;

	CQueue() {
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
		if (front == 0 && rear == SIZE - 1) {
			return true;
		} else if (front == rear + 1) {
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
			rear = (rear + 1) % SIZE;
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
			if (front == rear) {
				front = -1;
				rear = -1;
			} else {
				front = (front + 1) % SIZE;
			}
			System.out.println("\nDeleted " + element);
			return element;
		}
	}

	public void display() {

		if (isEmpty()) {
			System.out.println("Queue empty");
		} else {

//			System.out.print("\n");
			
//			System.out.print("\n");
			
			
			for (int i = 0; i < SIZE; i++) {
				System.out.print(items[i] + " ");
			}
			System.out.println("\nFront: " + front + ", Rear: " + rear);
			for (int i = front; i != rear; i = (i + 1) % SIZE) {
				System.out.print(items[i] + " ");
			}
			System.out.print(items[rear]);
		}
	}

	public static void main(String[] args) {

		CQueue q = new CQueue();

		q.dequeue();
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		q.enqueue(4);
		q.enqueue(5);
		q.enqueue(90);

		q.display();

		q.dequeue();
		q.display();
		q.dequeue();
		// q.dequeue();
		// q.dequeue();
		q.dequeue();
		q.display();
		q.enqueue(6);
		q.enqueue(7);
		q.enqueue(8);
		q.enqueue(9);
		q.enqueue(10);
		q.enqueue(11);
		q.display();

		q.dequeue();
		q.display();
	}
}
