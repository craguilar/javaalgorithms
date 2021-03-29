package concurrent;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class ReadWriteDictionaryTest {

  @Test
  public void testCorrectness() throws InterruptedException {
    int threads = 5;
    ExecutorService executor = Executors.newFixedThreadPool(threads);
    ReadWriteDictionary rwDictionary = new ReadWriteDictionary();

    List<Callable<String>> tasks = new ArrayList<>();
    final AtomicInteger counter = new AtomicInteger(0);
    for (int i = 0; i < threads; i++) {
      int current = counter.incrementAndGet();
      tasks.add(() -> writeToRwd(rwDictionary, format("id-%d", current), format("%d", current)));

    }
    counter.set(0);
    for (int i = 0; i < threads; i++) {
      tasks.add(() -> readFromRwd(rwDictionary, format("id-%s", counter.get())));
      counter.incrementAndGet();
    }

    List<Future<String>> results = executor.invokeAll(tasks);
    results.stream().forEach(result -> {
      try {
        result.get();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
    });

  }

  public String writeToRwd(ReadWriteDictionary rwDictionary, String key, String value) {
    String lastPut = rwDictionary.put(key, value);
    System.out.println(Thread.currentThread().getName() + " Put " + value);
    return lastPut;
  }

  public String readFromRwd(ReadWriteDictionary rwDictionary, String key) throws InterruptedException {
    String lastRead = null;
    Thread.sleep(100);
    for (int i = 0; i < 5; i++) {
      lastRead = rwDictionary.get(format("id-%s", i));
      System.out.println(Thread.currentThread().getName() + " Get " + lastRead);

    }
    return lastRead;
  }

}
