package prj.caruizag.concurrent;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * From
 * https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/locks/ReentrantReadWriteLock.html
 * 
 * An RW lock allows concurrent access for read-only operations, while write operations require
 * exclusive access
 */
public class ReadWriteDictionary {
  private final Map<String, String> m = new TreeMap<>();
  private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
  private final Lock r = rwl.readLock();
  private final Lock w = rwl.writeLock();

  public String get(String key) {
    r.lock();
    try {
      return m.get(key);
    } finally {
      r.unlock();
    }
  }

  public String[] allKeys() {
    r.lock();
    try {
      return (String[]) m.keySet().toArray();
    } finally {
      r.unlock();
    }
  }

  public String put(String key, String value) {
    w.lock();
    try {
      return m.put(key, value);
    } finally {
      w.unlock();
    }
  }

  public void clear() {
    w.lock();
    try {
      m.clear();
    } finally {
      w.unlock();
    }
  }
}
