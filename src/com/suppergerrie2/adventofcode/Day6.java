package com.suppergerrie2.adventofcode;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day6 extends Day {

	boolean printAreaForPart1 = false;

	@Override
	public String part1() {
		File file = new File("input/day6.txt");

		List<String> lines;
		try {
			lines = Files.readAllLines(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}

		List<Point> coords = new ArrayList<Point>();
		Point minPoint = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
		Point maxPoint = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);

		for(String line : lines) {
			String[] xy = line.split(", ");
			Point p = new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]));
			coords.add(p);

			if(p.x < minPoint.x) {
				minPoint.x = p.x;
			} 

			if(p.x > maxPoint.x) {
				maxPoint.x = p.x;
			}

			if(p.y < minPoint.y) {
				minPoint.y = p.y;
			}

			if(p.y > maxPoint.y) {
				maxPoint.y = p.y;
			}
		}

		minPoint.x--;
		minPoint.y--;
		maxPoint.x++;
		maxPoint.y++;

		HashMap<Point, Integer> closestCoord = new HashMap<Point, Integer>();

		for(int y = minPoint.y; y <= maxPoint.y; y++) {
			for(int x = minPoint.x; x <= maxPoint.x; x++) {
				double smallestDistance = Double.MAX_VALUE;
				Point checkPoint = new Point(x,y);

				int closestCoordIndex = -1;

				for(int i = 0; i < coords.size(); i++) {
					Point p = coords.get(i);
					if(distance(p, checkPoint)<smallestDistance) {
						smallestDistance = distance(p, checkPoint);
						closestCoordIndex = i;
					} else if (distance(p, checkPoint) == smallestDistance) {
						closestCoordIndex = -1;
					}
				}

				if(printAreaForPart1) {
					if(closestCoordIndex==-1) {
						System.out.print(".");
					} else {
						System.out.print((char)('!'+closestCoordIndex));
					}
				}

				closestCoord.put(checkPoint, closestCoordIndex);
			}
			if(printAreaForPart1) System.out.println("");
		}

		Set<Integer> invalidPoints = new HashSet<>();
		invalidPoints.add(-1);
		for(int y = minPoint.y; y <= maxPoint.y; y++) {
			Point left = new Point(minPoint.x, y);
			Point right = new Point(maxPoint.x, y);

			if(!invalidPoints.contains(closestCoord.get(left))) {
				invalidPoints.add(closestCoord.get(left));
			}

			if(!invalidPoints.contains(closestCoord.get(right))) {
				invalidPoints.add(closestCoord.get(right));
			}
		}

		for(int x = minPoint.x; x <= maxPoint.x; x++) {
			Point top = new Point(x, minPoint.y);
			Point bottom = new Point(x, maxPoint.y);

			if(!invalidPoints.contains(closestCoord.get(top))) {
				invalidPoints.add(closestCoord.get(top));
			}

			if(!invalidPoints.contains(closestCoord.get(bottom))) {
				invalidPoints.add(closestCoord.get(bottom));
			}
		}

		HashMap<Integer, Integer> areaForCoord = new HashMap<>();

		int maxArea = 0;

		for(Point p : closestCoord.keySet()) {
			int coordIndex = closestCoord.get(p);
			if(invalidPoints.contains(coordIndex)) continue;

			int area = 0;
			if(areaForCoord.containsKey(coordIndex)) {
				area = areaForCoord.get(coordIndex);
			}
			area++;
			areaForCoord.put(coordIndex, area);

			maxArea = Math.max(maxArea, area);
		}

		return "Max area: " + maxArea;
	}

	double distance(Point a, Point b) {
		return Math.abs(b.x - a.x) + Math.abs(b.y - a.y);
	}

	@Override
	public String part2() {

		File file = new File("input/day6.txt");

		List<String> lines;
		try {
			lines = Files.readAllLines(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}

		List<Point> coords = new ArrayList<Point>();
		Point minPoint = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
		Point maxPoint = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);

		for(String line : lines) {
			String[] xy = line.split(", ");
			Point p = new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]));
			coords.add(p);

			if(p.x < minPoint.x) {
				minPoint.x = p.x;
			} 

			if(p.x > maxPoint.x) {
				maxPoint.x = p.x;
			}

			if(p.y < minPoint.y) {
				minPoint.y = p.y;
			}

			if(p.y > maxPoint.y) {
				maxPoint.y = p.y;
			}
		}

		minPoint.x--;
		minPoint.y--;
		maxPoint.x++;
		maxPoint.y++;

		int area = 0;

		for(int y = minPoint.y; y <= maxPoint.y; y++) {
			for(int x = minPoint.x; x <= maxPoint.x; x++) {
				double totalDistanceToCoord = 0;
				Point checkPoint = new Point(x,y);

				for(int i = 0; i < coords.size(); i++) {
					Point p = coords.get(i);
					totalDistanceToCoord += distance(p, checkPoint);
				}

				if(totalDistanceToCoord < 10000) {
					area++;
				}
			}
		}

		return "Area: " + area;
	}

	@Override
	public String getDayName() {
		return "--- Day 6: Chronal Coordinates ---";
	}

}
