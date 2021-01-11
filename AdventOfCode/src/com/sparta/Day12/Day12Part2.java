package com.sparta.Day12;

import com.sparta.Day12.Day12;

import java.util.ArrayList;

public class Day12Part2 {

	private int eastWest;
	private int northSouth;

	private int waypointEastWest;
	private int waypointNorthSouth;

	public Day12Part2() {
		this.waypointEastWest = 10;
		this.waypointNorthSouth = 1;

		this.eastWest = 0;
		this.northSouth = 0;

	}

	public void moveShipAndWaypoint(ArrayList<String> lines) {
		for (String s : lines) {
			String[] action = Day12.processLine(s);
			processAction(action);
		}

	}

	private void processAction(String[] action) {
		int amount = Integer.parseInt(action[1]);
		switch (action[0]) {
			case "F":
				moveShipToWaypoint(amount);
				break;

			case "N":
				//move waypoint
				waypointNorthSouth += amount;
				break;

			case "E":
				waypointEastWest += amount;
				break;

			case "S":
				waypointNorthSouth -= amount;
				break;

			case "W":
				waypointEastWest -= amount;
				break;

			case "R":
				rotateWaypointClockwise(amount);
				break;

			case "L":
				rotateWaypointAnticlockwise(amount);
				break;
		}


	}




	private void moveShipToWaypoint(int amount) {
		eastWest += waypointEastWest * amount;
		northSouth += waypointNorthSouth * amount;

	}

	private void rotateWaypointClockwise(int amount) {
		int degree = amount / 90;
		for(int i = 0 ; i < degree; i++){
			rotateClockwiseOneUnit();
		}

	}

	private void rotateClockwiseOneUnit() {
		// scenarios for way point north(+) east(+), south(-) east(+), south(-) west(-), north(+)west(-)
		//from example, rotating is swapping values around and making some negative
		int holder = waypointEastWest;
		waypointEastWest = waypointNorthSouth;
		waypointNorthSouth = -1 * holder;

	}

	private void rotateWaypointAnticlockwise(int amount) {
		int degree = amount / 90;
		for(int i = 0; i < degree ; i++){
			rotateAnticlockwiseOneUnit();

		}

	}

	private void rotateAnticlockwiseOneUnit() {
		int holder = waypointNorthSouth;
		waypointNorthSouth = waypointEastWest;
		waypointEastWest = -1 * holder;

	}


	public static void main(String[] args) {
		Day12Part2 part2 = new Day12Part2();
		part2.moveShipAndWaypoint(Day12.loadFile("AdventOfCode/day12test.txt"));
		System.out.println(part2.eastWest);
		System.out.println(part2.northSouth);

		Day12Part2 puzzleInput = new Day12Part2();
		puzzleInput.moveShipAndWaypoint(Day12.loadFile("AdventOfCode/day12input.txt"));
		System.out.println(Day12.getManhattanDistance(puzzleInput.eastWest, puzzleInput.northSouth));

	}
}
