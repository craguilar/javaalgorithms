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

  @Test
  public void lengthOfLongestSubstring(){
    assertEquals(1,StringAlgorithms.lengthOfLongestSubstring("a"));
    assertEquals(1, StringAlgorithms.lengthOfLongestSubstring("aaaaa"));
    assertEquals(3, StringAlgorithms.lengthOfLongestSubstring("abcabc"));
    assertEquals(3, StringAlgorithms.lengthOfLongestSubstring("abac"));
  }


  @Test 
  public void oneEditApartTest(){
    assertEquals(false, StringAlgorithms.oneEditApart("cat", "dog"));
    assertEquals(true, StringAlgorithms.oneEditApart("cat", "cats"));
    assertEquals(true, StringAlgorithms.oneEditApart("cat", "cut"));
    assertEquals(true, StringAlgorithms.oneEditApart( "geaks",  "geeks"));
  }
}