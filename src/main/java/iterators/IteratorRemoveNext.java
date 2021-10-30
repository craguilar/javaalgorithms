package iterators;

import java.util.List;
import java.util.Iterator;

public class IteratorRemoveNext implements Iterator<Integer> {

  private final List<List<Integer>> collections;

  // Point to current position
  private int currentArrayInCollection;
  private int currentPositionInArray;

  // Last removed element
  private int lastRemovedArrayInCollection = -1;
  private int lastRemovedPositionInArray = -1;

  public IteratorRemoveNext(List<List<Integer>> collections) {
    this.collections = collections;
    currentArrayInCollection = 0;
    currentPositionInArray = 0;
  }

  @Override
  public boolean hasNext() {
    int x = currentArrayInCollection;
    int y = currentPositionInArray;
    while (x < collections.size()) {
      if (collections.get(x).size() > 0 && y < collections.get(x).size()) {
        return true;
      }
      y = 0;
      x++;
    }
    return false;
  }

  @Override
  public Integer next() {
    int x = currentArrayInCollection;
    int y = currentPositionInArray;
    while (x < collections.size()) {
      if (collections.get(x).size() > 0 && y < collections.get(x).size()) {
        currentArrayInCollection = x;
        currentPositionInArray = y + 1;
        // Last called Next
        lastRemovedArrayInCollection = x;
        lastRemovedPositionInArray = y;

        return collections.get(x).get(y);
      }
      y = 0;
      x++;
    }
    throw new IllegalStateException("Not available next!");
  }

  public void remove() {

    if (lastRemovedArrayInCollection != -1 && lastRemovedPositionInArray != -1) {
      // int value =
      // collections.get(lastRemovedArrayInCollection).get(lastRemovedPositionInArray);
      collections.get(lastRemovedArrayInCollection).remove(lastRemovedPositionInArray);
      lastRemovedArrayInCollection = -1;
      lastRemovedPositionInArray = -1;
      return;
    }
    throw new IllegalStateException("No element to remove!");
  }

}
