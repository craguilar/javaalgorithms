package prj.caruizag.algorithms.graph;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Ignore;
import org.junit.Test;

public class IslandsTest {

  private static final int[][] ONE_ISLAND_WITH_LAKE =
      new int[][] {{0, 1, 1, 1}, {0, 1, 0, 1}, {0, 1, 1, 1}};

  private static final int[][] ISLAND_WITH_BAY =
      new int[][] {{0, 1, 1, 1}, {0, 1, 0, 1}, {0, 0, 0, 1}};

  private static final int[][] TWO_ISLANDS_ONE_WITH_LAKE = new int[][] {{0, 1, 1, 1, 1},
      {0, 1, 0, 1, 0}, {0, 1, 1, 1, 0}, {0, 0, 0, 1, 0}, {1, 0, 0, 0, 0}};

  private static final int[][] ONE_ISLANDS_ONE_WITH_LAKE = new int[][] {{0, 1, 0, 1, 1},
      {0, 1, 0, 1, 0}, {0, 1, 1, 1, 0}, {0, 0, 0, 1, 0}, {1, 0, 0, 0, 0}};

  @Test
  public void maxAreaOfIsland() {

    assertEquals(8, Islands.builder().grid(ONE_ISLAND_WITH_LAKE).build().maxAreaOfIsland());
    assertEquals(6, Islands.builder().grid(ISLAND_WITH_BAY).build().maxAreaOfIsland());
    assertEquals(10, Islands.builder().grid(TWO_ISLANDS_ONE_WITH_LAKE).build().maxAreaOfIsland());
  }

  @Test
  public void countOceanOrLakesTestCorrectness() {
    assertEquals(2, Islands.builder().grid(ONE_ISLAND_WITH_LAKE).build().countOceanOrLakes());
    assertEquals(1, Islands.builder().grid(ISLAND_WITH_BAY).build().countOceanOrLakes());
    assertEquals(2, Islands.builder().grid(TWO_ISLANDS_ONE_WITH_LAKE).build().countOceanOrLakes());
  }

  @Test
  public void maxAreaOfContinentWithNoWaterInBorders() {
    // We clone this array to avoid modifyin the ref
    int[][] input =
        Arrays.stream(TWO_ISLANDS_ONE_WITH_LAKE).map(int[]::clone).toArray(int[][]::new);
    Islands map = Islands.builder().grid(input).build();
    map.fillLakesWithLand();
    assertEquals(11, map.maxAreaOfIsland());
    assertEquals(1, map.countOceanOrLakes());
  }

  @Test
  @Ignore
  public void maxAreaOfContinentWaterInBorders() {
    Islands map = Islands.builder().grid(ONE_ISLANDS_ONE_WITH_LAKE).build();
    map.fillLakesWithLand();
    assertEquals(11, map.maxAreaOfIsland());
    assertEquals(1, map.countOceanOrLakes());
  }

}
