package prj.caruizag.algorithms.dp;

public class Knapsack {

  public static class Item {
    private final int weight;
    private final int value;

    public Item(int weight, int value) {
      this.weight = weight;
      this.value = value;
    }
  }

  public int knapsack(Item[] items, int maxWeight) {
    return knapsackHelper(items, maxWeight, 0);
  }

  private int knapsackHelper(Item[] items, int maxWeight, int currentIndex) {
    if (currentIndex == items.length) {
      return 0;
    }
    if (maxWeight - items[currentIndex].weight < 0) {
      return knapsackHelper(items, maxWeight, currentIndex + 1); // Skip
    }
    // Find the maximum of including and not
    // including the current item
    return Math.max(knapsackHelper(items, maxWeight - items[currentIndex].weight, currentIndex + 1)
        + items[currentIndex].value, knapsackHelper(items, maxWeight, currentIndex + 1));

  }

}
