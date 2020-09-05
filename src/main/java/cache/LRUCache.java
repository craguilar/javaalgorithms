package cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LRUCache {

  /**
   * I need a data estructure that tells me what was used recently.
   */
  private List<Integer> usedList = new ArrayList<>();

  private Map<Integer, Integer> cache = new HashMap<>();

  private int capacity;

  public LRUCache(int capacity) {
    this.capacity = capacity;
  }

  public int get(int key) {
    updateList(key);
    return cache.getOrDefault(key, -1);
  }

  public void put(int key, int value) {
    updateAndEvictList(key);
    cache.put(key, value);
  }

  public void clear(){
    cache.clear();
    usedList.clear();
  }

  private void updateAndEvictList(int key) {
    boolean exists = cache.get(key) != null;
    if (!exists) {
      evictOld(key);
    } else {
      updateList(key);
    }
  }

  private void updateList(int key) {
    int index = usedList.indexOf(key);
    if (index != -1) {
      usedList.remove(index);
      usedList.add(0, key);
    }
  }

  private void evictOld(int key) {
    if (usedList.size() > (capacity - 1)) {
      usedList.add(0, key);
      int keyToRemove = usedList.remove(usedList.size() - 1);
      cache.remove(keyToRemove);
    } else {
      usedList.add(0, key);
    }
  }

}