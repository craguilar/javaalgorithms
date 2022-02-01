package algorithms.assignment;

import java.util.HashMap;
import java.util.Map;

public class EvenWorkAssignment {

  /**
   * A task is represented by a certain weight.
   * 
   * Given an amount of tasks and its associated weight to distribute, a list of
   * recipients and how much money each is owed, you should return the list of
   * recipients and how much each would be paid after following the business
   * logic below:
   * <ul>
   * <li>1 - no recipient is paid more than they are owed
   * <li>2 - the amount is divided as evenly as possible between the recipients
   * <ul>
   */
  public Map<String, Integer> assignTasks(Map<String, Integer> workers, int taskWork) {

    return distributeWork(workers, taskWork);
  }

  private Map<String, Integer> distributeWork(Map<String, Integer> workers, int availableWork) {

    Map<String, Integer> result = new HashMap<>();
    int totalowedAmount = 0;
    for (Map.Entry<String, Integer> entry : workers.entrySet()) {
      int workerAmount = entry.getValue();
      totalowedAmount += workerAmount;
      result.put(entry.getKey(), 0);
    }
    int maxToDistribute = Math.min(totalowedAmount, availableWork) / result.size();
    int leftToDistribute = Math.min(totalowedAmount, availableWork);
    // - If the total owed amount is <= available amount just assign each
    // whatthey need.
    // - The totalOwed amount > available amount Then assign available
    // amount/numberOfElemnts
    while (leftToDistribute > 0) {
      for (Map.Entry<String, Integer> entry : workers.entrySet()) {
        // If already depleted the use remove it
        if (entry.getValue() <= 0) {
          continue;
        }
        int amountOfWork = entry.getValue();
        int evenAmount = Math.min(Math.min(maxToDistribute, amountOfWork), leftToDistribute);
        leftToDistribute -= evenAmount;
        result.put(entry.getKey(), result.get(entry.getKey()) + evenAmount);
        workers.put(entry.getKey(), workers.get(entry.getKey()) - evenAmount);
      }
    }

    return result;

  }
}