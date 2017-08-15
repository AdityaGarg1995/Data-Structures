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
public class BSTNode {
    
    int data;
    BSTNode left, right;

    BSTNode(){
        left = null;
        right = null;
        data = 0;
    }
    
    BSTNode(int n){
        left = null;
        right = null;
        data = n; 
    }
    
    public void setLeft(BSTNode n){ left = n; }
    
    public void setRight(BSTNode n){ right = n; }
    
    public BSTNode getLeft(){ return left; }
    
    public BSTNode getRight(){ return right; }
    
    public void setData(int d){ data = d; }
     
    public int getData(){  return data; }
    
}