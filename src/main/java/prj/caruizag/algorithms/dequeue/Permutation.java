package prj.caruizag.algorithms.dequeue;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
  public static void main(String[] args) {
    Integer n = Integer.parseInt(args[0]);
    int count = 0;
    RandomizedQueue<String> q = new RandomizedQueue<>();
    while (!StdIn.isEmpty()) {
      q.enqueue(StdIn.readString());
    }
    count = 0;
    for (String a : q) {
      StdOut.println(a);
      if (count == n)
        count++;
    }

  }
}