package datastructures.list;

public class DoublyLinkedList {

    private DNode  H;
    private DNode  T;
    public DoublyLinkedList(){
        H=T=null;
    }
    private void insertNode(DNode find,int dato){
        DNode temp=new DNode(dato, null, null);
        temp.setLeft(find);
        temp.setRight(find.getRight());
        find.getRight().setLeft(temp);
        find.setRight(temp);
        if(temp.getRight()==null)
            T=temp;
    }
    public void insertBegining(int dato){
        DNode q=new DNode(dato,null,H);
        if(H==null)
            T=q;
        else
            H.setLeft(q);
        H=q;
    }
    public void insertEnd(int dato){
        DNode q=new DNode(dato,T,null);
        if(H==null)
            H=q;
        else
            T.setRight(q);
        T=q;
    }
    public void insertAfter(DNode insert,int dato){
        if(insert!=null){
            if(insert==T)
                insertEnd(dato);
            else
                insertNode(insert, dato);
        }
    }
    public void insertBefore(DNode insert,int dato){
        if(insert!=null){
            if(insert==H)
                insertBegining(dato);
            else
                insertNode(insert.getLeft(), dato);
        }
    }
    public DNode delete(int data){
        DNode delete=find(data);
        DNode aux=delete;
        if(delete!=null){
            if(H==T)
                H=T=null;
            else if(delete==H){
                delete.getRight().setLeft(null);
                H=delete.getRight();
            }
            else if(delete==T){
                delete.getLeft().setRight(null);
                T=delete.getLeft();
            }
            else{
                delete.getLeft().setRight(delete.getRight());
                delete.getRight().setLeft(delete.getLeft());
            }
        }
        return aux;
    }

    public  DNode find(int data){
        DNode q=H;
        while(q!=null&&data!=q.getValue())
            q=q.getRight();
        return q;
    }
    public DNode GetInicio(){
            return H;
    }
}

 