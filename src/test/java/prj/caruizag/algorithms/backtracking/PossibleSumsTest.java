package prj.caruizag.algorithms.backtracking;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

public class PossibleSumsTest {

  @Test
  @Ignore
  public void possibleSumsTestCorrecntess() {
    PossibleSums possibleSums = new PossibleSums();
    assertEquals(30008, possibleSums.possibleSums(new int[] {1, 2, 3}, new int[] {2, 3, 10000}));

  }
}
