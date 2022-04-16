package prj.caruizag.concurrent;

/**
 * Implementation of {@link CountingSemaphore} was copied from educative.io.
 * 
 * From Wikipedia ... a semaphore is a variable or abstract data type used to control access to a
 * common resource by multiple processes and avoid critical section problems in a concurrent system
 * such as a multitasking operating system. A trivial semaphore is a plain variable that is changed
 * (for example, incremented or decremented, or toggled) depending on programmer-defined conditions.
 */
public class BlockingQueueSemaphore<T> implements BlockingQueue<T> {

  private T[] array;
  private int size = 0;
  private int capacity;
  private int head = 0;
  private int tail = 0;

  /**
   * We use semLock as a mutual exlcusion lock.A semaphore initialized to one, and which is used
   * such that it only has at most one permit available, can serve as a mutual exclusion lock. This
   * is more commonly known as a binary semaphore, because it only has two states: one permit
   * available, or zero permits available. When used in this way, the binary semaphore has the
   * property (unlike many Lock implementations), that the "lock" can be released by a thread other
   * than the owner (as semaphores have no notion of ownership). This can be useful in some
   * specialized contexts, such as deadlock recovery.
   */
  private CountingSemaphore semLock = new CountingSemaphore(1, 1);
  private CountingSemaphore semProducer;
  private CountingSemaphore semConsumer;

  /**
   * We initialize a consumer semaphore with same capacity as the producer but with no available
   * resource, this will help us "block" the consumer when there are no elements enqueued
   * 
   * @param capacity
   */
  @SuppressWarnings("unchecked")
  public BlockingQueueSemaphore(int capacity) {
    // Restriction with generics:
    // https://docs.oracle.com/javase/tutorial/java/generics/restrictions.html#createObjects
    array = (T[]) new Object[capacity];
    this.capacity = capacity;
    this.semProducer = new CountingSemaphore(capacity, capacity);
    this.semConsumer = new CountingSemaphore(capacity, 0);
  }

  @Override
  public void enqueue(T item) throws InterruptedException {
    semProducer.acquire();
    semLock.acquire();

    // Begin Critical section
    if (tail == capacity) {
      tail = 0;
    }

    array[tail] = item;
    size++;
    tail++;
    // End of critical section

    semLock.release();
    semConsumer.release();
  }

  @Override
  public T dequeue() throws InterruptedException {
    T item = null;

    semConsumer.acquire();
    semLock.acquire();
    // Begin Critical section
    if (head == capacity) {
      head = 0;
    }

    item = array[head];
    array[head] = null;
    head++;
    size--;
    // End of critical section

    semLock.release();
    semProducer.release();
    return item;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

}
