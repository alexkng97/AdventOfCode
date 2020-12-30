package com.sparta;

import java.io.BufferedReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Day8 {

    public static ArrayList<String> loadFile(String url){
        ArrayList<String> result = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(url));
            String line;

            while ((line = bufferedReader.readLine())!= null){
                result.add(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

    public static int findAccumulator(ArrayList<String> operation){
        int accumulator = 0;
        int index = 0;
        ArrayList<Integer> indexVisited = new ArrayList<>();
        do {

            if(indexVisited.contains(index)){
                //TODO:incomplete part 2
                System.out.println(index);
                System.out.println(operation.get(index));

                System.out.println(indexVisited.toString());
                System.out.println(indexVisited.get(indexVisited.size()-1));
                System.out.println(operation.get(indexVisited.get(indexVisited.size()-1)));

                System.out.println("CHANGE INDEX:");
                index = indexVisited.get(indexVisited.size()-1);
                System.out.println(index);
                System.out.println(operation.get(index));

                operation.set(index, operation.get(index).replace("jmp","nop"));
                System.out.println(operation.get(index));

                break;
            }else{
                indexVisited.add(index);
            }

            String[] instructions = operation.get(index).split(" ");
            if (instructions[0].equals("acc")) {
                accumulator += Integer.valueOf(instructions[1]);
                index++;
            }

            if (instructions[0].equals("jmp")) {
                index += Integer.valueOf(instructions[1]);
            }

            if(instructions[0].equals("nop")){
                index++;
            }


        }while(index < operation.size());

        return accumulator;

    }


    public static void main(String[] args) {
        ArrayList<String> operations = loadFile("AdventOfCode/day8input.txt");
        //System.out.println(operations.toString());

        ArrayList<String> test = new ArrayList<>(Arrays.asList("nop -999", "jmp +2", "acc +2","acc -3","jmp -4"));

        //System.out.println(findAccumulator(test));
        System.out.println(findAccumulator(operations));

    }
}
