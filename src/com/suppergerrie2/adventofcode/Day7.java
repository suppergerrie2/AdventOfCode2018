package com.suppergerrie2.adventofcode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day7 extends Day {

	
	@Override
	public String part1() {
		File file = new File("input/day7.txt");

		List<String> lines;
		try {
			lines = Files.readAllLines(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		
		List<Step> steps = new ArrayList<Step>();
		
		for(String line : lines) {
			String dependencyName = line.substring(5,6);
			String stepName = line.substring(36,37);
		
			Step dependency = null;
			Step dependent = null;
			for(Step step : steps) {
				if(step.name.equals(dependencyName)) {
					dependency = step;
				} else if(step.name.equals(stepName)) {
					dependent = step;
				}
			}
			
			if(dependency == null) {
				dependency = new Step(dependencyName);
				steps.add(dependency);
			}
			if(dependent == null) {
				dependent = new Step(stepName);
				steps.add(dependent);
			}
			
			dependent.addDependency(dependency);
		}
		
		Collections.sort(steps);
		
		boolean done = false;
		String answer = "";
		do {
			done = true;
			for(Step step : steps) {
				if(!step.isDone&&step.canBeDone()) {
					answer+=step.name;
					step.isDone = true;
					done = false;
					break;
				}
				
				if(!step.isDone) {
					done = false;
				}
			}
			
		} while(!done);
		
		return answer;
	}

	@Override
	public String part2() {
		File file = new File("input/day7.txt");

		List<String> lines;
		try {
			lines = Files.readAllLines(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		
		List<Step> steps = new ArrayList<Step>();
		
		for(String line : lines) {
			String dependencyName = line.substring(5,6);
			String stepName = line.substring(36,37);
		
			Step dependency = null;
			Step dependent = null;
			for(Step step : steps) {
				if(step.name.equals(dependencyName)) {
					dependency = step;
				} else if(step.name.equals(stepName)) {
					dependent = step;
				}
			}
			
			if(dependency == null) {
				dependency = new Step(dependencyName);
				steps.add(dependency);
			}
			if(dependent == null) {
				dependent = new Step(stepName);
				steps.add(dependent);
			}
			
			dependent.addDependency(dependency);
		}
		
		Collections.sort(steps);
		
		boolean done = false;
		Set<Step> inWork = new HashSet<Step>();
		
		int totalTimeTaken = -1;
		
		do {
			done = true;
			for(Step step : steps) {
				
				if(inWork.size()<5) {
					if(step.canBeDone()&&!inWork.contains(step)) {
						done = false;
						inWork.add(step);
						continue;
					}
				}
				
				if(!step.isDone) {
					done = false;
				}
			}
			
			for(Step step : inWork) {
				step.timeLeft--;
				if(step.timeLeft==0) {
					step.isDone = true;
				}
			}
			
			inWork.removeIf((s)->{
				return s.timeLeft==0;
			});
			
			steps.removeIf((s)->s.timeLeft<=0);
			totalTimeTaken++;
			
		} while(!done);

		return "Time taken: " + totalTimeTaken;
	}

	@Override
	public String getDayName() {
		return "--- Day 7: The Sum of Its Parts ---";
	}

}

class Step implements Comparable<Step> {
	final String name;
	Set<Step> dependencies = new HashSet<>();
	boolean isDone = false;
	
	int timeLeft = -1;
	
	public Step(String name) {
		this.name = name;
		this.timeLeft = (60+(name.charAt(0)-64));
	}
	
	public void addDependency(Step step) {
		this.dependencies.add(step);
	}
	
	boolean canBeDone() {
		for(Step step : dependencies) {
			if(!step.isDone||!step.canBeDone()) return false;
		}

		return true;
	}

	@Override
	public int compareTo(Step o) {
		return name.compareTo(o.name);
	}
}
