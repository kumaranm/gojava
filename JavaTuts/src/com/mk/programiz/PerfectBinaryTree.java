package com.mk.programiz;

public class PerfectBinaryTree {

	Node root;

	public PerfectBinaryTree() {
		root = null;
	}

	int depth(Node node) {
		int d = 0;

		while (node != null) {
			d++;
			node = node.left;
		}
		return d;
	}

	boolean isPerfect(Node node, int d, int level) {

		// no node
		if (node == null) {
			return true;
		}
		// if child node, is depth same as calculated first
		if (node.left == null && node.right == null) {
			return (d == level + 1);
		}
		// either child is empty
		if (node.left == null || node.right == null) {
			return false;
		}
		// check
		return isPerfect(node.left, d, level + 1) && isPerfect(node.right, d, level + 1);
	}

	boolean isPerfect() {
		int d = depth(root);
		return isPerfect(root, d, 0);
	}

	public static void main(String[] args) {
		PerfectBinaryTree tree = new PerfectBinaryTree();

		tree.root = new Node(1);
		System.out.println(tree.isPerfect());
		tree.root.left = new Node(2);
		System.out.println(tree.isPerfect());

		tree.root.left.left = new Node(3);
		System.out.println(tree.isPerfect());

		tree.root.left.right = new Node(4);
		tree.root.right = new Node(5);
		tree.root.right.right = new Node(6);
		System.out.println(tree.isPerfect());
		
		tree.root.right.left = new Node(7);
		System.out.println(tree.isPerfect());
		
		tree.root.right.left.right = new Node(8);
		System.out.println(tree.isPerfect());
	}

}
