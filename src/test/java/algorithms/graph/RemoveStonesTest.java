package algorithms.graph;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RemoveStonesTest {

  @Test
  public void removeStonesTestEmptyStones() {

    RemoveStones rs = new RemoveStones();
    assertEquals(0, rs.removeStones(new int[][] { { 0, 0 } }));
  }

  @Test
  public void removeStonesTestMultipleUseCases() {

    RemoveStones rs = new RemoveStones();
    assertEquals(5, rs.removeStones(new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 2 }, { 2, 1 }, { 2, 2 } }));
    assertEquals(3, rs.removeStones(new int[][] { { 0, 0 }, { 0, 2 }, { 1, 1 }, { 2, 0 }, { 2, 2 } }));

  }
}