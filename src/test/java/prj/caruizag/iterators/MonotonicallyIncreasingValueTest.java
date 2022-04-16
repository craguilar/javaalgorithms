package prj.caruizag.iterators;

import org.junit.Test;

public class MonotonicallyIncreasingValueTest {

  @Test
  public void testIncrement() {
    MonotonicallyIncreasingValue value = new MonotonicallyIncreasingValue();
    int prev = value.getNext();
    for (int i = 0; i < 200; i++) {
      int current = value.getNext();
      if ((current - prev) <= 0) {
        throw new RuntimeException(
            "Not monotonically increasing prev =" + prev + " current=" + current);
      }
      prev = current;
    }
  }

}
