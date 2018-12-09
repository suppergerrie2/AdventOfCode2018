package com.suppergerrie2.adventofcode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Day8 extends Day {

	@Override
	public String part1() {
		File file = new File("input/day8.txt");

		List<String> lines;
		try {
			lines = Files.readAllLines(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}

		String[] numberStrings = lines.get(0).split(" ");

		Queue<Integer> numbers = new LinkedList<>();

		for(String s : numberStrings) {
			numbers.add(Integer.parseInt(s));
		}

		Node root = Node.parse(numbers);

		return "Total metadata: " + root.getTotalMetadata();
	}

	@Override
	public String part2() {
		File file = new File("input/day8.txt");

		List<String> lines;
		try {
			lines = Files.readAllLines(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}

		String[] numberStrings = lines.get(0).split(" ");

		Queue<Integer> numbers = new LinkedList<>();

		for(String s : numberStrings) {
			numbers.add(Integer.parseInt(s));
		}

		Node root = Node.parse(numbers);

		return "Root value: " + root.getValue();
	}

	@Override
	public String getDayName() {
		return "--- Day 8: Memory Maneuver ---";
	}

}

class Node {
	final int childrenCount;
	final int metadataCount;

	final Node[] children;
	final int[] metadata;
	
	public Node(int childrenCount, int metadataCount) {
		this.childrenCount = childrenCount;
		this.metadataCount = metadataCount;

		children = new Node[this.childrenCount];
		metadata = new int[this.metadataCount];
		
	}

	public int getTotalMetadata() {
		int total = 0;

		for(int i = 0; i < this.metadataCount; i++) {
			total+=metadata[i];
		}

		for(Node n : children) {
			total += n.getTotalMetadata();
		}

		return total;
	}

	public int getValue() {
		int value = 0;

		if(this.childrenCount == 0) {
			for(int i = 0; i < this.metadataCount; i++) {
				value+=this.metadata[i];
			}
		} else {
			for(int i = 0; i < this.metadataCount; i++) {
				int metaValue = this.metadata[i];
				if(metaValue>this.childrenCount) continue;
				
				value+=this.children[metaValue-1].getValue();
			}
		}

		return value;
	}

	public static Node parse(Queue<Integer> numbers) {
		int childrenCount = numbers.poll();
		int metadataCount = numbers.poll();
		Node n = new Node(childrenCount, metadataCount);

		for(int i = 0; i < childrenCount; i++) {
			n.children[i] = parse(numbers);
		}

		for(int i = 0; i < metadataCount; i++) {
			n.metadata[i] = numbers.poll();
		}

		return n;
	}

}
