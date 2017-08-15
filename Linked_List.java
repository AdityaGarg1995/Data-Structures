package DS;

import java.util.Scanner;

public class Linked_List {

    public static void enterNext(){
        System.out.println("Enter next element");
    }
    
    public static Node<Integer> takeLinkedListInput() {
        
        Node<Integer> head = null,
                      prevNode = null;
        
        enterNext();
        
        Scanner s = new Scanner(System.in);
        
        int input = s.nextInt();
        
        while (input != -1) {
        
            Node<Integer> newNode = new Node<>(input, null);
            
            if(head == null)
               head = newNode;
            else  prevNode.next = newNode;
            
            prevNode = newNode;
            
            enterNext();

            input = s.nextInt();
        }
        return head;
    }

    public static void printLinkedList(Node<Integer> input) {
        
        Node<Integer> currentNode = input;
        
        while (currentNode != null) {
            System.out.print(currentNode.data + "  ");
            currentNode = currentNode.next;
        }
        System.out.println();
    }

    public static Node<Integer> insertNode(Node<Integer> head, int newElement, int position){
    
        Node<Integer> newNode = new Node<>(newElement);

        if (position == 0) {
            newNode.next = head;
            return newNode;
        }

        int currentPosition = 1;
        Node<Integer> temp = head;

        while (currentPosition < position) {
            temp = temp.next;
            currentPosition++;
        }

        newNode.next = temp.next;
        temp.next = newNode;
        return head;
    }

    public static Node<Integer> deleteNode(Node<Integer> head, int position) {
            
        if(position == 0)
           return head.next;
        
        int currentPosition = 1;
        Node<Integer> temp = head;

        while(currentPosition < position) {
            temp = temp.next;
            currentPosition++;
        }

        temp.next = temp.next.next;
        
        return head;
    }

    public static Node<Integer> swap(Node<Integer> head, int i, int j) {
        
        if(i == 0){

            Node<Integer> ith = head,
                          prevj = null,
                          jth = head;
            
            int count = 0;
            
            while(count < j){
                prevj = jth;
                jth = jth.next;
                count++;
            }
            
            head = jth;
            
            Node<Integer> nexti = ith.next,
                          nextj = jth.next;

            if(i == (j - 1)){
               jth.next = ith;
               ith.next = nextj;
            }
            else{
                ith.next = nextj;
                prevj.next = ith;
                jth.next = nexti;
            }
            return head;
        }

        Node<Integer> previ = null,
                      ith = head;
        
        int count = 0;
        
        while(count < i){
            previ = ith;
            ith = ith.next;
            count++;
        }

        Node<Integer> prevj = null,
                      jth = head;
        
        count = 0;
        while(count < j) {
            prevj = jth;
            jth = jth.next;
            count++;
        }
        
        Node<Integer> nexti = ith.next,
                      nextj = jth.next;

        if(i != (j - 1)){

            previ.next = jth;
            prevj.next = ith;

            ith.next = nextj;
            jth.next = nexti;
            
        }
        
        else{
            previ.next = jth;
            ith.next = nextj;
            jth.next = ith;
        }

        return head;
    
    }

    public static int findMinPosition(Node<Integer> head) {
        
        int minPosition = 0,
            currentPosition = 0;
        
        Node<Integer> minNode = head,
                      temp = head;
        
        while(temp != null){
            if(temp.data < minNode.data){
                minPosition = currentPosition;
                minNode = temp;
            }
            currentPosition++;
            temp = temp.next;
        }
        
        return minPosition;
    
    }


    public static boolean headCheck(Node<Integer> head){ return ((head == null) || (head.next == null)); }
    
    public static Node<Integer> InsertionSort(Node<Integer> head) {
    
        if (headCheck(head))
            return head;

        Node<Integer> sortedSoFar = head,
                      remaining = head.next;
        
        head.next = null;

        while (remaining != null) {
        
            Node<Integer> currentNode = remaining;
            remaining = remaining.next;

            Node<Integer> temp = sortedSoFar,
                          prevTemp = null;
        
            while(temp != null){
                
                if (temp.data > currentNode.data) {
                
                    if (prevTemp == null) {
                        currentNode.next = sortedSoFar;
                        sortedSoFar = currentNode;
                    } 
                    
                    else {
                        currentNode.next = temp;
                        prevTemp.next = currentNode;
                    }
                    
                    break;
                
                }
                
                prevTemp = temp;
                temp = temp.next;
            
            }

            if (temp == null) {
                prevTemp.next = currentNode;
                currentNode.next = null;
            }
    
        }
        
        return sortedSoFar;
    }


    public static Node<Integer> selectionSortRecursive(Node<Integer> head) {
    
        if (headCheck(head))
            return head;

        int minP = findMinPosition(head);
        head = swap(head, 0, minP);
        head.next = selectionSortRecursive(head.next);
        return head;
    }

    public static void printReverse(Node<Integer> head) {
        if(head == null)
           return;
        
        printReverse(head.next);
        System.out.print(head.data + "  ");
    }

    Node<Integer> ReverseRecursively(Node head) {
    
        if(headCheck(head)) 
           return head;
    
        Node<Integer> smallerHead = ReverseRecursively(head.next),
                      temp = smallerHead;
    
        while(temp.next != null)
           temp = temp.next;
    
        temp.next = head;
        head.next = null;
    
        return smallerHead;
    }

//    Recursive reversion using class DoubleNode
    public static DoubleNode reverseRecursive3(Node<Integer> head) {
    
        if (headCheck(head)) {
            DoubleNode output = new DoubleNode(head, head);
            return output;
        }

        DoubleNode smallerOutput = reverseRecursive3(head.next);
        smallerOutput.tail.next = head;
        head.next = null;

        DoubleNode output = new DoubleNode(smallerOutput.head, head);
        
        return output;
    }
    public static Node<Integer> reverseRecursive2(Node<Integer> head) {
            
        if (headCheck(head)) 
            return head;
            
        Node<Integer> smallerHead = reverseRecursive2(head.next);
        head.next.next = head;
        head.next = null;
    
        return smallerHead;
    }
    public static Node<Integer> reverseRecursive(Node<Integer> head) {
        
        if (headCheck(head)) 
            return head;
        
        Node<Integer> smallerHead = reverseRecursive(head.next),
                      temp = smallerHead;
        
        while(temp.next != null) 
              temp = temp.next;
        
        temp.next = head;
        head.next = null;
        
        return smallerHead;
    }

//    Detect and remove loop from linked list    
    public static int detectAndRemoveLoop(Node<Integer> head){
        if(headCheck(head))
            return -1;
        
        Node<Integer> slow = head,
                      fast = head.next;
        
        while (slow != null && fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                removeLoop(slow, head);
                return 1;
            }
        }
        return -1;
    }
    public static void removeLoop(Node<Integer> loop, Node<Integer> head){
        Node<Integer> p1 = loop,
                      p2 = loop;

        int k = 1,
            i;
        
        while(p1.next != p2){
            p1 = p1.next;
            k++;
        }
        
        p1 = head;
        p2 = head;
        
        for (i = 0; i < k; i++)
            p2 = p2.next;
        
        while (p2 != p1){
            p1 = p1.next;
            p2 = p2.next;
        }
        
        p2 = p2.next;
        while (p2.next != p1)
            p2 = p2.next;
        
        p2.next = null;
    }
    
//     Merge Sort for Linked List
    /* Similar to mid point function for array for merge sort */
    public static Node getMiddle(Node head){
        if(headCheck(head))
            return head;
        
        Node fast = head.next,
             slow = head;
        
        while(fast != null){
            fast = fast.next;
            if(fast != null){
                fast = fast.next;
                slow = slow.next;
            }
        }
        return slow;
    }
    /* Merge function for two soreted Linked Lists */
    public static Node<Integer> merge(Node<Integer> a, Node<Integer> b){
        Node result = null;
        
        // Base cases
        if(a == null && b == null)  return null;
        else if(a == null)  return b;
        else if(b == null)  return a;
        
        if(a.data <= b.data){
           result = a;
           result.next = merge(a.next, b);
        }
        
        else if (a.data > b.data){
           result = b;
           result.next = merge(a, b.next);
        }
        
        return result;
    }    
    /* Merge Sort function */
    public static Node mergeSort(Node<Integer> head){
        if(headCheck(head))
            return head;
        
        Node<Integer> midNode = getMiddle(head),    /* End of left half linked list */
                      nextOfMidNode = midNode.next; /* Start of right half linked list */
        
        midNode.next = null;  /* Effectively dividing linked list into two parts */
        
        Node<Integer> left = mergeSort(head),
                      right = mergeSort(nextOfMidNode),
                      sortedList = merge(left, right);
        
        return sortedList;
    }
    
    public static Node addTwoLists(Node<Integer> a, Node<Integer> b){
        if(a == null && b == null)  return null;
        else if (a == null)  return b;
        else if (b == null)  return a;
        
        Node<Integer> temp,
                      result = null,
                      prev = null;
        
        int carry = 0,
            sum;
        
        while(a != null || b != null){
            sum = carry + (a != null ? a.data : 0) + (b != null ? b.data : 0); 
            carry = (sum >= 10) ? 1 : 0;
            sum %= 10;
            temp = new Node<>();
            temp.data = sum;
            
            if(result == null)
                result = temp;
            else 
                prev.next = temp;
            
            prev = temp;
            
            if(a != null) 
                a = a.next;
            if(b != null)
                b = b.next;
            
            if(carry > 0){
                temp.next = new Node<>();
                temp.data = carry;
            }
        }
        return result;
    }
    
    
    public static Node<Integer> rotate(Node<Integer> head, int k) {
        if (k == 0)
            return head;
 
        // Let us understand the below code for example k = 4 and list = 10->20->30->40->50->60.
        Node current = head;
 
        // current will either point to kth or NULL after this loop. current will point to node 40 in the above example
        int count = 1;
        while (count < k && current !=  null) {
            current = current.next;
            count++;
        }
 
        // If current is NULL, k is greater than or equal to count
        // of nodes in linked list. Don't change the list in this case
        if (current == null)
            return head;
 
        // current points to kth node. Store it in a variable.
        // kthNode points to node 40 in the above example
        Node kthNode = current;
 
        // current will point to last node after this loop
        // current will point to node 60 in the above example
        while (current.next != null)
            current = current.next;
 
        // Change next of last node to previous head
        // Next of 60 is now changed to node 10
 
        current.next = head;
 
        // Change head to (k+1)th node
        // head is now changed to node 50
        head = kthNode.next;
 
        // change next of kth node to null
        kthNode.next = null;
        return head; 
    }
 
    
    
    public static void main(String[] args) {
        
        Node<Integer> input = takeLinkedListInput();
        
        printLinkedList(input);
        
//        printReverse(input);
//        
//        input = InsertionSort(input);
//        printLinkedList(input);
//
//        printLinkedList(mergeSort(input));
//        System.out.println(detectAndRemoveLoop(input));
//        printLinkedList(addTwoLists(input, input));

          Node<Integer> head = rotate(input, 3);
          printLinkedList(head);
          printLinkedList(reverseRecursive3(input).head);
    }
}