package com.sparta.Day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Day12 {
	//east = positive movement in eastwest, west = negative
	//north = positive in northSouth, south = negative
	private int eastWest;
	private int northSouth;
	private Direction shipFacing;


	private enum Direction {NORTH, EAST, SOUTH, WEST};

	public Day12(){
		this.eastWest = 0;
		this.northSouth = 0;
		shipFacing = Direction.EAST;


	}

	public Direction getShipFacing() {
		return shipFacing;
	}

	public void setShipFacing(Direction shipFacing) {
		this.shipFacing = shipFacing;
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

	public static String[] processLine(String line){
		String[] result = new String[2];
		result[0] = line.substring(0,1);
		result[1] = line.substring(1);

		return result;
	}

	public void moveShip(ArrayList<String> lines){

		for(String string: lines){
			String[] action = processLine(string);
			performSingleInstruction(action);

		}
	}

	private void performSingleInstruction(String[] action){
		int amount = Integer.parseInt(action[1]);

		switch (action[0]){
			case "E":
				eastWest += amount;
				break;

			case "W":
				eastWest -= amount;
				break;

			case "N":
				northSouth += amount;
				break;

			case "S":
				northSouth -= amount;
				break;

			case "F":
				moveForwards(action[1]);
				break;

			case "R":
				rotateRight(amount);
				break;

			case "L":
				rotateLeft(amount);
				break;
		}

	}


	private void moveForwards(String amount) {
		//recursively calls performSingleInstruction with direction ship is facing
		String [] action = new String[2];
		switch(shipFacing){
			case EAST:
				action[0] = "E";
				action[1] = amount;
				performSingleInstruction(action);
				break;

			case WEST:
				action[0] = "W";
				action[1] = amount;
				performSingleInstruction(action);
				break;

			case NORTH:
				action[0] = "N";
				action[1] = amount;
				performSingleInstruction(action);
				break;

			case SOUTH:
				action[0] = "S";
				action[1] = amount;
				performSingleInstruction(action);
				break;

			default:
				System.out.println("Error!");


		}
	}

	private void rotateRight(int amount) {
		int degree = amount / 90;
		int shipFacingIndex = shipFacing.ordinal();

		shipFacingIndex += degree;
		if(shipFacingIndex > 3){
			shipFacingIndex = (shipFacingIndex - 3) - 1; //zero indexing
		}

		Direction newDirection = Direction.values()[shipFacingIndex];
		setShipFacing(newDirection);
	}

	private void rotateLeft(int amount) {
		int degree = amount / 90;
		int shipFacingIndex = shipFacing.ordinal();
		shipFacingIndex -= degree;

		if(shipFacingIndex < 0){
			shipFacingIndex = (shipFacingIndex + 4);
		}
		Direction newDirection = Direction.values()[shipFacingIndex];
		setShipFacing(newDirection);

	}

	public static int getManhattanDistance(int eastWest, int northSouth){
		return Math.abs(eastWest) + Math.abs(northSouth);
	}

	public static void main(String[] args) {
		Day12 day12 = new Day12();
		day12.moveShip(loadFile("AdventOfCode/day12test.txt"));
		System.out.println(getManhattanDistance(day12.eastWest, day12.northSouth));

		Day12 puzzleInput = new Day12();
		puzzleInput.moveShip(loadFile("AdventOfCode/day12input.txt"));
		System.out.println(getManhattanDistance(puzzleInput.eastWest, puzzleInput.northSouth));

	}

}
