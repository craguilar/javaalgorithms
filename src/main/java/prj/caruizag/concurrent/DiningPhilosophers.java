package prj.caruizag.concurrent;

import java.util.concurrent.Semaphore;

/**
 * TODO: https://leetcode.com/problems/the-dining-philosophers/submissions/
 */
class DiningPhilosophers {

  // The table allow max 4 Philosophers
  private Semaphore semaphore = new Semaphore(4);
  /*
   * The shared resources here are the forks, there are 5 forks -
   */
  private boolean[] forks; // True means it is in use
  // Object used for mutual exclusion for critical regions
  private Object lock;

  public DiningPhilosophers() {
    forks = new boolean[5];
    lock = new Object();
  }

  // call the run() method of any runnable to execute its code
  public void wantsToEat(int philosopher, Runnable pickLeftFork, Runnable pickRightFork,
      Runnable eat, Runnable putLeftFork, Runnable putRightFork) throws InterruptedException {
    semaphore.acquire(); // Philosopher entered the room.
    int leftForkId = philosopher;
    int rightForkId = philosopher != 4 ? philosopher + 1 : 0;
    // Step 1. When I want to eat I need to make sure the forks are availble
    int forksInUse = 0;

    while (forksInUse < 2) {
      synchronized (lock) {
        while (forks[leftForkId] || forks[rightForkId]) {
          lock.wait(); // Wait until there is a change.
        }
        // Begin Critical section
        if (!forks[leftForkId]) {
          forks[leftForkId] = true;
          pickLeftFork.run();
          forksInUse++;
        }
        if (!forks[rightForkId]) {
          forks[rightForkId] = true;
          pickRightFork.run();
          forksInUse++;
        }
        // End of critical section
        lock.notifyAll();
      }
    }

    // Step 2. If forks are available then I eat.
    eat.run();
    // Step 3. Finally I put bakc the forks.
    synchronized (lock) {
      putRightFork.run();
      putLeftFork.run();
      forks[rightForkId] = false;
      forks[leftForkId] = false;
      lock.notifyAll();
    }
    semaphore.release();
  }

}
