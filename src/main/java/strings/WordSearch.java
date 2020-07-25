package strings;

/**
 * <b>Word Search</b>
 * <p/>
 * Given a 2D board and a word, find if the word exists in the grid.
 * <p/>
 * board = [ <br>
 * ['A','B','C','E'], <br/>
 * ['S','F','C','S'], <br/>
 * ['A','D','E','E'] <br/>
 * ]
 * <p/>
 * Given word = "ABCCED", return true, given word = "SEE", return true. <br/>
 * Given word = "ABCB", return false.
 * <p>
 * Learning: The first solution approach I implemented did something similar as current solution. 
 * Main differences were
 * <ul>
 * <i>I was maintaining a second variable containing the values of backtrack path this was absolutely wrong</li>
 * <li>First condition on bracktraking was my exist loop , then I was doing the check</li>
 * <li></li>
 * </ul>
 * @author caruizag
 */
public class WordSearch {

  /**
   * 
   * @param board
   * @param word
   * @return
   */
  public static boolean exist(final char[][] board, final String word) {

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (findWord(board, word, 0, i, j)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * 
   * @param board
   * @param target
   *          If a final variable holds a reference to an object, then the state
   *          of the object may be changed by operations on the object, but the
   *          variable will always refer to the same object (this property of
   *          final is called non-transitivity)
   * @param current
   * @param x
   * @param y
   * @return true if the word matches.
   */
  private static boolean findWord(final char[][] board, final String target, int index, final int x,
      final int y) {

    if (index >= target.length()){
      return true;
    }
    if (x >= board.length || x < 0 || y >= board[0].length || y < 0|| board[x][y] != target.charAt(index)) {
      return false;
    }
    board[x][y] = '#';
    // The magic word.
    boolean result = false;
    int[] rowOffsets = { 0, 1, 0, -1 };
    int[] colOffsets = { 1, 0, -1, 0 };
    for (int d = 0; d < 4; ++d) {
      result = findWord(board, target ,index+1, x + rowOffsets[d], y + colOffsets[d]);
      if (result)
        break;
    }
    board[x][y] = target.charAt(index);
    return result;
  }

}