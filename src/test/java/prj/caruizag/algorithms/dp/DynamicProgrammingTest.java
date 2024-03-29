package prj.caruizag.algorithms.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DynamicProgrammingTest {

  @Test
  public void coinChange2TestDeepLoopIn() {

    assertEquals(3, CoinChange.coinChangeTopDown(new int[] {1, 2, 5}, 11));
    assertEquals(20, CoinChange.coinChangeTopDown(new int[] {186, 419, 83, 408}, 6249));
    assertEquals(-1, CoinChange.coinChangeTopDown(new int[] {2}, 3));
    assertEquals(21,
        CoinChange.coinChangeTopDown(new int[] {58, 92, 387, 421, 194, 208, 231}, 7798));
    assertEquals(16, CoinChange.coinChangeTopDown(new int[] {389, 46, 222, 352, 4, 250}, 5343));

    assertEquals(42, CoinChange.coinChangeTopDown(new int[] {1, 5, 10, 25}, 978));
  }



}
