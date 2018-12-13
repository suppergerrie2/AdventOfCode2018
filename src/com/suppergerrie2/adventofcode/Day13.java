package com.suppergerrie2.adventofcode;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.suppergerrie2.adventofcode.Day13.Direction;

public class Day13 extends Day {

	enum Direction { UP(0,-1), DOWN(0,1), LEFT(-1,0), RIGHT(1,0);

		int offsetX;
		int offsetY;
		private Direction(int offsetX, int offsetY) {
			this.offsetX = offsetX;
			this.offsetY = offsetY;
		}

		public Point getPosition(Point p) {
			return new Point(p.x + offsetX, p.y + offsetY);
		}

		public Direction rotateLeft() {
			switch(this) {
			case DOWN:
				return Direction.RIGHT;
			case LEFT:
				return Direction.DOWN;
			case RIGHT:
				return Direction.UP;
			case UP:
				return Direction.LEFT;
			}
			return null;
		}

		public Direction rotateRight() {
			switch(this) {
			case DOWN:
				return Direction.LEFT;
			case LEFT:
				return Direction.UP;
			case RIGHT:
				return Direction.DOWN;
			case UP:
				return Direction.RIGHT;
			}
			return null;
		} 
	};

	@Override
	public String part1() {
		File file = new File("input/day13.txt");

		List<String> lines;
		try {
			lines = Files.readAllLines(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}

		List<Car> cars = new ArrayList<Car>();

		for(int y = 0; y < lines.size(); y++) {
			String line = lines.get(y);
			for(int x = 0; x < line.length(); x++) {
				if(line.charAt(x)==' ') continue;

				switch(line.charAt(x)) {
				case '<':
					cars.add(new Car(new Point(x,y), Direction.LEFT));
					break;
				case '>':
					cars.add(new Car(new Point(x,y), Direction.RIGHT));
					break;
				case 'v':
					cars.add(new Car(new Point(x,y), Direction.DOWN));
					break;
				case '^':
					cars.add(new Car(new Point(x,y), Direction.UP));
					break;
				}
			}
		}

		while(true) { 
			for(int y = 0; y < lines.size(); y++) {
				String line = lines.get(y);
				for(int x = 0; x < line.length(); x++) {
					boolean isCar = false;
//					char carC = 'c';
					for(Car car : cars) {
						if(car.p.x == x && car.p.y == y) {
							isCar = true;
//							carC = car.getChar();
							break;
						}
					}

					if(isCar) {
//						System.out.print(carC);
					} else {
						char c = line.charAt(x);
						if(c=='<'||c=='>') {
							c='-';
						} else if (c=='v'||c=='^') {
							c='|';
						}
//						System.out.print(c);
					}
				}
//				System.out.println();
			}
			

			cars.sort((car1, car2)->{
				if(car1.p.y < car2.p.y) {
					return 1;
				} else if (car1.p.y > car2.p.y) {
					return -1;
				} else if (car1.p.x > car2.p.x) {
					return 1;
				} else if (car1.p.x < car2.p.x) {
					return -1;
				}

				return 0;
			});
			
			for(Car car : cars) {
				car.move(lines);
				
				for(Car car2 : cars) {
					if(car2==car) continue;
					if(car2.p.equals(car.p)) return "Collision at { " + (car.p.x) + "," + car.p.y +" }";
				}
			}
		}
	}

	@Override
	public String part2() {
		File file = new File("input/day13.txt");

		List<String> lines;
		try {
			lines = Files.readAllLines(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}

		List<Car> cars = new ArrayList<Car>();

		for(int y = 0; y < lines.size(); y++) {
			String line = lines.get(y);
			for(int x = 0; x < line.length(); x++) {
				if(line.charAt(x)==' ') continue;

				switch(line.charAt(x)) {
				case '<':
					cars.add(new Car(new Point(x,y), Direction.LEFT));
					break;
				case '>':
					cars.add(new Car(new Point(x,y), Direction.RIGHT));
					break;
				case 'v':
					cars.add(new Car(new Point(x,y), Direction.DOWN));
					break;
				case '^':
					cars.add(new Car(new Point(x,y), Direction.UP));
					break;
				}
			}
		}

		while(true) { 
			for(int y = 0; y < lines.size(); y++) {
				String line = lines.get(y);
				for(int x = 0; x < line.length(); x++) {
					boolean isCar = false;
//					char carC = 'c';
					for(Car car : cars) {
						if(car.p.x == x && car.p.y == y) {
							isCar = true;
//							carC = car.getChar();
							break;
						}
					}

					if(isCar) {
//						System.out.print(carC);
					} else {
						char c = line.charAt(x);
						if(c=='<'||c=='>') {
							c='-';
						} else if (c=='v'||c=='^') {
							c='|';
						}
//						System.out.print(c);
					}
				}
//				System.out.println();
			}
			
			if(cars.size()==1) {
				return "Final car at: { " + (cars.get(0).p.x) + "," + cars.get(0).p.y +" }";
			}
			cars.sort((car1, car2)->{
				if(car1.p.y < car2.p.y) {
					return 1;
				} else if (car1.p.y > car2.p.y) {
					return -1;
				} else if (car1.p.x > car2.p.x) {
					return 1;
				} else if (car1.p.x < car2.p.x) {
					return -1;
				}

				return 0;
			});
			List<Car> toRemove = new ArrayList<Car>();
			for(Car car : cars) {
				if(car.p.x==-1) continue;
				
				car.move(lines);
				
				for(Car car2 : cars) {
					if(car2==car) continue;
					if(car2.p.equals(car.p)) {
						toRemove.add(car);
						toRemove.add(car2);
						car.p = car2.p = new Point(-1,-1);
					}
				}
			}
			cars.removeAll(toRemove);
		}
	}

	@Override
	public String getDayName() {
		return "--- Day 13: Mine Cart Madness ---";
	}

}

class Car {
	Direction dir;
	Point p;
	int intersectionCounter = 0;

	public Car(Point p, Direction d) {
		this.p = p;
		this.dir = d;
	}

	public char getChar() {
		switch(dir) {
		case DOWN:
			return 'v';
		case LEFT:
			return '<';
		case RIGHT:
			return '>';
		case UP:
			return '^';
		default:
			return 'c';
		}
	}

	public void move(List<String> lines) {
		Point newPos = dir.getPosition(p);

		String line = lines.get(newPos.y);
		char c = line.charAt(newPos.x);

		dir = newDirection(c);
		p = newPos;
	}

	Direction newDirection(char c) {
		switch(c) {
		case '+':
			intersectionCounter++;
			if(intersectionCounter>3) intersectionCounter = 1;
			
			if(intersectionCounter==1) {
				return dir.rotateLeft();
			} else if (intersectionCounter == 2) {
				return dir;
			} else if (intersectionCounter == 3) {
				return dir.rotateRight();
			}
			
			break;
		case '-':
			break;
		case '|':
			break;
		case '\\':
			if(dir == Direction.UP) {
				return Direction.LEFT;
			} else if(dir == Direction.RIGHT) {
				return Direction.DOWN;
			} else if (dir == Direction.DOWN) {
				return Direction.RIGHT;
			} else if (dir == Direction.LEFT) {
				return Direction.UP;
			}
			break;
		case '/':
			if(dir == Direction.UP) {
				return Direction.RIGHT;
			} else if(dir == Direction.RIGHT) {
				return Direction.UP;
			} else if (dir == Direction.DOWN) {
				return Direction.LEFT;
			} else if (dir == Direction.LEFT) {
				return Direction.DOWN;
			}
			break;
		}

		return dir;
	}
}