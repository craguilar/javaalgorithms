package strings;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class StringAlgorithmsTest {

  @Test
  public void ladderLengthTest() {
    int length = StringAlgorithms.ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
    assertEquals(5, length);
  }

  @Test
  public void findLongestWordWhichIsASubsequenceTest() {
    String result = StringAlgorithms.findLongestWordWhichIsASubsequence("abppplee",
        new String[] { "able", "ale", "apple", "bale", "kangaroo" });
    assertEquals("apple", result);

  }

  @Test
  public void lengthOfLongestSubstring() {
    // TODO : Add jmh
    assertEquals(1, StringAlgorithms.lengthOfLongestSubstring("a"));
    assertEquals(1, StringAlgorithms.lengthOfLongestSubstring("aaaaa"));
    assertEquals(3, StringAlgorithms.lengthOfLongestSubstring("abcabc"));
    assertEquals(3, StringAlgorithms.lengthOfLongestSubstring("abac"));
    assertEquals(3, StringAlgorithms.lengthOfLongestSubstring("pwwkew"));
  }

  @Test
  public void oneEditApartTest() {
    assertEquals(false, StringAlgorithms.oneEditApart("cat", "dog"));
    assertEquals(true, StringAlgorithms.oneEditApart("cat", "cats"));
    assertEquals(true, StringAlgorithms.oneEditApart("cat", "cut"));
    assertEquals(true, StringAlgorithms.oneEditApart("geaks", "geeks"));
  }
}