package concurrent;

/**
 * 
 */
public class BlockingQueueSemaphore<T> implements BlockingQueue<T> {

  private T[] array;
  private int size = 0;
  private int capacity;
  private int head = 0;
  private int tail = 0;

  private CountingSemaphore semLock = new CountingSemaphore(1, 1);
  private CountingSemaphore semProducer;
  private CountingSemaphore semConsumer;

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
