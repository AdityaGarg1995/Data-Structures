/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DS;


public class StackUsingArray {

        
    int lastIndex, data[];   
    
    public StackUsingArray(int size){
        data = new int[size];
        lastIndex = -1;
    }
    
    
    public int size() { return (lastIndex + 1); }
    
    
    public boolean isEmpty() { return (size() == 0); }
                   
    public void throwEmptyException() throws StackEmptyException{
        if(isEmpty())
            throw new StackEmptyException();
    }
    
    public int top() throws StackEmptyException{
        throwEmptyException();
        return data[lastIndex];
    }
    
    public void push(int newElement){
        data[lastIndex + 1] = newElement;
        lastIndex++;
    }
    
    public int pop() throws StackEmptyException{
        throwEmptyException();
        
        int element = data[lastIndex];
        data[lastIndex]  = 0;
        lastIndex--;
        
        return element;
    }
            
    public static void main(String[] args) {
        
        int array[] = {1,2,3,4,5};
        
        StackUsingArray s = new StackUsingArray(array.length);
        
        for(int i = 0; i < array.length; i++)
            s.push(array[i]);
        
        System.out.println("Size of Stack = " + s.size());
       
        try{
            while(!s.isEmpty())
               System.out.println(s.pop());
        } catch(StackEmptyException e){  }
                
        System.out.println("Size of Stack = " + s.size());
    
    }    
}