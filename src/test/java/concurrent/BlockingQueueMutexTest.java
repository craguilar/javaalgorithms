package concurrent;

import org.junit.Test;

public class BlockingQueueMutexTest {

  @Test
  public void testCorrectness() throws InterruptedException {
    final BlockingQueue<Integer> q = new BlockingQueueMutex<Integer>(5);

    Thread producer1 = new Thread(new Runnable() {
      public void run() {
        try {
          int i = 1;
          while (true) {
            q.enqueue(i);
            System.out.println("Producer thread 1 enqueued " + i);
            i++;
          }
        } catch (InterruptedException ie) {
        }
      }
    });

    Thread producer2 = new Thread(new Runnable() {
      public void run() {
        try {
          int i = 5000;
          while (true) {
            q.enqueue(i);
            System.out.println("Producer thread 2 enqueued " + i);
            i++;
          }
        } catch (InterruptedException ie) {

        }
      }
    });

    Thread producer3 = new Thread(new Runnable() {
      public void run() {
        try {
          int i = 100000;
          while (true) {
            q.enqueue(i);
            System.out.println("Producer thread 3 enqueued " + i);
            i++;
          }
        } catch (InterruptedException ie) {

        }
      }
    });

    Thread consumer1 = new Thread(new Runnable() {
      public void run() {
        try {
          while (true) {
            System.out.println("Consumer thread 1 dequeued " + q.dequeue());
          }
        } catch (InterruptedException ie) {

        }
      }
    });

    Thread consumer2 = new Thread(new Runnable() {
      public void run() {
        try {
          while (true) {
            System.out.println("Consumer thread 2 dequeued " + q.dequeue());
          }
        } catch (InterruptedException ie) {

        }
      }
    });

    Thread consumer3 = new Thread(new Runnable() {
      public void run() {
        try {
          while (true) {
            System.out.println("Consumer thread 3 dequeued " + q.dequeue());
          }
        } catch (InterruptedException ie) {

        }
      }
    });

    producer1.start();
    producer2.start();
    producer3.start();

    consumer1.start();
    consumer2.start();
    consumer3.start();

    //
    System.out.println("Let's not wait(join) and exit!");

  }
}
