package datastructures.queue;

public class Queue {
    private Node H;
    private Node T;
    public Queue(){
        H=T=null;
    }
    
    public void Inserta(int dato){
        Node q=new Node(dato,H);
        if(T==null)
            T=H=q;
        else{
            T.setSig(q);
            T=q;
        }
    }
    public Node Borrar(){
        Node aux=H;
        if(!Vacia()){
        if(H==T)
            H=T=null;
        else
            H=H.getSig();
        }
        return aux;
    }
    private boolean Vacia(){
        return H==null;
    }
}
