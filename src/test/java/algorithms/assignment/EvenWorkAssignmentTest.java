package algorithms.assignment;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class EvenWorkAssignmentTest {

  @Test
  public void assignTasksEvenlWork() {
    EvenWorkAssignment taskAssignment = new EvenWorkAssignment();
    // Test case 1: Less available
    Map<String, Integer> workers1 = new HashMap<>();
    workers1.put("a", 10);
    workers1.put("b", 10);
    workers1.put("c", 10);
    Map<String, Integer> result = taskAssignment.assignTasks(workers1, 15);
    result.forEach((key, value) -> assertEquals(Integer.valueOf(5), value));
    // Test case 2: Equally divided
    Map<String, Integer> workers2 = new HashMap<>();
    workers2.put("a", 10);
    workers2.put("b", 10);
    workers2.put("c", 10);
    Map<String, Integer> result2 = taskAssignment.assignTasks(workers2, 30);
    result2.forEach((key, value) -> assertEquals(Integer.valueOf(10), value));
    // Test case 2: Work exceeds workers
    Map<String, Integer> workers3 = new HashMap<>();
    workers3.put("a", 10);
    workers3.put("b", 10);
    workers3.put("c", 10);
    Map<String, Integer> result3 = taskAssignment.assignTasks(workers3, 45);
    result3.forEach((key, value) -> assertEquals(Integer.valueOf(10), value));
  }

  @Test
  public void assignTasksNotEvenlWork() {
    EvenWorkAssignment taskAssignment = new EvenWorkAssignment();
    // Test case 1: Less available
    Map<String, Integer> workers1 = new HashMap<>();
    workers1.put("a", 10);
    workers1.put("b", 5);
    workers1.put("c", 10);
    Map<String, Integer> result = taskAssignment.assignTasks(workers1, 15);
    result.forEach((key, value) -> assertEquals(Integer.valueOf(5), value));
    // Test case 2:
    Map<String, Integer> workers2 = new HashMap<>();
    workers2.put("a", 18);
    workers2.put("b", 5);
    workers2.put("c", 2);
    Map<String, Integer> result2 = taskAssignment.assignTasks(workers2, 30);
    assertEquals(Integer.valueOf(18), result2.get("a"));
    assertEquals(Integer.valueOf(5), result2.get("b"));
    assertEquals(Integer.valueOf(2), result2.get("c"));
    // Test case 3
    Map<String, Integer> workers3 = new HashMap<>();
    workers3.put("a", 18);
    workers3.put("b", 6);
    workers3.put("c", 5);
    Map<String, Integer> result3 = taskAssignment.assignTasks(workers3, 15);
    assertEquals(Integer.valueOf(5), result3.get("a"));
    assertEquals(Integer.valueOf(5), result3.get("b"));
    assertEquals(Integer.valueOf(5), result3.get("c"));

    // Test case
    Map<String, Integer> workers4 = new HashMap<>();
    workers4.put("a", 18);
    workers4.put("b", 5);
    workers4.put("c", 3);
    Map<String, Integer> result4 = taskAssignment.assignTasks(workers4, 15);
    assertEquals(Integer.valueOf(7), result4.get("a"));
    assertEquals(Integer.valueOf(5), result4.get("b"));
    assertEquals(Integer.valueOf(3), result4.get("c"));
  }

}
