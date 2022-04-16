package prj.caruizag.algorithms.sort;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Shellsort To exhibit the value of knowing properties of elementary sorts, we next consider a fast
 * algorithm based on insertion sort. Insertion sort is slow for large unordered arrays because the
 * only exchanges it does involve adjacent entries, so items can move through the array only one
 * place at a time. For example, if the item with the smallest key happens to be at the end of the
 * array, N1 exchanges are needed to get that one item where it belongs. Shellsort is a simple
 * extension of insertion sort that gains speed by allowing exchanges of array entries that are far
 * apart, to produce partially sorted arrays that can be efficiently sorted, eventually by insertion
 * sort.
 */
public class ShellSort extends SortingAlgorithm {
  private static final Logger LOG = Logger.getLogger(ShellSort.class.getName());

  public ShellSort() {
    super();
  }

  @Override
  public int[] runAlgorithm(boolean showtime, int[] a) {
    LOG.log(Level.FINE, "SHELL SORT");
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
  public int[] sort(int[] a) {
    int n = a.length;
    int h = 1;
    while (h < n / 3)
      h = 3 * h + 1;

    while (h >= 1) { // h-sort the array.
      for (int i = h; i < n; i++) { // Insert a[i] among a[i-h], a[i-2*h],
        // a[i-3*h]... .

        for (int j = i; j >= h && a[j] < a[j - h]; j -= h) {
          swap(a, j, j - h);
        }

      }
      h = h / 3;
    }
    return a;
  }

}
