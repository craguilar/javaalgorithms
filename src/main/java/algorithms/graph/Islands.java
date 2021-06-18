package algorithms.graph;

import lombok.Builder;

/**
 * @caruizag
 */
@Builder
public class Islands {

  private final int[][] grid;

  public Islands(int[][] grid) {
    this.grid = grid;
  }

  public int maxAreaOfIsland() {
    int ans = 0;
    boolean[][] seen = new boolean[grid.length][grid[0].length];
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[0].length; c++) {
        ans = Math.max(ans, areaLand(this.grid, seen, r, c));
      }
    }
    return ans;
  }

  public int countOceanOrLakes() {
    boolean[][] seen = new boolean[grid.length][grid[0].length];
    int pounds = 0;
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[0].length; c++) {
        if (grid[r][c] == 0 && !seen[r][c]) {
          areaOceansOrLakes(this.grid, seen, r, c);
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
      System.out.print("| ");
      for (int c = 0; c < grid[0].length; c++) {
        System.out.print(grid[r][c] + " ");
      }
      System.out.print("|");
      System.out.println("");
    }
  }

  private void areaOceansOrLakes(int[][] grid, boolean[][] seen, int r, int c) {
    if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || seen[r][c] || grid[r][c] == 1)
      return;
    seen[r][c] = true;
    areaOceansOrLakes(grid, seen, r + 1, c);
    areaOceansOrLakes(grid, seen, r - 1, c);
    areaOceansOrLakes(grid, seen, r, c - 1);
    areaOceansOrLakes(grid, seen, r, c + 1);
  }

  private int areaLand(int[][] grid, boolean[][] seen, int r, int c) {
    if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || seen[r][c] || grid[r][c] == 0)
      return 0;
    seen[r][c] = true;
    return (1 + areaLand(grid, seen, r + 1, c) + areaLand(grid, seen, r - 1, c) + areaLand(grid, seen, r, c - 1)
        + areaLand(grid, seen, r, c + 1));
  }
}
