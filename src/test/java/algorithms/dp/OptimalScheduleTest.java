package algorithms.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OptimalScheduleTest {

  @Test
  public void testCorrectness() {
    int[] starts = new int[] { 1, 2, 3, 6, 7, 3, 4, 1 };
    int[] ends = new int[] { 4, 6, 5, 10, 11, 4, 5, 2 };
    int[] pays = new int[] { 4, 5, 2, 5, 1, 1, 3, 3 };

    OptimalSchedule schedule = new OptimalSchedule();
    assertEquals(11, schedule.maxProfit(starts, ends, pays));
  }
}
