package algs.backtracking;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import java.util.List;

public class ParenthesesTest {

  @Test
  public void generateParenthesisTestSingle() {
    List<String> result = Parentheses.generateParenthesis(1);
    assertEquals(1, result.size());
    assertEquals("()",result.get(0));
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
}