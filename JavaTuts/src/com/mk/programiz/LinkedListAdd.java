package com.mk.programiz;

class ListNode {
	int val;
	ListNode next;

	ListNode() {
	}

	ListNode(int val) {
		this.val = val;
	}

	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}

	public String toString() {
		return "" + val;
	}
}

public class LinkedListAdd {

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode result = null;
		ListNode head = null;
		int carry = 0;
		while (l1 != null && l2 != null) {
			int sum = l1.val + l2.val + carry;
			carry = sum / 10;
			sum = sum % 10;
			if (head == null) {
				result = new ListNode(sum, null);
				head = result;
			} else {
				result.next = new ListNode(sum, null);
				result = result.next;
			}
			l1 = l1.next;
			l2 = l2.next;
		}

		// either number is larger
		while (l1 != null) {
			int sum = l1.val + carry;
			carry = sum / 10;
			sum = sum % 10;
			result.next = new ListNode(sum, null);
			result = result.next;
			l1 = l1.next;
		}
		while (l2 != null) {
			int sum = l2.val + carry;
			carry = sum / 10;
			sum = sum % 10;
			result.next = new ListNode(sum, null);
			result = result.next;
			l2 = l2.next;
		}
		// if there is a carry over, extended the number
		if (carry != 0) {
			result.next = new ListNode(carry, null);
			result = result.next;
		}
		return head;
	}

	public static void main(String[] args) {

		//Reverse Order
		ListNode s = new ListNode(9, new ListNode(3, new ListNode(4, null)));
		ListNode t = new ListNode(2, new ListNode(3, new ListNode(4, null)));

		ListNode result = LinkedListAdd.addTwoNumbers(s, t);
		
		while(result != null)
		{
			System.out.print(result.val);
			result = result.next;
		}
	}

}
