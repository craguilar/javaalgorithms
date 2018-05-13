package datastructures.list;

public class LinkedList {
	private DNode H;
	private DNode T;
	private int value;
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
		DNode aux = previous.getRight();
		temp.setRight(aux);
		previous.setRight(temp);
		if (temp.getRight() == null)
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
			current = anterior.getRight();
			anterior.setRight(current.getRight());
		} else {
			current = H;
			H = H.getRight();
		}
		return current;
	}

	private DNode find(int data, DNode q) {
		while (q != null && data != q.getValue())
			q = q.getRight();
		return q;
	}

	public DNode findPrevious(int key) {
		exist = false;
		DNode current, anterior = null;
		for (current = H; current != null; current = current.getRight()) {
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

	private DNode swap(DNode i, DNode indice) {
		int aux = i.getValue();
		i.setValue(indice.getValue());
		indice.setValue(aux);
		return i.getRight();
	}
	/*
	 * 
	 * Definition for singly-linked list. class ListNode { public int val; public
	 * ListNode next; ListNode(int x) { val = x; next = null; } }
	 * 
	 * public class Solution { public ListNode reverseBetween(ListNode A, int m,
	 * int n) {
	 * 
	 * int i; ListNode node = A; ListNode prev = null; m--; n--;
	 * 
	 * for (i = 0; i < m; i++) { prev = node; node = node.next; }
	 * 
	 * if (prev != null) prev.next = reverseList(node, n - m + 1); else A =
	 * reverseList(node, n - m + 1);
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
