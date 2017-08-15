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

import java.lang.Math;

// Segment tree is used to perform summation of elements and updation in array in O(logn)
public class SegmentTree {
    
    /*
      Always full binary tree with n leaves and n - 1 internal nodes
    */
    
    public static int getMid(int s, int e){
        return s + (e - s)/2;
    }
    
    
    /*
    st = pointer to segment tree
    si = index of current node in segment tree. initially 0 is passe das root is at index 0
    ss,se = starting, ending indices of segment tree
    qs,qe = starting, ending indices of query range
    */
    
    public static int getSumUtil(int st[], int ss, int se, int qs, int qe, int si){
        
        if (qs <= ss || qe >= se)  return 0;
        
        if (se < qs || ss > qe)  return 0;
        
        int mid = getMid(ss, se);
        
        return getSumUtil(st, ss, mid, qs, qe, 2*si + 1) + 
               getSumUtil(st, mid + 1, se, qs, qe, 2*si + 2);
    }
    
    public static int getSum(int st[], int n, int qs, int qe){
        
        if(qs < 0 || qe > n-1 || qs > qe){
            System.err.println("Invalid input");
            return -1;
        }
        
        return getSumUtil(st, 0, n-1, qs, qe, 0);
    }
    
    public static void updateValueUtil(int st[], int ss, int se, int i, int diff, int si){
        
        // base Case : if input index lies outside segment range
        if (i < ss || i > se)  return;
        
        st[si] = st[si] + diff;
        
        if(se != ss){
            int mid = getMid(ss, se);
            updateValueUtil(st, ss, mid, i, diff, 2*si + 1);
            updateValueUtil(st, mid+1, se, i, diff, 2*si + 2);
        }
    
    }
    
    public static void updateValue(int array[], int st[], int n, int i, int value){
        
        if (i < 0 || i > n-1)
            System.err.println("Invalid Output");
        
        int diff = value - array[i];
        
        array[i] = value;
        
        updateValueUtil(st, 0, n-1, i, diff, 0);
        
    }
    
    public static int constructSTUtil(int array[], int ss, int se, int st[], int si){
        
        if (ss == se){
            st[si] = array[ss];
            return array[ss];
        }
        
        int mid = getMid(ss, se);
        
        st[si] = constructSTUtil(array, ss, mid, st, 2*si + 1) +
                 constructSTUtil(array, mid+1, se, st, 2*si + 2);

        return st[si];
        
    }
    
    public static int[] constructST(int array[], int n){
        
        int x = (int)Math.ceil(Math.log(n)/Math.log(2)),
            max  = 2*(int)Math.pow(2, x) - 1,
            st[] = new int[max];
        
        constructSTUtil(array, 0, n-1, st, 0);
        
        return st;
    }
    
    public static void main(String[] args) {
   
        int array[] = {1,3,5,7,9,11},
            n = array.length,
            st[] = constructST(array, n);
        
        System.out.println("Sum of values in given range = " + getSum(st, n, 1, 3));
        
        updateValue(array, st, n, 1, 10);
        
        System.out.println("Updated sum of values = " + getSum(st, n, 1, 3));
    }
    
}