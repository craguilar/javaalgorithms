package prj.caruizag.iterators;

import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class MonotonicallyIncreasingIterator implements Iterator<Integer> {

  private final PriorityQueue<Pair> customerObjects;

  public MonotonicallyIncreasingIterator(List<MonotonicallyIncreasingValue> monos) {
    customerObjects = new PriorityQueue<>(monos.size(), (a, b) -> a.lastValue - b.lastValue);
    for (MonotonicallyIncreasingValue mono : monos) {
      Pair pair = new Pair(mono.getNext(), mono);
      customerObjects.add(pair);
    }
  }

  @Override
  public boolean hasNext() {
    // Assume a never ending stream, so it always has next: Not necessarily true.
    return true;
  }

  @Override
  public Integer next() {
    Pair toReturn = customerObjects.poll();
    Pair pair = new Pair(toReturn.object.getNext(), toReturn.object);
    customerObjects.add(pair);
    // Get next returns the minimum of the current set of CustomerObjects
    // get a reference for that object and add a new value with next
    return toReturn.lastValue;
  }

  private static class Pair {
    int lastValue;
    MonotonicallyIncreasingValue object;

    public Pair(int lastValue, MonotonicallyIncreasingValue object) {
      this.lastValue = lastValue;
      this.object = object;
    }
  }

}
