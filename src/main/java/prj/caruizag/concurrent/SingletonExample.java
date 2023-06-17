package prj.caruizag.concurrent;

public class SingletonExample {

  /*
   * Java memory model allows an object to assign a memory while is still being initialized in the
   * constructor by another thread.
   * 
   * A nicer approach will be Bill Pugh's singleton.
   */
  private static volatile SingletonExample example;

  private SingletonExample() {

  }

  public static SingletonExample getInstance() {

    // Before trying to acquire a lock check if object is uninitialized , so we don't do a
    // costly lock every time we call get instance
    if (example == null) {
      // Then lock note that multiple threads can find example object to be null
      // and fall into this synchronized block.
      synchronized (SingletonExample.class) {
        // Check in a synchronized block as it is possible that other thread might have
        // initialized it already .
        if (example == null) {
          example = new SingletonExample();
        }
      }
    }

    return example;
  }

  public void doSomething() {
    System.out.println("I am an example!");
  }

}
