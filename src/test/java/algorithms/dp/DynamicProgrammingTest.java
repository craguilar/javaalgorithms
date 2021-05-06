package algorithms.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import algorithms.dp.DynamicProgramming.Item;

public class DynamicProgrammingTest {

  @Test
  public void coinChange2TestDeepLoopIn() {

    assertEquals(3, DynamicProgramming.coinChangeTopDown(new int[] { 1, 2, 5 }, 11));
    assertEquals(20, DynamicProgramming.coinChangeTopDown(new int[] { 186, 419, 83, 408 }, 6249));
    assertEquals(-1, DynamicProgramming.coinChangeTopDown(new int[] { 2 }, 3));
    assertEquals(21, DynamicProgramming.coinChangeTopDown(new int[] { 58, 92, 387, 421, 194, 208, 231 }, 7798));
    assertEquals(16, DynamicProgramming.coinChangeTopDown(new int[] { 389, 46, 222, 352, 4, 250 }, 5343));

    assertEquals(42, DynamicProgramming.coinChangeTopDown(new int[] { 1, 5, 10, 25 }, 978));
  }

  @Test
  @Ignore
  public void makeChangeTestCorrectness() {
    DynamicProgramming dp = new DynamicProgramming();
    assertEquals(3, dp.makeChange(21));
    assertEquals(1, dp.makeChange(10));
    assertEquals(4, dp.makeChange(22));
    assertEquals(2, dp.makeChange(70));
  }

  @Test
  public void knapsackTestCorrectness() {
    DynamicProgramming dp = new DynamicProgramming();

    Item item1 = new Item(2, 6);
    Item item2 = new Item(2, 10);
    Item item3 = new Item(3, 12);

    assertEquals(22, dp.knapsack(new Item[] { item1, item2, item3 }, 5));
    assertEquals(22, dp.knapsack(new Item[] { item1, item2, item3 }, 6));
    assertEquals(16, dp.knapsack(new Item[] { item1, item2, item3 }, 4));
  }
}