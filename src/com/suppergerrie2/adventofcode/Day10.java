package com.suppergerrie2.adventofcode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Day10 extends Day {

	int time = -1;
	
	@Override
	public String part1() {
		File file = new File("input/day10.txt");

		List<String> lines;
		try {
			lines = Files.readAllLines(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}

		List<Point> points = new ArrayList<>();

		for(String line : lines) {
			points.add(Point.fromString(line));
		}


		List<Point> closestPoints = new ArrayList<>(points);	
		int aMinX = Integer.MIN_VALUE;
		int aMaxX = Integer.MAX_VALUE;
		int aMinY = Integer.MIN_VALUE;
		int aMaxY = Integer.MAX_VALUE;
		
		while(true) {
			int minX = Integer.MAX_VALUE;
			int maxX = Integer.MIN_VALUE;
			int minY = Integer.MAX_VALUE;
			int maxY = Integer.MIN_VALUE;
			for(Point p : points) {
				p.Update();
				
				minX = Math.min(p.x, minX);
				maxX = Math.max(p.x, maxX);
				minY = Math.min(p.y, minY);
				maxY = Math.max(p.y, maxY);
			}
			
			time++;

			
			if(minX > aMinX && maxX < aMaxX && minY > aMinY && maxY < aMaxY) {
				aMinX = minX;
				aMaxX = maxX;
				aMinY = minY;
				aMaxY = maxY;
				
				closestPoints.clear();
				for(Point p : points) {
					closestPoints.add(p.copy());
				}
			} else {
				break;
			}
		}

		return printPoints(closestPoints);
	}

	String printPoints(List<Point> points) {
		int minX = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE;
		int minY = Integer.MAX_VALUE;
		int maxY = Integer.MIN_VALUE;
		for(Point p : points) {
			minX = Math.min(p.x, minX);
			maxX = Math.max(p.x, maxX);
			minY = Math.min(p.y, minY);
			maxY = Math.max(p.y, maxY);
		}

		List<Point> pointsCopy = new ArrayList<>(points);

		StringBuilder builder = new StringBuilder();
		
		for(int y = minY; y <= maxY; y++) {
			for(int x = minX; x <= maxX; x++) {
				boolean hasPoint = false;

				for(int i = 0; i < pointsCopy.size(); i++) {
					Point p = pointsCopy.get(i);
					if(p.x == x && p.y == y) {
						hasPoint = true;
						pointsCopy.remove(i);
						break;
					}
				}
				builder.append(hasPoint?"#":".");
			}
			builder.append("\n");
		}
		
		return builder.toString();
	}

	@Override
	public String part2() {
		time = -1;
		
		part1();
		
		return "Time: " + time;
	}

	@Override
	public String getDayName() {
		return "--- Day 10: The Stars Align ---";
	}

}

class Point {
	int x;
	int y;
	int xVell;
	int yVell;

	public Point(int x, int y, int xVell, int yVell) {
		this.x = x;
		this.y = y;
		this.xVell = xVell;
		this.yVell = yVell;
	}

	public Point() {
	}

	static Point fromString(String string) {
		String[] s = string.split("> ");

		String[] positions = s[0].split("=")[1].split(", ");
		String[] velocities = s[1].split("=")[1].split(", ");

		Point p = new Point();
		p.x = Integer.parseInt(positions[0].replaceAll("<", "").replaceAll(" ", ""));
		p.y = Integer.parseInt(positions[1].replaceAll(">", "").replaceAll(" ", ""));

		p.xVell = Integer.parseInt(velocities[0].replaceAll("<", "").replaceAll(" ", ""));
		p.yVell = Integer.parseInt(velocities[1].replaceAll(">", "").replaceAll(" ", ""));

		return p;
	}

	void Update() {
		this.x += xVell;
		this.y += yVell;
	}
	
	Point copy() {
		return new Point(x, y, xVell, yVell);
	}
}