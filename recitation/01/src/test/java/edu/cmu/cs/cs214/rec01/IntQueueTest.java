package edu.cmu.cs.cs214.rec01;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

/**
 * TODO: Write detailed unit tests for the {@link LinkedIntQueue} and
 * {@link ArrayIntQueue} classes, as described in the handout. The
 * {@link ArrayIntQueue} class contains a few bugs. Use the tests you wrote for
 * the {@link LinkedIntQueue} class to test the {@link ArrayIntQueue}
 * implementation. Refine your tests to achieve 100% line coverage and use them
 * to determine and correct the bugs!
 * 
 * @author Alex Lockwood
 */
public class IntQueueTest {

	private IntQueue mQueue;

	/**
	 * Called before each test.
	 */
	@Before
	public void setUp() {
		// comment/uncomment these lines to test each class
		mQueue = new LinkedIntQueue();
		//mQueue = new ArrayIntQueue();
	}

	@Test
	public void testIsEmpty() {
		assertTrue(mQueue.isEmpty());
	}

	@Test
	public void testPeekEmptyQueue() {
		assertNull(mQueue.peek());
	}

	@Test
	public void testContent() throws IOException {
		ArrayList<Integer> correctResult = new ArrayList<Integer>();
		FileInputStream in = null;
		in = new FileInputStream("src/test/resources/data.txt");
		Scanner scanner = new Scanner(in);
		scanner.useDelimiter("\\s*fish\\s*");
		while (scanner.hasNext()) {
			int input = Integer.parseInt(scanner.next());
			correctResult.add(new Integer(input));
			System.out.println(input);
			mQueue.enqueue(input);
		}
		for (int i=0; i < correctResult.size(); i++) {
			assertEquals(mQueue.dequeue(), correctResult.get(i));
		}
	}
}
