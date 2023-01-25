package com.coderscampus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		try {
			// Do I actually wrap this whole thing in a try block using CountDownLatch?

			// Create an instance of Assignment8
			Assignment8 assignment8 = new Assignment8();

			// Use a ConcurrentHashMap to store the count of each number
			ConcurrentHashMap<Integer, Integer> countMap = new ConcurrentHashMap<>();

			// Create a CountDownLatch with a count of 1000
			CountDownLatch latch = new CountDownLatch(1000);

			// Create a list to store the threads
			List<Thread> threads = new ArrayList<>();

			try {
				// Start a for loop with i starting at 0 and ending at 999
				for (int i = 0; i < 1000; i++) {
					Thread t = new Thread(() -> {
						// Retrieve the list of numbers from the Assignment8 object
						List<Integer> numbers = assignment8.getNumbers();
						// Iterate through the numbers
						for (Integer number : numbers) {
							// If the number is not present in the map, add it with a count of 0
							countMap.putIfAbsent(number, 0);
							// If the number is present, increment its count
							countMap.computeIfPresent(number, (k, v) -> v + 1);
						}
						// Decrement the count of the latch
						latch.countDown();
					});
					// Add the thread to the list
					threads.add(t);
					// Start the thread
					t.start();
				}
				// Wait for all threads to complete
				latch.await();
				// Initialize a variable i to keep track of the current iteration
				int i = 0;
				// Get the size of the countMap
				int size = countMap.size();
				for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
					// If the current iteration is not the last one
					if (i < size - 1) {
						// Print the number and its count followed by a newline
						System.out.println(entry.getKey() + ": " + entry.getValue());
						// If the current iteration is the last one
					} else {
						// Print the number and its count without a newline
						System.out.print(entry.getKey() + ": " + entry.getValue());
					}
					// Increment i for the next iteration
					i++;
				}
				// Handle the exception
			} catch (InterruptedException e) {
				e.printStackTrace();
				// Close all the thread after its execution
			} finally {
				for (Thread t : threads) {
					t.join();
				}
			}
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}