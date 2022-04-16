package prj.caruizag.algorithms.graph;

import java.util.LinkedList;
import java.util.Queue;

import lombok.Builder;
import lombok.Data;

/**
 * @caruizag
 */
@Builder
public class Islands {

  private final int[][] grid;

  public Islands(int[][] grid) {
    this.grid = grid;
  }

  /**
   * Get the max area of an island , land is defined by 1 .
   * 
   * @return
   */
  public int maxAreaOfIsland() {
    int ans = 0;
    boolean[][] seen = new boolean[grid.length][grid[0].length];
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[0].length; c++) {
        ans = Math.max(ans, areaLand(seen, r, c));
      }
    }
    return ans;
  }

  /**
   * This operation modifies the map, anf fill the lakes with water.
   */
  public void fillLakesWithLand() {
    boolean[][] seen = new boolean[grid.length][grid[0].length];
    int columnLength = grid[0].length;
    int rowLength = grid.length;
    for (int r = 0; r < grid.length; r++) {
      if (grid[r][0] == 0 && !seen[r][0]) {
        markBoundaryRegion(r, 0, seen);
      }
      if (grid[r][columnLength - 1] == 0 && !seen[r][columnLength - 1]) {
        markBoundaryRegion(r, columnLength - 1, seen);
      }
    }

    for (int c = 0; c < columnLength; c++) {
      if (grid[0][c] == 0 && !seen[0][c]) {
        markBoundaryRegion(0, c, seen);
      }
      if (grid[rowLength - 1][c] == 0 && !seen[rowLength - 1][c]) {
        markBoundaryRegion(rowLength - 1, c, seen);
      }
    }

    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < columnLength; c++) {
        if (grid[r][c] == 0 && !seen[r][c]) {
          grid[r][c] = 1;
        }
      }
    }
  }

  private void markBoundaryRegion(int r, int c, boolean[][] seen) {
    Queue<Coordinate> queue = new LinkedList<>();
    queue.add(Coordinate.builder().x(r).y(c).build());
    seen[r][c] = true;
    while (!queue.isEmpty()) {
      Coordinate curr = queue.poll();
      final int[][] DIRS = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
      for (int[] dir : DIRS) {
        Coordinate next = Coordinate.builder().x(curr.getX() + dir[0]).y(curr.getY() + dir[1]).build();
        if (next.getX() >= 0 && next.getX() < grid.length && next.getY() >= 0 && next.getY() < grid[0].length
            && grid[next.getX()][next.getY()] == 0 && !seen[next.getX()][next.getY()]) {
          seen[next.getX()][next.getY()] = true;
          queue.add(next);
        }
      }
    }
  }

  @Data
  @Builder
  private static class Coordinate {
    private int x;
    private int y;
  }

  public int countOceanOrLakes() {
    boolean[][] seen = new boolean[grid.length][grid[0].length];
    int pounds = 0;
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[0].length; c++) {
        if (grid[r][c] == 0 && !seen[r][c]) {
          visitWater(seen, r, c);
          pounds++;
        }
      }
    }
    return pounds;
  }

  /**
   * Print the grid for visualization.
   */
  public void printMap() {
    for (int r = 0; r < grid.length; r++) {
      System.out.print(r + ": | ");
      for (int c = 0; c < grid[0].length; c++) {
        System.out.print(grid[r][c] + " ");
      }
      System.out.print("|");
      System.out.println("");
    }
  }

  /**
   * This function depends on seen to keep track of visited Coordinates in the
   * map.
   * 
   * @param seen
   * @param r
   * @param c
   */
  private void visitWater(boolean[][] seen, int r, int c) {
    if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || seen[r][c] || grid[r][c] == 1)
      return;
    seen[r][c] = true;
    visitWater(seen, r + 1, c);
    visitWater(seen, r - 1, c);
    visitWater(seen, r, c - 1);
    visitWater(seen, r, c + 1);
  }

  private int areaLand(boolean[][] seen, int r, int c) {
    if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || seen[r][c] || grid[r][c] == 0)
      return 0;
    seen[r][c] = true;
    return (1 + areaLand(seen, r + 1, c) + areaLand(seen, r - 1, c) + areaLand(seen, r, c - 1)
        + areaLand(seen, r, c + 1));
  }
}
