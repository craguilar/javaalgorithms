package algs.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int size;
	private boolean[][] grid;
	private WeightedQuickUnionUF union;
	private int top;
	private int bottom;
	private boolean percolates;

	public Percolation(int n) {
		if (n <= 0)
			throw new IllegalArgumentException("Invalid constructor parameter");
		this.size = n;
		this.percolates = false;
		this.top = 0;
		this.bottom = n * n + 1;
		this.grid = new boolean[n][n];
		this.union = new WeightedQuickUnionUF(n * n + 2);
	}

	public void open(int row, int col) {
		// open site (row, col) if it is not open already
		if (!validIndex(row, col))
			throw new IndexOutOfBoundsException("Row or Cold index i out of bounds");
		if (isOpen(row, col))
			return;
		grid[row - 1][col - 1] = true;
		// We always connect to virtual node in top
		if (row == 1)
			union.union(getQFIndex(row, col), top);
		if (row == size)
			union.union(getQFIndex(row, col), bottom);
		// Up
		if (row > 1 && isOpen(row - 1, col))
			union.union(getQFIndex(row, col), getQFIndex(row - 1, col));
		// Bottom
		if (row < size && isOpen(row + 1, col))
			union.union(getQFIndex(row, col), getQFIndex(row + 1, col));
		// Right
		if (col < size && isOpen(row, col + 1))
			union.union(getQFIndex(row, col), getQFIndex(row, col + 1));
		// Left
		if (col > 1 && isOpen(row, col - 1))
			union.union(getQFIndex(row, col), getQFIndex(row, col - 1));

	}

	public boolean isOpen(int row, int col) {
		if (!validIndex(row, col))
			throw new IndexOutOfBoundsException("Row or Cold index i out of bounds");
		return grid[row - 1][col - 1];
	}

	public boolean isFull(int row, int col) {
		if (!validIndex(row, col))
			throw new IndexOutOfBoundsException("Row or Cold index i out of bounds");
		// is site (row, col) full?
		return union.connected(top, getQFIndex(row, col));
	}

	private int getQFIndex(int row, int col) {
		if (!validIndex(row, col))
			throw new IndexOutOfBoundsException("Row or Cold index i out of bounds");
		return (size) * (row - 1) + col;
	}

	private boolean validIndex(int row, int col) {
		if ((row <= 0 || row > size) || (col <= 0 || col > size))
			return false;
		return true;
	}

	public boolean percolates() {
		// does the system percolate?
		if (!percolates) {
			if (union.connected(top, bottom))
				percolates = true;
		}
		return percolates;
	}

}