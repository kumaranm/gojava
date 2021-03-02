package com.mk.programiz;

public class LeftTree {
	
	private Node root;
	
	public LeftTree() {
		root = null;
	}
	
	static class Node {
		int data;
		Node left, right;
		
		Node(int data){
			this.data = data;
			left = right = null;
		}
	}
	
	void preOrder(Node d){
		
		if(d == null){
			return;
		}
		System.out.print(d.data + " > ");
		preOrder(d.left);
	}

	public static void main(String[] args) {
		
		LeftTree t = new LeftTree();
		
		t.root = new Node(1);
		t.root.left = new Node(2);
		t.root.right = new Node(3);
		
		t.root.left.left = new Node(4);
		t.root.left.right = new Node(5);
		
		t.root.right.left = new Node(3);
		t.root.right.right = new Node(7);
		
		
		t.root.left.left.left = new Node(8);
		t.preOrder(t.root);

	}

}
