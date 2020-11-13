package datastructures.tree;

public class BinaryTreeNode {
	private Integer value;
	private BinaryTreeNode left;
	private BinaryTreeNode right;

	public BinaryTreeNode(Integer value) {
		super();
		this.value = value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public void setLeft(BinaryTreeNode left) {
		this.left = left;
	}

	public BinaryTreeNode getLeft() {
		return left;
	}

	public void setRight(BinaryTreeNode right) {
		this.right = right;
	}

	public BinaryTreeNode getRight() {
		return right;
	}

}
