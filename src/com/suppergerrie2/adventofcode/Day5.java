package com.suppergerrie2.adventofcode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Day5 extends Day {

	@Override
	public String part1() {
		File file = new File("input/day5.txt");

		List<String> lines;
		try {
			lines = Files.readAllLines(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		
		if(lines.size()>1) {
			return "ERROR, TOO MANY LINES!";
		}
		
		String polymer = lines.get(0);
		boolean reactionHappened = false;
		
		do {
			String newPolymer = polymer;
			for(int i = 65; i <= 90; i++) {
				String replacement = (char)i + "" + (char)(i+32);
				newPolymer = newPolymer.replaceAll(replacement, "");

				String replacement2 = (char)(i+32) + "" + (char)i;
				newPolymer = newPolymer.replaceAll(replacement2, "");
			}

			reactionHappened = polymer.length()!=newPolymer.length();
			polymer = newPolymer;
		} while(reactionHappened);
		
		return "Polymer size: " + polymer.length();
	}

	@Override
	public String part2() {
		File file = new File("input/day5.txt");

		List<String> lines;
		try {
			lines = Files.readAllLines(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		
		if(lines.size()>1) {
			return "ERROR, TOO MANY LINES!";
		}
		
		boolean reactionHappened = false;
		int smallestLength = Integer.MAX_VALUE;
		for(int c = 65; c <= 90; c++) {
			String polymer = lines.get(0).replaceAll((char)c + "", "").replaceAll((char)(c+32) + "", "");
			
			do {
				String newPolymer = polymer;
				for(int i = 65; i <= 90; i++) {
					String replacement = (char)i + "" + (char)(i+32);
					newPolymer = newPolymer.replaceAll(replacement, "");
	
					String replacement2 = (char)(i+32) + "" + (char)i;
					newPolymer = newPolymer.replaceAll(replacement2, "");
				}
	
				reactionHappened = polymer.length()!=newPolymer.length();
				polymer = newPolymer;
			} while(reactionHappened);
			
			smallestLength = Math.min(smallestLength, polymer.length());
		}
		
		return "Smallest polymer size: " + smallestLength;
	}

	@Override
	public String getDayName() {
		return "--- Day 5: Alchemical Reduction ---";
	}

}
