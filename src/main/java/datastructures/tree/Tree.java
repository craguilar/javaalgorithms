package datastructures.tree;

import java.util.LinkedList;
import java.util.Queue;

public class Tree {
	Node root;
	private static int pIndex = 0;

	public Tree() {
		super();
	}

	public Tree(int[] a) {
		root = sortedArraytoTree(a, 0, a.length - 1);
	}

	public Tree(int[] preOrder, int[] inOrder) {
		pIndex = 0;
		root = createTreeFromPreInOrder(preOrder, inOrder, pIndex, inOrder.length - 1);
	}

	// PRE ORDER=[F, B, A, D, C, E, G, I, H]
	// IN ORDER=[A, B, C, D, E, F, G, H, I]
	private Node createTreeFromPreInOrder(int[] preOrder, int[] inOrder, int left, int right) {
		int current = -1;
		Node n;
		if (left > right)
			return null;
		// Get Current value in inOrder array.
		for (int i = 0; i < inOrder.length; i++) {
			if (preOrder[pIndex] == inOrder[i]) {
				current = i;
				break;
			}
		}
		n = new Node(preOrder[pIndex++]);
		if (left == right)
			return n;
		n.setLeft(createTreeFromPreInOrder(preOrder, inOrder, left, current - 1));
		n.setRight(createTreeFromPreInOrder(preOrder, inOrder, current + 1, right));
		return n;
	}

	private Node sortedArraytoTree(int[] a, int left, int right) {
		Node n = null;
		int k = (left + right) / 2;
		if (right < left)
			return n;
		n = new Node(Integer.toString(a[k]));
		n.setLeft(sortedArraytoTree(a, left, k - 1));
		n.setRight(sortedArraytoTree(a, k + 1, right));
		return n;
	}

	public boolean isBST() {
		// Implement a function to check if a binary tree is a binary search tree.
		// A binary search tree imposes the condition that, for all nodes, the left
		// children are less than or equal to the current node, which is less that
		// all
		// the right nodes.
		return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private boolean isBST(Node node, int min, int max) {
		if (node == null)
			return true;
		if (Integer.parseInt(node.getValue()) <= min || Integer.parseInt(node.getValue()) > max)
			return false;
		if (!isBST(node.getLeft(), min, Integer.parseInt(node.getValue()))
				|| !isBST(node.getRight(), Integer.parseInt(node.getValue()), max))
			return false;
		return true;
	}

	// -- Tree trasversal
	public void preorderTraversal() {
		/*
		 * root -> left -> right Display the data part of root element (or current
		 * element) Traverse the left subtree by recursively calling the pre-order
		 * function. Traverse the right subtree by recursively calling the pre-order
		 * function.
		 */
		printTrasversal(root, "PRE");
	}

	public void inorderTransversal() {
		/*
		 * left ->root ->right Traverse the left subtree by recursively calling the
		 * in-order function. Display the data part of root element (or current
		 * element). Traverse the right subtree by recursively calling the in-order
		 * function.
		 */
		printTrasversal(root, "IN");
	}

	public void postorderTrasversal() {
		/* left ->right ->root */
		printTrasversal(root, "POS");
	}

	private void printTrasversal(Node root, String mode) {
		if (root == null)
			return;
		if ("PRE".equals(mode))
			System.out.print(" " + root.getValue() + " ");// Pre Orden
		printTrasversal(root.getLeft(), mode);
		if ("IN".equals(mode))
			System.out.print(" " + root.getValue() + " "); // In Orden
		printTrasversal(root.getRight(), mode);
		if ("POS".equals(mode))
			System.out.print(" " + root.getValue() + " "); // Post Orden

	}
	/*
	 * Node is defined as : class Node int data; Node left; Node right; 4 / \ 1 8
	 * \ / 2 7 \ 3 v1=1, v2=3
	 */

	public Node lca(Node root, int v1, int v2) {

		if (Integer.parseInt(root.getValue()) > v2 && Integer.parseInt(root.getValue()) > v1) {
			return lca(root.getLeft(), v1, v2);
		} else if (Integer.parseInt(root.getValue()) < v1 && Integer.parseInt(root.getValue()) < v2) {
			return lca(root.getRight(), v1, v2);
		}
		return root;

	}

	public int height(Node root) {
		if (root == null)
			return 0;
		return 1 + Math.max(height(root.getLeft()), height(root.getRight()));
	}

	public int heightNonRecursive(Node root) {
		int height = 0;
		Queue<Node> toVisit;
		Queue<Node> visited;
		Node n;
		// Initialization
		toVisit = new LinkedList<>();
		visited = new LinkedList<>();
		if (root != null)
			toVisit.add(root);
		while (!toVisit.isEmpty()) {
			while (!toVisit.isEmpty()) {
				n = toVisit.poll();
				visited.add(n);
			}
			while (!visited.isEmpty()) {
				n = visited.poll();
				if (n.getLeft() != null)
					toVisit.add(n.getLeft());
				if (n.getRight() != null)
					toVisit.add(n.getRight());
			}
			height++;
		}

		return height;
	}

	// -- Accessors
	public void setRoot(Node root) {
		this.root = root;
	}

	public Node getRoot() {
		return root;
	}
}
