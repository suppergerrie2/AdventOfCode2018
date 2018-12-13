package com.suppergerrie2.adventofcode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;

public class Day12 extends Day {

	@Override
	public String part1() {
		File file = new File("input/day12.txt");

		List<String> lines;
		try {
			lines = Files.readAllLines(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		
		String initialState = lines.remove(0);
		initialState = initialState.split(": ")[1];
		lines.remove(0);
		
		HashMap<Integer, Boolean> plants = new HashMap<Integer, Boolean>();

		for(int i = 0; i < initialState.length(); i++) {
			char c = initialState.charAt(i);
			if(c=='#') {
				plants.put(i, true);
			} else if (c=='.') {
				plants.put(i, false);
			} else {
				System.out.println("Whut?");
			}
		}
		
		HashMap<Integer, Boolean> rules = new HashMap<>();
		
		for(String line : lines) {
			String[] keyValue = line.split(" => ");
			
			int ruleKey = 0;
			
			for(int i = 0; i < keyValue[0].length(); i++) {
				char c = keyValue[0].charAt(i);
				if(c=='#') {
					ruleKey = ruleKey | (1 << (4-i));
				}
			}
			
			rules.put(ruleKey, keyValue[1].charAt(0)=='#');
		}
		
		int lowestPot = 0;
		int highestPot = plants.size();

		for(int gen = 1; gen <= 20; gen++) {
			HashMap<Integer, Boolean> newGen = new HashMap<>();
			int tempLow = lowestPot;
			int tempHigh = highestPot;
			lowestPot = Integer.MAX_VALUE;
			highestPot = Integer.MIN_VALUE;
			for(int pot = tempLow-2; pot < tempHigh+2; pot++) {
				
				int ruleKey = 0;
				
				for(int i = -2; i <= 2; i++) {
					boolean alive = getPlantStatus(plants, pot+i);
					
					if(alive) {
						ruleKey = ruleKey | (1 << (4-(i+2)));
					}
				}
				
				boolean alive = false;
				if(rules.containsKey(ruleKey)) {
					alive = rules.get(ruleKey);	
				}
				
				if(alive&&pot<lowestPot) {
					lowestPot = pot;
				}

				if(alive&&pot>highestPot) {
					highestPot = pot;
				}
				
				newGen.put(pot, alive);
			}

			plants = newGen;
		}
		
		long sum = 0;
		for(Integer pot : plants.keySet()) {
			if(plants.get(pot)) {
				sum+=pot;
			}
		}
		
		return "Sum: " + sum;
	}

	boolean getPlantStatus(HashMap<Integer, Boolean> plants, int pot) {
		boolean alive = false;
		
		if(plants.containsKey(pot)) {
			alive = plants.get(pot);
		}
		
		return alive;
	}
	
	@Override
	public String part2() {
		File file = new File("input/day12.txt");

		List<String> lines;
		try {
			lines = Files.readAllLines(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		
		String initialState = lines.remove(0);
		initialState = initialState.split(": ")[1];
		lines.remove(0);
		
		HashMap<Integer, Boolean> plants = new HashMap<Integer, Boolean>();

		for(int i = 0; i < initialState.length(); i++) {
			char c = initialState.charAt(i);
			if(c=='#') {
				plants.put(i, true);
			} else if (c=='.') {
				plants.put(i, false);
			} else {
				System.out.println("Whut?");
			}
		}
		
		HashMap<Integer, Boolean> rules = new HashMap<>();
		
		for(String line : lines) {
			String[] keyValue = line.split(" => ");
			
			int ruleKey = 0;
			
			for(int i = 0; i < keyValue[0].length(); i++) {
				char c = keyValue[0].charAt(i);
				if(c=='#') {
					ruleKey = ruleKey | (1 << (4-i));
				}
			}
			
			rules.put(ruleKey, keyValue[1].charAt(0)=='#');
		}
		
		int lowestPot = 0;
		int highestPot = plants.size();
		String lastGen = "";
		long endGen = 0;
		for(long gen = 1; gen <= 50000000000L; gen++) {
			HashMap<Integer, Boolean> newGen = new HashMap<>();
			int tempLow = lowestPot;
			int tempHigh = highestPot;
			lowestPot = Integer.MAX_VALUE;
			highestPot = Integer.MIN_VALUE;
			StringBuilder builder = new StringBuilder();
			for(int pot = tempLow-2; pot < tempHigh+2; pot++) {
				
				int ruleKey = 0;
				
				for(int i = -2; i <= 2; i++) {
					boolean alive = getPlantStatus(plants, pot+i);
					
					if(alive) {
						ruleKey = ruleKey | (1 << (4-(i+2)));
					}
				}
				
				boolean alive = false;
				if(rules.containsKey(ruleKey)) {
					alive = rules.get(ruleKey);	
				}
				
				if(alive&&pot<lowestPot) {
					lowestPot = pot;
				}

				if(alive&&pot>highestPot) {
					highestPot = pot;
				}
				
				newGen.put(pot, alive);
				builder.append(alive?"#":".");
			}

			String currentGen = builder.toString();
			
			if(currentGen.equals(lastGen)) {
				System.out.println(gen + " " + lowestPot + " " + highestPot);
				endGen = gen;
				break;
			}
			
			lastGen = currentGen;

			plants = newGen;
		}
		
		long sum = 0;
		for(Integer pot : plants.keySet()) {
			if(plants.get(pot)) {
				sum+=(pot + (50000000000L-endGen)+1);
			}
		}
		
		return "Sum: " + sum;
	}

	@Override
	public String getDayName() {
		return "--- Day 12: Subterranean Sustainability ---";
	}

}
