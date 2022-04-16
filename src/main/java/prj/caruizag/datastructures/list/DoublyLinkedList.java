package prj.caruizag.datastructures.list;

public class DoublyLinkedList {

  private DNode head;
  private DNode tail;

  public DoublyLinkedList() {
    head = tail = null;
  }

  private DNode insertNode(DNode find, int value) {
    DNode temp = new DNode(value, null, null);
    temp.setPrevious(find);
    temp.setNext(find.getNext());
    find.getNext().setPrevious(temp);
    find.setNext(temp);
    if (temp.getNext() == null)
      tail = temp;
    return temp;
  }

  public DNode insertBegining(int value) {
    DNode q = new DNode(value, null, head);
    if (head == null)
      tail = q;
    else
      head.setPrevious(q);
    head = q;
    return q;
  }

  public DNode insertEnd(int value) {
    DNode q = new DNode(value, tail, null);
    if (head == null)
      head = q;
    else
      tail.setNext(q);
    tail = q;
    return q;
  }

  public void insertAfter(DNode insert, int dato) {
    if (insert != null) {
      if (insert == tail)
        insertEnd(dato);
      else
        insertNode(insert, dato);
    }
  }

  public void insertBefore(DNode insert, int dato) {
    if (insert != null) {
      if (insert == head)
        insertBegining(dato);
      else
        insertNode(insert.getPrevious(), dato);
    }
  }

  public DNode delete(int data) {
    DNode delete = find(data);

    return remove(delete);
  }

  public DNode remove(DNode delete) {
    DNode aux = delete;
    if (delete != null) {
      if (head == tail)
        head = tail = null;
      else if (delete == head) {
        delete.getNext().setPrevious(null);
        head = delete.getNext();
      } else if (delete == tail) {
        delete.getPrevious().setNext(null);
        tail = delete.getPrevious();
      } else {
        delete.getPrevious().setNext(delete.getNext());
        delete.getNext().setPrevious(delete.getPrevious());
      }
    }
    return aux;
  }

  public DNode find(int data) {
    DNode q = head;
    while (q != null && data != q.getValue())
      q = q.getNext();
    return q;
  }

  public DNode getHead() {
    return head;
  }

  public DNode getTail() {
    return tail;
  }
}
