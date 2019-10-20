package sorting;

import java.util.LinkedList;

/**
 * Good explanation of Merge Sort
 * http://www.princeton.edu/~achaney/tmve/wiki100k/docs/Merge_sort.html Merge
 * Sort coded on :
 * -http://en.wikibooks.org/wiki/Algorithm_Implementation/Sorting/Merge_sort
 * -http://rosettacode.org/wiki/Sorting_algorithms/Merge_sort Merge sort is an
 * O(n log n) comparison-based sorting algorithm. Most implementations produce a
 * stable sort, meaning that the implementation preserves the input order of
 * equal elements in the sorted output. It is a divide and conquer algorithm.
 * Merge sort was invented by John Von Neumann in 1945.
 */
public class MergeSort extends SortingAlgorithm {
	public MergeSort() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sorting.SortingAlgorithm#runAlgorithm(boolean, int[])
	 */
	public int[] runAlgorithm(boolean showtime, int[] a) {
		System.out.print("");
		return mergeSortAlgorithmSE(a, 0, a.length - 1);
	}

	@Override
	public int[] sort(int[] a) {
		mergeSortAlgorithmSE(a, 0, a.length - 1);
		return a;
	}

	public static void sort(int[] a, int lo, int hi) { // Sort a[lo..hi].
		if (hi <= lo)
			return;
		int mid = lo + (hi - lo) / 2; // 3 5
		sort(a, lo, mid); // Sort left half.
		sort(a, mid + 1, hi); // Sort right half.
		merge(a, lo, mid, hi); // Merge results (code on page 271).
	}

	public int[] mergeSortAlgorithmSE(int[] a, int left, int right) {
		int q = right;
		int k = left + right / 2;
		if (left < right) {
			mergeSortAlgorithmSE(a, left, k);
			mergeSortAlgorithmSE(a, k + 1, q);
			mergeArraysStatic(a, left, k, right);
		}
		return a;
	}

	public int[] mergeSortAlgorithmDE(int[] a, int left, int right) {
		int q = right;
		int k = left + right / 2;
		if (left < right) {
			mergeSortAlgorithmDE(a, left, k);
			mergeSortAlgorithmDE(a, k + 1, q);
			mergeArraysDynamic(a, left, k, right);
		}
		return a;
	}

	// Simplified version of my original code
	private static int[] merge(int[] a, int lo, int mid, int hi) { // Merge
																																	// a[lo..mid]
																																	// with
																																	// a[mid+1..hi].
		int i = lo;
		int j = mid + 1;
		int[] aux = new int[hi - lo];
		for (int k = lo; k <= hi; k++) // Copy a[lo..hi] to aux[lo..hi].
			aux[k] = a[k];
		for (int k = lo; k <= hi; k++) // Merge back to a[lo..hi].
			if (i > mid)
				a[k] = aux[j++];
			else if (j > hi)
				a[k] = aux[i++];
			else if (aux[j] < aux[i])
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
		return a;
	}

	private void mergeArraysStatic(int[] a, int left, int mid, int right) {

		int[] temp = new int[right - left + 1];
		int c = left;
		int i = left;
		int j = mid + 1;
		for (int k = left; k <= right; k++) // Copy
			temp[i - left] = a[i];

		while ((i <= mid) || (j <= right)) {
			if ((i <= mid) && (j <= right)) {
				if (temp[j - left] > temp[i - left]) {
					a[c] = temp[i - left];
					i++;
				} else {
					a[c] = temp[j - left];
					j++;
				}
			} else if (i > mid) {
				a[c] = temp[j - left];
				j++;
			} else {
				a[c] = temp[i - left];
				i++;
			}
			c++;
		}
	}

	private void mergeArraysDynamic(int[] a, int left, int k, int right) {
		LinkedList<Integer> lleft = new LinkedList<>();
		LinkedList<Integer> lright = new LinkedList<>();
		int c = left;
		// Decrease
		for (int i = left; i <= k; i++)
			lleft.add(a[i]);
		for (int i = k + 1; i <= right; i++)
			lright.add(a[i]);
		while (!lright.isEmpty() || !lleft.isEmpty()) {
			if (!lright.isEmpty() && !lleft.isEmpty()) {
				if ((int) lright.peek() > (int) lleft.peek())
					a[c] = (int) lleft.remove();
				else
					a[c] = (int) lright.remove();
			} else if (!lright.isEmpty() && lleft.isEmpty()) {
				a[c] = (int) lright.pop();
			} else if (lright.isEmpty() && !lleft.isEmpty()) {
				a[c] = (int) lleft.pop();
			}
			c++;
		}
	}
}
