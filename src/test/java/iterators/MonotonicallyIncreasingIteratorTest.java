package iterators;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class MonotonicallyIncreasingIteratorTest {

  @Test
  public void MonotonicallyIncreasingIterato() {
    List<MonotonicallyIncreasingValue> monos = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      monos.add(new MonotonicallyIncreasingValue());
    }
    MonotonicallyIncreasingIterator iterator = new MonotonicallyIncreasingIterator(monos);
    int prev = iterator.next();
    for (int i = 0; i < 2000; i++) {
      int current = iterator.next();
      System.out.println(current);
      if ((current - prev) < 0) {
        throw new RuntimeException("Not monotonically increasing");
      }
      prev = current;
    }
  }

}
