package com.mk.ds;

public class LLinkedList {

	Node head;

	static class Node {

		Node next;
		int data;

		Node(int d) {
			data = d;
			next = null;
		}
	}

	void printList() {
		Node h = head;
		while (h != null) {
			System.out.print(h.data + " -> ");
			h = h.next;
		}
		if (h == null) {
			System.out.println("NULL");
		}
	}

	public static void main(String[] args) {
		LLinkedList ll = new LLinkedList();

		ll.head = new Node(1);

		Node second = new Node(2);
		Node third = new Node(3);

		ll.head.next = second;
		second.next = third;
		ll.printList();

		ll.addAtHead(5);
		ll.addAtHead(8);
		ll.printList();

		ll.addAfterNode(second, 10);
		ll.addAfterNode(third, 11);
		ll.printList();

		ll.deleteNode(10);
		ll.printList();
		ll.deleteNode(11);
		ll.printList();
		ll.deleteNode(8);
		ll.printList();

		ll.addMiddle(20);
		ll.printList();

		ll.addMiddle(21);
		ll.printList();

		ll.addMiddle(22);
		ll.printList();
		ll.addMiddle(23);
		ll.printList();

		ll.reverse();
		ll.printList();
	}

	void addAtHead(int d) {
		Node n = new Node(d);
		n.next = head;
		head = n;
	}

	void addAfterNode(Node node, int d) {
		Node n = new Node(d);
		n.next = node.next;
		node.next = n;
	}

	void deleteNode(int d) {
		Node n = head;

		if (n.data == d) {
			head = n.next;
			return;
		}
		while (n.next != null) {
			if (n.next.data == d) {
				Node temp = n.next.next;
				n.next = temp;
				break;
			}
			n = n.next;
		}
	}

	void addMiddle(int d) {
		Node f = head;
		Node s = head;
		while (s.next != null && s.next.next != null) {
			f = f.next;
			s = s.next.next;
		}
		addAfterNode(f, d);
	}

	void reverse() {

		Node prev = null;
		Node next = null;
		Node curr = head;

		while (curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		head = prev;
	}
	
	void addNumbers(Node a, Node b){
		
	}
	
	
}
