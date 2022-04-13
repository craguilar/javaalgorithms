package algorithms.dp;

import java.util.Arrays;

/**
 * Problem of scheduling several competing activities that require exclusive access to a common
 * resource, with a goal of selecting a maximum-size set of mutually exclusive elements.
 * 
 */
public class ActivitySelection {

  public int maxActivitiesToComplete(int[] starts, int[] ends) {

    // Data prep
    Resource[] resources = new Resource[starts.length];
    for (int i = 0; i < starts.length; i++) {
      resources[i] = new Resource(starts[i], ends[i]);
    }
    Arrays.sort(resources, (Resource a, Resource b) -> a.end - b.end);
    int[] dp = new int[starts.length];
    dp[0] = 1;
    int prevEnd = resources[0].end;
    for (int i = 1; i < resources.length; i++) {
      // Find the maximum of our two options:
      // Scheduling the current job or skipping
      if (resources[i].start >= prevEnd) {
        dp[i] = dp[i - 1] + 1;
        prevEnd = resources[i].end;
      } else {
        dp[i] = dp[i - 1];
      }

    }
    return dp[resources.length - 1];
  }

  static class Resource {
    int start;
    int end;

    public Resource(int start, int end) {
      this.start = start;
      this.end = end;
    }

    @Override
    public String toString() {
      return String.format("[%s-%s]", start, end);
    }
  }
}
