package prj.caruizag.concurrent;

import java.util.concurrent.Semaphore;

/**
 * This is the leet code solution for
 * https://leetcode.com/problems/the-dining-philosophers/submissions/ , please note the use of
 * synchronized in wantsToEat, this is not how I will implement this problem at all, as ideally we
 * could get rid of synchronized by adding an additional Semaphore with 4 permits to allow only 4
 * Philosophers to hold forks at a time , else this could result in algorithmjavj with starvation.
 */
class DiningPhilosophers {

  private Semaphore[] forks = new Semaphore[5];


  public DiningPhilosophers() {
    for (int i = 0; i < forks.length; i++) {
      forks[i] = new Semaphore(1);
    }
  }

  // call the run() method of any runnable to execute its code
  public synchronized void wantsToEat(int philosopher, Runnable pickLeftFork,
      Runnable pickRightFork, Runnable eat, Runnable putLeftFork, Runnable putRightFork)
      throws InterruptedException {

    int left = philosopher;
    int right = (philosopher + 4) % 5;
    forks[left].acquire();
    forks[right].acquire();
    pickLeftFork.run();
    pickRightFork.run();

    // Eat
    eat.run();

    // Leave the table
    forks[left].release();
    forks[right].release();
    putLeftFork.run();
    putRightFork.run();

  }
}
