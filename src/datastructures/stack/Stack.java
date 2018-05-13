package datastructures.stack;

public class Stack {
        private Node size;
        public Stack(){
            size=null;
        }
        public void PUSH(char dato){
            Node q=new Node(dato,size);
            size=q;
        }
        public Node POP(){
            Node aux=size;
            if(size!=null){
                size=size.getSig();
                return aux;
            }
            else
                System.out.println(":::EMPTY:::");
            return aux;
        }
}

 /*
    
     */
  
