package algorithms.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problems {

  private static Map<Character, List<String>> keyPad = new HashMap<>();

  static {
    keyPad.put('2', Arrays.asList("a", "b", "c"));
    keyPad.put('3', Arrays.asList("d", "e", "f"));
    keyPad.put('4', Arrays.asList("g", "h", "i"));
    keyPad.put('5', Arrays.asList("j", "k", "l"));
    keyPad.put('6', Arrays.asList("m", "n", "o"));
    keyPad.put('7', Arrays.asList("p", "q", "r", "s"));
    keyPad.put('8', Arrays.asList("t", "u", "v"));
    keyPad.put('9', Arrays.asList("w", "x", "y", "z"));

  };

  /**
   * Calculate and returns all possible combinations of digits according to a
   * key pad on a phone number
   * 
   * @param digits
   * @return
   */
  public static List<String> letterCombinations(String digits) {
    List<String> result = new ArrayList<>();
    if (digits == null || digits.length() == 0) {
      return result;
    }
    recurse(digits, "", 0, result);

    return result;
  }

  private static void recurse(String digits, String current, int index, List<String> result) {

    if (index >= digits.length()) {
      result.add(current);
      return;
    }
    for (String digit : keyPad.get(digits.charAt(index))) {
      recurse(digits, current + digit, index + 1, result);
    }
  }
}