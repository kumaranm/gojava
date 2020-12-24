package com.mk.programiz;

class BBTHeight {

	int height = 0;
}

public class BalancedBinaryTree {

	Node root;

	public BalancedBinaryTree() {
		root = null;
	}

	boolean isBalancedBinaryTree(Node node, BBTHeight h) {

		if (node == null) {
			h.height = 0;
			return true;
		}

		BBTHeight leftHighest = new BBTHeight();
		BBTHeight rightHighest = new BBTHeight();

		boolean l = isBalancedBinaryTree(node.left, leftHighest);
		boolean r = isBalancedBinaryTree(node.right, rightHighest);

		int leftHeight = leftHighest.height;
		int rightHeight = rightHighest.height;

		h.height = (leftHeight > rightHeight ? leftHeight : rightHeight) + 1;

		if ((leftHeight - rightHeight) >= 2 || (rightHeight - leftHeight) >= 2) {
			return false;
		}
		return l && r;
	}

	public static void main(String[] args) {
		
		BBTHeight height = new BBTHeight();
		
		BalancedBinaryTree tree = new BalancedBinaryTree();
		
		tree.root = new Node(1);

		tree.root.left = new Node(2);
//		tree.root.right = new Node(3);
		tree.root.left.left = new Node(4);
//		tree.root.left.right = new Node(5);
//		tree.root.right.right = new Node(6);

		
		System.out.println(tree.isBalancedBinaryTree(tree.root, height));
	}

}
