package concurrent;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class BlockingQueueTest {

  @Test
  public void testCorrectnessWithThreads() throws InterruptedException {

    final BlockingQueue<Integer> queue = new BlockingQueue<Integer>(5);

    Thread t1 = new Thread(new Runnable() {

      @Override
      public void run() {
        try {
          for (int i = 0; i < 50; i++) {
            queue.enqueue(i);
            log.info("enqueued [{}]" + i);
          }
        } catch (InterruptedException ie) {

        }
      }
    });

    Thread t2 = new Thread(new Runnable() {

      @Override
      public void run() {
        try {
          for (int i = 0; i < 25; i++) {
            queue.dequeue();
          }
        } catch (InterruptedException ie) {

        }
      }
    });

    Thread t3 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          for (int i = 0; i < 25; i++) {
            queue.dequeue();
          }
        } catch (InterruptedException ie) {

        }
      }
    });

    t1.start();
    Thread.sleep(400);
    t2.start();

    t2.join();

    t3.start();
    t1.join();
    t3.join();

    assertTrue(queue.getTail() == queue.getHead());
  }

  @Test
  public void testCorrectnessWithExecutorServiceWithRightPool() throws InterruptedException, ExecutionException {

    final BlockingQueue<Integer> queue = new BlockingQueue<Integer>(5);

    ExecutorService executor = Executors.newFixedThreadPool(3);

    Callable<Integer> producer = () -> {
      Integer lastProducedResource = Integer.MIN_VALUE;
      try {
        for (int i = 0; i < 50; i++) {
          lastProducedResource = i;
          queue.enqueue(i);
          System.out.println(Thread.currentThread().getName() + " -Enqueued " + i);
        }
      } catch (InterruptedException ie) {

      }
      return lastProducedResource;
    };

    Callable<Integer> consumer1 = () -> {
      Integer lastReadResource = Integer.MIN_VALUE;
      try {
        for (int i = 0; i < 25; i++) {
          lastReadResource = queue.dequeue();
          System.out.println(Thread.currentThread().getName() + " Consumer 1 dequeued: " + lastReadResource);
        }

      } catch (InterruptedException ie) {

      }
      return lastReadResource;
    };

    Callable<Integer> consumer2 = () -> {
      Integer lastReadResource = Integer.MIN_VALUE;
      try {
        for (int i = 0; i < 25; i++) {
          lastReadResource = queue.dequeue();
          System.out.println(Thread.currentThread().getName() + " Consumer 2 dequeued: " + lastReadResource);
        }
      } catch (InterruptedException ie) {

      }
      return lastReadResource;
    };
    Future<Integer> resultProducer = executor.submit(producer);
    Thread.sleep(400);
    Future<Integer> resultConsumer = executor.submit(consumer1);
    // Get is a blocking Operation.
    resultConsumer.get();

    Future<Integer> resultConsumer2 = executor.submit(consumer2);
    resultProducer.get();
    resultConsumer2.get();

    assertTrue(queue.getTail() == queue.getHead());
  }

  @Test
  public void testCorrectnessWithExecutorServiceWithNotStarvingPool() throws InterruptedException, ExecutionException {

    final BlockingQueue<Integer> queue = new BlockingQueue<Integer>(5);

    ExecutorService executor = Executors.newFixedThreadPool(2);

    Callable<Integer> producer = () -> {
      Integer lastProducedResource = Integer.MIN_VALUE;
      try {
        for (int i = 0; i < 50; i++) {
          lastProducedResource = i;
          queue.enqueue(i);
          System.out.println(Thread.currentThread().getName() + " -Enqueued " + i);
        }
      } catch (InterruptedException ie) {

      }
      return lastProducedResource;
    };

    Callable<Integer> consumer1 = () -> {
      Integer lastReadResource = Integer.MIN_VALUE;
      try {
        for (int i = 0; i < 25; i++) {
          lastReadResource = queue.dequeue();
          System.out.println(Thread.currentThread().getName() + " Consumer 1 dequeued: " + lastReadResource);
        }

      } catch (InterruptedException ie) {

      }
      return lastReadResource;
    };

    Callable<Integer> consumer2 = () -> {
      Integer lastReadResource = Integer.MIN_VALUE;
      try {
        for (int i = 0; i < 25; i++) {
          lastReadResource = queue.dequeue();
          System.out.println(Thread.currentThread().getName() + " Consumer 2 dequeued: " + lastReadResource);
        }
      } catch (InterruptedException ie) {

      }
      return lastReadResource;
    };
    Future<Integer> resultProducer = executor.submit(producer);
    Thread.sleep(400);
    Future<Integer> resultConsumer = executor.submit(consumer1);
    // Get is a blocking Operation.
    resultConsumer.get();

    Future<Integer> resultConsumer2 = executor.submit(consumer2);
    resultProducer.get();
    resultConsumer2.get();

    assertTrue(queue.getTail() == queue.getHead());
  }
}
