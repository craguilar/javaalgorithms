package prj.caruizag.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class was provided in Educative course "Java Multithreading for Senior
 * Engineering Interviews" from educative.io.
 * 
 * A Mutex as the name hints implies mutual exclusion. A mutex is used to guard
 * shared data such as a linked-list, an array or any primitive type. A mutex
 * allows only a single thread to access a resource or critical section.
 */
public class BlockingQueueMutex<T> implements BlockingQueue<T> {

  private T[] array;
  private Lock lock = new ReentrantLock();
  private int size = 0;
  private int capacity;
  private int head = 0;
  private int tail = 0;

  @SuppressWarnings("unchecked")
  public BlockingQueueMutex(int capacity) {
    // Restriction with generics:
    // https://docs.oracle.com/javase/tutorial/java/generics/restrictions.html#createObjects
    array = (T[]) new Object[capacity];
    this.capacity = capacity;
  }

  @Override
  public void enqueue(T item) throws InterruptedException {

    lock.lock();
    while (size == capacity) {
      // Release the mutex to give other threads
      lock.unlock();
      // Reacquire the mutex before checking the
      // condition
      lock.lock();
    }

    if (tail == capacity) {
      tail = 0;
    }

    array[tail] = item;
    size++;
    tail++;
    lock.unlock();

  }

  @Override
  public T dequeue() throws InterruptedException {

    T item = null;

    lock.lock();
    while (size == 0) {
      lock.unlock();
      lock.lock();
    }

    if (head == capacity) {
      head = 0;
    }

    item = array[head];
    array[head] = null;
    head++;
    size--;

    lock.notifyAll();
    lock.unlock();
    return item;

  }

  public boolean isEmpty() {
    return size == 0;
  }

}
