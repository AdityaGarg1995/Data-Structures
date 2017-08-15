package DS;

import java.util.ArrayList;

public class Map {

    PairNode[] bucket;
    int size;

    public Map() { bucket = new PairNode[5]; }

    public int size() { return size; }

    public boolean isEmpty() { return (size() == 0); }

    public int getCompressedHashCode(String key) {
        
        int hashCode = key.hashCode(),
            compressed = hashCode % bucket.length;
        
        return compressed;
    }

    public String getValue(String key) {
    
        int h = getCompressedHashCode(key);
        
        PairNode head = bucket[h];
        
        while (head != null) {
            
            if (head.data.key.equals(key))
                return head.data.value;
            
            head = head.next;
        
        }
        return null;
    }

    public void rehash() {
        
        PairNode[] tempBucket = bucket;
        bucket = new PairNode[bucket.length * 2];
        size = 0;
        
        for (PairNode currentBucketHead : tempBucket) {
            
            while (currentBucketHead != null) {
                add(currentBucketHead.data.key, currentBucketHead.data.value);
                currentBucketHead = currentBucketHead.next;
            }
        }
    }

    public void add(String key, String value) {
        
        int h = getCompressedHashCode(key);
        
        PairNode head = bucket[h],
                 temp = head;
        
        while (temp!= null) {
        
            if (temp.data.key.equals(key)) {
                temp.data.value = value;
                return;
            }
            temp = temp.next;
        }

        PairNode newNode = new PairNode();
        newNode.data.key = key;
        newNode.data.value = value;
        newNode.next = head;
        bucket[h] = newNode;
        size++;

        if ((size*1.0)/(1.0*bucket.length) > 0.6)
            rehash();
    }

    public String remove(String key) {
    
        int h = getCompressedHashCode(key);
        
        PairNode head = bucket[h],
                 prev = null;

        while (head != null) {
            
            if (head.data.key.equals(key))
                break;

            prev = head;
            head = head.next;
        }

        if (head == null)
            return null;
        
        size--;

        if (prev == null) {
            bucket[h] = head.next;
            return head.data.value;
        }

        prev.next = head.next;
        
        return head.data.value;
    }

    public ArrayList<String> getAllKeys() { 
        ArrayList<String> keys = new ArrayList<>();
        for (PairNode bucket1 : bucket) 
            keys.add(bucket1.data.key);
        return keys;
    }

    public ArrayList<String> getAllValues() {
        ArrayList<String> values = new ArrayList<>();
        for (PairNode bucket1 : bucket) 
            values.add(bucket1.data.value);
        return values;
    }
}