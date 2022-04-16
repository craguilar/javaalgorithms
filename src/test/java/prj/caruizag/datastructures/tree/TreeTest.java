package prj.caruizag.datastructures.tree;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TreeTest {

  @Test
  public void testPrintPreOrder() {
    Tree underTest = new Tree();
    underTest.setRoot(getValidBinaryTree());
    String actual = underTest.preorderTraversal();
    assertEquals("1,2,3,4,5", actual);

    underTest = new Tree(getSmallValidBinaryTree());
    actual = underTest.preorderTraversal();
    assertEquals("1,2,3", actual);
  }

  @Test
  public void testPrintInOrder() {
    Tree underTest = new Tree();
    underTest.setRoot(getValidBinaryTree());
    String actual = underTest.inorderTransversal();
    assertEquals("2,1,4,3,5", actual);

    underTest = new Tree(getSmallValidBinaryTree());
    actual = underTest.inorderTransversal();
    assertEquals("2,1,3", actual);
  }

  @Test
  public void testPrintPostOrder() {
    Tree underTest = new Tree(getValidBinaryTree());
    String actual = underTest.postorderTrasversal();
    assertEquals("2,4,5,3,1", actual);

    underTest = new Tree(getSmallValidBinaryTree());
    actual = underTest.postorderTrasversal();
    assertEquals("2,3,1", actual);
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

  @Test
  public void zigzagLevelOrderTest() {
    List<List<Integer>> result = Tree.zigzagLevelOrder(getValidZigZagTree());
    assertEquals(Arrays.asList(Arrays.asList(3), Arrays.asList(20, 9), Arrays.asList(1, 2, 15, 7),
        Arrays.asList(31, 30, 10, 8)), result);
  }

  private BinaryTreeNode getSmallValidBinaryTree() {
    BinaryTreeNode root = new BinaryTreeNode(1);
    BinaryTreeNode node2 = new BinaryTreeNode(2);
    BinaryTreeNode node3 = new BinaryTreeNode(3);
    root.setLeft(node2);
    root.setRight(node3);
    return root;
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

  private BinaryTreeNode getValidZigZagTree() {
    BinaryTreeNode root = new BinaryTreeNode(3);
    BinaryTreeNode node2 = new BinaryTreeNode(9);
    BinaryTreeNode node3 = new BinaryTreeNode(20);
    BinaryTreeNode node4 = new BinaryTreeNode(1);
    BinaryTreeNode node5 = new BinaryTreeNode(2);
    BinaryTreeNode node6 = new BinaryTreeNode(15);
    BinaryTreeNode node7 = new BinaryTreeNode(7);
    BinaryTreeNode node8 = new BinaryTreeNode(8);
    BinaryTreeNode node9 = new BinaryTreeNode(10);
    BinaryTreeNode node10 = new BinaryTreeNode(30);
    BinaryTreeNode node11 = new BinaryTreeNode(31);
    root.setLeft(node2);
    root.setRight(node3);
    node2.setLeft(node4);
    node2.setRight(node5);
    node3.setLeft(node6);
    node3.setRight(node7);
    node4.setLeft(node8);
    node4.setRight(node9);
    node6.setLeft(node10);
    node6.setRight(node11);
    return root;
  }
}
