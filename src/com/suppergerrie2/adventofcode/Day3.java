package com.suppergerrie2.adventofcode;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day3 extends Day {
	
	@Override
	public String part1() {
		File file = new File("input/day3.txt");

		List<String> lines;
		try {
			lines = Files.readAllLines(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		
		Set<Point> pointsSeen = new HashSet<>();
		Set<Point> overlap = new HashSet<>();
		
		for(String line : lines) {
			String[] idRectangle = line.split(" @ ");
			String[] posSize = idRectangle[1].split(": ");
			String[] stringPos = posSize[0].split(",");
			
			Point pos = new Point(Integer.parseInt(stringPos[0]), Integer.parseInt(stringPos[1]));
			int xSize = Integer.parseInt(posSize[1].split("x")[0]);
			int ySize = Integer.parseInt(posSize[1].split("x")[1]);
			
			for(int x = 0; x < xSize; x++) {
				for(int y = 0; y < ySize; y++) {
					Point p = new Point(pos.x + x, pos.y + y);
					
					if(pointsSeen.contains(p)) {
						if(!overlap.contains(p)) {
							overlap.add(p);
						}
					} else {
						pointsSeen.add(p);
					}
				}
			}
			
		}
		
		return "Overlapping area: " + overlap.size();
	}

	@Override
	public String part2() {
		File file = new File("input/day3.txt");

		List<String> lines;
		try {
			lines = Files.readAllLines(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}

		HashMap<Point, Integer> posToId = new HashMap<>();
		
		Set<Integer> validIds = new HashSet<>();
		
		for(String line : lines) {
			String[] idRectangle = line.split(" @ ");
			String[] posSize = idRectangle[1].split(": ");
			String[] stringPos = posSize[0].split(",");
			
			Point pos = new Point(Integer.parseInt(stringPos[0]), Integer.parseInt(stringPos[1]));
			int xSize = Integer.parseInt(posSize[1].split("x")[0]);
			int ySize = Integer.parseInt(posSize[1].split("x")[1]);
			int id = Integer.parseInt(idRectangle[0].substring(1));
			
			validIds.add(id);
			
			for(int x = 0; x < xSize; x++) {
				for(int y = 0; y < ySize; y++) {
					Point p = new Point(pos.x + x, pos.y + y);
					
					if(posToId.containsKey(p)) {
						validIds.remove(id);
						validIds.remove(posToId.get(p));
					}
					
					posToId.put(p, id);
				}
			}
			
		}
		
		int id = -1;
		if(validIds.size()!=1) {
			System.err.println("ERROR! Multiple answers found!");
		} else {
			for(int i : validIds) {
				id = i;
			}
		}
		
		return "Id is: " + id;	
	}

	@Override
	public String getDayName() {
		return "--- Day 3: No Matter How You Slice It ---";
	}

}
