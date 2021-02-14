package com.sparta;

import java.util.ArrayList;
import java.util.HashMap;

public class Day14 {
	HashMap<Integer,String> mask;

	public Day14(){
		mask = new HashMap<>();
	}

	public static String decimalToBinary(int decimal){
		return Integer.toBinaryString(decimal);
	}

	public static int binaryToDecimal(String binary){
		return Integer.parseInt(binary,2);
	}

	public void setMask(String firstLine){
		firstLine = firstLine.substring(firstLine.indexOf('=') + 1).trim();
		System.out.println(firstLine);
		for(int i = 0; i < firstLine.length(); i++){
			if(firstLine.charAt(i) != 'X'){
				mask.put(i,String.valueOf(firstLine.charAt(i)));
			}
		}

	}


	public static void main(String[] args) {
		ArrayList<String> lines = FileLoader.loadFileToArrayList("AdventOfCode/day14test.txt");
		Day14 day14 = new Day14();
		day14.setMask(lines.get(0));

		day14.mask.entrySet().forEach(e -> {
			System.out.println(e.getKey() + " " + e.getValue());
		});

	}

}
