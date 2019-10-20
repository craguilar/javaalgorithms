package datastructures.arrays;

public class AQueue {
	private int T, H;
	private int queue[];

	@SuppressWarnings("oracle.jdeveloper.java.multiple-assignment")
	public AQueue(int max) {
		super();
		queue = new int[max];
		T = H = -1;
	}

	public void insert(char data) {
		T++;
		if (T == 0)
			H++;
		queue[T] = data;
	}

	public int delete() {
		int aux;
		aux = queue[H];
		if (H == T)
			H = T = -1;
		else
			H++;
		return aux;
	}

	public boolean isFull() {
		return T == queue.length - 1;
	}

	public boolean isEmpty() {
		return H == -1;
	}

}