package DS;

public class GPair<T,V> {
    T value;
    V index;
    
    public GPair(){ }
    
    public GPair(T value, V index){
        this.value = value;
        this.index = index;
    }
}