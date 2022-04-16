package prj.caruizag.algorithms.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

  public int longestValidParentheses(String s) {
    boolean[] found = new boolean[s.length()];

    int index = s.indexOf("()", 0);
    while (index >= 0) {
      found[index] = true;
      found[index + 1] = true;
      index = s.indexOf("()", index + 1);

    }
    // This expansion needs to be more inteligent
    int pivot = 0;
    while (pivot < s.length()) {
      if (found[pivot]) {
        pivot = expand(s, found, pivot);
      } else {
        pivot++;
      }
    }
    int max = 0;
    int count = 0;
    for (int i = 0; i < s.length(); i++) {
      if (found[i]) {
        count++;
      } else {
        max = Math.max(max, count);
        count = 0;
      }
    }
    return Math.max(max, count);
  }

  /**
   * From Leetcode and for my reference.
   * 
   * @param s
   * @return
   */
  public int longestValidParenthesesWithStack(String s) {
    int maxans = 0;
    Stack<Integer> stack = new Stack<>();
    stack.push(-1);
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(') {
        stack.push(i);
      } else {
        stack.pop();
        if (stack.empty()) {
          stack.push(i);
        } else {
          maxans = Math.max(maxans, i - stack.peek());
        }
      }
    }
    return maxans;
  }

  private int expand(String s, boolean[] found, int begin) {
    int end = begin + 1;
    // Expand to last valid index
    while (begin >= 0 && end < s.length() && s.charAt(begin) == '(' && s.charAt(end) == ')') {
      found[begin] = true;
      found[end] = true;
      while (begin >= 0 && found[begin]) {
        begin--;
      }
      while (end < s.length() && found[end]) {
        end++;
      }
    }
    return end;
  }

  public boolean isValid(String str) {

    Stack<Character> queue = new Stack<>();
    int n = str.length();
    int current = 0;
    while (current < n) {
      char c = str.charAt(current);
      if (c == '(') {
        queue.push(c);
      } else if (c == ')' && !queue.isEmpty()) {
        queue.pop();
      } else if (c == ')') { // Supports any invalid char we might enter
        return false;
      }
      current++;
    }
    return queue.isEmpty();
  }

  /**
   * Ok , so what is happening here? It seems we are backtracking , but how? Each
   * time we call back track on firts if we will be appending a ( - the reason why
   * this of comes first is to keep the natural order of parentheses alignment.
   * After we have finished adding all opening parenthesis we can start adding
   * closing.
   * 
   * @param ans   holds the return value . A new value will be added to this
   *              string every time we found the break condition .
   * @param cur   contains the string that will be formed during backtracking for
   *              result.
   * @param open  indicate how many open parentheses have been included on cur.
   * @param close indicate how many open parentheses have been included on cur.
   * @param max   of parentheses.
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