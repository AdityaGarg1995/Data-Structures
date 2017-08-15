package DS;

import java.util.ArrayList;

public class MinPriorityQueue<V> {
   
    private final ArrayList<HeapPair<V>> heapArray;

    public MinPriorityQueue() {
        heapArray = new ArrayList<>();
        heapArray.add(null);
    }

    public int size() { return (heapArray.size() - 1); }


    public boolean isEmpty() { return size() == 0; }

    public void emptyException()throws HeapEmptyException{
        if (isEmpty()) {
            HeapEmptyException e = new HeapEmptyException();
            throw e;
        }
    }

    public V min() throws HeapEmptyException {
        emptyException();
        return heapArray.get(1).value;
    }

    public V removeMin() throws HeapEmptyException {
        emptyException();
        
        HeapPair<V> minPair = heapArray.get(1);

        int lastIndex = heapArray.size() - 1;
        heapArray.set(1, heapArray.get(lastIndex));
        heapArray.remove(lastIndex);

        int currentPosition = 1,
            firstChildPosition = 2*currentPosition,
            secondChildPosition = 2*currentPosition + 1;

        while (firstChildPosition < heapArray.size()) {
            
            HeapPair<V> current = heapArray.get(currentPosition),
                        firstChild = heapArray.get(firstChildPosition),
                        secondChild = null;
            
            if (secondChildPosition < heapArray.size()) 
                secondChild = heapArray.get(secondChildPosition);
       
            int toBeSwappedWithIndex = -1;
            
            if (firstChild.priority < current.priority) 
                toBeSwappedWithIndex = firstChildPosition;
            
            if ((secondChild != null) && (secondChild.priority < current.priority)
                    && (secondChild.priority < firstChild.priority)) 
                toBeSwappedWithIndex = secondChildPosition;
            
            if (toBeSwappedWithIndex != -1) {
                HeapPair<V> temp = heapArray.get(currentPosition);
                heapArray.set(currentPosition,heapArray.get(toBeSwappedWithIndex));
                heapArray.set(toBeSwappedWithIndex,temp);
            }
            else  break;
            
            currentPosition = toBeSwappedWithIndex;
            firstChildPosition = currentPosition * 2;
            secondChildPosition = currentPosition*2 + 1;
        }
        return minPair.value;
    }

    public void insert(int priority, V value) {
     
        HeapPair<V> p = new HeapPair<>(priority, value);
        heapArray.add(p);

        int childIndex = heapArray.size() - 1,
            parentIndex = childIndex/2;

        while (childIndex > 1) {
           
            if (heapArray.get(parentIndex).priority > heapArray.get(childIndex).priority) {
                HeapPair<V> temp = heapArray.get(parentIndex);
                heapArray.set(parentIndex,heapArray.get(childIndex));
                heapArray.set(childIndex,temp);
            }
            else  break;

            childIndex = parentIndex;
            parentIndex /= 2;
        }
    }
}