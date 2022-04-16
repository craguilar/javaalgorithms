package prj.caruizag.algorithms.backtracking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import java.util.List;

public class ParenthesesTest {

  @Test
  public void generateParenthesisTestSingle() {
    List<String> result = Parentheses.generateParenthesis(1);
    assertEquals(1, result.size());
    assertEquals("()", result.get(0));
  }

  @Test
  public void generateParenthesisTestEmpty() {
    List<String> result = Parentheses.generateParenthesis(0);
    assertEquals(0, result.size());
  }

  @Test
  public void generateParenthesisTesTwoCombinations() {
    List<String> result = Parentheses.generateParenthesis(2);
    assertEquals(2, result.size());
  }

  @Test
  public void validParentheses() {
    Parentheses obj = new Parentheses();
    assertTrue(obj.isValid("()"));
    assertFalse(obj.isValid(")()())"));
    assertTrue(obj.isValid("((()))()()"));
    assertTrue(obj.isValid("((((()())()()))()(()))"));
    assertTrue(obj.isValid("(a+b)-c"));
  }

  @Test
  public void longestValidParenthesesTestCorrectness() {
    Parentheses obj = new Parentheses();
    assertEquals(2, obj.longestValidParentheses("()"));
    assertEquals(4, obj.longestValidParentheses(")()())"));
    assertEquals(10, obj.longestValidParentheses("((()))()()"));
    assertEquals(22, obj.longestValidParentheses("((((()())()()))()(()))"));

  }

}
