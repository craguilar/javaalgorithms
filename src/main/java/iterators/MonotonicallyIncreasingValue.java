package iterators;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This object implementation is NOT fully correct- requires review.
 */
public class MonotonicallyIncreasingValue {

  private final int delta;
  private final AtomicInteger atomic;

  public MonotonicallyIncreasingValue() {
    Random rn = new Random(); // Pseudo random integer
    delta = rn.nextInt(200);
    atomic = new AtomicInteger(rn.nextInt(10000));
  }

  public int getNext() {
    return atomic.addAndGet(delta);
  }
}
