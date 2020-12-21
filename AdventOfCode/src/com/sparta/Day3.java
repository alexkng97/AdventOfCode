package com.sparta;

import java.io.IOException;
import java.util.ArrayList;

public class Day3 {


    public static int countTrees(ArrayList<String> map){
       //iterate through map, have position (right movement) //if right > size of line, reset
        int position = 0;
        int count = 0;
        for(int i = 1; i < map.size(); i++){
            position +=3;

            if(position >= map.get(i).length()){
                position = position - map.get(i).length();
            }
            if(map.get(i).charAt(position) == '#'){
                count++;
            }
        }
        return count;
    }

    public static int SecondCountTrees(ArrayList<String> map, int right, int down){
        //iterate through map, have position (right movement) //if right > size of line, reset
        int position = 0;
        int count = 0;
        for(int i = 1; i < map.size(); i+= down){
            position +=right;

            if(position >= map.get(i).length()){
                position = position - map.get(i).length();
            }
            if(map.get(i).charAt(position) == '#'){
                count++;
            }
        }
        return count;
    }


    public static void main(String[] args) {
        ArrayList<String> mapOfTrees = new ArrayList<>();
        try {
            mapOfTrees = Day2.loadFileByLine("/Users/alexng/AdventOfCode/day3input.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(mapOfTrees.toString());
        System.out.println(Day3.countTrees(mapOfTrees));

        int first = Day3.SecondCountTrees(mapOfTrees,3,1);
        int second = Day3.SecondCountTrees(mapOfTrees, 1,1);
        int third = Day3.SecondCountTrees(mapOfTrees, 5,1);
        int fourth = Day3.SecondCountTrees(mapOfTrees, 7,1);
        int fifth = Day3.SecondCountTrees(mapOfTrees, 1,2);

        long multiple = first * second;
        multiple *= third;
        multiple *= fourth;
        multiple *= fifth;

        System.out.println(multiple);
    }

}
