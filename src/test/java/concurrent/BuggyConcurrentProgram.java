package concurrent;

import static org.junit.Assert.assertEquals;

import java.lang.ref.WeakReference;

import org.junit.Ignore;
import org.junit.Test;

public class BuggyConcurrentProgram {

  @Test(expected = AssertionError.class)
  @Ignore
  public void testCorrectnessWithThreads() throws InterruptedException {

    // The higher the iterations the most probable we will always hit an error
    for (int i = 0; i < 15; i++) {
      testCaseExecutionAndAssert0();
    }

  }

  private void testCaseExecutionAndAssert0() throws InterruptedException {
    final BuggyConcurrentAccessClass buggyClass = new BuggyConcurrentAccessClass();

    Thread t1 = new Thread(new Runnable() {

      @Override
      public void run() {
        for (int i = 0; i < 50; i++) {
          buggyClass.increment();
          BuggyConcurrentProgram.gc();
        }
      }
    });

    Thread t2 = new Thread(new Runnable() {

      @Override
      public void run() {
        for (int i = 0; i < 50; i++) {
          buggyClass.decrement();
        }
      }
    });

    t1.start();
    t2.start();

    t1.join();
    t2.join();
    assertEquals(0, buggyClass.printFinalCounterValue());
  }

  /*
   * Credit goes to ForceGarbageCollection to
   */
  public static void gc() {
    Object obj = new Object();
    WeakReference weakObjectRef = new WeakReference<Object>(obj);
    obj = null;
    while (weakObjectRef.get() != null) {
      System.gc();
    }
  }

  private class BuggyConcurrentAccessClass {
    int count = 0;

    public void increment() {
      count++;
    }

    public void decrement() {
      count--;
    }

    public int printFinalCounterValue() {
      return count;
    }
  }

}
