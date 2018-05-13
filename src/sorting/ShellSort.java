package sorting;
/*
Shellsort 
To exhibit the value of knowing properties of elementary sorts, we next consider
a fast algorithm based on insertion sort. Insertion sort is slow for large unordered
arrays because the only exchanges it does involve adjacent entries, so items can 
move through the array only one place at a time. For example, if the item with the
smallest key happens to be at the end of the array, N1 exchanges are needed to get that
one item where it belongs. Shellsort is a simple extension of insertion sort that gains
speed by allowing exchanges of array entries that are far apart, to produce partially
sorted arrays that can be efficiently sorted, eventually by insertion sort.
*/
public class ShellSort extends SortingAlgorithm {
    public ShellSort() {
        super();
    }

    @Override
    public int[] runAlgorithm(boolean showtime, int[] a) {
        System.out.println("SHELL SORT");
        if(showtime){
            long startTime; 
            long stopTime ;
            long elapsedTime ;
            startTime= System.currentTimeMillis();
            sort(a);
            stopTime = System.currentTimeMillis();
            elapsedTime= stopTime - startTime;
            System.out.println("Elapsed time="+elapsedTime+"[ms]");
        }
        else
            sort(a);
            
        return a;
    }

    @Override
    public int[] sort(int[] a) {
        int N = a.length;
        int h = 1;
        while (h < N/3) h = 3*h + 1;
   
        while (h >= 1)
        { // h-sort the array.
            for (int i = h; i < N; i++)
            { // Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]... .
                
                for (int j = i; j >= h && a[j]<a[j-h]; j -= h)
                {   
                    swap(a, j, j-h);
                }
                    
            }
             h = h/3;
        }
        return a;
    }
    
}

