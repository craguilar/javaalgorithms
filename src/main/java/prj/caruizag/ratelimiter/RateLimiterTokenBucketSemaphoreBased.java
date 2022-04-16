package prj.caruizag.ratelimiter;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * In comparison to RateLimiterTokenBucketMapBased this implementation is
 * completely blocking .
 */
public class RateLimiterTokenBucketSemaphoreBased extends RateLimiter {

  private final Semaphore semaphore;
  private final ScheduledExecutorService scheduler;

  public RateLimiterTokenBucketSemaphoreBased(int capacity, Duration limitForPeriod) {
    super(capacity, limitForPeriod);

    this.semaphore = new Semaphore(capacity, true);
    this.scheduler = getSingleThreadedScheduler();

    scheduleClean();
  }

  @Override
  Optional<String> getToken(Thread treadAqcuiringAToken) {
    if (acquirePermission(1)) {
      return Optional.of(getOrderableId(treadAqcuiringAToken));
    }
    return Optional.empty();
  }

  public boolean acquirePermission(int permits) {
    try {
      boolean success = semaphore.tryAcquire(permits, this.limitForPeriod.toMillis(), TimeUnit.MILLISECONDS);
      return success;
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      return false;
    }
  }

  /**
   * This method , releases permits based on the current capacity that has been
   * allocated
   */
  private void cleanExpiredTokens() {
    int permissionsToRelease = this.capacity - semaphore.availablePermits();
    semaphore.release(permissionsToRelease);
  }

  private ScheduledExecutorService getSingleThreadedScheduler() {
    ThreadFactory threadFactory = target -> {
      Thread thread = new Thread(target, "RateLimiterTokenBucketSemaphoreBased");
      thread.setDaemon(true);
      return thread;
    };
    return Executors.newSingleThreadScheduledExecutor(threadFactory);
  }

  /**
   * Setup the {@link ScheduledExecutorService} to run and cleanup on a certain
   * frequency, which is the same to the amount from which we want to limit
   * calls
   */
  private void scheduleClean() {
    scheduler.scheduleAtFixedRate(this::cleanExpiredTokens, this.limitForPeriod.toMillis(),
        this.limitForPeriod.toMillis(), TimeUnit.MILLISECONDS);
  }

}
