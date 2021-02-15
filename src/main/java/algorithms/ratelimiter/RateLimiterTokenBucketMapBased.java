package algorithms.ratelimiter;

import java.time.Duration;
import java.time.Instant;
import java.util.Iterator;

import java.util.Optional;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Super naive implementation , what came to my mind while preparing coffee. I
 * know I will nede to build this with a Semaphore - but this help me getting
 * familiar with the problem/
 * 
 */
public class RateLimiterTokenBucketMapBased extends RateLimiter {

  private final ConcurrentHashMap<String, Instant> tokens;

  // java.util.concurrent.TimeUnit
  public RateLimiterTokenBucketMapBased(int capacity, Duration limitForPeriod) {
    super(capacity, limitForPeriod);
    // Initializa to half of the required capacity , default initial capacity
    // (16), load factor (0.75) and concurrencyLevel (16) ,see
    // https://docs.oracle.com/javase/9/docs/api/java/util/concurrent/ConcurrentHashMap.html
    tokens = new ConcurrentHashMap<>(capacity / 2);

  }

  @Override
  public Optional<String> getToken(Thread treadAqcuiringAToken) {
    String hashKey = getOrderableId(treadAqcuiringAToken);

    /**
     * This is the critical section.
     */
    synchronized (this) {
      // 1. Always clear all expired tokens
      cleanExpiredTokens();

      // 2. Then if token still valid and is associated to a thread just return
      if (tokens.get(hashKey) != null) {
        return Optional.of(hashKey);
      }
      // 3. Else , put it into the map.
      if (tokens.size() + 1 > capacity) {
        return Optional.empty();
      }

      tokens.put(hashKey, Instant.now());
    }

    return Optional.of(hashKey);
  }

  private void cleanExpiredTokens() {
    Iterator<Entry<String, Instant>> iterator = tokens.entrySet().iterator();
    while (iterator.hasNext()) {
      Entry<String, Instant> token = iterator.next();
      if (token.getValue().isAfter(Instant.now().plusMillis(this.limitForPeriod.toMillis()))) {
        tokens.remove(token.getKey());
      }
    }
  }

  private String getOrderableId(Thread treadAqcuiringAToken) {

    return String.format("%s-%d", treadAqcuiringAToken.getName().toLowerCase(), treadAqcuiringAToken.getId());
  }
}
