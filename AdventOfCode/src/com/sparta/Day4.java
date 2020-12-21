package com.sparta;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Day4 {
    ArrayList<String> passportRecords;
    String[] requiredFields = new String[]{"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};

    public Day4() {
        passportRecords = new ArrayList<>();
    }

    public void loadFile(String url) {
        try {
            File file = new File(url);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                if (line.isEmpty()) {
                    passportRecords.add(sb.toString());
                    sb = new StringBuilder();

                } else {
                    sb.append(line).append(" ");
                }
            }
            passportRecords.add(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public boolean isRecordValid(String record) {
        for (String s : requiredFields) {
            if (!record.contains(s)) {
                return false;
            }
        }
        return true;
    }

    public int countValidRecords(ArrayList<String> passportRecords) {
        int count = 0;

        for (String s : passportRecords) {
            if (isRecordValid(s) && processIndividualRecord(s)) {
                count++;
            }

        }

        return count;
    }

    public boolean processIndividualRecord(String record) {
        record = record.trim();
        String[] splitRecord = record.split(" ");
        //System.out.println(Arrays.toString(splitRecord));
        HashMap<String, String> fieldsAndValues = new HashMap<>();

        for (String s : splitRecord) {
            String[] fields = s.split(":");
            fieldsAndValues.put(fields[0], fields[1]);
        }

//        fieldsAndValues.entrySet().forEach(e -> {
//            System.out.println(e.getKey() + " " + e.getValue());
//        });

        return areValuesValid(fieldsAndValues);
    }

    public boolean areValuesValid(HashMap<String, String> fieldsAndValues) {
        // records that reach here will have all required fields: check if values in limits

        int birthYear = Integer.parseInt(fieldsAndValues.get("byr"));
        if (birthYear < 1920 || birthYear > 2002) {
            return false;
        }

        int issueYear = Integer.parseInt(fieldsAndValues.get("iyr"));
        if (issueYear < 2010 || issueYear > 2020) {
            return false;
        }

        int expiryYear = Integer.parseInt(fieldsAndValues.get("eyr"));
        if(expiryYear < 2020 || expiryYear > 2030){
            return false;
        }

        String height = fieldsAndValues.get("hgt");
        String heightUnit = height.substring(height.length()-2);
        int heightValue = Integer.parseInt(height.substring(0,height.length()-2));
        if(heightUnit.equals("cm")){
            if(heightValue < 150 || heightValue>193){
                return false;
            }
        }else{
            if(heightValue <59 || heightValue > 76){
                return false;
            }
        }

        String hairColour = fieldsAndValues.get("hcl");
        if(hairColour.charAt(0) != '#'){
            return false;
        }

        if(!hairColour.substring(1).matches("[0123456789abcdef]+") || hairColour.substring(1).length() != 6){
            return false;
        }

        String eye = fieldsAndValues.get("ecl");
        if(!eye.equals("amb") && !eye.equals("blu") && !eye.equals("brn") && !eye.equals("gry") && !eye.equals("grn") &&
                !eye.equals("hzl") && !eye.equals("oth")  ){
            return false;
        }

        if(fieldsAndValues.get("pid").length() != 9){
            return false;
        }







        return true;

    }


    public static void main(String[] args) {
        Day4 day4 = new Day4();
        day4.loadFile("day4input.txt");
        System.out.println(day4.passportRecords.toString());

        System.out.println(day4.isRecordValid(day4.passportRecords.get(3)));

        System.out.println(day4.countValidRecords(day4.passportRecords));

        day4.processIndividualRecord(day4.passportRecords.get(0));


    }


}
