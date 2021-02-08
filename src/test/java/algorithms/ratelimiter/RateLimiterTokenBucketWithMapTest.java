package algorithms.ratelimiter;

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
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class RateLimiterTokenBucketWithMapTest {

  @Test
  public void getTokenTestCorrectness() throws InterruptedException, ExecutionException {

    final int maxLoopCount = 500;
    int rateLimitTime = 2;
    int rateLimitThreads = 2;

    int apiCallers = 15;

    Duration rateLimiterDuration = Duration.of(rateLimitTime, ChronoUnit.SECONDS);
    final RateLimiter rateLimiter = new RateLimiterTokenBucketWithMap(rateLimitThreads, rateLimiterDuration);

    ExecutorService executor = Executors.newFixedThreadPool(apiCallers);

    List<Callable<Integer>> tasks = new ArrayList<>();
    for (int i = 0; i < apiCallers; i++) {
      tasks.add(() -> executeApiCalls(rateLimiter, maxLoopCount));
    }

    List<Future<Integer>> resultApiS = executor.invokeAll(tasks);

    int actual = resultApiS.stream().mapToInt(result -> {
      try {
        return result.get();
      } catch (InterruptedException | ExecutionException e) {
        return 0;
      }
    }).sum();

    assertTrue(deviationPercentageSimilar(rateLimitThreads * maxLoopCount, actual) < 2);

  }

  private double deviationPercentageSimilar(int target, int actual) {
    return Math.abs(100.0 - (float) (actual * 100) / target);
  }

  private Integer executeApiCalls(RateLimiter rateLimiter, int maxLoopCount) {
    AtomicInteger callsExecuted = new AtomicInteger(0);
    for (int i = 0; i < maxLoopCount; i++) {
      Optional<String> assignedToken = rateLimiter.getToken(Thread.currentThread());

      if (assignedToken.isPresent()) {
        callsExecuted.incrementAndGet();
      }
    }
    return callsExecuted.get();
  }
}
