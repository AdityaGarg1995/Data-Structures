package DS;

import java.util.Scanner;
import java.util.Stack;

public class StackQueueUse {

    public static boolean isExpressionBalanced(String s) {
        
        StackUsingLL stack = new StackUsingLL();
        
        for (int i = 0; i < s.length(); i++) {
            
            try {
                switch (s.charAt(i)) {
                    
                    case '{':
                    case '(':
                    case '[':
                        stack.push(s.charAt(i));
                        break;
                        
                    case '}':
                        if (stack.top() == '{')  stack.pop();
                        else  return false;
                        break;
                    
                    case ']':
                        if (stack.top() == '[')  stack.pop();
                        else  return false;
                        break;
                        
                    case ')':
                        if (stack.top() == '(')  stack.pop();
                        else  return false;
                        break;
                    
                    default: break;
                    
                }
            }
            catch(StackEmptyException e) { return false; }
        }

        return stack.isEmpty();
    }

    public void reverseStack(StackUsingLL s) {

        StackUsingLL s1 = new StackUsingLL(),
                     s2 = new StackUsingLL();

        while (!s.isEmpty()) {
            try {
                s1.push(s.pop());
            } catch (StackEmptyException e) {  /* Not possible */ }
        }

        while (!s1.isEmpty()) {
            try { 
                s2.push(s1.pop());
            } catch (StackEmptyException e) { /* Not possible */ }
        }

        while (!s2.isEmpty()) {
            try {
                s.push(s2.pop());
            } catch (StackEmptyException e) { /* Not possible */ }
        }
    }

    public static int[] findSpans(int[] a) {
        
        if (a.length == 0) 
            return new int[0];

        int[] output = new int[a.length];
        output[0] = 1;
        
        Stack<GPair<Integer, Integer>> s = new Stack<>();

        GPair<Integer, Integer> p = new GPair<>();
        p.index = 0;
        p.value = a[0];
        s.push(p);

        for (int i = 1; i < a.length; i++) {

            while (!s.empty() && s.peek().value < a[i] ) 
                s.pop();
            
            if (s.empty())  output[i] = (i + 1);
            else  output[i] = (i - s.peek().index);
            
            GPair<Integer, Integer> p1 = new GPair<>();
            p1.index = i;
            p1.value = a[i];
            s.push(p1);
        
        }
        return output;
    }

    public static void main(String[] args) throws QueueEmptyException, QueueFullException {
        
//        int[] a = {10,9,8,15,4,2,7,6,10,8,6},
//              spans = findSpans(a);
        
//        for (int i = 0; i < a.length; i++) 
//            System.out.println(a[i] + " " + spans[i]);
            
        int[] a = {1,2,3,4,5};
        StackUsingLL s = new StackUsingLL();

        for (int i = 0; i < a.length; i++) 
            s.push(a[i]);

        while(!s.isEmpty()) {
            try {
                System.out.println(s.pop());
            } catch (StackEmptyException e) {
                // Cant come here
                System.out.println("Cant come here");
            }
        }

//        int[] a = {1,2,3,4,5};
//        QueueUsingArray q = new QueueUsingArray();
//        for (int i = 0; i < a.length; i++) {
//            try {
//                q.enqueue(a[i]);
//            } catch (QueueFullException e) { }
//        }

//        System.out.println(q.dequeue());
//        q.enqueue(10);
//
//        Scanner s = new Scanner(System.in);
//        q.enqueue(s.nextInt());
//        q.enqueue(s.nextInt());
//        q.enqueue(s.nextInt());
//        
//        System.out.println(q.size());
//        
//        while (!q.isEmpty()) 
//            System.out.println(q.dequeue());
//        
//        System.out.println(q.size());
    }
}