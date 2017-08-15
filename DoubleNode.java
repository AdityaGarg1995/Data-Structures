package DS;

public class DoubleNode {
    int data;
    Node<Integer> head, tail;
    
    public DoubleNode(){
        
    }
    
    public DoubleNode(Node<Integer> head, Node<Integer> tail){
        this.head = head;
        this.tail = tail;
    }
    
    public DoubleNode(int data, Node<Integer> head, Node<Integer> tail){
        this.data = data;
        this.head = head;
        this.tail = tail;
    }
    
}