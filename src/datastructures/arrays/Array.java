package datastructures.arrays;

public class Array {
    private int[] array;
    private int index;
    public Array(int max){
        array=new int[max];
        index=-1;
    }
    public void insert(int data){
            index++;
            array[index]=data;
    }
    
    public void updateValue(int pos,int nvodato){
        array[pos]=nvodato;
    }
    public void deleteValue(int pos){
        array[pos]=array[index];
        index--;
    }
    public int Buscar(int data,int i){
       while(i<=index){
                if(data==array[i])
                    return i;
                i++;
             }
        return -1;
    }
    public int availableSpace(){
        return array.length-1-index;
    }
    public boolean canInsert(){
        return(index<array.length-1);
    }
    public boolean isSorted(){
       return true; 
    }
    public int binarySearch(int data){
        // IMPORTANT*************************
        // the array need to be sorted...
        // **********************************
        int low=0,high=array.length-1,middle;
        if(data>array[high]||data<array[low])
            return -1;
        else{
            do{
                middle=(low+high)/2;
                if(data==array[high])
                    return high;
                else if(data==array[low])
                    return low;
                else if(data==array[middle])
                    return middle;
                else{
                    if(array[middle]>data)
                        low=middle+1;
                    else
                        high=middle-1;
                }
            }while(high-1!=low);
        }

        return -1;
    }
    public void listValues(){
        for(int i=0;i<=index;i++) 
            System.out.println(" ["+i+"]="+array[i]);
    }
    public void showValue(int pos){
        System.out.println("Data "+array[pos]+" @ ["+pos+"].");
    }
    
 
}
