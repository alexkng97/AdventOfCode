package com.sparta;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class Day6 {


    public static ArrayList<String> loadFile(String url) {
        ArrayList<String> groupAnswers = new ArrayList<>();

        try {
            File file = new File(url);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder sb = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                if (line.isEmpty()) {
                    groupAnswers.add(sb.toString());
                    sb = new StringBuilder();

                } else {
                    sb.append(line).append(" ");
                }

            }
            groupAnswers.add(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return groupAnswers;
    }

    public static int countDistinctLetters(String string) {
        char[] charArray = string.toCharArray();
        HashSet<Character> distinct = new HashSet<>();

        for (char c : charArray) {
            if(c != ' ') {
                distinct.add(c);
            }
        }
        return distinct.size();

    }

    public static int[] distinctLettersByGroup(ArrayList<String> groupAnswers) {
        int[] results = new int[groupAnswers.size()];
        for (int i = 0; i < results.length; i++) {
            results[i] = countDistinctLetters(groupAnswers.get(i));
        }

        return results;
    }

    public static int sumOfArray(int[] array){
        int count = 0;
        for(int i : array){
            count += i;
        }

        return count;
    }


    public static int[] countYes (ArrayList<String> groupAnswers){
        //for loop for every string in group answers
        //testing with one: split string with spaces (individuals in group)
        int[] results = new int[groupAnswers.size()];
        for(int i = 0; i < results.length; i++){
            results[i] = countYesInGroup(groupAnswers.get(i));

        }

        return results;
    }

    public static int countYesInGroup(String groupAnswer){

        String[] individualAnswers = groupAnswer.split(" ");
       // System.out.println(Arrays.toString(individualAnswers));
        char[] firstCharacters = individualAnswers[0].toCharArray();

        ArrayList<String> firstLetters = new ArrayList<>();
        for(char c : firstCharacters){
            firstLetters.add(String.valueOf(c));
        }

        HashSet<String> toRemove = new HashSet<>();
        for(int i = 1; i < individualAnswers.length; i++){
            for(String s: firstLetters){
                if(!individualAnswers[i].contains(s)){
                    toRemove.add(s);
                }
            }
        }
        firstLetters.removeAll(toRemove);

        //System.out.println(firstLetters.toString());

        return firstLetters.size();
    }


    public static void main(String[] args) {
        //System.out.println(countDistinctLetters("aabbcc"));
        ArrayList<String> answersByGroup = loadFile("AdventOfCode/day6input.txt");
        //System.out.println(answersByGroup.toString());

        int[] groupDistinctAnswers = distinctLettersByGroup(answersByGroup);
        //System.out.println(Arrays.toString(groupDistinctAnswers));
        System.out.println(sumOfArray(groupDistinctAnswers));

        int[] everyYes = countYes(answersByGroup);
        System.out.println(sumOfArray(everyYes));
    }


}
