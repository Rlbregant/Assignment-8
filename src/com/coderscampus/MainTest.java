package com.coderscampus;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

// ByteArrayOutputStream captures ALL the output...
public class MainTest {
	@Test
	public void testMain() throws InterruptedException {
		// create a ByteArrayOutputStream to capture the output of the main method
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		// set the System.out PrintStream to the ByteArrayOutputStream
		System.setOut(ps);

		// create a thread to run the main method
		Thread mainThread = new Thread(() -> {
			try {
				Main.main(new String[] {});
			} catch (InterruptedException e) {
				// handle exception
			}
		});
		mainThread.start();
		// join the thread to wait for it to finish
		mainThread.join();

		// reset the System.out PrintStream back to the console
		System.setOut(System.out);

		// get the output from the ByteArrayOutputStream and convert it to a string
		String output = baos.toString();

		// assert that the output is as expected
		assertEquals(
				"0: 66491 "
				+ "1: 66404 "
				+ "2: 66818 "
				+ "3: 66768 "
				+ "4: 66671 "
				+ "5: 66397 "
				+ "6: 66512 "
				+ "7: 66564 "
				+ "8: 67454 "
				+ "9: 66732 "
				+ "10: 66628 "
				+ "11: 66688 "
				+ "12: 66578 "
				+ "13: 66699 "
				+ "14: 66596" ,
				output);
	}
}