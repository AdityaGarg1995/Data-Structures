/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DS;

import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Aditya Garg
 */


public class BST {
    
    BSTNode root;

    public BST() {
        this.root = null;
    }
    
    public void insert(int data){
        root = insert(root, data);
    }
    
    BSTNode insert(BSTNode root, int data){
        
        if (root == null)
            root = new BSTNode(data);
        
        else{
            if(data <= root.data)
                root.left = insert(root.left, data);
            else
                root.right = insert(root.right, data);
        }
        return root;        
    }
    
    public void delete(int k){
        
        if(root == null) 
            System.out.println("Tree is Empty");
        
        else if (!search(k))
            System.out.println(k + "Not present");
        
        else{
            root = delete(root, k);
            System.out.println(k + "deleted");
        }
    }
    
    BSTNode delete(BSTNode root, int k){
        BSTNode p, p2, node;
        
        if (root.data == k){
            
            BSTNode l = root.left,
                    r = root.right;
            
            if ((l == null) && (r == null))
                return null;
            
            else if (r == null){
                p = l;
                return p;
            }
            
            else if (l == null){
                p = r;
                return p;
            }
            
            else{
                p2 = r;
                p = r;
                
                while(p.left != null)
                    p = p.left;
                
                p.setLeft(l);
                
                return p2;
            }
        }
        
        if (k < root.data){
            node = delete(root.left, k);
            root.setLeft(node);
        }
        
        else{
            node = delete(root.right, k);
            root.setRight(node);
        }
        return root;
    }
    
    public int countNodes(){ return countNodes(root); }
    
    int countNodes(BSTNode root){
        
        if(root == null) 
            return 0;
        
        else{
            int count = 1;
            
            count += countNodes(root.left);
            count += countNodes(root.right);
            
            return count;        
        }        
    }
    
    public boolean search(int k){
        return search(root, k);
    }
    
    boolean search(BSTNode root, int value){
        
        boolean found = false;
        
        while(root != null && !found){
            
            int rootValue = root.data;
            
            if(value < rootValue)
                root = root.right;
            
            else if(value > rootValue)
                root = root.left;
            
            else{
                found = true;
                break;
            }
            
            found = search(root, value);
            
        }
        
        return found;
        
    }
 
    void inOrder(BSTNode root) {
        if (root == null)
            return;

        inOrder(root.left);
        System.out.println(root.data);
        inOrder(root.right);
        
    }
    
    public void inOrder(){
       inOrder(root);
    }

    public void printLevelWiseNextLine2(BSTNode root) {
        if (root == null)
            return;

        QueueusingLL<BSTNode> queue = new QueueusingLL<>();
        queue.enqueue(root);
        
        int currentLevelCount = 1,
            nextLevelCount = 0;

        while (!queue.isEmpty()) {

            BSTNode current;

            try {
                current = queue.dequeue();
                currentLevelCount--;
            } catch (QueueEmptyException e) { return; }

            System.out.print(current.data + " ");

            if (current.left != null) {
                queue.enqueue(current.left);
                nextLevelCount++;
            }

            if (current.right != null) {
                nextLevelCount++;
                queue.enqueue(current.right);
            }

            if (currentLevelCount == 0) {
                System.out.println();
                currentLevelCount = nextLevelCount;
                nextLevelCount = 0;
            }
        
        }

    }
    
        
    public int kthSmallest(BSTNode root, int k){
//        Use inorder, keeping count of nodes travesred. If count of nodes traversed == k, return 
        int ret = -1;
        
        if(root != null){
            
            BSTNode crawl = root;
            
            while(crawl != null){
                if(countNodes(crawl.left)+1 == k){
                    ret = crawl.data;
                    break;
                }
                else if(k > countNodes(crawl.left)){
                    k -= (countNodes(crawl.left)+1);
                    crawl = crawl.right;
                }
                else
                    crawl = crawl.left;
            }
        }
        return ret;
    }    
    
    public int kthLargest(BSTNode root, int k){
//        Use reverse inorder, keeping count of nodes travesred. If count of nodes traversed == k, return 
        int kthLargestElement = -1;
        
        if(root != null){
            BSTNode crawl = root;
            
            while(crawl != null){
                if(countNodes(crawl.right)+1 == k){
                    kthLargestElement = crawl.data;
                    break;
                }
                else if(k > countNodes(crawl.right)){
                    k -= (countNodes(crawl.right)+1);
                    crawl = crawl.left;
                }
                else
                    crawl = crawl.right;
            }
        }
        return kthLargestElement;
    }
    
    
    public static void main(String[] args) {
        
        BST bst = new BST();
        
        Scanner s = new Scanner(System.in);
        
        int n = 5;
        
        while(n > 0){
            bst.insert(s.nextInt());
            n--;
        }
        
//        bst.inOrder();
        
//        bst.printLevelWiseNextLine2(bst.root);
        
        System.out.println("No of nodes = " + bst.countNodes());
        System.out.println("3rd Smallest Element" + bst.kthSmallest(bst.root, 3));
        System.out.println("" + bst.kthLargest(bst.root, 2));
    }
}