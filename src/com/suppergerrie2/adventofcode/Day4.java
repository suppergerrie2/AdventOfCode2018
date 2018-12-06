package com.suppergerrie2.adventofcode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Day4 extends Day {

	@Override
	public String part1() {
		return doPart(true);
	}
	
	public String doPart(boolean part1) {
		File file = new File("input/day4.txt");

		List<String> lines;
		try {
			lines = Files.readAllLines(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}

		lines.sort(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				String date1String = o1.split("] ")[0].substring(1);
				String date2String = o2.split("] ")[0].substring(1);
				
				int month1 = Integer.parseInt(date1String.substring(5,7));
				int month2 = Integer.parseInt(date2String.substring(5,7));
				
				if(month1>month2) {
					return 1;
				} else if (month1<month2) {
					return -1;
				}

				int day1 = Integer.parseInt(date1String.substring(8,10));
				int day2 = Integer.parseInt(date2String.substring(8,10));
				
				if(day1>day2) {
					return 1;
				} else if (day1<day2) {
					return -1;
				}

				int hour1 = Integer.parseInt(date1String.substring(11,13));
				int hour2 = Integer.parseInt(date2String.substring(11,13));
				
				if(hour1>hour2) {
					return 1;
				} else if (hour1<hour2) {
					return -1;
				}				
				
				int minute1 = Integer.parseInt(date1String.substring(14,16));
				int minute2 = Integer.parseInt(date2String.substring(14,16));
				
				if(minute1>minute2) {
					return 1;
				} else if (minute1<minute2) {
					return -1;
				}
				
				return 0;
			}

			
		});
		
		int currentGuardID = -1;
		String asleepSince = "";
		HashMap<Integer, TimeSchedule> timeSleeping = new HashMap<>();
		for(String s : lines) {
			if(s.contains("Guard #")) {
				currentGuardID = Integer.parseInt(s.split(" ")[3].substring(1));
				asleepSince = "";
			} else if (s.contains("falls asleep")) {
				asleepSince = s.split(" ")[1];
				asleepSince = asleepSince.substring(0, asleepSince.length()-1);
			} else if(s.contains("wakes up")) {
				String wakeUpTime = s.split(" ")[1];
				if(!timeSleeping.containsKey(currentGuardID)) {
					timeSleeping.put(currentGuardID, new TimeSchedule());
				}
				
				timeSleeping.get(currentGuardID).addSleeping(asleepSince, wakeUpTime.replace("]", ""));
			}
			
		}
		
		int highestSleepTime = 0;
		int answer = -1;
		for(Integer id : timeSleeping.keySet()) {
			if(part1) {
				if(timeSleeping.get(id).getTotalTimeSleeping()>highestSleepTime) {
					highestSleepTime = timeSleeping.get(id).getTotalTimeSleeping();
					answer = timeSleeping.get(id).getMostSleptMinute() * id;
				}
			} else {
				if(timeSleeping.get(id).getMostAmountSleptInMinute()>highestSleepTime) {
					highestSleepTime = timeSleeping.get(id).getMostAmountSleptInMinute();
					answer = timeSleeping.get(id).getMostSleptMinute() * id;
				}
			}
		}
		
		return "Answer: " + answer;
	}

	@Override
	public String part2() {
		return doPart(false);
	}

	@Override
	public String getDayName() {
		return "--- Day 4: Repose Record ---";
	}

}

class TimeSchedule {
	
	HashMap<Integer, Integer> sleeping = new HashMap<>();

	public void addSleeping(String asleepSince, String wakeUpTime) {
		int startHour = Integer.parseInt(asleepSince.split(":")[0]);
		int startMinute = Integer.parseInt(asleepSince.split(":")[1]);
		
		int endHour = Integer.parseInt(wakeUpTime.split(":")[0]);
		int endMinute = Integer.parseInt(wakeUpTime.split(":")[1]);
		
		do {
			if(sleeping.containsKey(startHour*100+startMinute)) {
				sleeping.put(startHour*100+startMinute, sleeping.get(startHour*100+startMinute)+1);
			} else {
				sleeping.put(startHour*100+startMinute,1);
			}
			startMinute++;
			
			if(startMinute==60) {
				startMinute = 0;
				endHour++;
				
				if(endHour==24) {
					endHour = 0;
				}
			}
			
		} while(startHour!=endHour || startMinute != endMinute);
	}
	
	public int getMostAmountSleptInMinute() {
		int highestValue = 0;
		
		for(Integer time : sleeping.keySet()) {
			if(sleeping.get(time)>highestValue) {
				highestValue = sleeping.get(time);
			}
		}
		
		return highestValue;
	}

	int getTotalTimeSleeping() {
		int total = 0;
		for(int i : sleeping.keySet()) {
			total+=sleeping.get(i);
		}
		return total;
	}
	
	int getMostSleptMinute() {
		int highestKey = 0;
		int highestValue = 0;
		
		for(Integer time : sleeping.keySet()) {
			if(sleeping.get(time)>highestValue) {
				highestValue = sleeping.get(time);
				highestKey = time;
			}
		}
		
		return highestKey % 100;
	}
}