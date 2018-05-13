package datastructures.list;

public class DNode {
    private int   value;
    private DNode left;
    private DNode right;
    public DNode(int value, DNode left , DNode right){
        this.value= value;
        this.left = left;
        this.right= right;
    }
    public DNode(int value,  DNode right){
        this.value= value;
        this.left = null;
        this.right= right;
    }
    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setLeft(DNode left) {
        this.left = left;
    }

    public DNode getLeft() {
        return left;
    }

    public void setRight(DNode right) {
        this.right = right;
    }

    public DNode getRight() {
        return right;
    }
}

   