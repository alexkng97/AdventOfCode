package com.sparta;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Day1 {
    public static ArrayList<Integer> loadFile(String filePath) throws IOException {
        File file = new File(filePath);
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        ArrayList<Integer> list = new ArrayList<>();
        String line;
        while((line= bufferedReader.readLine()) != null){
            list.add(Integer.valueOf(line));

        }

        return list;
    }

    public static int[] getExpense(ArrayList<Integer> arrayList){
        int[] result = new int[2];
        for(int i = 0; i < arrayList.size(); i++){
            for(int j = i; j < arrayList.size(); j++){
                if(arrayList.get(i) + arrayList.get(j) == 2020){
                    result[0] = arrayList.get(i);
                    result[1] = arrayList.get(j);
                }
            }

        }
        return result;
    }

    public static int[] get3Expense(ArrayList<Integer> arrayList){
        int[] result = new int[3];
        for(int i = 0; i < arrayList.size(); i++){
            for(int j = i; j < arrayList.size(); j++){
                for(int k = j; k < arrayList.size(); k++){
                    if(arrayList.get(i) + arrayList.get(j) + arrayList.get(k)== 2020){
                        result[0] = arrayList.get(i);
                        result[1] = arrayList.get(j);
                        result[2] = arrayList.get(k);
                    }
                }
            }

        }
        return result;
    }

    public static void main(String[] args) {
        ArrayList<Integer> expenses = new ArrayList<>();
        try {
            expenses = Day1.loadFile("/Users/alexng/AdventOfCode/input.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(expenses.toString());
        int [] results = Day1.getExpense(expenses);
        System.out.println(Arrays.toString(Arrays.stream(results).toArray()));
        System.out.println(results[0] * results[1]);

        int[] second = Day1.get3Expense(expenses);
        System.out.println(Arrays.toString(Arrays.stream(second).toArray()));
        System.out.println(second[0] * second[1] * second[2]);
    }

}
