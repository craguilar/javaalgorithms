package algorithms.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import datastructures.tree.TreeNode;

public class CutLeaves {

  public static List<Set<TreeNode>> fallingLeaves(TreeNode root) {
    List<Set<TreeNode>> leaves = new ArrayList<>();
    dfs(root, leaves);
    return leaves;
  }

  private static int dfs(TreeNode node, List<Set<TreeNode>> leaves) { /// {G,I,D} , {H} , {C}
    if (node == null) {
      return -1;
    }
    int level = 0;
    for (TreeNode child : node.children) {
      level = Math.max(dfs(child, leaves), level); // level = 2
    }
    if (leaves.isEmpty() || leaves.size() <= level) {
      leaves.add(new HashSet<TreeNode>());
    }
    leaves.get(level).add(node);
    return level + 1;
  }

}
