package DS;

import java.util.*;

public class BinaryTreeUse {

   
    public static BinaryTreeNode<Integer> getBinaryTreeInput() {
       
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>();
        
        Scanner s = new Scanner(System.in);
        
        System.out.println("Enter root data");
        root.data = s.nextInt();
        
        QueueusingLL<BinaryTreeNode<Integer>> queue = new QueueusingLL<>();
        queue.enqueue(root);

        while (!queue.isEmpty()) {
    
            BinaryTreeNode<Integer> current;
            
            try {
                current = queue.dequeue();
            } catch (QueueEmptyException e) { return null; }

            System.out.println("Enter left child of " + current.data);
        
            int left = s.nextInt();
            
            if (left != -1) {
                BinaryTreeNode<Integer> leftNode = new BinaryTreeNode<>();
                leftNode.data = left;
                current.left = leftNode;
                queue.enqueue(leftNode);
            }

            System.out.println("Enter right child of " + current.data);
           
            int right = s.nextInt();
            if (right != -1) {
                BinaryTreeNode<Integer> rightNode = new BinaryTreeNode<>();
                rightNode.data = right;
                current.right = rightNode;
                queue.enqueue(rightNode);
            }
        }
        
        return root;
    
    }

    
    public static BinaryTreeNode<Integer> getTreeFromPreAndIn(int[] in, int[] pre, int inB, int inE, int preB, int preE) {

        if (inB > inE) 
            return null;

        BinaryTreeNode<Integer> root = new BinaryTreeNode<>();
        root.data = pre[preB];
        
        int rootInOrderIndex = -1;
        
        for (int i = inB; i <= inE; i++) {
            if (in[i] == root.data) {
                rootInOrderIndex = i;
                break;
            }
        }

        if (rootInOrderIndex == -1) 
            return null;
        
        int leftInOrderB  = inB,
            leftInorderE  = (rootInOrderIndex - 1),
            rightInOrderB = (rootInOrderIndex + 1),
            rightInOrderE = inE;

        int leftLength     = (rootInOrderIndex - inB),
            leftPreOrderB  = (preB + 1),
            leftPreOrderE  = (leftPreOrderB + leftLength - 1),
            rightPreOrderB = (leftPreOrderE + 1),
            rightPreOrderE = preE;

        //Left
        root.left = getTreeFromPreAndIn(in, pre, leftInOrderB, leftInorderE,
                                        leftPreOrderB, leftPreOrderE);

        //Right
        root.right = getTreeFromPreAndIn(in, pre, rightInOrderB, rightInOrderE,
                                         rightPreOrderB, rightPreOrderE);

        return root;
    
    }

    public static BinaryTreeNode<Integer> getTreeFromPostAndIn(int[] in, int[] post, int inB, int inE, int postB, int postE) {

        BinaryTreeNode<Integer> root = new BinaryTreeNode<>();

        return root;

    }

    
    public static int getHeight(BinaryTreeNode<Integer> root) {
        if (root == null) 
            return 0;
        return (1 + Math.max(getHeight(root.left), getHeight(root.right)));
    }

    public static int diameter(BinaryTreeNode<Integer> root) {
       
        if (root == null) 
            return 0;
        
        int height1 = getHeight(root.left),
            height2 = getHeight(root.right),
            diameter1 = diameter(root.left),
            diameter2 = diameter(root.right);
        
        return Math.max( (height2 + height1), Math.max(diameter1, diameter2));
    
    }
    
    public static DoubleInt diameterHeight(BinaryTreeNode<Integer> root) {

        if (root == null) {
            DoubleInt output = new DoubleInt(0,0);
            return output;
        }

        DoubleInt leftOutput  = diameterHeight(root.left),
                  rightOutput = diameterHeight(root.right),
                  output = new DoubleInt();
        
        output.y = Math.max(leftOutput.x + rightOutput.x,
                        Math.max(leftOutput.y, rightOutput.y));
        output.x = (1 + Math.max(leftOutput.x, rightOutput.x));
        
        return output;

    }

    
    public static void printBinaryTree(BinaryTreeNode<Integer> root) {
        
        if(root == null)  
            return;

        String printString = root.data + ":";
        
        if(root.left != null)  
            printString += " left:" + root.left.data;
        
        if(root.right != null)
            printString += " right:" + root.right.data;
        
        System.out.println(printString);

        printBinaryTree(root.left);
        printBinaryTree(root.right);
    
    }

    public static void printLevelWise(BinaryTreeNode<Integer> root) {
        
        if(root == null)
           return;
        
        QueueusingLL<BinaryTreeNode<Integer>> queue = new QueueusingLL<>();
        queue.enqueue(root);
        
        while(!queue.isEmpty()) {
        
            BinaryTreeNode<Integer> current;
            
            try {
                current = queue.dequeue();
            } catch (QueueEmptyException e) { return; }
            
            if(current.left != null)
                queue.enqueue(current.left);
            
            if(current.right != null)
                queue.enqueue(current.right);
            
            System.out.print(current.data + " ");
        
        }
    
    }
    
    public static void preOrder(BinaryTreeNode<Integer> root) {
        
        if (root == null)
            return;

        System.out.println(root.data);
        preOrder(root.left);
        preOrder(root.right);
        
    }

    public static void inOrder(BinaryTreeNode<Integer> root) {
    
        if (root == null)
            return;

        inOrder(root.left);
        System.out.println(root.data);
        inOrder(root.right);
        
    }

    public static void postOrder(BinaryTreeNode<Integer> root) {
        
        if (root == null) 
            return;

        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.data);
    
    }

    
    public static BinaryTreeNode<Integer> findElement(BinaryTreeNode<Integer> root, int k) {
        
        if (root == null)
            return null;
        
        if (root.data == k)
            return root;
        
        else if (k > root.data)
            return findElement(root.right, k);
        
        else return findElement(root.left, k);
        
    }

    
    public static int findMin(BinaryTreeNode<Integer> root) {
        if (root == null) 
            return Integer.MAX_VALUE;
        return Math.min(root.data, Math.min(findMin(root.left), findMin(root.right)));
    }
    
    public static int findMax(BinaryTreeNode<Integer> root) {
        if (root == null)
            return Integer.MIN_VALUE;
        return Math.max(root.data, Math.max(findMax(root.left), findMax(root.right)));
    }

    
    // Find node for which sum of its data and data of its children is max
    public static BinaryTreeNode<Integer> nodeWithMaxSum(BinaryTreeNode<Integer> root){
        
        if(root == null) return null;
        if(root.left == null && root.right == null) return root;
        
        BinaryTreeNode<Integer> maxNode = root;
        int maxSumRoot = root.data + root.left.data + root.right.data;
        
        BinaryTreeNode<Integer> temp1 = nodeWithMaxSum(root.left),
                                temp2 = nodeWithMaxSum(root.right),                                  
                                maxChild = null;
        int maxTemp1 = temp1.data + temp1.left.data + temp1.right.data,
            maxTemp2 = temp2.data + temp2.left.data + temp2.right.data;      
        
        if(Math.max(maxTemp1, maxTemp2) == maxTemp1)
            maxChild = temp1;
        else maxChild = temp2;
        
        int maxChildSum = maxChild.data + maxChild.left.data + maxChild.right.data;
        
        if(Math.max(maxSumRoot, maxChildSum) == maxChildSum)
            maxNode = maxChild;
        else maxNode = root;
        
        
        return maxNode;
        
    }
    
    
    public static DoubleInt secondLargest(BinaryTreeNode<Integer> root) {

        if (root == null) {
            DoubleInt output = new DoubleInt();
            output.x = Integer.MIN_VALUE;
            output.y = Integer.MIN_VALUE;
            return output;
        }

        int largest = root.data,
            secondLargest = Integer.MIN_VALUE;

        DoubleInt left = secondLargest(root.left),
                  right = secondLargest(root.right);

        if (left.x > largest) {
            secondLargest = largest;
            largest = left.x;
        }
        else if (left.x > secondLargest) 
            secondLargest = left.x;
        
        if (left.y > secondLargest) 
            secondLargest = left.y;
        
        if (right.x > largest) {
            secondLargest = largest;
            largest = right.x;
        }
        else if (right.x > secondLargest) 
            secondLargest = right.x;
        
        if (right.y > secondLargest) 
            secondLargest = right.y;
        
        DoubleInt output = new DoubleInt(largest, secondLargest);
        
        return output;
    }

    
    // BST
    public static class IsBSTReturnNode {
        boolean isBST;
        int min, max;
    }

    public static IsBSTReturnNode isBSTFaster(BinaryTreeNode<Integer> root) {
        
        if (root == null) {
            IsBSTReturnNode output = new IsBSTReturnNode();
            output.isBST = true;
            output.min = Integer.MAX_VALUE;
            output.max = Integer.MIN_VALUE;
        }

        IsBSTReturnNode leftOutput = isBSTFaster(root.left),
                        rightOutput = isBSTFaster(root.right),
                        output = new IsBSTReturnNode();
    
        if (leftOutput.isBST && rightOutput.isBST 
                && (root.data > leftOutput.max) 
                && (root.data < rightOutput.min)) {
            output.isBST = true;
            // In case root.left was null and leftoutput.min is Integer.MAX_VALUE
            // We need to return root.data as min. 
            output.min = Math.min(leftOutput.min, root.data);
            output.max = Math.max(root.data, rightOutput.max);
        }
        
        else output.isBST = false;
             // No need to set min max if the tree is not BST
        
        return output;
    
    }

    public static boolean isBST(BinaryTreeNode<Integer> root) {
            
        if (root == null)
            return true;

        if ((root.data < findMax(root.left)) || (root.data > findMin(root.right))) 
            return false;

        if ((!isBST(root.left)) || (!isBST(root.right))) 
            return false;

        return true;
        
    }

    public static BinaryTreeNode<Integer> largestBSTsubtree(BinaryTreeNode<Integer> root){
        
        if (root == null){
            System.out.println("Empty Tree");
            return null;
        }
        
        if (root.left == null && root.right == null){
            System.out.println("No Subtree as only root exists");
            return null;
        }
        
        if (isBST(root.left) && isBST(root.right))
            return root;
        
        largestBSTsubtree(root.left);
        largestBSTsubtree(root.right);
        
        return root;
        
    }
    
    
    public static void printBetweenK1K2(BinaryTreeNode<Integer> root, int k1, int k2) {
        
        if (root == null) 
            return;

        if ((root.data > k1) && (root.data < k2))  
            System.out.println(root.data);
        
        if (root.data >= k1)  
            printBetweenK1K2(root.left, k1, k2);
        
        if (root.data <= k2)  
            printBetweenK1K2(root.right, k1, k2);
    
    }

    
    public static Node<Integer> getSortedLinkedList(BinaryTreeNode<Integer> root) {
        
        if (root == null) 
            return null;

        Node<Integer> currentnode = new Node<>();
        currentnode.data = root.data;

        Node<Integer> leftHead = getSortedLinkedList(root.left),
                      rightHead = getSortedLinkedList(root.right);

        currentnode.next = rightHead;
        
        if (leftHead != null) {

            Node<Integer> tail = leftHead;

            while(tail.next != null) 
                tail = tail.next;

            tail.next = currentnode;

            return leftHead;

        }

        else return currentnode;
        
    }
    
    
    public static boolean isBalanced(BinaryTreeNode<Integer> root) {
        
        if (root == null) 
            return true;

        if (Math.abs(getHeight(root.left) - getHeight(root.right)) > 1) 
            return false;
            
        if (!isBalanced(root.left)) 
            return false;
            
        if (!isBalanced(root.right)) 
            return false;
    
        return true;
    
    }


    public static BinaryTreeNode<Integer> removeLeaves(BinaryTreeNode<Integer> root) {
            
        if (root == null)
            return null;
            
        if ((root.left == null) && (root.right == null)) 
            return null;
            
        root.left = removeLeaves(root.left);
        root.right = removeLeaves(root.right);
    
        return root;
    
    }

    // Print levelwise next line    
    public static void printLevelWiseNextLine2(BinaryTreeNode<Integer> root) {
            
        if (root == null)
            return;

        QueueusingLL<BinaryTreeNode<Integer>> queue = new QueueusingLL<>();
        queue.enqueue(root);
        
        int currentLevelCount = 1,
            nextLevelCount = 0;

        while (!queue.isEmpty()) {

            BinaryTreeNode<Integer> current;

            try {
                current = queue.dequeue();
                currentLevelCount--;
            }
            catch (QueueEmptyException e) {
                return;
            }

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

    public static void printLevelWiseNextLine1(BinaryTreeNode<Integer> root) {
            
        if(root == null)  
            return;

        QueueusingLL<BinaryTreeNode<Integer>> currentLevelQueue = new QueueusingLL<>(),
                                              nextLevelQueue = new QueueusingLL<>();
        
        currentLevelQueue.enqueue(root);

        while(!currentLevelQueue.isEmpty()) {
            
            BinaryTreeNode<Integer> current;
            
            try {
                current = currentLevelQueue.dequeue();
            }
            catch (QueueEmptyException e) { return; }
            
            System.out.print(current.data + " ");
            
            if (current.left != null)
                nextLevelQueue.enqueue(current.left);

            if (current.right != null)
                nextLevelQueue.enqueue(current.right);

            if (currentLevelQueue.isEmpty()) {
                QueueusingLL<BinaryTreeNode<Integer>> swap = currentLevelQueue;
                currentLevelQueue = nextLevelQueue;
                nextLevelQueue = swap;
                System.out.println();
            }

        }

    }

    
    // pretty printing
    
    public static void prettyPrint(BinaryTreeNode<Integer> root, int indent){
    
        if((root.left != null) && (root.right != null)){
        
            System.out.println("(" + root.data);
        
            prettyPrint(root.left, indent + 1);
            prettyPrint(root.right, indent + 1);

            System.out.print(")");
        
        }
        
        else if(root.left == null && root.right != null){
  
            System.out.println("(" + root.data);
            prettyPrint(root.right, indent);
            
            System.out.print(")");
        
        }
        
        else if(root.left != null && root.right == null){
            
            System.out.println("(" + root.data);
            prettyPrint(root.left, indent);
            
            System.out.print(")");
        
        }

        else if (root != null)
            System.out.println(root.data);
        
        else  System.out.println("()");
    }
    public static String getStartingSpace(int height){
        
        int noOfSpaces = (int)(Math.pow(2, height - 1)/2);
        
        StringBuilder space = new StringBuilder();
        
        for(int i = 0; i < noOfSpaces; i++)
            space.append(" ");
        
        return space.toString();
        
    }
    public static String getUnderscores(int height){
        
        int noOfLeftElements = (int)(Math.pow(2, height - 1)/2),
            noOfUnderscores = noOfLeftElements - (int)(Math.pow(2, height - 1)/2);
        
        StringBuilder underScore = new StringBuilder();
        
        for(int i = 0; i < noOfUnderscores; i++)
            underScore.append("_");
        
        return underScore.toString();
        
    }
    public static String spaceBetweenNodes(int height){
        
        int nodesInSubtree = (int)(Math.pow(2, height - 1)/2),
            noOfSpace = nodesInSubtree*2 + 1;    
        
        StringBuilder space = new StringBuilder();
        
        for(int i = 0; i < noOfSpace; i++)
            space.append("_");
        
        return space.toString();
    }
    public static void printNodes(ArrayList<BinaryTreeNode<Integer>> nodeQueue,
                                 int noOfNodesAtCurrentHeight, int height){
        
        StringBuilder nodesAtHeight = new StringBuilder();
        
        String startSpace        = getStartingSpace(height),
               spaceBetweenNodes = spaceBetweenNodes(height),
               underScores       = getUnderscores(height);
        
        nodesAtHeight.append(startSpace);
        
        for(int i = 0; i < noOfNodesAtCurrentHeight; i++){
            
            BinaryTreeNode<Integer> node = nodeQueue.get(i);
            if (node == null)  continue;
            
            nodeQueue.add(node.left);
            nodeQueue.add(node.right);
            
            nodesAtHeight.append(underScores);
            nodesAtHeight.append(node.data);
            nodesAtHeight.append(underScores);
            nodesAtHeight.append(spaceBetweenNodes);
            
        }
        
        nodesAtHeight.substring(0, nodesAtHeight.length() - spaceBetweenNodes.length());

        System.out.println(nodesAtHeight.toString());
    }
    public static String spaceBetweenLeftRightBranch(int height){
        
        int noOfNodesBetweenLeftRightBranches = (int)(Math.pow(2, height - 1)/2);
        
        StringBuilder spaceBetweenLeftRightBranch = new StringBuilder();
        
        for(int i = 0; i < noOfNodesBetweenLeftRightBranches; i++)
            spaceBetweenLeftRightBranch.append(" ");
        
        return spaceBetweenLeftRightBranch.toString();
    }
    public static String spaceBetweenRightLeftBranch(int height){
        
        int noOfNodesBetweenLeftRightBranches = (int)(Math.pow(2, height - 1)/2);
        
        StringBuilder spaceBetweenLeftRightBranch = new StringBuilder();
        
        for(int i = 0; i < noOfNodesBetweenLeftRightBranches; i++)
            spaceBetweenLeftRightBranch.append(" ");
        
        return spaceBetweenLeftRightBranch.toString();
    }
    public static void PrintBranches(ArrayList<BinaryTreeNode<Integer>> nodeQueue,
                                    int noOfNodesAtCurrentHeight, int height){
        
        if (height <= 1) 
            return;
        
        StringBuilder branchesAtHeight = new StringBuilder();
        
        String startSpace = getStartingSpace(height),
               leftRightSpace = spaceBetweenLeftRightBranch(height),
               rightLeftSpace = spaceBetweenRightLeftBranch(height);
        
        branchesAtHeight.append(startSpace.substring(0, startSpace.length() - 1));
        
        for(int i = 0; i < noOfNodesAtCurrentHeight; i++)
            branchesAtHeight.append("/").append(leftRightSpace)
                            .append("\\").append(rightLeftSpace);
        
        System.out.println(branchesAtHeight.
            substring(0, branchesAtHeight.length() - rightLeftSpace.length()));
    }
    public static void prettyPrinting(BinaryTreeNode<Integer> root){
        
        ArrayList<BinaryTreeNode<Integer>> list = new ArrayList<>();
        
        int height = getHeight(root),
            level = 0,
            noOfNodesAtCurrentHeight = 0;
        
        boolean add = list.add(root);
        
        while(!list.isEmpty() && (level < height)){
            
            noOfNodesAtCurrentHeight = (int)Math.pow(2, level);
            printNodes(list, noOfNodesAtCurrentHeight, height - level);
            PrintBranches(list, noOfNodesAtCurrentHeight, height - level);
            
            for(int i = 0; i < noOfNodesAtCurrentHeight; i++)
                System.out.println(list.get(i).data);
            
            level++;
        }
    }

    
    // LCA
    public static BinaryTreeNode<Integer> findLCA(BinaryTreeNode<Integer> root,
                                                  int n1, int n2){
                
        if (root == null)  
            return null;
        
        if ((root.data == n1) || (root.data == n2))  
            return root;
        
        BinaryTreeNode<Integer> leftLCA = findLCA(root.left, n1, n2),
                                rightLCA = findLCA(root.right, n1, n2);
        
        if ((leftLCA != null) && (rightLCA != null))
            return root;
        
        else  return (leftLCA != null) ? leftLCA : rightLCA;
        
    }

//    Print all possible interpretations of a numeric string
//    Create a binary tree with leaves as interpretations, print them
    public static String alpabets[] = {"", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q",
                               "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    public static void printLeaf(BinaryTreeNode<String> root){
        if(root == null)
            return;
        if(root.left == null & root.right == null)
            System.out.println(root.data);
        printLeaf(root.left);
        printLeaf(root.right);
    }
    public static BinaryTreeNode<String> createBinaryTreeForCodes(int data, String number, int array[]){
        if(data > 26)  
            return null;
        String dataToString = number + alpabets[data];  //"" + a
        BinaryTreeNode<String> root = new BinaryTreeNode<>(dataToString, null, null);
        // 112  aab, kb, al
        // if arry.length == 0, work finished
        if(array.length != 0){
            data = array[0];   // 1
            int newString[] = Arrays.copyOfRange(array, 1, array.length);  //12
            root.left = createBinaryTreeForCodes(data, dataToString, newString);  //1, a, 12
            
            if(array.length > 1){
                data = array[0]*10 + array[1]; // 10+1
                newString = Arrays.copyOfRange(array, 2, array.length); // 2
                root.right = createBinaryTreeForCodes(data, dataToString, newString); // 11, k, 2 
            }
        }
        return root;
    }
    
    
    public static void main(String[] args) {

//        int[] in = {4,2,5,1,6,3,7},
//              pre = {1,2,4,5,3,6,7};
//
//
//        BinaryTreeNode<Integer> root = getTreeFromPreAndIn(in, pre, 0, in.length - 1,
//                                                          0, pre.length - 1);

//        BinaryTreeNode<Integer> root = getBinaryTreeInput();
        
//        int height = getHeight(root);    
//        double indent = Math.pow(2, height - 1) - 1;
//        prettyPrint(root, (int)indent);

//        prettyPrinting(root);

//        System.out.println(isBalanced(root));

//        printLevelWiseNextLine2(root);
//        printLevelWise(root);
//        printLevelWiseNextLine1(root);

//      System.out.println(findLCA(root, 4, 5).data);

        int array[] = {1,1,2};
        printLeaf(createBinaryTreeForCodes(0, "", array));

    }
}