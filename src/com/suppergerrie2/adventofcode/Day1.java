package com.suppergerrie2.adventofcode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day1 extends Day {

	@Override
	public String part1() {
		File file = new File("input/day1.txt");
		
		List<String> lines;
		try {
			lines = Files.readAllLines(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		
		int frequency = 0;
		
		for(String line : lines) { 
			frequency += Integer.parseInt(line);
		}
		
		return "Frequency: " + frequency;
	}

	@Override
	public String part2() {
		File file = new File("input/day1.txt");

		List<String> lines;
		
		try {
			lines = Files.readAllLines(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		
		Set<Integer> frequenciesFound = new HashSet<Integer>();

		int frequency = 0;
		frequenciesFound.add(frequency);

		while(true) {
			for(String line : lines) { 
				frequency += Integer.parseInt(line);

				if(frequenciesFound.contains(frequency)) {
					return "Frequency: " + frequency;
				}

				frequenciesFound.add(frequency);
			}
		}		
	}

	@Override
	public String getDayName() {
		return "--- Day 1: Chronal Calibration ---";
	}

}
