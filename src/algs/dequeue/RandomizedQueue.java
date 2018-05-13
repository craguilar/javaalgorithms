package algs.dequeue;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] items;
	private int count;

	public RandomizedQueue() {
		// construct an empty randomized queue
		items = (Item[]) new Object[1];
		count = 0;
	}

	public boolean isEmpty() {
		// is the queue empty?
		return count == 0;
	}

	public int size() {
		// return the number of items on the queue
		return count;
	}

	public void enqueue(Item item) {
		if (!validateItem(item))
			throw new java.lang.NullPointerException();
		if (count == items.length) {
			resize(2 * items.length);
		}
		items[count] = item;
		// add the item
		++count;
	}

	public Item dequeue() {
		// remove and return a random item
		emptyCheck();
		int rand = StdRandom.uniform(count);
		Item elt = items[rand];
		if (count - 1 == rand) {
			items[rand] = null;
		} else {
			items[rand] = items[count - 1];
			items[count - 1] = null;
		}
		if (count > 0 && count == items.length / 4) {
			resize(items.length / 2);
		}
		--count;
		return elt;
	}

	public Item sample() {
		// return (but do not remove) a random item
		emptyCheck();
		int rand = StdRandom.uniform(count);
		Item elt = items[rand];
		return elt;
	}

	private void resize(int capacity) {
		Item[] copy = (Item[]) new Object[capacity];
		for (int i = 0; i < count; i++) {
			copy[i] = items[i];
		}
		items = copy;
	}

	private boolean validateItem(Item i) {
		return i != null;
	}

	private void emptyCheck() {
		if (count == 0) {
			throw new java.util.NoSuchElementException();
		}
	}

	public Iterator<Item> iterator() {
		// return an independent iterator over items in random order
		return new RandomizedIterator();
	}

	private class RandomizedIterator implements Iterator<Item> {
		int eltsinlist = count;

		public Item next() {
			if (!hasNext()) {
				throw new java.util.NoSuchElementException();
			}
			int rand = StdRandom.uniform(eltsinlist);
			Item itemtoreturn = items[rand];
			if (rand == eltsinlist - 1) {
				eltsinlist--;
				return itemtoreturn;
			} else {
				items[rand] = items[eltsinlist - 1];
				items[eltsinlist - 1] = itemtoreturn;
				eltsinlist--;
				return itemtoreturn;
			}

		}

		public boolean hasNext() {
			return eltsinlist != 0;
		}

		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}
	}

}
