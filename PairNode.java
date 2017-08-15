package DS;

public class PairNode {
   
    PairNode next;
    pair data;

    public PairNode() {
        data = new pair();
    }
    
    public PairNode(PairNode next, pair data){
        this.next = next;
        this.data = data;
    }
}