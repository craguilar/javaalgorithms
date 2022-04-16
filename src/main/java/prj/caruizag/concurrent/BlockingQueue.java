package prj.caruizag.concurrent;

/**
 * Interface that help us provide multiple implementations of the
 * consumer-producer problem.
 */
public interface BlockingQueue<T> {

  public void enqueue(T item) throws InterruptedException;

  public T dequeue() throws InterruptedException;

  public boolean isEmpty();
}
