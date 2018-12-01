package com.suppergerrie2.adventofcode.day1.part1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Day1P1 {

	public static void main(String[] args) throws IOException {
		File file = new File("input/day1.txt");
		
		List<String> lines = Files.readAllLines(file.toPath());
		
		int frequency = 0;
		
		for(String line : lines) { 
			frequency += Integer.parseInt(line);
		}
		
		System.out.println(frequency);
	}

}
