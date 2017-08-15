package DS;

public class HeapPair<V> {
    int priority;
    V value;

    public HeapPair(){ }
    
    public HeapPair(int priority, V value){
        this.priority = priority;
        this.value = value;
    }
}