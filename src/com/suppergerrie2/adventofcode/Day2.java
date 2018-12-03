package com.suppergerrie2.adventofcode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Day2 extends Day {
	
	@Override
	public String part1() {
		File file = new File("input/day2.txt");

		List<String> lines;
		try {
			lines = Files.readAllLines(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}

		int twoLetters = 0;
		int threeLetters = 0;

		int[] letterFrequency = new int[26];
		for(String line : lines) { 
			for(int i = 0; i < line.length(); i++) {
				letterFrequency[line.charAt(i)-97]++;
			}
			
			boolean hasTwo = false;
			boolean hasThree = false;
			
			for(int i = 0; i < letterFrequency.length; i++) {
				if(!hasTwo&&letterFrequency[i]==2) {
					hasTwo = true;
					twoLetters++;
				} else if(!hasThree&&letterFrequency[i]==3) {
					hasThree=true;
					threeLetters++;
				}
				letterFrequency[i]=0;
			}
		}
		
		return "Checksum: "+twoLetters*threeLetters;
	}

	@Override
	public String part2() {
		File file = new File("input/day2.txt");

		List<String> lines;
		try {
			lines = Files.readAllLines(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}

		for(int i = 0; i < lines.size(); i++) {
			String lineA = lines.get(i);
			for(int j = i+1; j < lines.size(); j++) {
				String lineB = lines.get(j);
				
				int difference = 0;
				for(int charIndex = 0; charIndex < lineA.length(); charIndex++) {
					if(difference>1) break;
					
					if(lineA.charAt(charIndex)!=lineB.charAt(charIndex)) {
						difference++;
					}
				}
				
				if(difference==1) {
					String answer = "";
					for(int charIndex = 0; charIndex < lineA.length(); charIndex++) {
						if(lineA.charAt(charIndex)==lineB.charAt(charIndex)) {
							answer+=lineA.charAt(charIndex);
						}
					}
					
					return "Common letters: " + answer;
				}
			}
		}
		
		return "";
	}

	@Override
	public String getDayName() {
		return "--- Day 2: Inventory Management System ---";
	}

}
