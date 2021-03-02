package com.mk.programiz;

public class FullBinaryTree {

	Node root;

	public FullBinaryTree() {
		root = null;
	}

	boolean isFullBinaryTree(Node node) {

		// if no nodes
		if (node == null) {
			return true;
		}

		// if leaf node
		if (node.left == null && node.right == null) {
			return true;
		}

		// if node with left and right children
		if (node.left != null && node.right != null) {
			return isFullBinaryTree(node.left) && isFullBinaryTree(node.right);
		}
		return false;
	}

	public static void main(String[] args) {

		FullBinaryTree fbt = new FullBinaryTree();
		
		fbt.root = new Node(1);
		
		System.out.println(fbt.isFullBinaryTree(fbt.root));
		
		fbt.root.left = new Node(2);
		System.out.println(fbt.isFullBinaryTree(fbt.root));
		
		fbt.root.left.left = new Node(3);
		System.out.println(fbt.isFullBinaryTree(fbt.root));

		
		fbt.root.left.right = new Node(4);
		System.out.println(fbt.isFullBinaryTree(fbt.root));
		
		fbt.root.right = new Node(5);
		System.out.println(fbt.isFullBinaryTree(fbt.root));
	}

}
