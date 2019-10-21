package strings;

import java.util.Arrays;
import java.util.Comparator;

public class StringAlgorithms {

  public static String findLongestWordWhichIsASubsequence(String target, String[] dictionary) {
    String longestSubsequence = null;
    Arrays.sort(dictionary, new StringComparatorSize());
    for (int i = 0; i < dictionary.length; i++) {
      if (StringAlgorithms.isSubsequence(target, dictionary[i]))
        return dictionary[i];
    }
    return longestSubsequence;
  }

  // "abpppeee" , "apple"
  public static boolean isSubsequence(String target , String word) {
    int counter = 0;
    for(int i=0;i<word.length();i++ ){
      while(counter< target.length() && word.charAt(i) != target.charAt(counter)){ 
        counter++;
      }
      if(counter>= target.length()){
        return false;
      }
      counter++;
    }
    return true;
  }

  static class StringComparatorSize implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
      return o2.length()-o1.length() ;
    }

  }


}