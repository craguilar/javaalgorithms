package cache;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LRUCacheWithLinkedHashMap extends LinkedHashMap<String, Integer> {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private int maxEntries;

  @Override
  protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
    return size() > maxEntries;
  }

}
