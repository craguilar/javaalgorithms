package algorithms.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * On a 2D plane, we place stones at some integer coordinate points. Each
 * coordinate point may have at most one stone.
 * 
 * Now, a move consists of removing a stone that shares a column or row with
 * another stone on the grid.
 * 
 * What is the largest possible number of moves we can make?
 */
public class RemoveStones {

  int max;

  public int removeStones(int[][] stones) {
    max = 0;
    Map<String, List<String>> graph = createGraph(stones);
    Set<String> visited = new HashSet<>();
    for (String key : graph.keySet()) {
      if (!visited.contains(key)) {
        dfs(graph, key, visited);
      }
    }
    return max;

  }

  private void dfs(Map<String, List<String>> graph, String current, Set<String> visited) {
    List<String> adjacentStones = graph.get(current);
    if (adjacentStones.size() == 0) {
      return;
    }
    visited.add(current);
    for (String stone : adjacentStones) {
      if (!visited.contains(stone)) {
        max++;
        dfs(graph, stone, visited);
      }

    }
  }

  private Map<String, List<String>> createGraph(int[][] stones) {
    Map<String, List<String>> graph = new HashMap<>();
    for (int i = 0; i < stones.length; i++) {
      String key = getKey(stones, i);
      graph.put(key, new ArrayList<>());
      for (int j = 0; j < stones.length; j++) {
        if (i != j && (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1])) {
          String relatedStone = getKey(stones, j);
          graph.get(key).add(relatedStone);
        }
      }
    }
    return graph;
  }

  private String getKey(int[][] stones, int i) {
    return stones[i][0] + "," + stones[i][1];
  }
}