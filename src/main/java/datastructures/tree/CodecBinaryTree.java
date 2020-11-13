package datastructures.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Serialize and deserialize a tree
 */
public class CodecBinaryTree {

  // Encodes a tree to a single string.
  public String serialize(BinaryTreeNode root) {
    List<Integer> result = new ArrayList<>();
    Queue<BinaryTreeNode> bsf = new LinkedList<>();
    bsf.add(root);
    while (!bsf.isEmpty()) {
      BinaryTreeNode current = bsf.poll();
      result.add(current != null ? current.getValue() : null);
      if (current != null) {
        bsf.add(current.getLeft());
        bsf.add(current.getRight());
      }

    }
    int heigth = treeHeight(root);
    return result.subList(0, (int) Math.pow(2, heigth) - 1).toString();
  }

  // Decodes your encoded data to tree.
  public BinaryTreeNode deserialize(String data) {
    BinaryTreeNode root = null;
    Integer[] values = getListOfValues(data);
    int level = 1;
    int index = 0;
    Queue<BinaryTreeNode> currentLevel = new LinkedList<>();
    Queue<BinaryTreeNode> prevLevel = new LinkedList<>();
    while (index < values.length) {
      while (index < values.length && (index == 0 || (level > 1 && index < Math.pow(2, level) - 1))) {
        currentLevel.add(new BinaryTreeNode(values[index]));
        index++;
      }
      level++;
      while (!currentLevel.isEmpty()) {
        BinaryTreeNode current = currentLevel.poll();
        if (prevLevel.isEmpty()) {
          root = current;
        } else {
          assignAsChild(prevLevel, current);
        }
        prevLevel.add(current);
      }
    }
    cleanNullNodes(root);
    return root;
  }

  private void cleanNullNodes(BinaryTreeNode node) {
    if (node == null) {
      return;
    }
    if (node.getLeft() != null && node.getLeft().getValue() == null) {
      node.setLeft(null);
    }
    if (node.getRight() != null && node.getRight().getValue() == null) {
      node.setRight(null);
    }

    cleanNullNodes(node.getLeft());
    cleanNullNodes(node.getRight());

  }

  private void assignAsChild(Queue<BinaryTreeNode> prevLevel, BinaryTreeNode current) {
    while (!prevLevel.isEmpty()) {
      BinaryTreeNode prev = prevLevel.peek();
      if (prev.getValue()==null || (prev.getLeft() != null && prev.getRight() != null)) {
        prevLevel.poll();
        continue;
      }
      if (prev.getLeft() == null) {
        prev.setLeft(current);
        break;
      }
      if (prev.getRight() == null) {
        prev.setRight(current);
        break;
      }
    }
  }

  private Integer[] getListOfValues(String data) {
    String[] values = data.replace("[", "").replace("]", "").replace(" ", "").split(",");
    Integer[] result = new Integer[values.length];
    int count = 0;
    for (String value : values) {
      Integer current = null;
      if (!"null".equalsIgnoreCase(value)) {
        current = Integer.valueOf(value);
      }
      result[count] = current;
      count++;
    }
    return result;
  }

  private int treeHeight(BinaryTreeNode node) {
    if (node == null)
      return 0;
    else {
      /* compute the depth of each subtree */
      int lDepth = treeHeight(node.getLeft());
      int rDepth = treeHeight(node.getRight());

      /* use the larger one */
      if (lDepth > rDepth)
        return (lDepth + 1);
      else
        return (rDepth + 1);
    }
  }

}