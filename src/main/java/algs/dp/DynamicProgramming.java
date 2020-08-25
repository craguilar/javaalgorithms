package algs.dp;


public class DynamicProgramming {

  /**
   * Leasson learned : Top Down approach and static caches are likely to cause bug
   * 
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
      if (res >= 0 && res < min){
        min = 1 + res;
      }
    }
    count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
    return count[rem - 1];
  }

}