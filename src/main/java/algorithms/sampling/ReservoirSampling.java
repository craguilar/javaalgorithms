package algorithms.sampling;

import java.util.Random;

public class ReservoirSampling {
  public int[] getKRandomElements(int[] arr, int k) {
    int n = arr.length;
    assert (n >= 1);
    assert (k >= 1);
    assert (k <= n);
    // Validations on k an n assumptions
    Random rand = new Random(); // We could get a seed
    int[] result = new int[k];
    for (int i = 0; i < k; i++) {
      int random = i + rand.nextInt(n - 1 - i); // Integer between 0-n-1
      swap(arr, i, random);
      result[i] = arr[i];
    }
    return result;
  }

  protected static void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

}
