package DS;

public class QueueUsingArray {
    
    private int data[],
                beginIndex,
                lastIndex,
                size;

    public QueueUsingArray() {
        data = new int[50];
        beginIndex = -1;
        lastIndex = -1;
    }

    public boolean isEmpty() { return (size == 0); }

    public int size() { return size; }

    public int front() throws QueueEmptyException {
        if (size() == 0) {
            throw new QueueEmptyException();
        }
        return data[beginIndex];
    }

    public void enqueue(int newElement) throws QueueFullException {
        
        if (size == data.length) {
            size *= 2;
            int newData[] = new int[size];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }

        lastIndex = (lastIndex + 1) % data.length;
        data[lastIndex] = newElement;
        
        if (beginIndex == -1)
            beginIndex = 0;
        
        size++;        
    }

    public int dequeue() throws QueueEmptyException {
        if (size() == 0) {
            throw new QueueEmptyException();
        }

        int temp = data[beginIndex];
        
        if (size() == 1) {
            beginIndex = -1;
            lastIndex = -1;
        }
        
        else  beginIndex = (beginIndex + 1) % data.length;

        size--;
        
        return temp;
    }
}