package com.sparta;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Day5 {

    public static ArrayList<String> loadFileByLine(String filePath) {
        ArrayList<String> list = null;
        try {
            File file = new File(filePath);
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            list = new ArrayList<>();
            String line;
            while((line= bufferedReader.readLine()) != null){
                list.add(line);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }


    public int[] getSeat(String boardingPass){

        String rows = boardingPass.substring(0, boardingPass.length() - 3);
        String columns = boardingPass.substring(boardingPass.length() - 3);

        return new int[]{getRow(rows), getColumn(columns)};
    }

    private int getRow(String rows) {
        int lowerBound = 0;
        int upperBound = 127;
        int mid;

        for(char s: rows.toCharArray()){
            mid = (upperBound + lowerBound )  / 2;
            switch (s){
                case 'F':
                    upperBound = mid;
                    break;
                case 'B':
                    lowerBound = mid + 1;
                    break;
                default:
                    System.out.println("invalid");
            }

        }

        return lowerBound;
    }

    private int getColumn(String columns) {
        int lowerBound = 0;
        int upperBound = 7;
        int mid;

        for(char s : columns.toCharArray()){
            mid = (upperBound + lowerBound )  / 2;
            switch (s){
                case 'L':
                    upperBound = mid;
                    break;
                case 'R':
                    lowerBound = mid + 1;
                    break;

                default:
                    System.out.println("invalid");
            }
        }
        return lowerBound;

    }

    public int getSeatID(int[] seat){
        return (seat[0] * 8) + seat [1];

    }


    public int getHighestSeatID(ArrayList<String> boardingPasses){
        int max = 0;
        for(String s: boardingPasses){
            int[] seat = getSeat(s);
            if(getSeatID(seat) > max){
                max = getSeatID(seat);
            }
        }
        return max;
    }

    public void allID(ArrayList<String> boardingPasses){
        ArrayList<Integer> allIDs = new ArrayList<>();
        for(String s: boardingPasses){
            int[] seat = getSeat(s);
            allIDs.add(getSeatID(seat));
        }
        Collections.sort(allIDs);
        System.out.println(allIDs.toString());

        int start = 78;
        for(int i = 0; i < allIDs.size(); i++){
            if(allIDs.get(i) != start){
                break;
            }
            start++;
        }
        System.out.println(start);
    }

    public static void main(String[] args) {
        Day5 day5 = new Day5();
        System.out.println(Arrays.toString(day5.getSeat("FBFBBFFRLR")));
        System.out.println(day5.getSeatID(day5.getSeat("FBFBBFFRLR")));

        System.out.println(Arrays.toString(day5.getSeat("BFFFBBFRRR")));
        System.out.println(day5.getSeatID(day5.getSeat("BFFFBBFRRR")));

        System.out.println(Arrays.toString(day5.getSeat("FFFBBBFRRR")));
        System.out.println(day5.getSeatID(day5.getSeat("FFFBBBFRRR")));

        System.out.println(Arrays.toString(day5.getSeat("BBFFBBFRLL")));
        System.out.println(day5.getSeatID(day5.getSeat("BBFFBBFRLL")));

        ArrayList<String> boardingPasses = loadFileByLine("AdventOfCode/day5input.txt");
        System.out.println(day5.getHighestSeatID(boardingPasses));

        day5.allID(boardingPasses);


    }


}
