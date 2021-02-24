package com.sparta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Day14 {
	HashMap<Integer,String> mask;
	HashMap<String, Long> memory;

	public Day14(){
		mask = new HashMap<>();
		memory = new HashMap<>();
	}

	public static String decimalToBinary(int decimal){
		return Integer.toBinaryString(decimal);
	}

	public static int binaryToDecimal(String binary){
		return Integer.parseInt(binary,2);
	}

	public static long binaryToLong(String binary) {return Long.parseLong(binary, 2);}

	public void setMask(String firstLine){
		firstLine = firstLine.substring(firstLine.indexOf('=') + 1).trim();

		for(int i = 0; i < firstLine.length(); i++){
			if(firstLine.charAt(i) != 'X'){
				mask.put(i,String.valueOf(firstLine.charAt(i)));
			}
		}
	}

	public static String[] splitMemoryLine(String line){
		String[] result = new String[2];
		int equals = line.indexOf('=');
		result[0] = line.substring(0,equals).trim();
		result[1] = line.substring(equals+1).trim();

		return result;
	}

	public static String padBinary(String binary){
		return String.format("%036d", Integer.parseInt(binary));
	}

	public static String padAndConvert(int decimal){
		return String.format("%36s", Integer.toBinaryString(decimal)).replace(' ', '0');
	}

	public String maskOverwrite(String binary){
		StringBuilder result = new StringBuilder(binary);
		for(Map.Entry<Integer, String> entry: mask.entrySet()){
			result.setCharAt(entry.getKey(),entry.getValue().charAt(0));
		}

		return result.toString();
	}

	public long sumOfAll(){
		return memory.values().stream().mapToLong(i -> i).sum();
	}


	public void partOne(String file){
		ArrayList<String> lines = FileLoader.loadFileToArrayList(file);
		for (String line : lines) {
			if(line.contains("mask")){
				setMask(line);
			}else {
				String[] memoryLine = splitMemoryLine(line);
				int value = Integer.parseInt(memoryLine[1]);

				String binaryWithPad = padAndConvert(value);
				String withOverwrite = maskOverwrite(binaryWithPad);
				long longValue = binaryToLong(withOverwrite);
				memory.put(memoryLine[0], longValue);
			}
		}

		System.out.println(sumOfAll());

	}


	public static void main(String[] args) {
		Day14 day14 = new Day14();
		day14.partOne("AdventOfCode/day14test.txt");


		System.out.println("-------------------------");
		Day14 longShot = new Day14();
		day14.partOne("AdventOfCode/day14input.txt");

	}

}
