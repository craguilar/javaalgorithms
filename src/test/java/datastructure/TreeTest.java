package datastructure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import datastructures.tree.BinaryTreeNode;
import datastructures.tree.Tree;

public class TreeTest {

  @Test
  public void testPrintPreOrder() {
    Tree underTest = new Tree();
    underTest.setRoot(getValidBinaryTree());
    String actual = underTest.preorderTraversal();
    assertEquals("1,2,3,4,5", actual);
  }

  @Test
  public void testPrintInOrder() {
    Tree underTest = new Tree();
    underTest.setRoot(getValidBinaryTree());
    String actual = underTest.inorderTransversal();
    assertEquals("2,1,4,3,5", actual);
  }

  @Test
  public void testPrintPostOrder() {
    Tree underTest = new Tree(getValidBinaryTree());
    String actual = underTest.postorderTrasversal();
    assertEquals("2,4,5,3,1", actual);
  }

  @Test
  public void serializeTestSimpleTest() {
    String result = Tree.serialize(getValidBinaryTree());
    assertEquals("1,2,null,null,3,4,null,null,5,null,null", result);
  }

  @Test
  public void deserializeTestSimpleTest() {
    String treeStr = "1,2,null,null,3,4,null,null,5,null,null";
    BinaryTreeNode root = Tree.deserialize(treeStr);
    String deserializeTreeStr = Tree.serialize(root);
    assertEquals(treeStr, deserializeTreeStr);

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
