package prj.caruizag.cache;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LRUCacheTest {

  @Test
  public void getTestWhenNull() {
    LRUCache cache = new LRUCache(2);

    cache.put(1, 1);
    cache.put(3, 1);
    cache.put(3, 1);
    assertEquals(-1, cache.get(100));
  }

  @Test
  public void getTestMultupleGets() {
    LRUCache cache = new LRUCache(2);

    cache.put(1, 1);
    cache.put(1, 1);
    cache.put(2, 1);
    cache.put(1, 1);
    cache.put(3, 1);
    cache.put(4, 1);
    assertEquals(-1, cache.get(1));

    cache.clear();
    cache.put(1, 1);
    cache.put(2, 2);
    assertEquals(1, cache.get(1));
    cache.put(3, 3);
    assertEquals(-1, cache.get(2));
    cache.put(4, 4);
    assertEquals(-1, cache.get(1));
    assertEquals(3, cache.get(3));
    assertEquals(4, cache.get(4));
  }

}
