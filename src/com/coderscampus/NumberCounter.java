package com.coderscampus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberCounter {

	public static Map<Integer, Integer> countNumbers(List<Integer> numbers) {
		// Create a HashMap to store the count of each number
		Map<Integer, Integer> countMap = new HashMap<>();

		// Iterate through the numbers
		for (Integer number : numbers) {
			// If the number is already present in the map, increment its count
			if (countMap.containsKey(number)) {
				countMap.put(number, countMap.get(number) + 1);
			}
			// If the number is not present, add it with a count of 1
			else {
				countMap.put(number, 1);
			}
		}

		// Return the count map
		return countMap;
	}

	// Method to print the count of each number in the map
	void printDigitCount(Map<Integer, Integer> countMap) {
		System.out.println("Count of each number in the array:");
		// Iterate through the entries in the map
		for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
			// Print the number and its count
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}
}