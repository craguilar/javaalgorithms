package prj.caruizag.algorithms.dequeue;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class RemoveSmallestPeaksInOrderTest {

  @Test
  public void testBruteForceCorrectness() {
    int[] test = new int[] {3, 5, 1, 4, 2};
    RemoveSmallestPeaksInOrder s = new RemoveSmallestPeaksInOrder();
    int[] result = s.solveBruteForce(test);
    assertArrayEquals(new int[] {4, 2, 5, 3, 1}, result);
  }

  @Test
  public void testImprovedBruteForceCorrectness() {
    int[] test = new int[] {3, 5, 1, 4, 2};
    RemoveSmallestPeaksInOrder s = new RemoveSmallestPeaksInOrder();
    int[] result = s.solveBruteForce(test);
    assertArrayEquals(new int[] {4, 2, 5, 3, 1}, result);
  }

  @Test
  public void testOptimalSolutionCorrectness() {

    RemoveSmallestPeaksInOrder s = new RemoveSmallestPeaksInOrder();

    assertArrayEquals(new int[] {4, 2, 5, 3, 1}, s.solveOptimized(new int[] {3, 5, 1, 4, 2}));
    assertArrayEquals(new int[] {10, 9, 8}, s.solveOptimized(new int[] {10, 9, 8}));
    assertArrayEquals(new int[] {3, 2, 1}, s.solveOptimized(new int[] {1, 2, 3}));
  }

}
