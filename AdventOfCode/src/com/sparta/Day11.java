package com.sparta;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Day11 {

    public static ArrayList<String> loadFile(String url) {
        ArrayList<String> result = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(url));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                result.add(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static char[][] toMatrix(ArrayList<String> lines) {
        int sizeOfLine = lines.get(0).length();
        char[][] matrix = new char[lines.size()][sizeOfLine];

        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = lines.get(i).toCharArray();

        }
        return matrix;
    }

    public static void printMatrix(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }

            System.out.println(" ");
        }
    }

    public static char[] getAdjacentSeats(char[][] matrix, int row, int column) {
        char[] adjacentSeats = new char[8];
        //POSITIONS:
        //0 = top left, 1 = top, 2 = top right, 3 = right, 4 = bottom right, 5 = bottom, 6 = bottom left, 7 = left

        adjacentSeats[0] = getSeat(matrix, row - 1, column -1);
        adjacentSeats[1] = getSeat(matrix, row - 1, column);
        adjacentSeats[2] = getSeat(matrix, row - 1, column + 1);

        adjacentSeats[3] = getSeat(matrix, row, column + 1);

        adjacentSeats[4] = getSeat(matrix, row + 1, column +1);
        adjacentSeats[5] = getSeat(matrix, row +1, column);
        adjacentSeats[6] = getSeat(matrix,row + 1,column - 1);

        adjacentSeats[7] = getSeat(matrix,row,column - 1);


        return adjacentSeats;
    }

    public static char getSeat(char[][] matrix, int row, int column) {
        char seat = '\u0000';
        try {
            seat = (matrix[row][column]);

        } catch (ArrayIndexOutOfBoundsException e) {

        }
        return seat;
    }

    public static int countAdjacentOccupiedSeats(char [] adjacentSeats){
        int count = 0;
        for(char c: adjacentSeats){
            if(c == '#'){
                count++;
            }
        }
        return count;
    }


    public static char[][] applyRules(char[][] matrix){
        char[][] results = new char[matrix.length][matrix[0].length];

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){

                if(matrix[i][j] == 'L' && countAdjacentOccupiedSeats(getAdjacentSeats(matrix, i, j)) == 0) {
                    results[i][j] = '#';

                }else if(matrix[i][j] == '#' && countAdjacentOccupiedSeats(getAdjacentSeats(matrix,i,j)) >= 4){
                    results[i][j] = 'L';

                }else{
                    results[i][j] = matrix[i][j];
                }

            }
        }

        return results;
    }

    public static int countOccupiedSeats(char[][] matrix){
        int count = 0;

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                if(matrix[i][j] == '#'){
                    count++;
                }
            }
        }

        return count;
    }

    public static int numberOfOccupiedSeatsAtStable(char [][] matrix){
        int occupiedSeatsBefore;
        int occupiedSeatsAfter;

        do{
            occupiedSeatsBefore = countOccupiedSeats(matrix);
            matrix = applyRules(matrix);
            occupiedSeatsAfter = countOccupiedSeats(matrix);

        }while(occupiedSeatsBefore != occupiedSeatsAfter);


        return occupiedSeatsAfter;

    }



    public static void main(String[] args) {
        ArrayList<String> testLines = loadFile("AdventOfCode/day11test.txt");
        char[][] testMatrix = toMatrix(testLines);
        printMatrix(testMatrix);
        System.out.println(Arrays.toString(getAdjacentSeats(testMatrix, 0, 0)));
        System.out.println("");

        System.out.println(numberOfOccupiedSeatsAtStable(testMatrix));

        char[][] seats = toMatrix(loadFile("AdventOfCode/day11input.txt"));

        System.out.println(numberOfOccupiedSeatsAtStable(seats));

    }
}
