package com.sparta;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileLoader {

	public static ArrayList<String> loadFileToArrayList(String path){
		ArrayList<String> output = new ArrayList<>();
		try{
			BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
			String line;
			while((line = bufferedReader.readLine()) != null){
				output.add(line);
			}
		}catch(IOException e ){
			e.printStackTrace();
		}

		return output;

	}

}
