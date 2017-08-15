package DS;

import java.util.HashMap;

public class MapUse {

    
    public static void printIntersection(int[] first, int[] second) {

        HashMap<Integer, Boolean> map = new HashMap<>();

        for (int i = 0; i < first.length; i++) 
            map.put(first[i], true);
        
        for (int i = 0; i < second.length; i++) 
            if (map.containsKey(second[i]))
                System.out.println(second[i]);
    }

    
    public static void printCount(int[] array) {
        
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < array.length; i++) {
            
            if (map.containsKey(array[i])) {
                int count = map.get(array[i]);
                map.put(array[i], count + 1);
            }
            
            else 
                map.put(array[i], 1);
        }
        
        for (int i = 0; i < array.length; i++) {
            if (map.containsKey(array[i])) {
                System.out.println(array[i] + " " + map.get(array[i]));
                map.remove(array[i]);
            }
        }
    }

    
    public static void main(String args[]) {

        Map m = new Map();
        
        m.add("abcff", "def");
        System.out.println("abcff".hashCode() + " " + "abcd".hashCode() + " "
                       + "ghi".hashCode() + " " + "ghiydgugugfgifh".hashCode());
        m.add("ghi", "xyz");
        System.out.println(m.getValue("abcff"));

        m.add("abcd", "dgey");
        m.add("ghiydgugugfgifh", "xyz");

        int[] input = {1,2,3,4,1,2,1,3,4},
              input2 = {1,3,4,5,6};
       
        printCount(input);
        printIntersection(input, input2);
    }
}