package com.mk.programiz;

public class BinarySearchTree {

	Node root;

	public BinarySearchTree() {
		root = null;
	}

	void insert(int key) {
		root = insertKey(root, key);
	}

	Node insertKey(Node node, int key) {
		if (node == null) {
			node = new Node(key);
			return node;
		}

		if (key < node.item) {
			node.left = insertKey(node.left, key);
		} else if (key > node.item) {
			node.right = insertKey(node.right, key);
		}
		return node;
	}

	void inOrder() {
		System.out.print("\n");
		inOrderTraversal(root);
	}

	void inOrderTraversal(Node node) {
		if (node == null) {
			return;
		}
		inOrderTraversal(node.left);
		System.out.print(node.item + " -> ");
		inOrderTraversal(node.right);
	}

	// find in order successor
	int minValue(Node node) {
		int minv = node.item;
		while (node.left != null) {
			minv = node.left.item;
			node = node.left;
		}
		return minv;
	}

	void delete(int key) {
		root = deleteKey(root, key);
	}

	Node deleteKey(Node node, int key) {

		if (node == null) {
			return node;
		}

		// find node to delete
		if (key < node.item) {
			node.left = deleteKey(node.left, key);
		} else if (key > node.item) {
			node.right = deleteKey(node.right, key);
		} else {

			// node wit 0 or 1 child
			if (node.left == null) {
				return node.right;
			} else if (node.right == null) {
				return node.left;
			}

			// node with 2 child
			node.item = minValue(node.right);

			//delete successor
			node.right = deleteKey(node.right, node.item);

		}
		return node;
	}

	public static void main(String[] args) {

		BinarySearchTree tree = new BinarySearchTree();

		tree.insert(8);
		tree.insert(4);
		tree.insert(1);
		tree.insert(6);
		tree.insert(7);
		tree.insert(10);
		tree.insert(14);

		tree.inOrder();

		tree.delete(4);
		tree.inOrder();
	}
}
