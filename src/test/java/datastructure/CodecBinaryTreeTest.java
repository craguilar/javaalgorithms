package datastructure;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import datastructures.tree.BinaryTreeNode;
import datastructures.tree.CodecBinaryTree;

public class CodecBinaryTreeTest {

  @Test
  public void serializeTestSimpleTest() {
    BinaryTreeNode root = getValidBinaryTree();
    CodecBinaryTree codec = new CodecBinaryTree();
    String result = codec.serialize(root);
    assertEquals("[1, 2, 3, null, null, 4, 5]", result);
  }

  @Test
  public void deserializeTestSimpleTest() {
    String tree = "[1, 2, 3, null, null, 4, 5]";
    CodecBinaryTree codec = new CodecBinaryTree();
    BinaryTreeNode result = codec.deserialize(tree);
    String strResult = codec.serialize(result);
    assertEquals(tree, strResult);

  }

  private BinaryTreeNode getValidBinaryTree() {
    BinaryTreeNode root = new BinaryTreeNode(1);
    BinaryTreeNode node2 = new BinaryTreeNode(2);
    BinaryTreeNode node3 = new BinaryTreeNode(3);
    BinaryTreeNode node4 = new BinaryTreeNode(4);
    BinaryTreeNode node5 = new BinaryTreeNode(5);
    root.setLeft(node2);
    root.setRight(node3);
    node3.setLeft(node4);
    node3.setRight(node5);
    return root;
  }
}
