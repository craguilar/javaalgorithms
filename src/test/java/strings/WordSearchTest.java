package strings;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class WordSearchTest {

  @Test
  public void existTestWordExist() {
    char[][] board = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };

    boolean result = WordSearch.exist(board, "ABCCED");
    System.out.println("Result " + result);
    assertTrue(result);

  }

}