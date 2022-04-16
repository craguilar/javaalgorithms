package prj.caruizag.algorithms.sort;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * Selection sort One of the simplest sorting algorithms works as follows: First, find the smallest
 * item in the array and exchange it with the first entry (itself if the first entry is already the
 * smallest). Then, find the next smallest item and exchange it with the second entry. Continue in
 * this way until the entire array is sorted. This method is called selection sort because it works
 * by repeatedly selecting the smallest remaining item.
 */
public class SelectionSort extends SortingAlgorithm {
  private static final Logger LOG = Logger.getLogger(SelectionSort.class.getName());

  public SelectionSort() {
    super();
  }

  @Override
  public int[] runAlgorithm(boolean showtime, int[] a) {
    LOG.log(Level.FINE, "Selection sort");
    if (showtime) {
      long startTime;
      long stopTime;
      long elapsedTime;
      startTime = System.nanoTime();
      sort(a);
      stopTime = System.nanoTime();
      elapsedTime = stopTime - startTime;
      LOG.log(Level.FINE, "Elapsed time= {0} [ms]",
          TimeUnit.MILLISECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS));
    } else {
      sort(a);
    }
    return a;
  }

  @Override
  public int[] sort(int a[]) {
    int index;
    for (int i = 0; i < a.length - 1; i++) {
      index = i;
      for (int j = i + 1; j < a.length; j++) {
        if (a[index] > a[j])
          index = j;
      }
      swap(a, i, index);
    }
    return a;
  }
}
