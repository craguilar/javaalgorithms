package prj.caruizag.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;

public class StringAlgorithms {

  public static String writeStringSinusoidally(String s) {

    StringBuffer up = new StringBuffer();
    StringBuffer center = new StringBuffer();
    StringBuffer down = new StringBuffer();
    boolean goUp = true;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      // Center
      if (i % 2 == 0) {
        up.append(' ');
        center.append(c);
        down.append(' ');
      } else if (goUp) {
        up.append(c);
        center.append(' ');
        down.append(' ');
        goUp = !goUp;
      } else {
        up.append(' ');
        center.append(' ');

        down.append(c);
        goUp = !goUp;
      }
    }
    return String.join("\n", up, center, down);
  }

  /**
   * 
   * @param str
   * @param pairs
   * @return
   */
  public static String swapLexOrder(String str, int[][] pairs) {
    Set<String> visited = new HashSet<>();
    Queue<String> toVisit = new LinkedList<>();
    String last = str;
    toVisit.add(str);
    while (!toVisit.isEmpty()) {
      String current = toVisit.poll();
      if (visited.contains(current)) {
        continue;
      }
      visited.add(current);
      for (int[] pair : pairs) {
        String swaped = swap(current, pair[0], pair[1]);
        toVisit.add(swaped);
        last = swaped.compareTo(last) >= 0 ? swaped : last;
      }
    }
    return last;
  }

  private static String swap(String str, int a, int b) {
    char[] charArray = str.toCharArray();
    char c = charArray[a - 1];
    charArray[a - 1] = charArray[b - 1];
    charArray[b - 1] = c;
    return new String(charArray);
  }

  /**
   * Given two words (beginWord and endWord), and a dictionary's word list, find the length of
   * shortest transformation sequence from beginWord to endWord, such that:
   * <ul>
   * <li>Only one letter can be changed at a time. Each transformed word must exist.</li>
   * <li>in the word list. Note that beginWord is not a transformed word.</li>
   * <ul>
   * 
   * @param beginWord
   * @param endWord
   * @param wordList
   * @return length of the shortest path.
   */
  public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
    List<String> wordListFinal = new ArrayList<>(wordList);
    Map<String, Set<String>> graph = createGraph(beginWord, endWord, wordListFinal);
    return bfs(graph, beginWord, endWord);
  }

  private static Map<String, Set<String>> createGraph(String beginWord, String endWord,
      List<String> wordList) {
    Map<String, Set<String>> graph = new HashMap<>();
    if (!wordList.contains(beginWord)) {
      wordList.add(0, beginWord);
    }
    for (int i = 0; i < wordList.size(); i++) {
      String currentWord = wordList.get(i);
      Set<String> neighbors = new HashSet<>();
      graph.put(currentWord, neighbors);
      for (int j = 0; j < wordList.size(); j++) {
        boolean isOneLetterAway = isOneLetterAway(currentWord, wordList.get(j));
        boolean isNotSameWord = !currentWord.equals(wordList.get(j));
        if (isNotSameWord && isOneLetterAway) {
          graph.get(currentWord).add(wordList.get(j));
        }
      }
    }
    return graph;
  }

  private static int bfs(Map<String, Set<String>> graph, String beginWord, String endWord) {

    Set<String> visited = new HashSet<>();
    Queue<Pair<String, Integer>> queue = new LinkedList<>();
    queue.add(Pair.of(beginWord, 1));
    while (!queue.isEmpty()) {
      Pair<String, Integer> currentNode = queue.poll();
      String current = currentNode.getKey();
      int level = currentNode.getValue();
      if (!current.equals(endWord) && !visited.contains(current)) {
        Set<String> neighbors = graph.get(current);
        visited.add(current);
        for (String neighbor : neighbors) {
          queue.add(Pair.of(neighbor, level + 1));
        }
      } else if (current.equals(endWord)) {
        return level;
      }
    }
    return 0;

  }

  private static boolean isOneLetterAway(String s1, String s2) {
    int m = s1.length();
    int n = s2.length();
    if (Math.abs(m - n) > 1)
      return false;

    int count = 0; // Count of edits
    int i = 0, j = 0;
    while (i < m && j < n) {
      // If current characters don't match
      if (s1.charAt(i) != s2.charAt(j)) {
        if (count == 1)
          return false;
        i++;
        j++;
        count++;
      } else {
        i++;
        j++;
      }
    }

    if (i < m || j < n)
      count++;

    return count == 1;
  }

  /**
   * Given a string, find the length of the longest substring without repeating characters.
   * 
   * @param s where search of longest longest substring without repeating characters will happen
   * @return length of the longest substring.
   */
  public static int lengthOfLongestSubstring(final String s) {
    int begin = 0;
    int end = 0;
    int max = 0;
    Set<Character> bag = new HashSet<>();
    while (end < s.length()) {
      Character currentChar = s.charAt(end);
      if (!bag.contains(currentChar)) {
        bag.add(currentChar);
      } else {
        while (bag.contains(currentChar)) {
          bag.remove(s.charAt(begin));
          begin++;
        }
        bag.add(currentChar);
      }
      end++;
      max = Math.max(max, end - begin);
    }
    return max;
  }

  public static boolean oneEditApart(final String s1, final String s2) {
    final int[] differentChars = new int[26];
    if (s1.length() > s2.length()) {
      populateDifferentChars(differentChars, s1);
      removeExistingChars(differentChars, s2);
    } else {
      populateDifferentChars(differentChars, s2);
      removeExistingChars(differentChars, s1);
    }
    int editSum = 0;
    for (int i = 0; i < differentChars.length; i++) {
      editSum = editSum + Math.abs(differentChars[i]);
      if (editSum > 2)
        return false;
    }
    return true;
  }

  private static void populateDifferentChars(final int[] differentChars, final String s1) {
    for (int i = 0; i < s1.length(); i++) {
      differentChars[s1.charAt(i) - 'a']++;
    }
  }

  private static void removeExistingChars(final int[] differentChars, final String s1) {
    for (int i = 0; i < s1.length(); i++) {
      differentChars[s1.charAt(i) - 'a']--;
    }
  }

  public static String findLongestWordWhichIsASubsequence(final String target,
      final String[] dictionary) {
    final String longestSubsequence = null;
    Arrays.sort(dictionary, new StringComparatorSize());
    for (int i = 0; i < dictionary.length; i++) {
      if (StringAlgorithms.isSubsequence(target, dictionary[i]))
        return dictionary[i];
    }
    return longestSubsequence;
  }

  /**
   * Given a string, return the sum of the numbers appearing in the string, ignoring all other
   * characters. A number is a series of 1 or more digit chars in a row. (Note:
   * Character.isDigit(char) tests if a char is one of the chars '0', '1', .. '9'.
   * Integer.parseInt(string) converts a string to an int.)
   * 
   * 
   * sumNumbers("abc123xyz") → 123 sumNumbers("aa11b33") → 44 sumNumbers("7 11") → 18
   */

  public static boolean isSubsequence(final String target, final String word) {
    int counter = 0;
    for (int i = 0; i < word.length(); i++) {
      while (counter < target.length() && word.charAt(i) != target.charAt(counter)) {
        counter++;
      }
      if (counter >= target.length()) {
        return false;
      }
      counter++;
    }
    return true;
  }

  static class StringComparatorSize implements Comparator<String> {

    @Override
    public int compare(final String o1, final String o2) {
      return o2.length() - o1.length();
    }

  }

  public static String compressString(String s) {

    StringBuffer sb = new StringBuffer();
    int counter = 1;
    for (int i = 0; i < s.length(); i++) {
      if (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
        counter++;
      } else {
        String count = counter == 1 ? "" : counter + "";
        sb.append(s.charAt(i) + "" + count);
        counter = 1;
      }
    }
    return sb.toString();

  }

}
