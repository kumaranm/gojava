package com.mk.programiz;

class Node {

	int item;
	Node left, right;

	public Node(int d) {
		item = d;
		left = right = null;
	}
}

public class BinaryTree {

	Node root;

	BinaryTree() {
		root = null;
	}

	void preOrder(Node node) {
		// Root L R

		if (node == null) {
			return;
		}

		System.out.print(node.item + " -> ");
		preOrder(node.left);
		preOrder(node.right);
	}

	void postOrder(Node node) {
		// L R Root

		if (node == null) {
			return;
		}

		postOrder(node.left);
		postOrder(node.right);

		System.out.print(node.item + " -> ");
	}

	void inOrder(Node node) {
		// L Root R

		if (node == null) {
			return;
		}

		inOrder(node.left);
		System.out.print(node.item + " -> ");
		inOrder(node.right);

	}

	public static void main(String[] args) {

		BinaryTree tree = new BinaryTree();

		tree.root = new Node(1);
		tree.root.left = new Node(12);
		tree.root.right = new Node(9);

		tree.root.left.left = new Node(5);
		tree.root.left.right = new Node(6);

		System.out.print("\nInOrder Traversal   -> ");
		tree.inOrder(tree.root);

		System.out.print("\nPreOrder Traversal  -> ");
		tree.preOrder(tree.root);

		System.out.print("\nPostOrder Traversal -> ");
		tree.postOrder(tree.root);
	}

}
