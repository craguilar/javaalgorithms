package datastructures.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Tree {

  private BinaryTreeNode root;
  private static int pIndex = 0;

  public Tree() {
    super();
  }

  public Tree(BinaryTreeNode root) {
    super();
    this.root = root;
  }

  /**
   * Build the tree from a sorted array
   * 
   * @param a
   *          sorted array
   */
  public Tree(int[] a) {
    root = sortedArraytoTree(a, 0, a.length - 1);
  }

  public Tree(int[] preOrder, int[] inOrder) {
    pIndex = 0;
    root = createTreeFromPreInOrder(preOrder, inOrder, pIndex, inOrder.length - 1);
  }

  /**
   * Example , PRE ORDER=[F, B, A, D, C, E, G, I, H] and IN ORDER=[A, B, C, D,
   * E, F, G, H, I]
   */
  private BinaryTreeNode createTreeFromPreInOrder(int[] preOrder, int[] inOrder, int left, int right) {
    int current = -1;
    BinaryTreeNode n;
    if (left > right)
      return null;
    // Get Current value in inOrder array.
    for (int i = 0; i < inOrder.length; i++) {
      if (preOrder[pIndex] == inOrder[i]) {
        current = i;
        break;
      }
    }
    n = new BinaryTreeNode(preOrder[pIndex++]);
    if (left == right)
      return n;
    n.setLeft(createTreeFromPreInOrder(preOrder, inOrder, left, current - 1));
    n.setRight(createTreeFromPreInOrder(preOrder, inOrder, current + 1, right));
    return n;
  }

  private BinaryTreeNode sortedArraytoTree(int[] a, int left, int right) {
    BinaryTreeNode n = null;
    int k = (left + right) / 2;
    if (right < left)
      return n;
    n = new BinaryTreeNode(a[k]);
    n.setLeft(sortedArraytoTree(a, left, k - 1));
    n.setRight(sortedArraytoTree(a, k + 1, right));
    return n;
  }

  /**
   * Implement a function to check if a binary tree is a binary search tree. A
   * binary search tree imposes the condition that, for all nodes, the left
   * children are less than or equal to the current node, which is less that all
   * the right nodes.
   * 
   * @return
   */
  public boolean isBST() {

    return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private boolean isBST(BinaryTreeNode node, int min, int max) {
    if (node == null)
      return true;
    if (node.getValue() <= min || node.getValue() > max)
      return false;
    if (!isBST(node.getLeft(), min, node.getValue()) || !isBST(node.getRight(), node.getValue(), max))
      return false;
    return true;
  }

  public static String serialize(BinaryTreeNode root) {
    StringBuilder sb = new StringBuilder();
    serializeHelper(root, sb);
    return removeLastCharFromString(sb.toString());
  }

  public static BinaryTreeNode deserialize(String str) {
    List<String> values = new ArrayList<>(Arrays.asList(str.split(",")));
    return deserializeHelper(values);
  }

  private static void serializeHelper(BinaryTreeNode node, StringBuilder sb) {
    if (node == null) {
      sb.append("null,");
      return;
    }
    sb.append(node.getValue() + ",");
    serializeHelper(node.getLeft(), sb);
    serializeHelper(node.getRight(), sb);
  }

  public static BinaryTreeNode deserializeHelper(List<String> values) {
    if("null".equalsIgnoreCase(values.get(0))){
      values.remove(0);
      return null;
    }
    BinaryTreeNode node = new BinaryTreeNode(Integer.valueOf(values.get(0)));
    values.remove(0);
    BinaryTreeNode left   = deserializeHelper(values);
    BinaryTreeNode right  = deserializeHelper(values);
    node.setLeft(left);
    node.setRight(right);
    return node ;
  }

  // -- Tree trasversal
  public String preorderTraversal() {
    /*
     * root -> left -> right Display the data part of root element (or current
     * element) Traverse the left subtree by recursively calling the pre-order
     * function. Traverse the right subtree by recursively calling the pre-order
     * function.
     */
    StringBuffer result = new StringBuffer();
    printTrasversal(root, "PRE", result);
    return removeLastCharFromString(result.toString());
  }

  public String inorderTransversal() {
    /*
     * left ->root ->right Traverse the left subtree by recursively calling the
     * in-order function. Display the data part of root element (or current
     * element). Traverse the right subtree by recursively calling the in-order
     * function.
     */
    StringBuffer result = new StringBuffer();
    printTrasversal(root, "IN", result);
    return removeLastCharFromString(result.toString());
  }

  public String postorderTrasversal() {
    /* left ->right ->root */
    StringBuffer result = new StringBuffer();
    printTrasversal(root, "POS", result);
    return removeLastCharFromString(result.toString());
  }

  private void printTrasversal(BinaryTreeNode root, String mode, StringBuffer sb) {
    if (root == null) {
      return;
    }
    if ("PRE".equals(mode)) {
      System.out.print(root.getValue() + ",");
      sb.append(root.getValue() + ",");
    }
    // Pre Order
    printTrasversal(root.getLeft(), mode, sb);
    if ("IN".equals(mode)) {
      System.out.print(root.getValue() + ",");
      sb.append(root.getValue() + ",");
    }
    // In Order
    printTrasversal(root.getRight(), mode, sb);
    if ("POS".equals(mode)) {
      System.out.print(root.getValue() + ","); // Post Order
      sb.append(root.getValue() + ",");
    }

  }

  private static String removeLastCharFromString(String str) {
    if (str.isEmpty()) {
      return "";
    }
    // a -> ""
    // aa -> "a"
    // aaa -> "aa"
    return str.substring(0, str.length() - 1);
  }

  public BinaryTreeNode lowestCommonAntecesor(BinaryTreeNode root, int v1, int v2) {

    if (root.getValue() > v2 && root.getValue() > v1) {
      return lowestCommonAntecesor(root.getLeft(), v1, v2);
    } else if (root.getValue() < v1 && root.getValue() < v2) {
      return lowestCommonAntecesor(root.getRight(), v1, v2);
    }
    return root;

  }

  public int height(BinaryTreeNode root) {
    if (root == null)
      return 0;
    return 1 + Math.max(height(root.getLeft()), height(root.getRight()));
  }

  public int heightNonRecursive(BinaryTreeNode root) {
    int height = 0;
    Queue<BinaryTreeNode> toVisit;
    Queue<BinaryTreeNode> visited;
    BinaryTreeNode n;
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
  public void setRoot(BinaryTreeNode root) {
    this.root = root;
  }

  public BinaryTreeNode getRoot() {
    return root;
  }
}
