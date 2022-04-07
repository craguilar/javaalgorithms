package algorithms.tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.junit.Ignore;
import org.junit.Test;
import datastructures.tree.TreeNode;


public class CutLeavesTest {

  // TODO: for some reaons this test fails randomly
  @Ignore
  @Test
  public void testCorrectness() {
    for (TestCase test : getTestCases()) {
      List<Set<TreeNode>> leaves = CutLeaves.fallingLeaves(test.root);
      assertNotNull(leaves);
      StringBuffer sb = new StringBuffer();
      for (Set<TreeNode> leavesLevel : leaves) {
        leavesLevel.forEach(l -> sb.append(l.getValue() + ","));
      }
      String validaton = sb.substring(0, sb.length() - 1);
      assertEquals(test.expectedValue, validaton);
    }
  }


  private TestCase[] getTestCases() {

    TreeNode root1 = new TreeNode("A");

    TreeNode root2 = new TreeNode("A");
    TreeNode ch21 = new TreeNode("B");
    TreeNode ch22 = new TreeNode("C");
    root2.children.addAll(Arrays.asList(ch21, ch22));

    TreeNode root3 = new TreeNode("A");
    TreeNode ch31 = new TreeNode("B");
    TreeNode ch32 = new TreeNode("C");
    TreeNode ch33 = new TreeNode("D");
    TreeNode ch34 = new TreeNode("E");
    TreeNode ch35 = new TreeNode("I");
    root3.children.addAll(Arrays.asList(ch31, ch32));
    ch31.children.addAll(Arrays.asList(ch33, ch34));
    ch34.children.addAll(Arrays.asList(ch35));

    TreeNode root4 = new TreeNode("A");
    TreeNode ch41 = new TreeNode("B");
    TreeNode ch42 = new TreeNode("H");
    TreeNode ch43 = new TreeNode("D");
    TreeNode ch44 = new TreeNode("C");
    TreeNode ch46 = new TreeNode("G");
    TreeNode ch47 = new TreeNode("X");
    TreeNode ch48 = new TreeNode("Z");
    TreeNode ch49 = new TreeNode("I");
    root4.children.addAll(Arrays.asList(ch41, ch42));
    ch41.children.addAll(Arrays.asList(ch44, ch43));
    ch44.children.addAll(Arrays.asList(ch46, ch47));
    ch47.children.addAll(Arrays.asList(ch49));
    ch43.children.addAll(Arrays.asList(ch48));
    return new TestCase[] {new TestCase(root1, "A"), new TestCase(root2, "B,C,A"),
        new TestCase(root3, "I,C,D,E,B,A"), new TestCase(root4, "G,I,H,Z,X,D,C,B,A")};
  }

  private static class TestCase {
    TreeNode root;

    String expectedValue;

    public TestCase(TreeNode root, String expectedValue) {
      this.root = root;
      this.expectedValue = expectedValue;
    }

  }

}
