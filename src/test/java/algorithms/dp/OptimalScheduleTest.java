package algorithms.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import lombok.AllArgsConstructor;

public class OptimalScheduleTest {

  @Test
  public void testCorrectnessTopDown() {
    for (TestCase tc : getTestCases()) {
      OptimalSchedule schedule = new OptimalSchedule();
      assertEquals(tc.expected, schedule.maxProfit(tc.starts, tc.ends, tc.pays));
    }
  }


  private TestCase[] getTestCases() {
    TestCase tc1 = new TestCase(new int[] {1, 2, 3, 6, 7, 3, 4, 1},
        new int[] {4, 6, 5, 10, 11, 4, 5, 2}, new int[] {4, 5, 2, 5, 1, 1, 3, 3}, 11);
    TestCase tc3 = new TestCase(new int[] {1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12, 20},
        new int[] {4, 5, 6, 7, 9, 9, 10, 11, 12, 14, 16, 21},
        new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 5);
    return new TestCase[] {tc1, tc3};
  }

  @AllArgsConstructor
  private static class TestCase {
    int[] starts;
    int[] ends;
    int[] pays;
    int expected;

  }
}
