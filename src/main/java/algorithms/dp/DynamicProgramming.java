package algorithms.dp;

import java.util.HashMap;

public class DynamicProgramming {

  private int minChange;
  private HashMap<Integer, Integer> cache = new HashMap<>();

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
      return knapsackHelper(items, maxWeight, currentIndex + 1);
    }
    // Find the maximum of including and not
    // including the current item
    return Math.max(
        knapsackHelper(items, maxWeight - items[currentIndex].weight, currentIndex + 1) + items[currentIndex].value,
        knapsackHelper(items, maxWeight, currentIndex + 1));

  }

  /**
   * DO NOT USE :) ! Just for testing purpose , this is not optinal nor it will
   * ever be + I hate to share class variables :) . Given an integer
   * representing a given amount of change, write a function to compute the
   * total number of coins required to make that amount of change. You can
   * assume that there is always a 1¢ coin. eg. (assuming American coins: 1, 5,
   * 10, and 25 cents) makeChange(1)
   * 
   * @param n
   * @return
   */
  public int makeChange(int n) {
    minChange = Integer.MAX_VALUE;
    cache.clear();
    makeChangeHelper(n, 0, 0);
    return minChange;
  }

  private void makeChangeHelper(int n, int currentSum, int numOfCoins) {
    if (cache.get(currentSum) != null) {
      minChange = Math.min(cache.get(currentSum), numOfCoins);
      return;
    }
    if (currentSum > n) {
      return;
    }
    if (currentSum == n) {
      minChange = Math.min(numOfCoins, minChange);
      cache.put(currentSum, minChange);
      return;
    }
    // System.out.println("Remains for " + (n - currentSum) + " numOfCoins " +
    // numOfCoins);
    makeChangeHelper(n, currentSum + 1, numOfCoins + 1);
    makeChangeHelper(n, currentSum + 5, numOfCoins + 1);
    makeChangeHelper(n, currentSum + 10, numOfCoins + 1);
    makeChangeHelper(n, currentSum + 25, numOfCoins + 1);

  }

  /**
   * You are given coins of different denominations and a total amount of money
   * amount. Write a function to compute the fewest number of coins that you
   * need to make up that amount. If that amount of money cannot be made up by
   * any combination of the coins, return -1.
   * 
   * @param coins
   * @param amount
   * @return If that amount of money cannot be made up by any combination of the
   *         coins, return -1.
   */
  public static int coinChangeTopDown(int[] coins, int amount) {
    if (amount < 1) {
      return 0;
    }
    return coinChangeTopDown(coins, amount, new int[amount]);
  }

  private static int coinChangeTopDown(int[] coins, int rem, int[] count) {
    if (rem < 0) {
      return -1;
    }
    if (rem == 0) {
      return 0;
    }
    if (count[rem - 1] != 0) {
      return count[rem - 1];
    }
    int min = Integer.MAX_VALUE;
    for (int coin : coins) {
      int res = coinChangeTopDown(coins, rem - coin, count);
      if (res >= 0 && res < min) {
        min = 1 + res;
      }
    }
    count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
    return count[rem - 1];
  }

}