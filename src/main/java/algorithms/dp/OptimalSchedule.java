package algorithms.dp;

import java.util.Arrays;
import javax.naming.directory.InvalidAttributeValueException;

/**
 * Problem Statement You're a delivery person, and you want to try planning out your schedule. You
 * can view a list of deliveries along with their associated start time, end time, and dollar amount
 * for completing the order. Assuming dashers can only deliver one order at a time, determine the
 * maximum amount of money you can make from the given deliveries.
 * 
 * The inputs are as follows:
 * 
 * <ul>
 * <li>int start_time: when you plan to start your schedule
 * <li>int end_time: when you plan to end your schedule
 * <li>int d_starts[n]: the start times of each delivery[i]
 * <li>int d_ends[n]: the end times of each delivery[i]
 * <li>int d_pays[n]: the pay for each delivery[i]
 * </ul>
 * 
 * The output should be an integer representing the maximum amount of money you can make by forming
 * a schedule with the given deliveries.
 * 
 * </p>
 * Constraints
 * <ul>
 * <li>end_time >= start_time
 * <li>d_ends[i] >= d_starts[i]
 * <li>d_pays[i] > 0 len(d_starts) == len(d_ends) == len(d_pays)
 * </ul>
 * 
 */
public class OptimalSchedule {


  public int maxProfit(int[] starts, int[] ends, int[] pays) {
    // Data preparation
    int[] dp = new int[starts.length];
    Arrays.fill(dp, -1);
    Job[] jobs = new Job[starts.length];
    for (int i = 0; i < starts.length; i++) {
      jobs[i] = new Job(starts[i], ends[i], pays[i]);
    }
    Arrays.sort(jobs, (Job a, Job b) -> a.end - b.end);
    // Recursion
    return findMaxProfit(jobs, 0, dp);
  }

  private int findMaxProfit(Job[] jobs, int index, int[] dp) {
    // If we've gone through all the items, return
    if (index == jobs.length) {
      return 0;
    }

    // return result directly if it's calculated
    if (dp[index] != -1) {
      // return dp[index];
    }
    // nextIndex is the index of next non-conflicting job
    int nextIndex = findNextJob(jobs, jobs[index].end);

    // Find the maximum of our two options: skipping or scheduling the current
    // job
    dp[index] = Math.max(findMaxProfit(jobs, nextIndex, dp) + jobs[index].pay,
        findMaxProfit(jobs, index + 1, dp));

    return dp[index];
  }

  private int findNextJob(Job[] jobs, int lastEndingTime) {
    int start = 0;
    int end = jobs.length - 1;
    int nextIndex = jobs.length;

    while (start <= end) {
      int mid = (start + end) / 2;
      if (jobs[mid].start > lastEndingTime) {
        nextIndex = mid;
        end = mid - 1;
      } else {
        start = mid + 1;
      }
    }
    return nextIndex;
  }

  static class Job {
    int start;
    int end;
    int pay;

    public Job(int start, int end, int pay) {
      this.start = start;
      this.end = end;
      this.pay = pay;
    }

    @Override
    public String toString() {
      return String.format("[%s-%s]", start, end);
    }
  }
}
