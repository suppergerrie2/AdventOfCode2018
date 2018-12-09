package com.suppergerrie2.adventofcode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Day9 extends Day {

	@Override
	public String part1() {
		File file = new File("input/day9.txt");

		List<String> lines;
		try {
			lines = Files.readAllLines(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		
		String line = lines.get(0);
		
		int players = Integer.parseInt(line.split(" ")[0]);
		int maxScore = Integer.parseInt(line.split(" ")[6]);
		
		int[] scores = new int[players];
		
		List<Integer> marbles = new ArrayList<Integer>();
		
		int currentPlayer = -1;
		marbles.add(0);
		int currentMarbleIndex = 0;
		
		currentPlayer++;
		for(int i = 1; i <= maxScore; i++) {
			
			if(i%23==0) {
				scores[currentPlayer]+=i;
				currentMarbleIndex-=7;
				if(currentMarbleIndex<0) {
					currentMarbleIndex+=marbles.size();
				}
				
				scores[currentPlayer]+=marbles.remove(currentMarbleIndex);
			} else {
				currentMarbleIndex+=2;
				
				if(currentMarbleIndex>marbles.size()) {
					currentMarbleIndex-=marbles.size();
				}

				marbles.add(currentMarbleIndex, i);
			}
			currentPlayer = (currentPlayer+1)%players;
		}
		
		int answer = 0;
		for(int i : scores) {
			answer= Math.max(answer, i);
		}
		
		return "HighScore: " + answer;
	}
	
	@Override
	public String part2() {
		File file = new File("input/day9.txt");

		List<String> lines;
		try {
			lines = Files.readAllLines(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		
		String line = lines.get(0);
		
		int players = Integer.parseInt(line.split(" ")[0]);
		int maxScore = Integer.parseInt(line.split(" ")[6])*100;
		
		long[] scores = new long[players];
		
		MarbleList marbles = new MarbleList();

		int currentPlayer = 0;
		for(int i = 1; i <= maxScore; i++) {
			if(i%23==0) {
				scores[currentPlayer]+=i+marbles.remove(-7).points;
			} else {
				marbles.add(new Marble(i), 2);
			}
			currentPlayer = (currentPlayer+1)%players;
		}
		
		long answer = 0;
		for(long i : scores) {
			answer= Math.max(answer, i);
		}
		
		return "HighScore: " + answer;
	}

	@Override
	public String getDayName() {
		return "--- Day 9: Marble Mania ---";
	}

}

class MarbleList {
	Marble root = new Marble(0);
	Marble current = root;
	
	public MarbleList() {
		root.previousMarble = root;
		root.nextMarble = root;
		current = root;
	}
	
	Marble getAt(int pos) {
		Marble m = root;
		for(int i = 0; i < pos; i++) {
			m = m.nextMarble;
		}
		return m;
	}
	
	void add(Marble marble, int offsetFromCurrent) {
		Marble m = current;
		for(int i = 0; i < offsetFromCurrent-1; i++) {
			if(offsetFromCurrent>0) {
				m = m.nextMarble;
			} else {
				m = m.previousMarble;
			}
		}
		
		marble.nextMarble = m.nextMarble;
		marble.previousMarble = m;

		marble.nextMarble.previousMarble = marble;
		m.nextMarble = marble;
		
		current = marble;
	}
	
	Marble remove(int offsetFromCurrent) {
		Marble m = current;
		boolean backwards = false;
		
		if(offsetFromCurrent<0) {
			backwards = true;
			offsetFromCurrent= Math.abs(offsetFromCurrent);
		}
		
		for(int i = 0; i < offsetFromCurrent; i++) {
			if(backwards) {
				m = m.previousMarble;
			} else {
				m = m.nextMarble;
			}
		}
		
		Marble next = m.nextMarble;
		Marble previous = m.previousMarble;
		previous.nextMarble = next;
		next.previousMarble = previous;
		
		current = next;
		
		return m;
	}
	
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		
		Marble c = root;
		do {
			b.append(" ").append(c.points);
			c = c.nextMarble;
		} while(c!=root);
		
		return b.toString();
	}
}

class Marble {
	Marble nextMarble;
	Marble previousMarble;
	
	int points;
	
	public Marble(int i) {
		this.points = i;
	}
	
	@Override
	public String toString() {
		return "m " + points;
	}
}
