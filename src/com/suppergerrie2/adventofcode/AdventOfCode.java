package com.suppergerrie2.adventofcode;

import java.util.ArrayList;
import java.util.List;

public class AdventOfCode {

	public static List<Day> days = new ArrayList<Day>();

	public static void main(String[] args) {
		days.add(new Day1());
		days.add(new Day2());
		days.add(new Day3());
		days.add(new Day4());
		days.add(new Day5());
		days.add(new Day6());
		
		for(Day day : days) {
			System.out.println(day.getDayName());
			printResults(day);
		}
	}

	static int measureTimeRunCount = 10;
	static void printResults(Day day) {
		System.out.println("Part 1: ");
		System.out.println(day.part1());

		long start = System.nanoTime();

		for(int i = 0; i < measureTimeRunCount; i++) day.part1(); 

		long duration = System.nanoTime()-start;
		System.out.println("Average duration of " + (duration/measureTimeRunCount)/1000000f + " ms");

		System.out.println("Part 2: ");
		System.out.println(day.part2());

		start = System.nanoTime();

		for(int i = 0; i < measureTimeRunCount; i++) day.part2(); 

		duration = System.nanoTime()-start;
		System.out.println("Average duration of " + (duration/measureTimeRunCount)/1000000f + " ms");
	}

}
