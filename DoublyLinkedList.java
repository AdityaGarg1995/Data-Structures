/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DS;

import java.util.Scanner;

/**
 *
 * @author Aditya Garg
 */


class DLLNode{
    int data;
    DLLNode prev, next;
}

class DoubleDLLNode{
    DLLNode head, tail;
    
    public DoubleDLLNode(){
        
    }
    
    public DoubleDLLNode(DLLNode head, DLLNode tail){
        this.head = head;
        this.tail = tail;
    }
}

public class DoublyLinkedList {
    
    
    DLLNode head = null,
            tail = null;
        
    public DoubleDLLNode input(){
    
        DLLNode prevNode = null;
        
        Scanner s = new Scanner(System.in);
        
        int input = s.nextInt();
        
        while (input != -1) {
        
            DLLNode newNode = new DLLNode();
            newNode.data = input;
            newNode.next = null;

            if(head == null){
               head = newNode;
               tail = newNode;
            }
            else{
                prevNode.next = newNode;
                newNode.prev = prevNode;
                tail = newNode;
            }
            
            prevNode = newNode;
            
            System.out.println("Enter next element");

            input = s.nextInt();
        
        }

        DoubleDLLNode newPair = new DoubleDLLNode(head, tail);
        
        return newPair;    
    }
    
  
    void printHelper(DLLNode current, DLLNode next){
        System.out.print(current.data + "  ");
        current = next;
    }
    
    public void printDoublyLinkedList(DLLNode start){
        
        if(start == head){
         
            DLLNode currentNode = head;
        
            while (currentNode != null) {
                printHelper(currentNode, currentNode.next);
            }
            System.out.println();
        }
        
        else if(start == tail){
            
            DLLNode currentNode = tail;
        
            while (currentNode != null) {
                printHelper(currentNode, currentNode.prev);
            }
            System.out.println();
        }
    }
    
       
    public DLLNode swapNodes(DLLNode start, int x, int y){
    
        if(x == y)
            return start;
        
        DLLNode prevX = null, currX = start, prevY = null, currY = start;
        
        while(currX != null && currX.data != x){
            prevX = currX;
            currX = currX.next;
        }
        
        while(currY != null && currY.data != y){
            prevY = currY;
            currY = currY.next;
        }
        
        
        if(currX != null && currY != null){
            
            // Nodes next to each other
            if(currX.next == currY){
            
                currX.next = currY.next;
                currY.prev = currX.prev;
                
                if(currX.next != null)
                   currX.next.prev = currX;
               
                if(currY.prev != null)
                   currY.prev.next = currY;
               
                currY.next = currX;
                currX.prev = currY;
            
            }
            
            else{
               
                DLLNode temp1 = currY.prev,
                        temp2 = currY.next;
                
                currY.prev = currX.prev;
                currY.next = currX.next;
                
                currX.prev = temp1;
                currX.next = temp2;
                        
                if(currY.next != null)
                    currY.next.prev = currY;
                
                if(currY.prev != null)
                    currY.prev.next = currY;
                
                if(currX.next != null)
                    currX.next.prev = currX;
                if(currX.prev != null)
                    currX.prev.next = currX;

            }
            
        }
        return start;
    }
    
    
    public int length(DLLNode start){
        
        if(start == null) 
            return 0;
        
        int count = 0;
        DLLNode temp = start;
           
        if(start == head){
           while(temp != null){
              temp = temp.next;
              count++;
           }
        }
        else if(start == tail){
           while(temp != null){
              temp = temp.prev;
              count++;
           }
        }
        return count;
    }
    
    public static void main(String[] args) {
        
        DoublyLinkedList list = new DoublyLinkedList();
        DoubleDLLNode start = list.input();
        list.head = start.head;
        list.tail = start.tail;
 
        list.printDoublyLinkedList(list.head);
        list.printDoublyLinkedList(list.tail);
        
        System.out.println(list.length(list.head));
    
    }
    
}