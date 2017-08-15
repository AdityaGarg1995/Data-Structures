/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DS;

import java.util.*;

/**
 *
 * @author Aditya Garg
 */

abstract class HuffmanTree implements Comparable<HuffmanTree>{
    
    public final int frequency;
    
    public HuffmanTree(int frequency1){ frequency = frequency1; }
    
    @Override
    public int compareTo(HuffmanTree tree){ return (frequency - tree.frequency); }
    
}

class HuffmanLeaf extends HuffmanTree{
    
    public final char value;    

    public HuffmanLeaf(int freq, char val){
        super(freq);
        value = val;
    }
     
}

class HuffmanNode extends HuffmanTree{
    
    public final HuffmanTree left, right;

    public HuffmanNode(HuffmanTree l, HuffmanTree r){
        super(l.frequency + r.frequency);
        left = l;
        right = r;
    }
    
}

public class HuffmanCode {
    
    // input is an array of frequencies, indexed by character code
    
    public static HuffmanTree buildTree(int charFrequency[]){
        
        PriorityQueue<HuffmanTree> trees = new PriorityQueue<>();
        
        // initially there's a forest of leaves
        // one for each non empty character
        
        for(int i = 0; i < charFrequency.length; i++)
            if(charFrequency[i] > 0)  
                trees.offer(new HuffmanLeaf(charFrequency[i], (char)i));
        
        assert trees.size() > 0;
        
        // loop until onlt one tree left
        while(trees.size() > 1){
            
            // two trees with leat frequency
            HuffmanTree a = trees.poll(),
                        b = trees.poll();
            
            // put into new node and re-inserrt into queue
            trees.offer(new HuffmanNode(a, b));
            
        }
        
        return trees.poll();
        
    }
    
    public static void printCodes(HuffmanTree tree, StringBuffer prefix){
        
        assert tree != null;
        
        if( tree instanceof HuffmanLeaf){
            
            HuffmanLeaf leaf = (HuffmanLeaf)tree;
            
            // print character, frequency and code for this leaf(which is the prefix)
            System.out.println(leaf.value + "\t" + leaf.frequency + "\t" + prefix);
            
        }
        
        else if(tree instanceof HuffmanNode){
            
            HuffmanNode node = (HuffmanNode)tree;
            
            // traverse left
            prefix.append('0');
            printCodes(node.left, prefix);
            prefix.deleteCharAt(prefix.length() - 1);
            
            // traverse right
            prefix.append('1');
            printCodes(node.right, prefix);
            prefix.deleteCharAt(prefix.length() - 1);
            
        }
        
    }
    
    public static void main(String[] args) {
        
        String test = "this is an example for huffamn encoding";
        
        int charFrequency[] = new int[256];
        
        for(char c : test.toCharArray())
            charFrequency[c]++;
        
        HuffmanTree tree = buildTree(charFrequency);
        
        System.out.println("Example String: " + test + "\nSymbol\tWeight\tHuffman code");

        printCodes(tree, new StringBuffer());
    }
}