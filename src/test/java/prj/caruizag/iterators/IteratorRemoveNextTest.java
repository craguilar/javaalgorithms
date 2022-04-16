package prj.caruizag.iterators;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class IteratorRemoveNextTest {

  @Test
  public void testCorrectness() {
    List<List<Integer>> input = getTestCase1();
    IteratorRemoveNext iterator = new IteratorRemoveNext(input);
    while (iterator.hasNext()) {
      int value = iterator.next();
      if (value % 2 == 0) {
        iterator.remove();
      }
    }
    int[] afterRemoval = new int[4];
    int count = 0;
    iterator = new IteratorRemoveNext(input);
    while (iterator.hasNext()) {
      afterRemoval[count++] = iterator.next();
    }
    assertArrayEquals(new int[] {1, 3, 5, 7}, afterRemoval);
  }

  private List<List<Integer>> getTestCase1() {
    List<List<Integer>> collections = new ArrayList<>();

    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();
    List<Integer> list3 = new ArrayList<>();
    List<Integer> list4 = new ArrayList<>();
    List<Integer> list5 = new ArrayList<>();
    list2.add(1);
    list2.add(2);
    list2.add(3);
    list4.add(5);
    list4.add(6);
    list4.add(7);
    list5.add(8);
    collections.add(list1);
    collections.add(list2);
    collections.add(list3);
    collections.add(list4);
    collections.add(list5);
    return collections;
  }

}
