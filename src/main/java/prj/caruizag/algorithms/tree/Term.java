package prj.caruizag.algorithms.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 
 * There are two kinds of terms: atomic term and compound term. Atomic term is just a string
 * literal, for example "abc", "123", "x1" etc. Compound term has a name and one or more arguments,
 * each argument is a term (can be either atomic or compound). For example: "abc(1, x1)",
 * "a(b, c(x1, x2))", "a(b, c(x1, x2(c1, c2, c3)))". First define a Term class, which should have
 * some basic properties like whether it is atomic or compound, the name, arguments (if it is
 * compound). Then write a parsing function whose input is a string and returns a term object.
 * 
 * Example 1) abc(c(1), 1, x1) Node { name="abc" , atomicValues=[1,x1] childCompunds = []}
 * 
 * Example 2) Node { name="a" , atomicValues=[b] childCompunds ={xNextNode}} , xNextNode ={name="c",
 * atomicValues=[x1,x2} childCompunds =[]}
 * 
 * Example 3) a(b(x3), c(x1, x2)) Example 4) abc(c(1), 1, x1) Compund compud = new
 * Compund("abc(c(1), 1, x1)"); com.get(0) = Compund{value=c,childCompunds={1, isAtomic=true}}
 * 
 */
public class Term {
  String value;
  List<Term> childTerms = new ArrayList<>();


  public static void main(String[] args) {

    isListEquals(Arrays.asList("abc", "c", "1", "x1", "1"), buildTerms("abc(c(1), 1, x1)").bfs());
    isListEquals(Arrays.asList("abc", "1", "x1"), buildTerms("abc(1, x1)").bfs());
    isListEquals(Arrays.asList("a", "b", "c", "x3", "x1"), buildTerms("a(b(x3), c(x1))").bfs());
    isListEquals(Arrays.asList("a", "b", "c", "x1", "x2", "c1", "c2", "c3"),
        buildTerms("a(b, c(x1, x2(c1, c2, c3)))").bfs());
    isListEquals(Arrays.asList("a", "b", "c", "d", "x1", "x2", "c1", "c2", "c3"),
        buildTerms("a(b, c(x1, x2(c1, c2, c3)),d)").bfs());
    System.out.println("Success!");
  }

  public Term(String value) {
    this.value = value;
  }

  public static Term buildTerms(String value) {
    // DFS approach
    int nextOpenP = value.indexOf("(");
    int lastCloseP = value.lastIndexOf(")");
    if (nextOpenP == -1) {
      return null; // No more childs of any type
    }
    String name = value.substring(0, nextOpenP);
    Term root = new Term(name);
    // Child tokenization - this is the most complex piece to handle compounds properly
    String childStr = value.substring(nextOpenP + 1, lastCloseP);
    while (childStr.length() > 0) {
      String child = childStr.split(",")[0];
      int begin = 0;
      if (child.contains("(") && !child.contains(")")) { // It means is a COMPLEX compund
        lastCloseP = childStr.lastIndexOf(")");
        child = childStr.substring(0, lastCloseP + 1);
        begin = lastCloseP + 1;
      } else { // Atomic
        begin = begin + child.length();
      }
      Term childTerm = buildTerms(child.trim());
      if (childTerm == null) {
        childTerm = new Term(child.trim());
      }
      root.childTerms.add(childTerm);
      childStr = childStr.length() >= begin + 1 ? childStr.substring(begin + 1) : "";
    }
    // End of child tokenization
    return root;
  }

  public List<String> bfs() {
    List<String> bfs = new ArrayList<>();
    Queue<Term> level = new LinkedList<>();
    level.add(this);
    while (!level.isEmpty()) {
      Queue<Term> next = new LinkedList<>();
      while (!level.isEmpty()) {
        Term current = level.poll();
        bfs.add(current.value);
        for (Term child : current.childTerms) {
          next.add(child);
        }
      }
      while (!next.isEmpty()) {
        Term current = next.poll();
        level.add(current);
      }
    }
    return bfs;
  }

  private static boolean isListEquals(List<String> expected, List<String> result) {
    if (expected.size() != result.size()) {
      return false;
    }
    for (int i = 0; i < expected.size(); i++) {
      if (!expected.get(i).equalsIgnoreCase(result.get(i))) {
        throw new RuntimeException(
            "Incorrect result received [" + expected.get(i) + "] expected [" + result.get(i) + "]");
      }
    }
    return true;
  }
}
