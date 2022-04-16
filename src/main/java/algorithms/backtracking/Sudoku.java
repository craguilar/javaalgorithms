package algorithms.backtracking;

import java.util.*;


public class Sudoku {

  public void solveSudoku(char[][] board) {
    Board game = new Board(board);
    int count = 0;
    for (int i = 0; i < board.length; i = i + 3) {
      for (int j = 0; j < board.length; j = j + 3) {
        char[][] grid = new char[3][3];
        for (int k = 0; k < 3; k++) {
          for (int l = 0; l < 3; l++) {
            grid[k][l] = board[i + k][j + l];
            if (board[i + k][j + l] != '.') {
              game.addValueToRow(i + k, Character.valueOf(board[i + k][j + l]));
              game.addValueToColumn(j + l, Character.valueOf(board[i + k][j + l]));
            }
          }
        }
        game.boxes[count++] = new Box(grid);
      }
    }
    // Backtrack
    solve(game, 0, 0);
  }

  private void solve(Board game, int x, int y) {
    if (game.isComplete()) {
      game.solved = true;
      return;
    }

    // I need to test placing the character in each
    // available spot , if it is valid
    if (game.grid[x][y] == '.') {
      int box = (x / 3) * 3 + (y / 3);
      for (int d = 1; d <= 9 && !game.solved; d++) {
        Character c = Character.valueOf((char) ('0' + d));
        if (!game.boxes[box].in.contains(c) && !game.containValueInRow(x, c)
            && !game.containValueInColumn(y, c)) {
          game.boxes[box].in.add(c);
          game.addValueToRow(x, c);
          game.addValueToColumn(y, c);
          game.grid[x][y] = c;
          placeNextNumbers(game, x, y);
          if (game.solved) {
            break;
          }
          if (!game.solved) {
            game.grid[x][y] = '.';
            game.removeValueToRow(x, c);
            game.removeValueToColumn(y, c);
            game.boxes[box].in.remove(c);
          }

        }
      }
    } else {
      placeNextNumbers(game, x, y);
    }
  }

  private void placeNextNumbers(Board game, int row, int col) {
    if (game.isComplete()) {
      game.solved = true;
      return;
    } else {
      if (col == 8)
        solve(game, row + 1, 0);
      else
        solve(game, row, col + 1);
    }
  }

  static class Board {
    Box[] boxes = new Box[9];
    char[][] grid = new char[9][9];
    boolean solved = false;
    Map<Integer, Set<Character>> rows = new HashMap<>();
    Map<Integer, Set<Character>> columns = new HashMap<>();

    public Board(char[][] grid) {
      this.grid = grid;
    }

    public boolean containValueInRow(int row, Character value) {
      return rows.get(row).contains(value);
    }

    public boolean containValueInColumn(int column, Character value) {
      return columns.get(column).contains(value);
    }

    public void addValueToRow(int row, Character value) {
      Set<Character> number = rows.getOrDefault(row, new HashSet<>());
      number.add(value);
      rows.put(row, number);
    }

    public void addValueToColumn(int column, Character value) {
      Set<Character> number = columns.getOrDefault(column, new HashSet<>());
      number.add(value);
      columns.put(column, number);
    }

    public void removeValueToRow(int row, Character value) {
      Set<Character> number = rows.getOrDefault(row, new HashSet<>());
      number.remove(value);
      rows.put(row, number);
    }

    public void removeValueToColumn(int column, Character value) {
      Set<Character> number = columns.getOrDefault(column, new HashSet<>());
      number.remove(value);
      columns.put(column, number);
    }

    public boolean isComplete() {
      int sum = 0;
      for (int i = 0; i < 9; i++) {
        sum += rows.get(i).size();
      }
      return sum == 81;
    }
  }

  static class Box {
    Set<Character> in = new HashSet<>();

    public Box(char[][] grid) {
      for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid.length; j++) {
          if (grid[i][j] != '.') {
            in.add(grid[i][j]);
          }
        }
      }
    }
  }

}
