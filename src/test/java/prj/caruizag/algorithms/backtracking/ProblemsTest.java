package prj.caruizag.algorithms.backtracking;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ProblemsTest {

  @Test
  public void letterCombinationsTestEmpty() {
    assertTrue(Problems.letterCombinations("").size() == 0);
  }
}
