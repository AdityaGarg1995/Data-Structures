package DS;

public class QueueusingLL<T> {
	
    private Node<T> head, tail;
    private int size;

    public int size(){ return size; }

    public boolean isEmpty(){ return (size == 0); }

    
    public void throwEmptyException() throws QueueEmptyException{
        if(isEmpty()){
            throw new QueueEmptyException();
        }
    }
    
    public T front() throws QueueEmptyException{
        throwEmptyException();
        return head.data;
    }

    public void enqueue(T element){

        Node<T> newNode = new Node<>();
        newNode.data = element;

        if((tail == null) && (head == null)){
            tail = newNode;
            head = newNode;
        }
        else{
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public T dequeue() throws QueueEmptyException{

        throwEmptyException();
        
        if(size == 1){

            size--;
            T element = head.data;
            head = head.next;
            tail = null;

            return element;
        }

        size--;
        T element = head.data;
        head = head.next;

        return element;
    }
}