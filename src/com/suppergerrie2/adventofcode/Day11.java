package com.suppergerrie2.adventofcode;

public class Day11 extends Day {

	final int input = 4455;
	int[][] fuelLevels = new int[300][300];

	@Override
	public String part1() {
		for(int x = 1; x <= 300; x++) {
			for(int y = 1; y <= 300; y++) {
				fuelLevels[x-1][y-1] = getFuelLevel(x,y);
			}
		}
		
		int maxFuelLevel = Integer.MIN_VALUE;
		int topLeftX = -1;
		int topLeftY = -1;
		for(int x = 1; x <= 300-2; x++) {
			for(int y = 1; y <= 300-2; y++) {
				if(getFuelLevel(x,y, 3)>maxFuelLevel) {
					topLeftX = x;
					topLeftY = y;
					maxFuelLevel = getFuelLevel(x,y, 3);
				}
			}
		}

		return "Top left corner: " + topLeftX + "," + topLeftY;
	}
	

	int getFuelLevel(int x, int y) {
		int rackID = x+10;
		int powerLevel = rackID * y;
		powerLevel+=input;
		powerLevel*=rackID;
		return (int) (powerLevel%1000)/100-5;
	}

	int getFuelLevel(int x, int y, int size) {
		int totalFuelLevel = 0;

		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				totalFuelLevel+=fuelLevels[x+i-1][y+j-1];
			}
		}

		return totalFuelLevel;
	}

	@Override
	public String part2() {
		for(int x = 1; x <= 300; x++) {
			for(int y = 1; y <= 300; y++) {
				fuelLevels[x-1][y-1] = getFuelLevel(x,y);
			}
		}
		
		int maxFuelLevel = Integer.MIN_VALUE;
		int topLeftX = -1;
		int topLeftY = -1;
		int squareSize = -1;

		for(int size = 1; size <= 300; size++) {
			for(int x = 1; x <= 300-size+1; x++) {
				for(int y = 1; y <= 300-size+1; y++) {
					if(getFuelLevel(x,y, size)>maxFuelLevel) {
						topLeftX = x;
						topLeftY = y;
						maxFuelLevel = getFuelLevel(x,y, size);
						squareSize = size;
					}
				}
			}
		}

		return "Top left corner wit size: " + topLeftX + "," + topLeftY + ","+squareSize;
	}

	@Override
	public String getDayName() {
		return "--- Day 11: Chronal Charge ---";
	}

}
