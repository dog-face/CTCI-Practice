package com.mznco;

import com.sun.org.apache.xpath.internal.operations.Mult;
import sun.awt.image.ImageWatched;

import java.util.HashMap;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String input = "poiuytrewq";
        HashMap<Character, Boolean> charTracker = new HashMap<Character, Boolean>();

        System.out.println("isUnique: " + isUnique(input, charTracker));

        System.out.println("isUnique: " + isUnique(input));


        String input2 = "qwertyuiop";

        System.out.println("isPermutation: " + isPermutation(input, input2));

        int[][] twoDArray = {{0, 1, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11}, {12, 13, 14, 15}};

        //print4x4Array(twoDArray);
        //int[][] newArr = RotateArray(twoDArray);
        //System.out.println();
        //print4x4Array(newArr);

        String input1 = "bale";
        input2 = "pale";

        System.out.println("oneAway: " + oneAway(input1, input2));

        LinkedList<String> ll = new LinkedList<String>();
        ll.add("a");
        ll.add("b");
        ll.add("c");
        ll.add("b");

        removeDups(ll);

        for(int i = 0; i < ll.size(); i++){
            System.out.print(ll.get(i) + ", ");
        }
        System.out.println();

        LinkedList<Integer> ll2 = new LinkedList<Integer>();
        ll2.add(3);
        ll2.add(5);
        ll2.add(8);
        ll2.add(5);
        ll2.add(10);
        ll2.add(2);
        ll2.add(1);

        partition(ll2, 5);

        for(int i = 0; i < ll2.size(); i++){
            System.out.print(ll2.get(i) + ", ");
        }
        System.out.println();

        MinStack msTest = new MinStack();
        msTest.push(5);
        msTest.push(6);
        msTest.push(5);
        System.out.println(msTest.min());
        msTest.push(2);
        System.out.println(msTest.min());
        msTest.pop();
        System.out.println(msTest.min());
        msTest.pop();
        System.out.println(msTest.min());

        //interactiveStackTester();

        Graph myGraph = new Graph();
        myGraph.addNode("a");
        myGraph.addNode("b");
        myGraph.addNode("c");
        myGraph.addNode("d");
        myGraph.addNode("e");
        myGraph.addNode("f");

        myGraph.addEdge("a", "b");
        myGraph.addEdge("b", "c");
        myGraph.addEdge("c", "f");
        myGraph.addEdge("d", "e");
        myGraph.addEdge("e", "f");
        myGraph.addEdge("a", "c");

        myGraph.printGraph();

        System.out.println(myGraph.findPath("a", "c"));


        // write your code here
    }

    public static void interactiveStackTester(){
        MultiStack test = new MultiStack();
        System.out.println("Commands: \npush x, pop, exit");
        Scanner scan = new Scanner(System.in);
        String command = scan.nextLine();
        while(!command.equals("exit")){
            if(command.contains("push")){
                int val = Integer.parseInt(command.substring(5));
                test.push(val);
            }
            if(command.contains("pop")){
                System.out.println(test.pop());
            }
            command = scan.nextLine();
        }

    }

    public static void partition(LinkedList<Integer> list, int partition){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i) < partition){
                int temp = list.remove(i);
                list.addFirst(temp);
            }
        }
    }

    public static void removeDups(LinkedList<String> list){
        HashMap<String, Integer> dupTracker = new HashMap<String, Integer>();
        for(int i = 0; i < list.size(); i++){
            if(dupTracker.containsKey(list.get(i))){//we have a duplicate
                list.remove(i);
                i--;
            }
            else{
                dupTracker.put(list.get(i), 1);
            }
        }
    }


    public static boolean oneAway(String input1, String input2){
        if(input1.equals(input2)){//quick check
            return true;
        }

        int dist = input1.length() - input2.length();
        if(dist == 0){//matching length. check for replacement
            char[] input1a = input1.toCharArray();
            //Arrays.sort(input1a);
            char[] input2a = input2.toCharArray();
            //Arrays.sort(input2a);
            int arrdist = 0;
            for(int i = 0; i < input1a.length; i++){
                if(input1a[i] != input2a[i]){
                    arrdist++;
                    if(arrdist > 1){
                        return false;
                    }
                }
            }
            if(arrdist <= 1){
                return true;
            }
        }
        if(dist == -1 || dist == 1){//one away. Check for addition/removal
            char[] input1a = input1.toCharArray();
            //Arrays.sort(input1a);
            char[] input2a = input2.toCharArray();
            //Arrays.sort(input2a);

            int index1 = 0;
            int index2 = 0;
            int arrdist = 0;
            while(index1 < input1a.length && index2 < input2a.length){
                if(input1a[index1] != input2a[index2]){
                    //skip this letter in the longer string and see if the rest match
                    if(dist == -1)
                        index2++;
                    else
                        index1++;
                    arrdist++;
                    if(arrdist > 1){
                        return false;
                    }
                }
                index1++;
                index2++;
            }
            if(arrdist <= 1){
                return true;
            }
        }

        return false;
    }

    static int[][] RotateArray(int[][] arr){
        int[][] newarr = new int[arr.length][arr.length];
        int l = arr.length-1;
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length; j++){
                newarr[j][i] = arr[l-i][j];
            }
        }
        return newarr;

    }

    static void print4x4Array(int[][] twoDArray){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(twoDArray[i][j] < 10){
                    System.out.print(twoDArray[i][j] + " ");
                }
                else{
                    System.out.print(twoDArray[i][j]);
                }
            }
            System.out.println();
        }
    }

    static boolean isPermutation(String one, String two){
        if(one.length() != two.length())
            return false;

        HashMap<Character, Integer> oneMap = new HashMap<Character, Integer>();
        HashMap<Character, Integer> twoMap = new HashMap<Character, Integer>();

        for(int i = 0; i < one.length(); i++) {
            Integer currentValue1 = oneMap.get(one.charAt(i));
            if (currentValue1 != null) {
                oneMap.put(one.charAt(i), currentValue1 + 1);
            } else {
                oneMap.put(one.charAt(i), 1);
            }
            Integer currentValue2 = twoMap.get(two.charAt(i));
            if (currentValue2 != null) {
                twoMap.put(two.charAt(i), currentValue2 + 1);
            } else {
                twoMap.put(two.charAt(i), 1);
            }

        }

        for(int i = 0; i < one.length(); i++){
            if(oneMap.get(one.charAt(i)) != twoMap.get(one.charAt(i))){
                return false;
            }
        }
        return true;
    }

    static boolean isUnique(String input, HashMap<Character, Boolean> charTracker){
        for(int i = 0; i < input.length(); i++){
            Character thisLetter = input.charAt(i);
            if(charTracker.containsKey(thisLetter)){
                if(charTracker.get(thisLetter)){
                    return false;
                }
                else{
                    charTracker.put(thisLetter, true);
                }
            }
            else{
                charTracker.put(thisLetter, true);
            }
        }
        return true;
    }

    //this version uses no external data structures.
    static boolean isUnique(String input){
        char[] array = input.toCharArray();
        Arrays.sort(array);
        for(int i = 0; i < array.length-1; i ++){
            if(array[i] == array[i+1])
                return false;
        }
        return true;
    }
}
