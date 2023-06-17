package prj.caruizag.algorithms.dp;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import prj.caruizag.algorithms.dp.Knapsack.Item;


public class KnapsackTest {

  @Test
  public void knapsackTestCorrectness() {
    Knapsack dp = new Knapsack();

    Item item1 = new Item(2, 6);
    Item item2 = new Item(2, 10);
    Item item3 = new Item(3, 12);

    assertEquals(22, dp.knapsack(new Item[] {item1, item2, item3}, 5));
    assertEquals(22, dp.knapsack(new Item[] {item1, item2, item3}, 6));
    assertEquals(16, dp.knapsack(new Item[] {item1, item2, item3}, 4));
  }
}
