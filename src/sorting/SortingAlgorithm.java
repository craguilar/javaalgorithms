package sorting;

public abstract class SortingAlgorithm {
    // Swap method ----------------------------------------

    /**
     * @param a array to be sorted
     * @param i index i
     * @param j index j
     * @return
     */
    protected int[] swap(int[] a ,int i, int j ){
        int c    = a[i];
        a[i] = a[j];
        a[j] = c   ;
        return a;
    }
    
    /**
     *
     * @param a
     * @return
     */
    public abstract int[] sort(int a[]);
    /**
     * Runs sorting algorithm if you provide showitime parameter then it will 
     * show time parameters.
     * @param showtime
     * @param a
     * @return
     */
    public abstract int[] runAlgorithm(boolean showtime, int a[]);
   
}
