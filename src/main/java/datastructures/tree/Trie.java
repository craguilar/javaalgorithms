package datastructures.tree;

public class Trie {
  TrieNode root;

  Trie(String[] array) {
    root = new TrieNode();
    for (String s : array) {
      insert(s);
    }
  }

  public void insert(String s) {
    TrieNode cur = root;
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      cur.children.computeIfAbsent(ch, k -> new TrieNode());
      cur = cur.children.get(ch);
    }
    cur.isWord = true;
  }

  /**
   * The search function receives
   * 
   * @param s
   *          String , where search will start from character 0 until a word
   *          match
   * @return the size of the matching string.
   */
  public int search(String s) {
    TrieNode cur = root;
    int result = -1;

    for (int i = 0; cur != null && i < s.length(); i++) {
      cur = cur.children.get(s.charAt(i));
      if (cur != null && cur.isWord) {
        result = i + 1;
      }
    }
    System.out.println(s + " result " + result);
    return result;
  }
}
