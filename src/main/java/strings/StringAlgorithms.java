package strings;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class StringAlgorithms {

  /**
   * Given a string, find the length of the longest substring without repeating
   * characters.
   * 
   * @param s where search of longest longest substring without repeating
   *          characters will happen 
   * @return length of the longest substring.
   */
  public static int lengthOfLongestSubstring(final String s) {
    int max = 0;
    int beginWindow = 0;
    int endWindow = 0;
    for (int i = 0; i < s.length(); i++) {

      // On each iteration I need to keep a state of chars for that , I use
      // bag I expand the window with while and bag contains and then reduce
      // the begin window on if endWindow less than s lenth
      final Set<Character> bag = new HashSet<>();
      int currentMax = 0;
      endWindow = beginWindow;
      while (endWindow < s.length() && !bag.contains(s.charAt(endWindow))) {
        bag.add(s.charAt(endWindow));
        currentMax++;
        endWindow++;
      }
      if (endWindow < s.length()) {
        final Character toRemove = s.charAt(endWindow);
        while (beginWindow < s.length() && bag.contains(toRemove)) {
          bag.remove(s.charAt(beginWindow));
          beginWindow++;
        }
      }

      max = Math.max(max, currentMax);
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

  public static String findLongestWordWhichIsASubsequence(final String target, final String[] dictionary) {
    final String longestSubsequence = null;
    Arrays.sort(dictionary, new StringComparatorSize());
    for (int i = 0; i < dictionary.length; i++) {
      if (StringAlgorithms.isSubsequence(target, dictionary[i]))
        return dictionary[i];
    }
    return longestSubsequence;
  }

  /**
   * Given a string, return the sum of the numbers appearing in the string,
   * ignoring all other characters. A number is a series of 1 or more digit chars
   * in a row. (Note: Character.isDigit(char) tests if a char is one of the chars
   * '0', '1', .. '9'. Integer.parseInt(string) converts a string to an int.)
   * 
   * 
   * sumNumbers("abc123xyz") → 123 sumNumbers("aa11b33") → 44 sumNumbers("7 11") →
   * 18
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

}