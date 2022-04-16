package prj.caruizag.ratelimiter;

import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.Ignore;
import org.junit.Test;
import lombok.Getter;


public class RateLimiterTokenBucketTest {

  @Test
  public void getTokenTestCorrectnessWithMapBased()
      throws InterruptedException, ExecutionException {
    int rateLimitThreads = 2;
    int rateLimitTime = 2;
    Duration rateLimiterDuration = Duration.of(rateLimitTime, ChronoUnit.SECONDS);
    final RateLimiter rateLimiter =
        new RateLimiterTokenBucketMapBased(rateLimitThreads, rateLimiterDuration);
    correctnessTestCase(rateLimiter, rateLimitThreads, rateLimiterDuration, null);
  }

  @Test
  @Ignore
  public void getTokenTestCorrectnessWithSemaphoreBased()
      throws InterruptedException, ExecutionException {
    int rateLimitThreads = 2;
    int rateLimitTime = 2;
    Duration rateLimiterDuration = Duration.of(rateLimitTime, ChronoUnit.SECONDS);
    Duration testDuration = Duration.of(2, ChronoUnit.SECONDS);
    final RateLimiter rateLimiter =
        new RateLimiterTokenBucketSemaphoreBased(rateLimitThreads, rateLimiterDuration);
    correctnessTestCase(rateLimiter, rateLimitThreads, rateLimiterDuration, testDuration);
  }

  private void correctnessTestCase(RateLimiter rateLimiter, int rateLimitThreads,
      Duration rateLimiterDuration, Duration testDuration)
      throws InterruptedException, ExecutionException {

    final int maxLoopCount = 500;

    int apiCallers = 15;

    ExecutorService executor = Executors.newFixedThreadPool(apiCallers);

    List<Callable<Integer>> tasks = new ArrayList<>();
    for (int i = 0; i < apiCallers; i++) {
      if (testDuration == null) {
        tasks.add(() -> executeNonBlockingApiCalls(rateLimiter, maxLoopCount));
      } else {
        tasks.add(() -> executeTimedApiCall(testDuration, rateLimiter));
      }
    }

    List<Future<Integer>> resultApiS = executor.invokeAll(tasks);

    int actual = resultApiS.stream().mapToInt(result -> {
      try {
        return result.get();
      } catch (InterruptedException | ExecutionException e) {
        return 0;
      }
    }).sum();
    if (testDuration == null) {
      assertTrue(deviationPercentageSimilar(rateLimitThreads * maxLoopCount, actual) < 2);
    } else {
      // I don't like this assertion
      assertTrue(deviationPercentageSimilar(rateLimitThreads * (int) testDuration.toSeconds(),
          actual) < 2);
    }
  }

  private double deviationPercentageSimilar(int target, int actual) {
    return Math.abs(100.0 - (float) (actual * 100) / target);
  }

  private Integer executeTimedApiCall(Duration testDuration, RateLimiter rateLimiter)
      throws InterruptedException, ExecutionException {

    Integer returnValue = null;
    ExecutorService executor = Executors.newSingleThreadExecutor();
    LongRunningCount longRunningCount = new LongRunningCount(rateLimiter);
    Future<Integer> future = executor.submit(longRunningCount);

    try {
      returnValue = future.get(testDuration.toMillis(), TimeUnit.MILLISECONDS);
    } catch (TimeoutException e) {
      returnValue = longRunningCount.getCallsExecuted().get();
      future.cancel(true);
    } finally {
      executor.shutdownNow();
    }

    return returnValue;
  }

  private Integer executeNonBlockingApiCalls(RateLimiter rateLimiter, int maxLoopCount) {
    AtomicInteger callsExecuted = new AtomicInteger(0);
    for (int i = 0; i < maxLoopCount; i++) {
      Optional<String> assignedToken = rateLimiter.getToken(Thread.currentThread());
      if (assignedToken.isPresent()) {
        callsExecuted.incrementAndGet();
      }
    }
    return callsExecuted.get();
  }

  private class LongRunningCount implements Callable<Integer> {

    @Getter
    private AtomicInteger callsExecuted = new AtomicInteger(0);
    private RateLimiter rateLimiter;

    public LongRunningCount(RateLimiter rateLimiter) {
      this.rateLimiter = rateLimiter;
    }

    @Override
    public Integer call() throws Exception {
      try {
        while (!Thread.interrupted()) {
          Optional<String> assignedToken = rateLimiter.getToken(Thread.currentThread());
          if (assignedToken.isPresent()) {
            callsExecuted.incrementAndGet();
          }
        }
      } catch (Exception e) {
        System.out.println("Interrupted " + e.getMessage());
      }

      return callsExecuted.get();
    }
  }

}
