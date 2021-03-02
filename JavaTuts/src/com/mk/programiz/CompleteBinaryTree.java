package com.mk.programiz;

public class CompleteBinaryTree {

	Node root;

	public CompleteBinaryTree() {
		root = null;
	}

	int noOfNodes(Node node) {
		if (node == null) {
			return 0;
		}

		return (1 + noOfNodes(node.left) + noOfNodes(node.right));
	}

	boolean isCompleteBinaryTree(Node node, int index, int noOfNodes) {

		if (node == null) {
			return true;
		}

		if (index >= noOfNodes) {
			return false;
		}

		return isCompleteBinaryTree(node.left, 2 * index + 1, noOfNodes)
				&& isCompleteBinaryTree(node.right, 2 * index + 2, noOfNodes);
	}

	public static void main(String[] args) {

		CompleteBinaryTree tree = new CompleteBinaryTree();

		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
//		tree.root.left.left = new Node(4);
//		tree.root.left.right = new Node(5);

		//false
//		tree.root.right.left = new Node(8);
//		tree.root.right.right = new Node(6);
		
		int index = 0;
		int num = tree.noOfNodes(tree.root);
		System.out.println(tree.isCompleteBinaryTree(tree.root, index, num));

	}

}
