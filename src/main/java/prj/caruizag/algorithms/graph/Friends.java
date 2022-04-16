package prj.caruizag.algorithms.graph;

import java.util.*;

public class Friends {

  public static void main(String[] args) {
    // you can write to stdout for debugging purposes, e.g.
    User s = new User("Shelly");
    User i = new User("Ian");
    User c = new User("Carlos");
    User j = new User("Sanjay");
    User b = new User("Bill");
    User m = new User("Maria");
    User k = new User("Kim");
    Graph g = new Graph();
    // Add users
    g.addUser(s);
    g.addUser(i);
    g.addUser(c);
    g.addUser(j);
    g.addUser(b);
    g.addEdge(s, i);
    g.addEdge(s, j);
    g.addEdge(i, c);
    g.addEdge(j, b);
    g.addEdge(b, c);
    g.addEdge(m, k);
    System.out.println("Minimal distance between Shelly and Carlos "
        + g.findShortestDistanceBetweenTwoFriends(s, c));
    System.out.println("Minimal distance between Carlos and Shelly "
        + g.findShortestDistanceBetweenTwoFriends(c, s));
    g.addEdge(s, c);
    System.out
        .println("Minimal distance between Carlos and Shelly after Shelly added Carlos as Friend "
            + g.findShortestDistanceBetweenTwoFriends(c, s));
    System.out
        .println("Minimal distance between Carlos and Shelly after Shelly added Carlos as Friend "
            + g.findShortestDistanceBetweenTwoFriends(c, s));
    System.out.println(
        "Minimal distance between Shelly and Kim " + g.findShortestDistanceBetweenTwoFriends(s, k));
    g.addEdge(b, m);
    System.out
        .println("Minimal distance between Shelly and Kim after Bill became friend with Maria= "
            + g.findShortestDistanceBetweenTwoFriends(s, k));
  }

  public static class Graph {
    private Set<User> nodes = new HashSet<>();
    private Map<User, Set<User>> edges = new HashMap<>();

    /**
     * 
     * @param user1
     * @param user2
     * @return -1 if no path viable
     */
    public int findShortestDistanceBetweenTwoFriends(User user1, User user2) {
      Map<User, Integer> distances = new HashMap<>();

      dfs(user1, new HashSet<>(), distances);

      return distances.get(user2) != null ? distances.get(user2) : -1;
    }

    private void dfs(User node1, Set<User> visited, Map<User, Integer> distances) {

      if (visited.contains(node1) || edges.get(node1) == null) {
        return;
      }
      visited.add(node1);
      for (User friend : edges.get(node1)) {
        // visited [Shelly, Sanjay , Bill ] 2
        Integer prevDistance = distances.getOrDefault(node1, 0); // 2
        Integer currentDistance = distances.getOrDefault(friend, 0);
        if (currentDistance == 0 || prevDistance + 1 <= currentDistance) {
          distances.put(friend, currentDistance + prevDistance + 1);

          dfs(friend, visited, distances);

        }
      }
      visited.remove(node1);
    }

    public void addUser(User user) {
      if (!nodes.contains(user)) {
        nodes.add(user);
      }
    }

    public void addEdge(User user1, User user2) {

      Set<User> linkedUsers1 = edges.getOrDefault(user1, new HashSet<>());
      Set<User> linkedUsers2 = edges.getOrDefault(user2, new HashSet<>());
      linkedUsers1.add(user2);
      edges.put(user1, linkedUsers1);
      linkedUsers2.add(user1);
      edges.put(user2, linkedUsers2);
    }
  }


  /**
   * Friend object
   */
  public static class User {
    private final String name;

    public User(String name) {
      this.name = name;
    }

    @Override
    public int hashCode() {
      return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof User) {
        final User user = (User) obj;

        return this.name.equals(user.name);
      }
      return false;
    }


  }
}
