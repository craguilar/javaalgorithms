package prj.caruizag.algorithms.sampling;

import static org.junit.Assert.assertFalse;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import org.junit.Test;

public class ReservoirSamplingTest {

  @Test
  public void samplingCorrectness() {
    ReservoirSampling sampling = new ReservoirSampling();

    int[] result = sampling.getKRandomElements(getNSizeNonRepeatedArray(1000), 500);
    Set<Integer> repeated = new HashSet<>();
    for (int i = 0; i < result.length; i++) {
      assertFalse(repeated.contains(result[i]));
      repeated.add(result[i]);
    }
  }

  private int[] getNSizeNonRepeatedArray(int n) {
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = i;
    }
    Random rand = new Random(System.currentTimeMillis());
    for (int i = 0; i < n; i++) {
      ReservoirSampling.swap(arr, i, i + rand.nextInt(n - i));
    }
    return arr;
  }

}
