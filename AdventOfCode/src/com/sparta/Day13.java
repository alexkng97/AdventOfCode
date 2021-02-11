package com.sparta;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Day13 {
	private int earliestTimestamp;
	private ArrayList<Integer> busID;
	private int timeOfEarliestBus;
	private int idOfEarliestBus;

	public Day13() {
		busID = new ArrayList<>();
	}

	public static ArrayList<String> loadFile(String url){
		ArrayList<String> results = new ArrayList<>();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(url));
			String line;
			while((line = bufferedReader.readLine()) != null){
				results.add(line);
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return results;
	}

	public void processLines(ArrayList<String> lines){
		earliestTimestamp = Integer.parseInt(lines.get(0));
		String[] secondLine = lines.get(1).split(",");
		for(String buses : secondLine){
			if(!buses.equals("x")){
				busID.add(Integer.parseInt(buses));
			}
		}
	}

	public void getEarliestBus(){
		boolean foundTime = false;
		int time = earliestTimestamp;
		do{
			for(int id: busID){
				if(time % id == 0){
					timeOfEarliestBus = time;
					idOfEarliestBus = id;
					foundTime = true;
				}
				time++;
			}
		}while(!foundTime);
	}

	public int getPartOneAnswer(){
		int wait = timeOfEarliestBus - earliestTimestamp;
		return idOfEarliestBus * wait;
	}

	public static String[] splitString(String line){
		return line.split(",");
	}

	public static long partTwo(String[] buses){
		long timestamp = 100000000000000L;
		int firstBus = Integer.parseInt(buses[0]);
		boolean found = false;
		do{
			timestamp++;
			if(timestamp % firstBus == 0){
				found = isTimestampValid(timestamp, buses);

			}

		}while(!found);

		return timestamp;

	}

	public static boolean isTimestampValid(long timestamp, String[] buses){
		//System.out.println(timestamp);
		for(int i = 1; i < buses.length; i++){
			timestamp++;
			if(!buses[i].equals("x")){
				if(timestamp % Integer.parseInt(buses[i]) != 0){
					return false;
				}

			}
		}
		return true;

	}


	public static void main(String[] args) {
		Day13 day13 = new Day13();
		day13.processLines(Day13.loadFile("AdventOfCode/day13input.txt"));
		day13.getEarliestBus();
		System.out.println(day13.getPartOneAnswer());


//		System.out.println(Day13.partTwo(Day13.splitString("7,13,x,x,59,x,31,19")));
//		System.out.println(Day13.partTwo(Day13.splitString("17,x,13,19")));
//		System.out.println(Day13.partTwo(Day13.splitString("67,7,59,61")));

		ArrayList<String> lines = Day13.loadFile("AdventOfCode/day13input.txt");
		//System.out.println(lines.get(1));
		System.out.println(Day13.partTwo(Day13.splitString(lines.get(1))));


	}
}
