package sorting;

public class QuickSort extends SortingAlgorithm {
  public QuickSort() {
    super();
  }

  @Override
  public int[] runAlgorithm(boolean showtime, int a[]) {

    System.out.println("QUICK SORT:");
    if (showtime) {
      long startTime;
      long stopTime;
      long elapsedTime;
      startTime = System.currentTimeMillis();
      quicksort(a, 0, a.length - 1);
      stopTime = System.currentTimeMillis();
      elapsedTime = stopTime - startTime;
      System.out.println("Elapsed time=" + elapsedTime + "[ms]");
    } else
      quicksort(a, 0, a.length - 1);

    return a;
  }

  @Override
  public int[] sort(int[] a) {
    quicksort(a, 0, a.length - 1);
    return a;
  }

  public int[] quicksort(int a[], int left, int right) {
    int pivot;
    /* Termination condition! */
    if (right > left) {
      pivot = Partition(a, left, right);
      quicksort(a, left, pivot - 1);
      quicksort(a, pivot + 1, right);
    }
    return a;
  }

  private int Partition(int a[], int left, int right) {
    int p = left;
    int q = right;
    int pivotitem = a[left];
    while (p < q) {
      while (p < right && a[p] <= pivotitem)
        p++;
      while (q > left && a[q] > pivotitem)
        q--;
      if (p < q)
        swap(a, p, q);
    }
    a[left] = a[q];
    a[q] = pivotitem;
    return q;
  }

}
