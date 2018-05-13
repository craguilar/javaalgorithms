package sorting;
/*
Selection sort One of the simplest sorting algorithms works as follows: First, find
the smallest item in the array and exchange it with the first entry (itself if the first entry
is already the smallest). Then, find the next smallest item and exchange it with the second
entry. Continue in this way until the entire array is sorted. This method is called
selection sort because it works by repeatedly selecting the smallest remaining item.
*/
public class SelectionSort extends SortingAlgorithm {
    public SelectionSort() {
        super();
    }

    @Override
    public int[] runAlgorithm(boolean showtime, int[] a) {
        System.out.println("SELECTION SORT:");
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
    public int[]  sort( int a[]){
        int indice;
        for(int i=0;i<a.length-1;i++)
        {
            indice=i;
            for(int j=i+1;j<a.length;j++){
                if (a[indice] > a[j])
                    indice=j;
            }
            swap(a,i, indice);//Contabilizara siempre este Intercambio puede suceder que aveces no suceda nada.
        }
        return a;
    }
}
