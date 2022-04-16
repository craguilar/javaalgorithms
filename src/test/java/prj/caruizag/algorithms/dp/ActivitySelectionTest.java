package prj.caruizag.algorithms.dp;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import lombok.AllArgsConstructor;

public class ActivitySelectionTest {

  @Test
  public void maxActivitiesToCompleteCorrectness() {
    ActivitySelection as = new ActivitySelection();
    for (TestCase tc : getTestCases())
      assertEquals(tc.expected, as.maxActivitiesToComplete(tc.starts, tc.ends));

  }

  private TestCase[] getTestCases() {
    TestCase tc2 =
        new TestCase(new int[] {1, 2, 3, 6, 7, 3, 4, 1}, new int[] {4, 6, 5, 10, 11, 4, 5, 2}, 4);
    TestCase tc3 = new TestCase(new int[] {1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12, 20},
        new int[] {4, 5, 6, 7, 9, 9, 10, 11, 12, 14, 16, 21}, 5);
    return new TestCase[] {tc2, tc3};
  }

  @AllArgsConstructor
  private static class TestCase {
    int[] starts;
    int[] ends;
    int expected;

  }
}
