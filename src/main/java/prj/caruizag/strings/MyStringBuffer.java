package prj.caruizag.strings;

import java.util.BitSet;

/**
 * @author carlos
 *
 */
public class MyStringBuffer {
  private StringBuilder str;

  public MyStringBuffer(String str) {
    super();
    this.str = new StringBuilder(str);
  }

  public String getStr() {
    return str.toString();
  }

  public void setStr(String str) {
    this.str.setLength(0);
    this.str.append(str);
  }

  /**
   * Implement an algorithm to determine if a string has all unique characters. What if you cannot
   * use additional data structures?
   * 
   * @return
   */
  public boolean hasUniqueCharacters() {
    BitSet b = new BitSet(('z' - 'a') * 2);
    for (int i = 0; i < str.length(); i++) {
      if (b.get(str.charAt(i) - 'a'))
        return false;
      b.set(str.charAt(i) - 'a');
    }
    return true;
  }

  /**
   * Function that indicates if s2 is a rotated version of s1
   * 
   * @param s1
   * @param s2
   * @return True if is rotated else if not
   */
  public static boolean isRotated(String s1, String s2) {
    return s1.length() == s2.length() && (s1 + s1).contains(s2);
  }

  /**
   * @param s
   * @return
   */
  public static String compressString(String s) {
    int[] charOcurrence = new int[('z' - 'a') * 2];
    for (int i = 0; i < s.length(); i++) {
      charOcurrence[s.charAt(i) - 'a']++;
    }
    StringBuilder str2 = new StringBuilder();
    for (int i = 0; i < charOcurrence.length; i++) {
      if (charOcurrence[i] > 0) {
        str2 = str2.append((char) (i + 'a'));
        str2 = str2.append('%');
        str2 = str2.append(charOcurrence[i]);
      }
    }
    return str2.toString();
  }

  /**
   * @param str
   * @return
   */
  public static boolean checkIfPalindrome(String str) {
    str = str.toLowerCase();
    int i = 0;
    int j = str.length() - 1 - i;
    while (j > i) {
      if (str.charAt(i) == str.charAt(j)) {
        i++;
        j--;
      } else {
        return false;
      }
    }
    return true;
  }

  /**
   * Given a string of lowercase letters, determine the index of the character whose removal will
   * make the string a palindrome. If the string is already a palindrome, then print . There will
   * always be a valid solution.
   * 
   * @return
   */
  public static int palindromeIndex(String s) {
    int index = -1;
    int n = s.length();
    int j = n - 1;
    for (int i = 0; i < n; i++) {
      if (s.charAt(i) != s.charAt(j - i))
        if (MyStringBuffer.checkIfPalindrome(s.substring(i, j - i)))
          return j - i;
        else
          return i;
    }
    return index;
  }

  /**
   * 
   * @param s String to invert
   * @param n Index that should be initialized on 0
   * @return
   */
  public static String reverseString(char[] s, int n) {
    if (n == s.length / 2)
      return String.valueOf(s);
    else {
      reverseString(s, n + 1);
      int temp = s[n];
      s[n] = s[s.length - n - 1];
      s[s.length - n - 1] = (char) temp;
    }
    return String.valueOf(s);
  }

  /**
   * Alice decides on an encryption scheme involving 2 large strings where encryption is dependent
   * on the minimum number of character deletions required to make the two strings anagrams. She
   * need your help in finding out this number.
   * 
   * Given two strings (they can be of same or different length) help her in finding out the minimum
   * number of character deletions required to make two strings anagrams Any characters can be
   * deleted from any of the strings.
   */
  public static int makeItAnagram(String aIn, String bIn) {
    int count = 0;
    String a = aIn;
    String b = bIn;
    int[] aSet = new int[95];
    int[] bSet = new int[95];
    for (int i = 0; i < a.length(); i++) {
      aSet[a.charAt(i) - ' ']++;
    }
    for (int i = 0; i < b.length(); i++) {
      bSet[b.charAt(i) - ' ']++;
    }
    for (int i = 0; i < aSet.length; i++) {
      count = count + Math.abs(aSet[i] - bSet[i]);
    }
    /* Print output to STDOUT */
    return count;
  }

  /**
   * @param n
   * @return
   */
  public static int getOnBits(int n) {
    int sum = 0;
    do {
      sum = sum + (n % 2);
      n = n / 2;
    } while (n >= 1);
    return sum;
  }


  /**
   * @param s
   * @return
   */
  public static String longestPalindrome(String s) {
    int n = s.length();
    if (n == 0) {
      return "";
    }

    String longest = s.substring(0, 1); // a single char itself is a
    // palindrome
    for (int i = 0; i < n - 1; i++) {
      String p1 = expandAroundCenter(s, i, i);
      if (p1.length() > longest.length()) {
        longest = p1;
      }

      String p2 = expandAroundCenter(s, i, i + 1);
      if (p2.length() > longest.length()) {
        longest = p2;
      }

    }
    return longest;
  }

  /**
   * @param s
   * @param c1
   * @param c2
   * @return
   */
  private static String expandAroundCenter(String s, int c1, int c2) {
    int l = c1;
    int r = c2;
    int n = s.length();
    while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
      l--;
      r++;
    }
    return s.substring(l + 1, r);
  }

  /**
   * d is fixed lenght
   * 
   * @param a
   * @param l
   * @return
   */
  public static String[] orderStrings(String[] a, int l) {
    int x = 'A'; // Offset
    int n = a.length; // N
    int[] count;
    String[] aux = new String[n];
    for (int d = l - 1; d >= 0; d--) {
      count = new int[('Z' - 'A') + 1 + 1];
      // Count freq
      for (int k = 0; k < n; k++)
        count[a[k].charAt(d) - x + 1]++;
      // Calculating indexes
      for (int k = 0; k < count.length - 1; k++)
        count[k + 1] = count[k] + count[k + 1];
      // Mapping in aux
      for (int k = 0; k < n; k++)
        aux[count[a[k].charAt(d) - x]++] = a[k];
      // Copy back
      System.arraycopy(aux, 0, a, 0, n);
    }
    return a;
  }

}
