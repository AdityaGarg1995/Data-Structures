package DS;


public class StackUsingLL {

    
    private Node<Integer> head;
    private int size;

    public void throwEmptyException() throws StackEmptyException{
        if (size == 0) 
            throw new StackEmptyException();
    }
    
    public int top() throws StackEmptyException{
        throwEmptyException();
        return head.data;
    }

    public int size() { return size; }

    public boolean isEmpty() { return (size() == 0); }
    
    public int pop() throws StackEmptyException {
        throwEmptyException();
        
        int toBeReturned = head.data;
        head = head.next;
    
        size--;
    
        return toBeReturned;
    }

    public void push(int newElement) {
        size++;
        Node<Integer> newNode = new Node<>(newElement, head);
        head = newNode;
    }
    
    public static void main(String[] args) {
        int array[] = {1,2,3,4,5};
        
        StackUsingLL s = new StackUsingLL();
        
        for(int i = 0; i < array.length; i++)
            s.push(array[i]);
        
        System.out.println("Size of Stack = " + s.size());
       
        try{
            while(!s.isEmpty())
               System.out.println(s.pop());
        } catch(StackEmptyException e){  }
                
        System.out.println("Size of Stack = " + s.size());
    }    
}