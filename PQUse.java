package DS;

public class PQUse {

    public static void insert(int[] array, int index) {
        
        int childIndex = index,
            parentIndex = index/2;
        
        while (childIndex > 1) {
                
            if (array[parentIndex] > array[childIndex]) {
                int temp = array[parentIndex];
                array[parentIndex] = array[childIndex];
                array[childIndex] = temp;
            }
                
            else  break;

            childIndex = parentIndex;
            parentIndex /= 2;
                
        }
        
    }

    public static int remove(int[] array, int lastIndex) {
        
        int minValue = array[1];
        array[1] = array[lastIndex];
        lastIndex--;
        
        int currentIndex = 1,
            firstChildIndex = 2*currentIndex,
            secondChildIndex = 2*currentIndex + 1;
        
        while (firstChildIndex <= lastIndex) {
            
            int tobeSwappedIndex = -1;
            
            if (array[currentIndex] > array[firstChildIndex]) 
                tobeSwappedIndex = firstChildIndex;
            
            if ((secondChildIndex <= lastIndex)
                    && (array[secondChildIndex] < array[currentIndex]) 
                    && (array[secondChildIndex] < array[firstChildIndex]))
                tobeSwappedIndex = secondChildIndex;
            
            if (tobeSwappedIndex == -1)
                break;
                    
            else {
                int temp = array[tobeSwappedIndex];
                array[tobeSwappedIndex] = array[currentIndex];
                array[currentIndex] = temp;
            }
    
            currentIndex = tobeSwappedIndex;
            firstChildIndex = 2*currentIndex;
            secondChildIndex = 2*currentIndex + 1;
        
        }
        
        return minValue;
    }

    public static void main(String[] args) {
        
        int array[] = {-1,10,9,8,7,6,5,4,3,2,1,11,14,12,13};
        
        for (int i = 1; i < array.length; i++) 
            insert(array, i);
        
        for (int i = array.length - 1 ; i >= 1; i--) 
            array[i] = remove(array, i);
        
        for (int i = 1; i < array.length; i++) 
            System.out.println(array[i]);

        MinPriorityQueue<Integer> pq = new MinPriorityQueue<>();
        pq.insert(1, 20);
        pq.insert(1, 21);
        pq.insert(1, 22);
        pq.insert(1, 23);
        
             
        while(!pq.isEmpty()) {
            try {
                System.out.println(pq.removeMin());
            } catch (HeapEmptyException e) {  }
        }

    }
}