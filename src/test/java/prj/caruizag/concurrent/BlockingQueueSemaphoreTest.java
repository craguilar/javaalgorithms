package prj.caruizag.concurrent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.Semaphore;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BlockingQueueSemaphoreTest {

  @Test
  public void testSemaphoreJavaBehavior() throws InterruptedException {
    Semaphore semaphore = new Semaphore(3);
    assertEquals(3, semaphore.availablePermits());
    semaphore.acquire();
    assertEquals(2, semaphore.availablePermits());
    semaphore.acquire();
    assertEquals(1, semaphore.availablePermits());
    semaphore.acquire();
    assertEquals(0, semaphore.availablePermits());
    semaphore.release();
    semaphore.release();
    semaphore.release();
    assertEquals(3, semaphore.availablePermits());
    semaphore.release();
    // ? ;)
    assertEquals(4, semaphore.availablePermits());
  }

  @Test
  public void testCorrectnessWithThreads() throws InterruptedException {

    final BlockingQueue<Integer> queue = new BlockingQueueSemaphore<Integer>(5);

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

    assertTrue(queue.isEmpty());
  }
}
