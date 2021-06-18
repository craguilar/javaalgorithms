package algorithms.search;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Ignore;
import org.junit.Test;

public class ArraySearchTest {

  @Test
  @Ignore
  public void findDuplicateTestCorrectness() {

    ArraySearch as = new ArraySearch();
    Pair<Integer, int[]> result = generateArrayFromOneToN(10);
    assertEquals(result.getLeft(), (Integer) as.findDuplicate(result.getRight()));
  }

  private Pair<Integer, int[]> generateArrayFromOneToN(int n) {
    assert n >= 2 : "Come on - give me a valid value!";
    List<Integer> list = new ArrayList<>();
    for (int i = 1; i <= n; i++) {
      list.add(i);
    }
    Collections.shuffle(list);
    int repeated = list.get(0);
    list.set(list.get(1) - 1, repeated);
    int[] result = new int[list.size()];
    for (int i = 0; i < n; i++) {
      result[i] = list.get(i);
    }
    return Pair.of(repeated, result);
  }
}
