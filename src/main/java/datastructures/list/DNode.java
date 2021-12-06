package datastructures.list;

public class DNode {
  private int value;
  private DNode previous;
  private DNode next;

  public DNode(int value, DNode previous, DNode next) {
    this.value = value;
    this.previous = previous;
    this.next = next;
  }

  public DNode(int value, DNode next) {
    this.value = value;
    this.previous = null;
    this.next = next;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public void setPrevious(DNode previous) {
    this.previous = previous;
  }

  public DNode getPrevious() {
    return previous;
  }

  public void setNext(DNode right) {
    this.next = right;
  }

  public DNode getNext() {
    return next;
  }
}
