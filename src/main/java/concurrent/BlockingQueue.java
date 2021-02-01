package concurrent;

import lombok.Data;

/**
 * Class was provided in Educative course "Java Multithreading for Senior
 * Engineering Interviews"
 */
@Data
public class BlockingQueue<T> {

  private T[] array;
  private Object lock = new Object();
  private int size = 0;
  private int capacity;
  private int head = 0;
  private int tail = 0;

  @SuppressWarnings("unchecked")
  public BlockingQueue(int capacity) {
    // Restriction with generics:
    // https://docs.oracle.com/javase/tutorial/java/generics/restrictions.html#createObjects
    array = (T[]) new Object[capacity];
    this.capacity = capacity;
  }

  public void enqueue(T item) throws InterruptedException {

    synchronized (lock) {
      while (size == capacity) {
        lock.wait();
      }

      if (tail == capacity) {
        tail = 0;
      }

      array[tail] = item;
      size++;
      tail++;
      lock.notifyAll();
    }
  }

  public T dequeue() throws InterruptedException {

    T item = null;
    synchronized (lock) {
      while (size == 0) {
        lock.wait();
      }

      if (head == capacity) {
        head = 0;
      }

      item = array[head];
      array[head] = null;
      head++;
      size--;

      lock.notifyAll();
    }

    return item;
  }
}
