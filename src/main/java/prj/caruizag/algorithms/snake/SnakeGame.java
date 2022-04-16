package prj.caruizag.algorithms.snake;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/*
    rampras@microsoft.com
    SnakeGame:
    - Player , When it was played.
    - board is a matrix[][] - fixed size
    - Fruit is represented as 1 , else is 0.
    - This looks like this
    [[s,s,s],
     [s,s,s],
     [s,s,s]]
    - Inititial state of the board is given, snake starts at position 0,0 and initial
    snake length is 1
    - If a snake goes over a fruit it increases its length on 1.
    - When a user inputs a direction : L, R , U , D
        * With the new direction did the snake hit a wall or hit its body - end the game.
        * If there is a fruit 
           - Add the additional lenght to the tail.
           - Add 1 to the score.
    - Refresh moving the snake to last input direction by the user
        * With the current direction did the snake hit the wall or hit its body.
    - Game end , is add current score to the score table.
        * Pass the current session details ScoreTable.addGame()
    ScoreTable:
    - Keep track of the games played and its score. Ordered lis twith the scores.
    [[s,s,s],
     [0,0,0],
     [0,0,0]]
*/
public class SnakeGame {
  private static final char DIR_LEFT = 'L';
  private static final char DIR_RIGHT = 'R';
  private static final char DIR_UP = 'U';
  private static final char DIR_DOWN = 'D';
  private static final char[] POSITIONS_ARRAY = new char[] { DIR_RIGHT, DIR_UP, DIR_DOWN, DIR_LEFT };

  private SnakeBoard board;
  private Snake snake;
  private int currentDir;
  boolean endGame = false;
  private String player;
  int score;

  public static void main(String[] args) {
    ScoreTable scoreTable = new ScoreTable();
    SnakeGame game1 = new SnakeGame("carlos.ruiz");
    // I will use a random generator to select the next move
    Random positionIndex = new Random();
    while (!game1.endGame) {
      game1.refreshBoard();
      game1.currentDir = POSITIONS_ARRAY[positionIndex.nextInt(3 - 0) + 0];
    }
    scoreTable.addPlayedGame(new PlayedGame(game1.player, game1.score));
    // New game
    SnakeGame game2 = new SnakeGame("bill.gates");
    while (!game2.endGame) {

      game2.refreshBoard();
      game2.currentDir = POSITIONS_ARRAY[positionIndex.nextInt(3 - 0) + 0];
    }
    scoreTable.addPlayedGame(new PlayedGame(game2.player, game2.score));
    scoreTable.printScore();
  }

  public SnakeGame(String player) {
    this.currentDir = 'R';
    this.score = 0;
    this.player = player;
    this.board = new SnakeBoard(5, 5);
    this.snake = new Snake(this.board.board[0][0]); // Always initailize on
                                                    // initial position.
  }

  public void refreshBoard() {
    Position nextPosition = getNext(snake.head);
    if (nextPosition == null || snake.checkSelfCrash(nextPosition)) {
      endGame = true;
      return;
    }
    snake.move(nextPosition);
    if (nextPosition.value == SnakeBoard.FRUIT_POSITION) {
      snake.add();
      // One you eat a delicious fruit add more fruit
      board.setFoodInBoard();
      score++;
    }
  }

  private Position getNext(Position currentPosition) {

    int row = currentPosition.x;
    int col = currentPosition.y;

    if (currentDir == DIR_RIGHT) {
      col++;
    } else if (currentDir == DIR_LEFT) {
      col--;
    } else if (currentDir == DIR_UP) {
      row--;
    } else {
      row++;
    }
    if (row < 0 || row >= board.board.length || col < 0 || col >= board.board[row].length) {
      return null;
    }
    return board.board[row][col];

  }

  private static class ScoreTable {
    private List<PlayedGame> games = new ArrayList<>();

    public void addPlayedGame(PlayedGame game) {
      games.add(game);
      Collections.sort(games, (g1, g2) -> g1.compareTo(g2));
    }

    public void printScore() {
      for (PlayedGame game : games) {
        System.out.println(game.user + " score:" + game.score);
      }
    }
  }

  private static class PlayedGame {
    String user;
    int score;

    public PlayedGame(String user, int score) {
      this.user = user;
      this.score = score;
    }

    public int compareTo(PlayedGame game) {
      return this.score - game.score;
    }
  }

  public class Snake {

    private LinkedList<Position> snake = new LinkedList<>();

    private Position head;

    public Snake(Position head) {
      this.head = head;
      snake.add(head);
    }

    public void add() {
      snake.add(head);
    }

    public void move(Position next) {

      Position tail = snake.removeLast();
      // clear the position when is moving
      tail.value = SnakeBoard.EMPTY_POSITION;
      head = next;
      next.value = SnakeBoard.SNAKE_POSITION;
      // Add the next cell into the head of the snake
      snake.addFirst(head);

    }

    public boolean checkSelfCrash(Position next) {
      for (Position position : snake) {
        if (position == next) {
          return true;
        }
      }
      return false;
    }
  }

  private static class SnakeBoard {
    public static char EMPTY_POSITION = '0';
    public static char FRUIT_POSITION = 'f';
    public static char SNAKE_POSITION = 's';

    private Position[][] board;
    private final int rowCount;
    private final int columnCount;

    public SnakeBoard(int row, int column) {
      board = new Position[row][column];
      rowCount = row;
      columnCount = column;
      for (int i = 0; i < row; i++) {
        for (int j = 0; j < column; j++) {
          board[i][j] = new Position(i, j, EMPTY_POSITION);
        }
      }
    }

    public void setFoodInBoard() {
      boolean foodAssigned = false;
      while (!foodAssigned) { // Kind of bruteforce
        int row = (int) (Math.random() * rowCount);
        int column = (int) (Math.random() * columnCount);
        if (board[row][column].value != SNAKE_POSITION) {
          board[row][column].value = FRUIT_POSITION;
          foodAssigned = true;
        }
      }
    }
  }

  private static class Position {
    int x;
    int y;
    char value;

    public Position(int x, int y, char value) {
      this.x = x;
      this.y = y;
      this.value = value;
    }
  }
}
