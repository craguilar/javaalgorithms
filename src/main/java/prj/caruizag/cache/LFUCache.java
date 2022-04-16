package prj.caruizag.cache;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;




/**
 * Experimental implementation of a Least Frequently Used (LFU) cache.
 */
public class LFUCache {

  /**
   * I need a data estructure that tells me what was used recently.
   */
  private List<AgedElement> ageList = new LinkedList<>();

  private Map<Integer, Integer> cache = new ConcurrentHashMap<>();

  private int capacity;

  public LFUCache(int capacity) {
    this.capacity = capacity;
  }

  public int get(int key) {
    updateValues(key);
    return cache.getOrDefault(key, -1);
  }

  public void put(int key, int value) {
    boolean alreadyExists = cache.get(key) != null;
    if (!alreadyExists && ageList.size() > (capacity - 1)) {
      Collections.sort(ageList);
      int keyToRemove = ageList.remove(0).value;
      cache.remove(keyToRemove);
    }

    if (cache.get(key) == null) {
      ageList.add(new AgedElement(key, 0));
    }
    cache.put(key, value);
    updateValues(key);
  }

  private void updateValues(int value) {
    for (AgedElement element : ageList) {
      if (element.value != value) {
        element.age++;
      } else {
        element.age--;
      }
    }
  }

  private class AgedElement implements Comparable<AgedElement> {
    public int value;

    public long age;

    public AgedElement(int value, long age) {
      this.value = value;
      this.age = age;
    }

    @Override
    public int compareTo(AgedElement ae) {
      return Long.compare(ae.age, this.age);
    }

    @Override
    public String toString() {
      return String.format("{value = %s, age = %s}", value, age);
    }
  }
}