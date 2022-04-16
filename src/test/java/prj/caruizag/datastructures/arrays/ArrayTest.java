package prj.caruizag.datastructures.arrays;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ArrayTest {

  @Test
  public void binarySearchLessThanTargetTestCorrectness() {
    int[] array = new int[] {1, 45, 62, 78, 110, 122, 127, 136, 147, 153, 160, 178, 201, 204, 209,
        254, 268, 275, 282, 284, 295, 309, 320, 350, 359, 370, 385, 386, 415, 415, 441, 462, 467,
        468, 534, 571, 574, 639, 735, 786, 838, 839, 854, 865, 875, 900, 914, 942, 944, 994};

    assertEquals(153, Array.binarySearchLessThanTarget(array, 2, array.length - 1, 155));
  }
}
