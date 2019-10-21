package strings;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringAlgorithmsTest {

  @Test
  public void findLongestWordWhichIsASubsequenceTest() {
    String result = StringAlgorithms.findLongestWordWhichIsASubsequence("abppplee",
        new String[] { "able", "ale", "apple", "bale", "kangaroo" });
    assertEquals("apple",result);

  }
}