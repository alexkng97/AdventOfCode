package com.sparta;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day2 {

    public static ArrayList<String> loadFileByLine(String filePath) throws IOException {
        File file = new File(filePath);
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        ArrayList<String> list = new ArrayList<>();
        String line;
        while((line= bufferedReader.readLine()) != null){
            list.add(line);

        }

        return list;
    }

    public static int countValid(ArrayList<String> lines){
        int count = 0;
        for(String string: lines){
            if(secondValid(string)){
                count++;
            }
        }

        return count;
    }


    public static boolean isValid(String string){
        String[] split = string.split(" ");
        String[] numbers = split[0].split("-");

        int lowerBound = Integer.parseInt(numbers[0]);
        int upperBound = Integer.parseInt(numbers[1]);
        char letter = split[1].charAt(0);

        int count = 0;
        for(int i = 0; i < split[2].length(); i++){
            if(split[2].charAt(i) == letter){
                count++;
            }
        }

        return count >= lowerBound && count <= upperBound;
    }


    public static boolean secondValid(String string){
        String[] split = string.split(" ");
        String[] numbers = split[0].split("-");

        int firstIndex = Integer.parseInt(numbers[0]) -1;
        int secondIndex = Integer.parseInt(numbers[1]) -1;
        char letter = split[1].charAt(0);

        if(split[2].charAt(firstIndex) == letter){
            return split[2].charAt(secondIndex) != letter;

        }else return split[2].charAt(secondIndex) == letter;


    }


    public static void main(String[] args) {
        ArrayList <String> lines = new ArrayList<>();
        try {
            lines =Day2.loadFileByLine("/Users/alexng/AdventOfCode/day2input.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(Day2.countValid(lines));

    }

}
