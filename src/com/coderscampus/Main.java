package com.coderscampus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

				// Print the count of each number
				System.out.flush();
				for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
					// Print the number and its count
					System.out.println(entry.getKey() + ": " + entry.getValue());
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