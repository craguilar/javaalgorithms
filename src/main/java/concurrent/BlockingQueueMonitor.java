package concurrent;

/**
 * From Wikipedia . a monitor is a synchronization construct that allows threads
 * to have both mutual exclusion and the ability to wait (block) for a certain
 * condition to become false. Monitors also have a mechanism for signaling other
 * threads that their condition has been met. A monitor consists of a mutex
 * (lock) object and condition variables. A condition variable essentially is a
 * container of threads that are waiting for a certain condition. Monitors
 * provide a mechanism for threads to temporarily give up exclusive access in
 * order to wait for some condition to be met, before regaining exclusive access
 * and resuming their task.
 */
public class BlockingQueueMonitor<T> implements BlockingQueue<T> {

  private T[] array;
  private Object lock = new Object();
  private int size = 0;
  private int capacity;
  private int head = 0;
  private int tail = 0;

  @SuppressWarnings("unchecked")
  public BlockingQueueMonitor(int capacity) {
    // Restriction with generics:
    // https://docs.oracle.com/javase/tutorial/java/generics/restrictions.html#createObjects
    array = (T[]) new Object[capacity];
    this.capacity = capacity;
  }

  @Override
  public void enqueue(T item) throws InterruptedException {

    synchronized (lock) {
      while (size == capacity) {
        lock.wait();
      }
      // Begin Critical section
      if (tail == capacity) {
        tail = 0;
      }

      array[tail] = item;
      size++;
      tail++;
      // End of critical section
      lock.notifyAll();
    }
  }

  @Override
  public T dequeue() throws InterruptedException {

    T item = null;
    synchronized (lock) {
      while (size == 0) {
        lock.wait();
      }
      // Begin Critical section
      if (head == capacity) {
        head = 0;
      }

      item = array[head];
      array[head] = null;
      head++;
      size--;
      // End of critical section
      lock.notifyAll();
    }

    return item;
  }

  public boolean isEmpty() {
    return size == 0;
  }

}
