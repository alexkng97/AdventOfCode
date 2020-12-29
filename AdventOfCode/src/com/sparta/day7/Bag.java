package com.sparta.day7;

import java.util.ArrayList;

public class Bag {
    private String type;
    private int numOfSubBags;
    private ArrayList<String> subBags;

    public Bag(String type){
        this.type = type;
        this.subBags = new ArrayList<>();
        this.numOfSubBags = 0;
    }

    public int getNumOfSubBags() {
        return numOfSubBags;
    }

    public void setNumOfSubBags(int numOfSubBags) {
        this.numOfSubBags = numOfSubBags;
    }

    public void addToSubBags(String bag){
        subBags.add(bag);
    }

    public ArrayList<String> getSubBags() {
        return subBags;
    }

    public void setSubBags(ArrayList<String> subBags) {
        this.subBags = subBags;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type + " bag";
    }

    public boolean doesSubBagsContain(String bag){
        return subBags.contains(bag);
    }

}
