package datastructures.arrays;

public class AStack {
	private int size;
	private int array[];

	public AStack(int max) {
		array = new int[max];
		size = -1;
	}

	public void push(int data) {
		size++;
		array[size] = data;
	}

	public int pop() {
		size--;
		return array[size + 1];
	}

	public boolean isEmpty() {
		return size == -1;
	}

	public boolean isFull() {
		return size == array.length - 1;
	}

	public int peek() {
		return array[size];
	}

	public void list() {
		for (int i = size; i > -1; i--)
			System.out.println(array[i]);
	}

}
