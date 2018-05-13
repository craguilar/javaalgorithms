package algs.dequeue;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

	private class Node {
		public Item value;
		public Node next;
		public Node prev;
	}

	private Node head;
	private Node tail;
	private int count;

	public Deque() {
		// construct an empty deque
		head = tail = null;
		count = 0;
	}

	public boolean isEmpty() {
		// is the deque empty?
		return count == 0;
	}

	public int size() {
		// return the number of items on the deque
		return count;
	}

	public void addFirst(Item item) {
		// add the item to the front
		if (!validateItem(item))
			throw new java.lang.NullPointerException();
		Node old = head;

		head = new Node();
		head.value = item;
		head.next = old;
		head.prev = null;
		if (size() == 0)
			tail = head;
		else
			old.prev = head;
		count++;
	}

	public void addLast(Item item) {
		// add the item to the end
		if (!validateItem(item))
			throw new java.lang.NullPointerException();
		Node old = tail;
		tail = new Node();
		tail.prev = old;
		tail.value = item;
		tail.next = null;
		if (isEmpty())
			head = tail;
		else
			old.next = tail;
		count++;
	}

	public Item removeFirst() {
		// remove and return the item from the front
		Node old = head;
		if (isEmpty())
			throw new java.util.NoSuchElementException();
		if (old.next == null)
			head = tail = null;
		else {
			head = old.next;
			head.prev = null;
		}
		count--;
		return old.value;
	}

	public Item removeLast() {
		// remove and return the item from the end
		if (isEmpty())
			throw new java.util.NoSuchElementException();
		Node old = tail;

		if (tail == head)
			head = tail = null;
		else {
			tail = old.prev;// <<-- Bug :)
			tail.next = null;
		}
		count--;
		return old.value;
	}

	private boolean validateItem(Item i) {
		return i != null;
	}

	public Iterator<Item> iterator() {
		// return an iterator over items in order from front to end
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> {
		private Node current = head;

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}

		public Item next() {
			if (current == null)
				throw new java.util.NoSuchElementException();
			Item item = current.value;
			current = current.next;
			return item;
		}
	}

	public static void main(String[] args) {
		Deque<Integer> deque = new Deque<>();
		deque.addFirst(0);
		deque.removeFirst();
	}
}