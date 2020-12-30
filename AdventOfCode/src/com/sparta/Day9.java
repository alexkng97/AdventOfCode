package com.sparta;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class Day9 {
    public static ArrayList<Long> loadFile(String url) {
        ArrayList<Long> result = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(url));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.add(Long.parseLong(line));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean isValid(ArrayList<Long> preamblePlusCheck) {
        Long numberToCheck = preamblePlusCheck.get(preamblePlusCheck.size() - 1);

        for (int i = 0; i < preamblePlusCheck.size() - 1; i++) {
            for (int j = 0; j < preamblePlusCheck.size() - 1; j++) {
                if (preamblePlusCheck.get(i) + preamblePlusCheck.get(j) == numberToCheck) {
                    return true;
                }
            }
        }

        return false;

    }

    public static Long invalidNumber(ArrayList<Long> input, int preambleSize){
        //int start = preambleSize +1;
        ArrayList <Long> preamblePlusCheck;
        for(int i = preambleSize + 1 ; i < input.size(); i++){
            preamblePlusCheck = new ArrayList<>(input.subList(i - (preambleSize + 1), i));

            if(!isValid(preamblePlusCheck)){
                System.out.println("this be invalid!");
                System.out.println(preamblePlusCheck.toString());
                return preamblePlusCheck.get(preambleSize);
            }

        }
        return 0L;

    }


    public static long encryptionWeakness(ArrayList<Long> input, Long invalidNumber){
        //only need to measure up until invalid number(list is ordered after first preamble)
        int indexOfInvalid = input.indexOf(invalidNumber);
        boolean weaknessFound = false;
        ArrayList<Long> contigousRange = new ArrayList<>();

        for(int i = 0; i < indexOfInvalid - 1; i++){
            Long sum =0L;

            if(weaknessFound){
                break;
            }
            for(int j = i; j < indexOfInvalid -1; j++){
                sum += input.get(j);

                if (sum.equals(invalidNumber)) {
                    weaknessFound = true;
                    contigousRange = new ArrayList<>(input.subList(i,j));
                    System.out.println(input.get(i));
                    System.out.println(input.get(j));

                    break;
                }

                if(sum > invalidNumber){
                    break;
                }

            }

        }



        return Collections.min(contigousRange) + Collections.max(contigousRange) ;

    }

    public static void main(String[] args) {
        ArrayList<Long> input = loadFile("AdventOfCode/day9input.txt");
        ArrayList<Long> test = loadFile("AdventOfCode/day9test.txt");

        Long invalidTestNumber = invalidNumber(test,5);

        Long invalidInputNumber = invalidNumber(input, 25);

        System.out.println("-------PART TWO--------");

        System.out.println(encryptionWeakness(test,invalidTestNumber));

        System.out.println(encryptionWeakness(input,invalidInputNumber));



    }
}
