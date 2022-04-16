package prj.caruizag.algorithms.search;

import java.util.Arrays;

public class ArraySearch {
  public int findDuplicate(int[] nums) {
    Arrays.sort(nums);
    int left = 0, right = nums.length - 1, mid = 0;
    while (left < right) {
      mid = left + (right - left) / 2;
      if (nums[mid] == nums[mid + 1]) {
        return nums[mid]; // What you do here is find if what you are looking is
                          // next .
      }
      /*
       * What we do next is to search the balance , we will expect mid to be in
       * the mid, but if for some reason: 1. mid is at the right(nums[mid]<mid)
       * it will mean that , the duplicate is using an additional number at the
       * left. 2. mid is at the left(nums[mid]>mid) it will mean that , the
       * duplicate is using an additional numer at the right. dup 1 2 3 4 4 orig
       * 0 1 2 3 4
       */
      else if (nums[mid] > mid) {
        left = mid;
      } else {
        right = mid;
      }
    }
    return nums[left];
  }

  /**
   * Buggy
   * 
   * @param nums
   * @return
   */
  public int findDuplicateNoSort(int[] nums) {
    // Arrays.sort(nums);
    int left = 1, right = nums.length, mid = 0, count = 0;
    while (left < right) {
      mid = left + (right - left) / 2;
      count = 0;
      for (int num : nums) { // You count on a balance based on where the mid is
                             // supposed to be
        if (num <= mid)
          count++;
      }
      if (count <= mid)
        left = mid + 1;
      else
        right = mid;
    }
    return left;
  }
}
