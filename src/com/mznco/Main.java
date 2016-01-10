package com.mznco;

import java.util.HashMap;
import java.util.Arrays;

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

        String input1 = "bake";
        input2 = "pale";

        System.out.println("oneAway: " + oneAway(input1, input2));

	// write your code here
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
                System.out.println(input1a[i] + " " + input2a[i]);
                System.out.println(arrdist);
                if(input1a[i] != input2a[i]){
                    arrdist++;
                    if(arrdist > 1){
                        System.out.println("Hello");
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
