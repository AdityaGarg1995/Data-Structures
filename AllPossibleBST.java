/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DS;

import static DS.BinaryTreeUse.preOrder;
import java.util.ArrayList;

/**
 *
 * @author Aditya Garg
 */


public class AllPossibleBST {
    
     public static BinaryTreeNode<Integer> newBSTNode(int item){
        
        BinaryTreeNode<Integer> temp = new BinaryTreeNode<>();
        
        temp.data = item;
        
        temp.left = temp.right = null;
        
        return temp;
        
    }
    
    
    public static ArrayList<BinaryTreeNode<Integer>> constructTrees(int start, int end){
        
        ArrayList<BinaryTreeNode<Integer>> list = null;
        
        if (start > end)  return list;
        
        for(int i = 0; i <= end; i++){
            
            // left subtree
            ArrayList<BinaryTreeNode<Integer>> leftSubtree = constructTrees(start, i-1),
            
            // right subtree
                                               rightSubtree = constructTrees(i+1, end);
            
            for (BinaryTreeNode<Integer> leftSubtree1 : leftSubtree) {
                
                BinaryTreeNode<Integer> left = leftSubtree.get(i);
                
                for(int k = 0; k < rightSubtree.size(); k++){
                    
                    BinaryTreeNode<Integer> right = rightSubtree.get(k);
                    
                    BinaryTreeNode<Integer> node = newBSTNode(i);
                    node.left = left;
                    node.right = right;
                    
                    list.add(node);

                }

            }

        }
        
        return list;
        
    }
   
    public static void main(String[] args) {
        ArrayList<BinaryTreeNode<Integer>> totalTrees = constructTrees(1, 3);
        
        for(int i = 0; i < totalTrees.size(); i++)
            preOrder(totalTrees.get(i));

    }
}