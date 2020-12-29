package com.sparta.day7;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Day7 {
    ArrayList<Bag> bagList;

    public Day7(){
        bagList = new ArrayList<>();
    }

    public void loadFile(String url){
        try {
            File file = new File(url);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;

            while((line = bufferedReader.readLine()) != null){

                processLine(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void processLine(String line) {
        //Filtering out unnecessary bits of string:
        line = line.replace(".","");
        line = line.replace("bags", "");
        line = line.replace("bag", "");

        String[] bagsAndSubBags = line.split("contain");

        Bag bag = new Bag(bagsAndSubBags[0].trim());
        String[] subBags = (bagsAndSubBags[1].trim()).split(" , ");

        if(!subBags[0].equals("no other")) {
            int count = 0;
            for (String s : subBags) {
                count += Integer.parseInt(s.substring(0, 1));
                //System.out.println(s.substring(2));
                bag.addToSubBags(s.substring(2));
            }

            bag.setNumOfSubBags(count);
        }

        bagList.add(bag);
    }


    public int numberOfBagsHolding(String bag){

        HashSet<Bag> containing = new HashSet<>();
        for(Bag bagInList : bagList){
            if(bagInList.doesSubBagsContain(bag)){
                containing.add(bagInList);
            }
        }
        System.out.println(containing.toString());
        ArrayList<Bag> toAdd = new ArrayList<>();
        for(Bag bagDirectHolding : containing){
            System.out.println("LOOOKING AT: " + bagDirectHolding);

            for(Bag bagInList : bagList){
                if(bagInList.doesSubBagsContain(bagDirectHolding.getType())){
                    toAdd.add(bagInList);
                    System.out.println(bagInList);
                }

            }
        }

        //TODO:fix this?
        System.out.println(toAdd.size());
        containing.addAll(toAdd);


        return containing.size();
    }


    public static void main(String[] args) {
        Day7 day7 = new Day7();
        day7.loadFile("AdventOfCode/day7input.txt");
        System.out.println(day7.bagList.toString());
        System.out.println(day7.bagList.get(83).getSubBags().toString());
        System.out.println(day7.bagList.get(83).doesSubBagsContain("shiny gold"));

        System.out.println(day7.numberOfBagsHolding("shiny gold"));
    }



}
