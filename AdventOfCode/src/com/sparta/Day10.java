package com.sparta;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Day10 {

    public static ArrayList<Integer> loadFile(String url){
        ArrayList<Integer> result = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(url));
            String line;

            while((line = bufferedReader.readLine()) != null){
                result.add(Integer.parseInt(line));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return result;

    }

    public static int[] orderAndGetDifference(ArrayList<Integer> adapters){
        adapters.add(0,0);
        Collections.sort(adapters);
        adapters.add(adapters.size(), adapters.get(adapters.size() -1 ) + 3);
        System.out.println(adapters.toString());

        int[] differences = new int[adapters.size() - 1];
        for(int i = 0; i < adapters.size() -1 ;i++){
            differences[i] = adapters.get(i+1)-adapters.get(i);
        }

        return differences;
    }


    public static int countOnes(int[] differences){
        int count = 0;

        for(int i: differences){
            if(i == 1){
                count++;
            }
        }
        return count;
    }



    public static int countThrees(int[] differences){
        int count = 0;

        for(int i: differences){
            if(i == 3){
                count++;
            }
        }
        return count;
    }


    public static void performTask(String url){
        ArrayList<Integer> adapters = loadFile(url);
        System.out.println(adapters.toString());

        int[] differences = orderAndGetDifference(adapters);
        System.out.println(Arrays.toString(differences));
        int ones =countOnes(differences);
        int threes = countThrees(differences);
        System.out.println(ones);
        System.out.println(threes);

        System.out.println(ones* threes);
    }

    public static void main(String[] args) {
        performTask("AdventOfCode/day10test.txt");

        performTask("AdventOfCode/day10input.txt");

    }


}
