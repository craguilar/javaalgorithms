package sorting;

import java.util.LinkedList;
/*
 * Good explanation of Merge Sort http://www.princeton.edu/~achaney/tmve/wiki100k/docs/Merge_sort.html
 * Merge Sort coded on : 
 *  -http://en.wikibooks.org/wiki/Algorithm_Implementation/Sorting/Merge_sort
 *  -http://rosettacode.org/wiki/Sorting_algorithms/Merge_sort
Merge sort is an O(n log n) comparison-based sorting algorithm. Most implementations 
produce a stable sort, meaning that the implementation preserves the input order of equal
elements in the sorted output. It is a divide and conquer algorithm. 
Merge sort was invented by John Von Neumann in 1945.
*/
public class MergeSort extends SortingAlgorithm{
    public MergeSort() {
        super();
    }
    public int[] runAlgorithm(boolean showtime, int a[]){
        System.out.println("MERGE SORT WITH STATIC STRUCTURES:");
        if(showtime){
            long startTime; 
            long stopTime ;
            long elapsedTime ;
            startTime= System.currentTimeMillis();
            MergeSortAlgorithmSE(a,0, a.length-1);
            stopTime = System.currentTimeMillis();
            elapsedTime= stopTime - startTime;
            System.out.println("Elapsed time="+elapsedTime+"[ms]");
            
        }
        else
            MergeSortAlgorithmSE(a,0, a.length-1);
        return a;
    }

    //    sort(a, 0, a.length - 1);
    public static void sort(int[] a, int lo, int hi)
        { // Sort a[lo..hi].
        if (hi <= lo) return;
        int mid = lo + (hi - lo)/2; //3 5
        sort(a, lo, mid); // Sort left half.
        sort(a, mid+1, hi); // Sort right half.
        merge(a, lo, mid, hi); // Merge results (code on page 271).
    }
    private static int[] merge(int[] a, int lo, int mid, int hi)
    { // Merge a[lo..mid] with a[mid+1..hi].
        int i = lo;
        int j = mid+1;
        int aux[]= new int[hi-lo];
        for (int k = lo; k <= hi; k++) // Copy a[lo..hi] to aux[lo..hi].
            aux[k] = a[k];
        for (int k = lo; k <= hi; k++) // Merge back to a[lo..hi].
            if (i > mid) a[k] = aux[j++];
            else if (j > hi ) a[k] = aux[i++];
            else if (aux[j]< aux[i]) a[k] = aux[j++];
            else a[k] = aux[i++];
        return a;
    }
    public int[] MergeSortAlgorithmSE(int[] a,int left,int right){
        int p=left;
        int q=right;
        int k = (int) Math.floor((left + right) / 2);
        if(left<right){
            MergeSortAlgorithmSE(a,left,k);
            MergeSortAlgorithmSE(a,k+1,q);
            MergeArraysStatic(a,left,k,right);
        }
        return a;
    }
     private void MergeArraysStatic(int a[],int left, int mid, int right){

        int[] temp  = new int[right-left+1];
        int c = left;
        int i = left;
        int j = mid+1;
        for(int k=left;k<=right;k++) // Copy
            temp[i-left]=a[i];
        
        while((i<=mid)||(j<=right)) {
            if((i<=mid)&&(j<=right)){
                if(temp[j-left]>temp[i-left]){
                    a[c]=temp[i-left];
                    i++;
                }
                else{
                    a[c]=temp[j-left];
                    j++;
                }
            }
            else if(!(i<=mid)){
                a[c]=temp[j-left];
                j++;
            }
            else if(!(j<=right)){
                a[c]=temp[i-left];
                i++;
            }
            c++;        
        }
    }
 

    public int[] MergeSortAlgorithmDE(int[] a,int left,int right){
        int p=left;
        int q=right;
        int k = (int) Math.floor((left + right) / 2);
        if(left<right){
            MergeSortAlgorithmDE(a,left,k);
            MergeSortAlgorithmDE(a,k+1,q);
            MergeArraysDynamic(a,left,k,right);   
        }
        return a;
    }
    private void MergeArraysDynamic(int a[],int left, int k, int right){
        LinkedList<Integer> lleft  = new LinkedList<>();
        LinkedList<Integer> lright = new LinkedList<>();
        int c =left;
        // Decrease
        for(int i=left;i<=k;i++)
            lleft.add(a[i]);
        for(int i=k+1;i<=right;i++)
            lright.add(a[i]);
        while(!lright.isEmpty()||!lleft.isEmpty()) {
            if(!lright.isEmpty()&&!lleft.isEmpty()){
                if((int)lright.peek()>(int)lleft.peek())
                    a[c]=(int)lleft.remove();
                else
                    a[c]=(int)lright.remove();
            }
            else if(!lright.isEmpty()&&lleft.isEmpty())
                a[c]=(int)lright.pop();
            else if(lright.isEmpty()&&!lleft.isEmpty())
                a[c]=(int)lleft.pop();
            c++;        
        }
    }
   

    @Override
    public int[] sort(int[] a) {
        MergeSortAlgorithmSE(a,0, a.length-1);
        return a;
    }
}
