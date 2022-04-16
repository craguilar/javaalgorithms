package prj.caruizag.datastructures.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

  private String value;

  public List<TreeNode> children = new ArrayList<>();

  public TreeNode(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

}
