package DS;

import java.util.Objects;
import java.util.Scanner;


public class TreeUse {

    
    public static void enterRoot(){ System.out.println("Enter root"); }
    
    public static void enterNumChildren(int data){ System.out.println("Enter number of children of " + data); }
    
//    Iterative method
    public static TreeNode<Integer> takeTreeInput2() {
        QueueusingLL<TreeNode<Integer>> queue = new QueueusingLL<>();
        
        enterRoot();
        
        Scanner s = new Scanner(System.in);
        
        int rootData = s.nextInt();
        
        TreeNode<Integer> root = new TreeNode<>(rootData);

        queue.enqueue(root);

        while(!queue.isEmpty()) {
            
            TreeNode<Integer> currentNode;
            
            try {
                currentNode = queue.dequeue();
            } catch (QueueEmptyException e) { /* Cant reach here */ return null; }

            enterNumChildren(currentNode.data);

            int numChildren = s.nextInt();

            for (int i = 0; i < numChildren; i++) {
                System.out.println("Enter data for " + i + "th child of " + currentNode.data);
                int childData = s.nextInt();
                TreeNode<Integer> child = new TreeNode<>(childData);
                currentNode.children.add(child);
                queue.enqueue(child);
            }
        }
        return root;
    }
    
//   Recursive method
    public static TreeNode<Integer> takeTreeInput() {
        enterRoot();
        
        Scanner s = new Scanner(System.in);

        int rootData = s.nextInt();

        TreeNode<Integer> root = new TreeNode<>(rootData);

        enterNumChildren(rootData);

        int numChildren = s.nextInt();

        for (int i = 0; i < numChildren; i++) {
            TreeNode<Integer> child = takeTreeInput();
            root.children.add(child);
        }
        return root;    
    }

    public static void print(TreeNode<Integer> root) {
        String rootString = root.data + ":";

        for (int i = 0; i < root.children.size(); i++) 
            rootString = rootString + root.children.get(i).data + ",";

        System.out.println(rootString);

        for (int i = 0; i < root.children.size(); i++) 
            print(root.children.get(i));
    }

    public static void printAtK(TreeNode<Integer> root, int k) {
        if (k == 0) {
            System.out.println(root.data);
            return;
        }
        
        for (int i = 0; i < root.children.size(); i++) {
            System.out.println("Calling " + root.children.get(i).data + " with " + (k-1));
            printAtK(root.children.get(i), k-1);
        }

    }

    public static boolean sameStructure(TreeNode<Integer> root1, TreeNode<Integer> root2) {
       
        if (!Objects.equals(root1.data, root2.data))  
            return false;
        
        if (root1.children.size() != root2.children.size()) 
            return false;

        for (int i = 0; i < root1.children.size(); i++) {
            
            boolean currentChildrenSame = sameStructure(root1.children.get(i), root2.children.get(i));
            
            if (!currentChildrenSame)
                return false;
            
        }
        return true;	
    }


    public static void preorder(TreeNode<Integer> root) {
        System.out.print(root.data + " ");
        
        for (int i = 0; i < root.children.size(); i++) 
            preorder(root.children.get(i));
    }

    public static void postorder(TreeNode<Integer> root) {
        for (int i = 0; i < root.children.size(); i++) 
            preorder(root.children.get(i));

        System.out.print(root.data + " ");
    }

    public static int largestNode(TreeNode<Integer> root) {
        if(root == null)
           return -1;        
        
        int max = root.data;

        for (int i = 0; i < root.children.size(); i++) {
            int currentMax = largestNode(root.children.get(i));
            if (currentMax > max)  max = currentMax;
        }
        return max;
    }
    
    public static int smallestNode(TreeNode<Integer> root) {
        if(root == null)
           return -1;        
        
        int min = root.data;

        for (int i = 0; i < root.children.size(); i++) {
            int currentMin = smallestNode(root.children.get(i));
            if (currentMin < min)  min = currentMin;
        }
        return min;
    }

    
    static class IntPairHelper{
        int largest, secondLargest;
        public IntPairHelper(){  }
        public IntPairHelper(int largest, int secondLargest){
            this.largest = largest;
            this.secondLargest = secondLargest;
        }
    }
    public static IntPairHelper largestAndSecondLargestNode(TreeNode<Integer> root){
        IntPairHelper iph = new IntPairHelper();
        
        if(root == null || root.children == null)
            return new IntPairHelper(Integer.MIN_VALUE, Integer.MIN_VALUE);
        
        iph.largest = root.data;
        iph.secondLargest = Integer.MIN_VALUE;
        for (int i = 0; i < root.children.size(); i++) {
            int currentLargest = largestAndSecondLargestNode(root.children.get(i)).largest;   
            
            if(currentLargest > iph.largest){
                iph.secondLargest = iph.largest;
                iph.largest = currentLargest;
            }
            else if(currentLargest > iph.secondLargest && currentLargest < iph.largest)
                iph.secondLargest = currentLargest;
        }
        return iph;
    }

   public static int elementJustGreaterThanN(TreeNode<Integer> root, int n){
        if(root == null)
           return -1;
        int nextLargest = Integer.MAX_VALUE;
       
        for (TreeNode<Integer> child : root.children) {
            int childNextLargest = elementJustGreaterThanN(child, n);     
            if(childNextLargest > n && childNextLargest < nextLargest)
                nextLargest = childNextLargest;
        }
       
        if(root.data > n && root.data < nextLargest)
            return root.data;
       
        return nextLargest;
   }
    
    
    public static void main(String[] args) {

        TreeNode<Integer> root = takeTreeInput2();
        //print(root);
        //printAtK(root, 2);
        //System.err.println(largestNode(root));
        //System.err.println(largestAndSecondLargestNode(root).largest + "  " + largestAndSecondLargestNode(root).secondLargest);
        System.err.println("" + elementJustGreaterThanN(root, 5));
    }
}