package prj.caruizag.datastructures.list;

public class LinkedList {
  private DNode H;
  private DNode T;
  private boolean exist;

  @SuppressWarnings("oracle.jdeveloper.java.multiple-assignment")
  public LinkedList() {
    H = T = null;
  }

  public void insert(int dato) {
    this.insertEnd(dato);
  }

  public void insertBegining(int value) {
    DNode temp = new DNode(value, H);
    H = temp;
    if (T == null)
      T = temp;
  }

  public void insertEnd(int dato) {
    if (T == null)
      insertBegining(dato);
    else
      insertNode(T, dato);
  }

  private void insertNode(DNode previous, int data) {
    DNode temp = new DNode(data, null);
    DNode aux = previous.getNext();
    temp.setNext(aux);
    previous.setNext(temp);
    if (temp.getNext() == null)
      T = temp;
  }

  public void insertAfter(DNode after, int dato) {
    insertNode(after, dato);
  }

  public void insertBefore(DNode prev, int dato) {
    if (prev != null)
      insertNode(prev, dato);
    else
      insertBegining(dato);
  }

  public DNode delete(int data) {
    DNode anterior = findPrevious(data);
    DNode current = null;
    if (!getExist())
      return null;
    else if (H == T) {
      current = H;
      H = T = null;
    } else if (anterior != null) {
      current = anterior.getNext();
      anterior.setNext(current.getNext());
    } else {
      current = H;
      H = H.getNext();
    }
    return current;
  }


  public DNode findPrevious(int key) {
    exist = false;
    DNode current, anterior = null;
    for (current = H; current != null; current = current.getNext()) {
      if (exist = current.getValue() == key)
        return anterior;
      else
        anterior = current;
    }
    return null;
  }

  public DNode getHead() {
    return H;
  }

  private Boolean getExist() {
    return exist;
  }

  /*
   * 
   * Definition for singly-linked list. class ListNode { public int val; public ListNode next;
   * ListNode(int x) { val = x; next = null; } }
   * 
   * public class Solution { public ListNode reverseBetween(ListNode A, int m, int n) {
   * 
   * int i; ListNode node = A; ListNode prev = null; m--; n--;
   * 
   * for (i = 0; i < m; i++) { prev = node; node = node.next; }
   * 
   * if (prev != null) prev.next = reverseList(node, n - m + 1); else A = reverseList(node, n - m +
   * 1);
   * 
   * return A; }
   * 
   * public ListNode reverseList(ListNode A, int count) {
   * 
   * ListNode node, prev, temp, last;
   * 
   * node = A; last = A;
   * 
   * if (node == null) return null;
   * 
   * prev = null;
   * 
   * while (node != null && count > 0) {
   * 
   * temp = node.next; node.next = prev; prev = node; node = temp; count--; }
   * 
   * last.next = node;
   * 
   * return prev; } }
   */

}
