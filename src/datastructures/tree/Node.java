package datastructures.tree;

public class Node {
	private String value;
	private Node left;
	private Node right;

	public Node(String value) {
		super();
		this.value = value;
	}

	public Node(int value) {
		super();
		this.value = String.valueOf(Character.toChars(value));
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getLeft() {
		return left;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public Node getRight() {
		return right;
	}

}
