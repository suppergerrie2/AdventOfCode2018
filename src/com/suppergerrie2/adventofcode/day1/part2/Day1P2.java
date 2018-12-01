package com.suppergerrie2.adventofcode.day1.part2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day1P2 {

	public static void main(String[] args) throws IOException {
		File file = new File("input/day1.txt");

		List<String> lines = Files.readAllLines(file.toPath());
		Set<Integer> frequenciesFound = new HashSet<Integer>();

		int frequency = 0;
		frequenciesFound.add(frequency);

		int loops = 0;
		while(true) {
			loops++;
			for(String line : lines) { 
				frequency += Integer.parseInt(line);

				if(frequenciesFound.contains(frequency)) {
					System.out.println(frequency + " Loops: " + loops);
					return;
				}

				frequenciesFound.add(frequency);
			}
		}

	}

}
