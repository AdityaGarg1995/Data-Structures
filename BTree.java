/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DS;

/**
 *
 * @author Aditya Garg
 */

class BTreeNode{
    
    int keys[], t, n;
    
    boolean leaf;

    BTreeNode children[];
    
    public BTreeNode(int t1, boolean leaf1) {
       
       t = t1;
       leaf1 = leaf;
       
       keys = new int[2*(t-1)];
       children = new BTreeNode[2*t];
       n = 0;
       
    }
    
    public void traverse(){

        int i;
        
        for(i = 0; i < n; i++){
            if (leaf == false)  children[i].traverse();
            System.out.println(" " + keys[i]);
        }

        if (leaf == false)  children[i].traverse();
        
    }
    
    public BTreeNode search(int k){
        
        int i = 0;
        
        while((i < n) && (k > keys[i]))  i++;
        
        if (keys[i] == k)  return this;
        
        if (leaf == true)  return null;
        
        return children[i].search(k);
        
    }
    
    public void insertNonFull(int k){
        
        int i = n-1;
        
        if(leaf == true){
            
            while ((i >= 0) && (keys[i] > k)){
                keys[i+1] = keys[i];
                i--;
            }       
            
            keys[i+1] = keys[i];
            n++;
            
        }
        
        else{
            
            while ((i >= 0) && (keys[i] > k)) 
                  i--;
       
            if (children[i+1].n == 2*t-1){
                
                splitChild(i+1, children[i+1]);
                
                if (keys[i+1] < k)  i++;
                
            }
            
            children[i+1].insertNonFull(k);
            
        }
        
    }
    
    public void splitChild(int i, BTreeNode node){
      
        BTreeNode z = new BTreeNode(node.t, node.leaf);
        z.n = t-1;

        for(int j = 0; j < t-1; j++)
            z.keys[j] = node.keys[j+t];
        
        if (node.leaf == false){
            for(int j = 0; j < t; j++)
                z.children[j] = node.children[j+t];
        }
       
        node.n = (t-1);
        
        for(int j = n; j >= i+1; j--)
            children[j+1] = children[j];
        
        children[i+1] = z;
        
        for(int j = n; j >= i; j--)
            keys[j+1] = keys[j];
        
        keys[i] = node.keys[t-1];
        
        n++;
        
    }
 
}


public class BTree {
    
    BTreeNode root;
    int t;
    
    public BTree(int t1){
        root = null;
        t = t1;
    }
    
    public void insert(int k){
        
        if (root == null){
            root = new BTreeNode(t, true);
            root.keys[0] = k;
            root.n = 1;
        }
        
        else{
            
            if (root.n == 2*t-1){
                
                BTreeNode s = new BTreeNode(t, true);
                s.children[0] = root;
                s.splitChild(0, root);
                
                int i = 0;
                
                if (s.keys[0] < k)  i++;
                
                s.children[i].insertNonFull(k);
                
                root = s;
                
            }
        
            else  root.insertNonFull(k);
            
        }
        
    }
    
    public static void main(String[] args) {
        
        BTree t = new BTree(3);
        t.insert(10);
        t.insert(20);
        t.insert(5);
        t.insert(6);
        t.insert(12);
        t.insert(30);
        t.insert(7);
        t.insert(17);
        
        System.out.println("Traversal:");
        t.root.traverse();
        
        System.out.println(t.root.search(6));
        
    }
}