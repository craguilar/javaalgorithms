package sorting;

/**
 * Insertion sort The algorithm that people often use to sort bridge hands is to consider
 * the cards one at a time, inserting each into its proper place among those already
 * considered (keeping them sorted). In a computer implementation, we need to make
 * space to insert the current item by moving larger items one position to the right, before
 * inserting the current item into the vacated position.
*/
public class InsertionSort extends SortingAlgorithm {
	public InsertionSort() {
		super();
	}

	public int[] insertionSortAlgorithm(int[] a) {
	
		for (int i = 0; i < a.length - 1; i++) {
			if (a[i] > a[i + 1]) {
				for (int j = i + 1; j > 0; j--) {
					if (a[j] < a[j - 1])
						swap(a, j, j - 1);
					else
						break; // improves T
				}
			}
		}
		return a;
	}

	@Override
	public int[] sort(int[] a) {
		insertionSortAlgorithm(a);
		return a;
	}

	@Override
	public int[] runAlgorithm(boolean showtime, int[] a) {
		return insertionSortAlgorithm(a);
	}

}
