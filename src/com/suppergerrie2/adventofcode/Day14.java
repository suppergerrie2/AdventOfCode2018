package com.suppergerrie2.adventofcode;

public class Day14 extends Day {

	int input = 939601;
	
	@Override
	public String part1() {
		Scoreboard scoreboard = new Scoreboard();
		
		while(scoreboard.recipeCount<=input+10) {
			int newScore = scoreboard.elf1.score + scoreboard.elf2.score;
			
			String s = String.valueOf(newScore);
			
			for(int i = 0; i < s.length(); i++) {
				scoreboard.appendRecipe(Integer.valueOf(s.substring(i, i+1)));
			}
			
			int score = scoreboard.elf1.score;
			for(int i = 0; i < score+1; i++) {
				scoreboard.elf1 = scoreboard.elf1.next;
			}
			
			score = scoreboard.elf2.score;
			for(int i = 0; i < score+1; i++) {
				scoreboard.elf2 = scoreboard.elf2.next;
			}
		}
		
		Recipe r = scoreboard.root;
		String answer = "";
		for(int i = 0; i < input+10; i++) {
			if(i>=input) {
				answer+=r.score;
			}
			r = r.next;
		}
		
		return "Last 10 recipes: " + answer;
	}

	@Override
	public String part2() {
		Scoreboard scoreboard = new Scoreboard();
		
		String lastSequence = "";
		String input = String.valueOf(this.input);
		while(!lastSequence.equals(input)) {
			
			int newScore = scoreboard.elf1.score + scoreboard.elf2.score;
			
			String s = String.valueOf(newScore);
			
			for(int i = 0; i < s.length(); i++) {
				scoreboard.appendRecipe(Integer.valueOf(s.substring(i, i+1)));
				
				lastSequence+=s.charAt(i);
				if(lastSequence.length()>input.length()) {
					lastSequence = lastSequence.substring(lastSequence.length()-input.length(), lastSequence.length());
				}
				
				if(lastSequence.equals(input)) {
					break;
				}
			}
			
			int score = scoreboard.elf1.score;
			for(int i = 0; i < score+1; i++) {
				scoreboard.elf1 = scoreboard.elf1.next;
			}
			
			score = scoreboard.elf2.score;
			for(int i = 0; i < score+1; i++) {
				scoreboard.elf2 = scoreboard.elf2.next;
			}
		}
				
		return ""+(scoreboard.recipeCount-input.length());
	}

	@Override
	public String getDayName() {
		return "--- Day 14: Chocolate Charts ---";
	}

}

class Scoreboard {
	
	Recipe root;
	Recipe elf1;
	Recipe elf2;
	
	int recipeCount = 0;
	
	public Scoreboard() {
		root = new Recipe(3);
		root.next = root;
		root.previous = root;
		recipeCount++;
		elf1 = root;
		elf2 = appendRecipe(7);
	}

	public void print() {
		Recipe current = root;
		StringBuilder builder = new StringBuilder();
		do {
			if(current==elf1) {
				builder.append("(").append(current.score).append(")");
			} else if (current == elf2) {
				builder.append("[").append(current.score).append("]");
			} else {
				builder.append(" ").append(current.score).append(" ");
			}
			current = current.next;
		} while(current!=root);
		
		System.out.println(builder.toString());
	}

	Recipe appendRecipe(int score) {
		Recipe r = new Recipe(score);
		r.next = root;
		r.previous = root.previous;
		root.previous.next = r;
		root.previous = r;
		recipeCount++;
		return r;
	}
	
}

class Recipe {
	final int score;
	Recipe next;
	Recipe previous;
	
	public Recipe(int score) {
		this.score = score;
	}
	
	@Override
	public String toString() {
		return ""+score;
	}
}