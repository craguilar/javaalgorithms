package algs.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * I decided to start coding (even by copying some of the solutions) backgrating
 * problems because they are difficult for me to undertand and code.
 * 
 * @author caruizag
 *
 */
public class Parentheses {

  public static List<String> generateParenthesis(int n) {
    List<String> ans = new ArrayList<>();
    backtrack(ans, "", 0, 0, n);
    return ans;
  }

  /**
   * Ok , so what is happening here? It seems we are backtracking , but how?
   * Each time we call back track on firts if we will be appending a ( - the
   * reason why this of comes first is to keep the natural order of parentheses
   * alignment. After we have finished adding all opening parenthesis we can
   * start adding closing.
   * 
   * @param ans
   *          holds the return value . A new value will be added to this string
   *          every time we found the break condition .
   * @param cur
   *          contains the string that will be formed during backtracking for
   *          result.
   * @param open
   *          indicate how many open parentheses have been included on cur.
   * @param close
   *          indicate how many open parentheses have been included on cur.
   * @param max
   *          of parentheses.
   *
   */
  private static void backtrack(List<String> ans, String cur, int open, int close, int max) {
    if (max < 1) {
      return;
    } else if (cur.length() == max * 2) {
      ans.add(cur);
      return;
    }

    if (open < max) {
      backtrack(ans, cur + "(", open + 1, close, max);
    }

    if (close < open) {
      backtrack(ans, cur + ")", open, close + 1, max);
    }

  }
}