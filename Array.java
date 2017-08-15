/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DS;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Aditya Garg
 */
public class Array {

        
    public static int maxSubarrayXor(int array[]){
        
        int maxXor = Integer.MIN_VALUE;
        
        for(int i = 0; i < array.length; i++){
            int currentXor = 0;
            for(int j = i; j< array.length; j++){
                currentXor ^= array[j];
                maxXor = Math.max(maxXor, currentXor);
            }
        }
        return maxXor;
    }
    
    
    public static void sumArrays(int a[], int b[]){
       if(a.length <= b.length)
           sumOfTwoArraysWithFirstArraySmaller(a, b);
       else
           sumOfTwoArraysWithFirstArraySmaller(b, a);
    }

    
    public static void sumOfTwoArraysWithFirstArraySmaller(int array1[], int array2[]){
       
        int l1 = array1.length,
            l2 = array2.length,
            outputLength = l2+1,
            outputArray[] = new int[outputLength];

        boolean moreThanNine = false;
        
        for(int i = 1; i < outputLength; i++){
        
           if(i <= l1)
              outputArray[outputLength-i] = array1[l1-i] + array2[l2-i]; 
           
           else
               outputArray[outputLength-i] = array2[l2-1];
           
           if(moreThanNine){
              ++outputArray[outputLength-i];
              moreThanNine = false;
           }
           if(outputArray[outputLength-i] > 9){
               outputArray[outputLength-i] -= 10;
               moreThanNine = true;
           }
        }
        
        if(moreThanNine)
            outputArray[0] = 1;
        
        if(outputArray[0] != 0)
            for(int k = 0; k < outputLength; k++)
               System.out.print(outputArray[k]);
        else    
            for(int k = 1; k < outputLength; k++)
               System.out.print(outputArray[k]);  
    }
    
// Smallest of the peaks    
    public static void peakOfArray(int a[]){
        
        int peak = Integer.MAX_VALUE;
    
        if(a.length < 3)
            System.out.println("No peak exists");
     
//      Sureshot method
        ArrayList<Integer> peaks = new ArrayList<>();
        long start1 = System.nanoTime();
        for(int i = 1; i < a.length-1; i++)
            if(a[i] > a[i-1] && a[i] > a[i+1])
                peaks.add(a[i]);
        long end1 = System.nanoTime();
        
//      Faster method
        long start2 = System.nanoTime();
        for(int i = 1; i < a.length-1; i++)
            if(a[i] > a[i-1] && a[i] > a[i+1]){
                int currentPeak = a[i];
                if(currentPeak < peak)  
                    peak = currentPeak;
            }
        long end2 = System.nanoTime();
        
        if(peaks.isEmpty()){
            System.out.println("No peak exists " + (end1-start1));
            System.out.println(peak + " " + (end2-start2));
        }
        else{
            Arrays.sort(peaks.toArray());
            System.out.println(peaks.get(0) + " " + (end1-start1));
            System.out.println(peak + " " + (end2-start2));
        }
    }
    
    public static void printTriplets(int[] input, int x) {
        
        for (int i = 0; i < input.length; i++) 
            for (int j = 0; j < i; j++) 
                for (int k = 0; k < j; k++) 
                    if ((input[i] + input[j] + input[k]) == x) 
                            System.out.println(i + " "  + j + " " + k);
    }
    
    public static void printDoublets(int input[] , int x){
        
        for (int i = 0; i < input.length; i++) 
            for (int j = 0; j < i; j++) 
                if ((input[i] + input[j]) == x) 
                    System.out.println(i + " "  + j);
    }

    
//    Sort an array contaiming only 0's and 1's

    public static int[] sortArrayWith01_1(int array[]){
    /*  Traverse array: count num of 0's and count num of 1's
        Put 0's in beginning then 1's
    */ 
        int countOfZeroes = 0,
            countOfOnes = 0;
        for(int i = 0; i < array.length; i++){
            if(array[i] == 0)
                countOfZeroes++;
            else if (array[i] == 1)
                countOfOnes++;
        }
        
        int index = 0;
        while(index < countOfZeroes)
            array[index] = 0;
        while(index < array.length)
            array[index] = 1;
        return array;
    }
    
    public static void sortArrayWith01_2(){
        
    }
    public static void main(String[] args) {
//        int array[] = {8,1,2,12};
//        System.out.println(maxSubarrayXor(array));
        
        int a1[] = {1,3,8},
            a2[] = {2,2,2,2,2};
//        sumOfTwoArraysWithFirstArraySmaller(a1, a2);
        peakOfArray(a2);
       int array[] = {1, 0, 1, 0, 0, 1, 1}; 
       array = sortArrayWith01_1(array);
       for(int i = 0; i < array.length; i++){
            System.out.print(array[i]);
       }
    }
}