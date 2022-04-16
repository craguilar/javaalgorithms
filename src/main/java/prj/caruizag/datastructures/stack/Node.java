package prj.caruizag.datastructures.stack;

public class Node {
  private int data;
  private Node sig;

  public Node(int data, Node sig) {
    this.data = data;
    this.sig = sig;
  }

  public void setData(int data) {
    this.data = data;
  }

  public int getData() {
    return data;
  }

  public void setSig(Node sig) {
    this.sig = sig;
  }

  public Node getSig() {
    return sig;
  }

}