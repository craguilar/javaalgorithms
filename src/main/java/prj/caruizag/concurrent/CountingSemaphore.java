package prj.caruizag.concurrent;

public class CountingSemaphore {

  int usedPermits = 0;
  int maxCount;

  public CountingSemaphore(int count, int initialPermits) {
    this.maxCount = count;
    this.usedPermits = this.maxCount - initialPermits;
  }

  public synchronized void acquire() throws InterruptedException {

    while (usedPermits == maxCount) {
      wait();
    }
    usedPermits++;
    notify();
  }

  public synchronized void release() throws InterruptedException {

    while (usedPermits == 0) {
      wait();
    }

    usedPermits--;
    notify();
  }
}
