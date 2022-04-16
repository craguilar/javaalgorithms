package prj.caruizag.algorithms.dequeue;

import java.util.*;
import java.util.stream.Collectors;
import prj.caruizag.datastructures.list.DNode;
import prj.caruizag.datastructures.list.DoublyLinkedList;

/**
 * A chef receives all his orders for the day as a list of order ids.
 * 
 * Given this list, the chef chooses to prepare them in the following way.
 * 
 * He creates a new list by repeatedly removing the smallest eligible order from
 * the list and appending it to the new list.
 * 
 * An order is considered eligible if its id is greater than its immediate left
 * and right neighbors in the list. Return the order in which the chef creates
 * the new list
 * 
 * Clarifications: For order id at index 0, it is eligible if it is greater than
 * its right neighbor [Since no left neighbor exists] For order id at index n-1
 * in a list of size n, it is eligible if it is greater than its left neighbor
 * [Since no right neighbor exists] When there is only one order in the list, it
 * is automatically eligible. Order ids are unique.
 * 
 * Examples Example 1 order_ids = [3, 5, 1, 4, 2]
 * 
 * Iteration 1:
 * 
 * order_ids = [3, 5, 1, 4, 2] order-5 is elgible since 3 < 5 > 1 order-4 is
 * eligible, since 1 < 4 > 2 we pick the one with lowest id => order-4 order-4
 * is removed from the list
 * 
 * Iteration 2:
 * 
 * order_ids become [3, 5, 1, 2] order-5, order-2 are eligible, we pick the one
 * with lowest id => order-2
 * 
 * Iteration 3:
 * 
 * order_ids become [3, 5, 1] order-5 is eligible, we pick => order-5
 * 
 * Iteration 4: order_ids become [3, 1] order-3 is eligible, we pick => order-3
 * 
 * Iteration 5: order_ids become [1] order-1 is eligible, we pick => order-1
 * 
 * The output is [4, 2, 5, 3, 1]
 * 
 * Example 2 order_ids = [5, 6, 7] , : Expected output is [7, 6, 5]
 * 
 * Example 3 order_ids = [3, 2, 1] Expected output is [3, 2, 1]
 * 
 * Analysis: Helpful conditions
 * 
 * eligibility ( i > i-1 && i> i+1 ) || (i==0 && i> i+1 ) || ( i==n-1 && i>i-1)
 * || n==1 Finding peaks .
 * 
 * 
 * Approach 1)
 * 
 * while array is not empty : Iterate the array , and ever time find all the
 * peak - based on eligibility . During those peaks , keep to varibles the index
 * of the lastMinimun and value . After you completed n , remove that from the
 * least .
 * 
 * O(n^2)
 * 
 * 
 * Approach 2)
 * 
 * Example 1: [3, 5, 1, 4, 2] [3, 5, 1, 2] [3, 5, 1] [3, 1] [1] Iteration-1: [3,
 * 5, 1, 4, 2] QueueOfPeaks = {} QueueOfPeaks = {4,5} resultList {4} ,
 * QueueOfPeaks {5} array = [3, 1, 2] QueueOfPeaks = {2,5} resultList {4,2,5}
 * Iteration-2 : array = [3, 1] QueueOfPeaks = {3} resultList {4,2,5,3}
 * Iteration-3 :
 * 
 * Expected [4, 2, 5, 3, 1]
 * 
 * resultList while array is not empty : if MinQueueOfPeaks is empty fill the
 * Queue and remove from List
 * 
 * resultList .add( queue.poll()) fill the Queue after the Index where I removed
 * the last element {2,5} // Empty the queue resultList .add( queue.poll() )
 * 
 * Example 3: order_ids = [3, 2, 1] Expected output is [3, 2, 1]
 * 
 * Example 2: order_ids = [5, 6, 7] , : Expected output is [7, 6, 5]
 * 
 * Iteration-1: 7 Iteration-2: 6 Iteration-3: 5
 */
public class RemoveSmallestPeaksInOrder {

  /**
   * Brute force, it could result in O(n^2 in a worst case)
   * 
   * @param nums
   * @return
   */
  public int[] solveBruteForce(int[] nums) {
    int[] result = new int[nums.length];
    int n = nums.length;
    int assigned = 0;
    List<Integer> numsList = new ArrayList<>();
    for (int num : nums) {
      numsList.add(num);
    }
    while (assigned < n) {
      int indexOfMeanPeak = 0;
      int minPeak = Integer.MAX_VALUE;
      for (int i = 0; i < numsList.size(); i++) {
        if (isAPeak(numsList, i) && minPeak > numsList.get(i)) {
          minPeak = numsList.get(i);
          indexOfMeanPeak = i;
        }
      }
      result[assigned++] = numsList.get(indexOfMeanPeak);
      numsList.remove(indexOfMeanPeak);

    }
    return result;
  }

  /**
   * Improved from brute force, it doesn't go for the entire array but instead
   * it iterates over a PriorityQueue , the main problem with this approach is
   * that every time we remove an element from the PriorityQueue we will need to
   * re adjust the indexes of elements >= index we just removed. Again this is
   * complex handling
   * 
   * @param arrayOfOrders
   * @return
   */
  public int[] solveImprovedBruteForce(int[] nums) {

    List<Integer> orders = Arrays.stream(nums).boxed().collect(Collectors.toList());

    int assigned = 0;
    int[] result = new int[nums.length];
    PriorityQueue<Peak> processingOrders = new PriorityQueue<>((Peak a, Peak b) -> a.orderId - b.orderId);
    int counter = 0;
    for (Integer orderId : orders) {
      if (isAPeak(orders, counter)) {
        processingOrders.offer(new Peak(orderId, counter));
      }
      counter++;
    }

    while (!processingOrders.isEmpty()) {
      Peak element = processingOrders.poll();
      result[assigned++] = (element.orderId);
      orders.remove(element.index);
      // Adjust indexes in the queue
      Iterator<Peak> it = processingOrders.iterator();
      while (it.hasNext()) {
        Peak order = it.next();
        if (order.index > element.index) {
          order.index -= 1;
        }
      }
      if (isAPeak(orders, element.index - 1)) {
        processingOrders.offer(new Peak(orders.get(element.index - 1), element.index - 1));
      }
      if (isAPeak(orders, element.index)) {
        processingOrders.offer(new Peak(orders.get(element.index), element.index));
      }

    }
    return result;
  }

  /**
   * Uses a Doubly Linked list and referenced nodes for removal rather than
   * index based
   */
  public int[] solveOptimized(int[] nums) {
    int n = nums.length;
    if (n <= 1) {
      return nums;
    }
    int[] arr = new int[n];
    DoublyLinkedList list = new DoublyLinkedList();
    list.insertBegining(Integer.MIN_VALUE);
    list.insertEnd(Integer.MIN_VALUE);

    for (int i = 0; i < n; i++) {
      list.insertBefore(list.getTail(), nums[i]);
    }

    DNode trav = list.getHead().getNext();
    PriorityQueue<DNode> pq = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
    while (trav.getNext() != null) {
      if (trav.getPrevious().getValue() < trav.getValue() && trav.getNext().getValue() < trav.getValue()) {
        pq.offer(trav);
      }
      trav = trav.getNext();
    }
    int k = 0;
    while (pq.size() > 0) {
      DNode pr = pq.poll();
      list.remove(pr);
      arr[k++] = pr.getValue();
      trav = pr.getPrevious();
      if (trav != list.getHead() && trav != list.getTail() && trav.getPrevious().getValue() < trav.getValue()
          && trav.getNext().getValue() < trav.getValue()) {
        pq.offer(trav);
      }
      trav = trav.getNext();
      if (trav != list.getHead() && trav != list.getTail() && trav.getPrevious().getValue() < trav.getValue()
          && trav.getNext().getValue() < trav.getValue()) {
        pq.offer(trav);
      }

    }
    return arr;
  }

  /**
   * Return if a givel element is a peak
   */
  private static boolean isAPeak(List<Integer> array, int i) {
    if (i >= array.size() || i < 0) {
      return false;
    }
    return !array.isEmpty() && (array.size() == 1 || (i == 0 && array.get(i) > array.get(i + 1))
        || (i == array.size() - 1 && array.get(i) > array.get(i - 1))
        || (i > 0 && array.size() > 2 && array.get(i) > array.get(i - 1) && array.get(i) > array.get(i + 1)));
  }

  private static class Peak {
    int orderId;
    int index;

    public Peak(int orderId, int index) {
      this.orderId = orderId;
      this.index = index;
    }

  }
}
