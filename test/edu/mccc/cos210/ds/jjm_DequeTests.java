package edu.mccc.cos210.ds;

import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import edu.mccc.cos210.MyClass;
import edu.mccc.cos210.MySubClass;

public class jjm_DequeTests {
	private static StringBuilder report = new StringBuilder();
	
	@Rule
	public TestWatcher watchman = new TestWatcher() {
		@Override
	    protected void failed(Throwable t, Description description) {
	        report.append("  FAILURE: ").append(description.getMethodName()).append("\n");
	    }
	    @Override
	    protected void succeeded(Description description) {
	        report.append("  Success: ").append(description.getMethodName()).append("\n");
	    }
	};
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
        report.append("\nTesting: ").append(Deque.class.getSimpleName()).append("\n");
	}
	
	@AfterClass
	public static void tearDownClass() {
	    System.out.println(report.toString());
	}
	
	//Tests
	@Test
	public void enqueueingFirst_peekingFirst() {
		final int TEST_SIZE = 10;
		
		IDeque<Integer> testDeque = new Deque<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testDeque.enqueueFirst(i);
			assertEquals(true, testDeque.peekFirst().equals(i));
		}
	}
	
	@Test
	public void enqueueingFirst_peekingLast() {
		final int TEST_SIZE = 10;
		final int INITIAL = 0;
		
		IDeque<Integer> testDeque = new Deque<>();
		for (int i = INITIAL; i < TEST_SIZE + INITIAL; i++) {
			testDeque.enqueueFirst(i);
			assertEquals(true, testDeque.peekLast().equals(INITIAL));
		}
	}
	
	@Test
	public void enqueueingLast_peekingFirst() {
		final int TEST_SIZE = 10;
		final int INITIAL = 0;
		
		IDeque<Integer> testDeque = new Deque<>();
		for (int i = INITIAL; i < TEST_SIZE; i++) {
			testDeque.enqueueLast(i);
			assertEquals(true, testDeque.peekFirst().equals(INITIAL));
		}
	}

	@Test
	public void enqueueingLast_peekingLast() {
		final int TEST_SIZE = 10;
		
		IDeque<Integer> testDeque = new Deque<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testDeque.enqueueLast(i);
			assertEquals(true, testDeque.peekLast().equals(i));
		}
	}

	@Test
	public void dequeueingFirst_enqueueingFirst() {
		final int TEST_SIZE = 10;
		
		IDeque<Integer> testDeque = new Deque<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testDeque.enqueueFirst(i);
		}
		for (int i = 0; i < TEST_SIZE; i++) {
			assertEquals(true, testDeque.dequeueFirst().equals((TEST_SIZE - 1) - i));
		}
	}

	@Test
	public void dequeueingFirst_enqueueingLast() {
		final int TEST_SIZE = 10;
		
		IDeque<Integer> testDeque = new Deque<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testDeque.enqueueLast(i);
		}
		for (int i = 0; i < TEST_SIZE; i++) {
			assertEquals(true, testDeque.dequeueFirst().equals(i));
		}
	}

	@Test
	public void dequeueingLast_enqueueingFirst() {
		final int TEST_SIZE = 10;
		
		IDeque<Integer> testDeque = new Deque<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testDeque.enqueueFirst(i);
		}
		for (int i = 0; i < TEST_SIZE; i++) {
			assertEquals(true, testDeque.dequeueLast().equals(i));
		}
	}

	@Test
	public void dequeueingLast_enqueueingLast() {
		final int TEST_SIZE = 10;
		
		IDeque<Integer> testDeque = new Deque<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testDeque.enqueueLast(i);
		}
		for (int i = 0; i < TEST_SIZE; i++) {
			assertEquals(true, testDeque.dequeueLast().equals((TEST_SIZE - 1) - i));
		}
	}

	@Test
	public void startsEmpty() {
		IDeque<Integer> testDeque = new Deque<>();
		assertEquals(true, testDeque.isEmpty());
	}

		@Test
		public void doesFill_enqueueingFirst() {
			final int TEST_SIZE = 10;
			
			IDeque<Integer> testDeque = new Deque<>();
			for (int i = 0; i < TEST_SIZE; i++) {
				testDeque.enqueueFirst(i);
				assertEquals(false, testDeque.isEmpty());
			}
		}

		@Test
		public void doesFill_enqueueingLast() {
			final int TEST_SIZE = 10;
			
			IDeque<Integer> testDeque = new Deque<>();
			for (int i = 0; i < TEST_SIZE; i++) {
				testDeque.enqueueLast(i);
				assertEquals(false, testDeque.isEmpty());
			}
		}

	@Test
	public void doesEmpty_dequeueingFirst() {
		final int TEST_SIZE = 10;
		
		IDeque<Integer> testDeque = new Deque<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testDeque.enqueueFirst(i);
		}
		assertEquals(false, testDeque.isEmpty());
		for (int i = 0; i < TEST_SIZE; i++) {
			testDeque.dequeueFirst();
		}
		assertEquals(true, testDeque.isEmpty());
	}

	@Test
	public void doesEmpty_dequeueingLast() {
		final int TEST_SIZE = 10;
		
		IDeque<Integer> testDeque = new Deque<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testDeque.enqueueFirst(i);
		}
		assertEquals(false, testDeque.isEmpty());
		for (int i = 0; i < TEST_SIZE; i++) {
			testDeque.dequeueLast();
		}
		assertEquals(true, testDeque.isEmpty());
	}

	@Test(expected=NoSuchElementException.class)
	public void peekingFirstEmpty() {
		IDeque<Integer> testDeque = new Deque<>();
		testDeque.peekFirst();
	}

	@Test(expected=NoSuchElementException.class)
	public void peekingLastEmpty() {
		IDeque<Integer> testDeque = new Deque<>();
		testDeque.peekLast();
	}

	@Test(expected=NoSuchElementException.class)
	public void dequeueingFirstEmpty() {
		IDeque<Integer> testDeque = new Deque<>();
		testDeque.dequeueFirst();
	}

	@Test(expected=NoSuchElementException.class)
	public void dequeueingLastEmpty() {
		IDeque<Integer> testDeque = new Deque<>();
		testDeque.dequeueLast();
	}

	@Test
	public void sizing_enqueueingFirst() {
		final int TEST_SIZE = 10;
		
		IDeque<Integer> testDeque = new Deque<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testDeque.enqueueFirst(i);
			assertEquals(i + 1, testDeque.getSize());
		}
	}

	@Test
	public void sizing_empty() {
		
		IDeque<Integer> testDeque = new Deque<>();
		assertEquals(0, testDeque.getSize());
	}

	@Test
	public void sizing_enqueueingLast() {
		final int TEST_SIZE = 10;
		
		IDeque<Integer> testDeque = new Deque<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testDeque.enqueueLast(i);
			assertEquals(i + 1, testDeque.getSize());
		}
	}

	@Test
	public void sizing_dequeueingFirst() {
		final int TEST_SIZE = 10;
		
		IDeque<Integer> testDeque = new Deque<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testDeque.enqueueFirst(i);
		}
		for (int i = 0; i < TEST_SIZE; i++) {
			testDeque.dequeueFirst();
			assertEquals(TEST_SIZE - (i + 1), testDeque.getSize());
		}
	}

	@Test
	public void sizing_dequeueingLast() {
		final int TEST_SIZE = 10;
		
		IDeque<Integer> testDeque = new Deque<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testDeque.enqueueFirst(i);
		}
		for (int i = 0; i < TEST_SIZE; i++) {
			testDeque.dequeueLast();
			assertEquals(TEST_SIZE - (i + 1), testDeque.getSize());
		}
	}

	@Test
	public void integrity_enqueueingAlternating_compareToClone() {
		final int TEST_SIZE = 30;
		
		IDeque<Integer> testDeque = new Deque<>();
		IDeque<Integer> compareToDeque = new Deque<>();
		boolean flipflop = false;
		for (int i = 0; i < TEST_SIZE; i++) {
			if (flipflop) {
				testDeque.enqueueFirst(i);
				compareToDeque.enqueueFirst(i);
			} else {
				testDeque.enqueueLast(i);
				compareToDeque.enqueueLast(i);
			}
			flipflop = !flipflop;
		}
		for (int i = 0; i > TEST_SIZE; i++) {
			assertEquals(true, testDeque.dequeueFirst().equals(compareToDeque.dequeueFirst()));
		}
	}

	@Test
	public void integrity_enqueueingAlternating_compareToSB() {
		final int TEST_SIZE = 50;
		
		IDeque<Integer> testDeque = new Deque<>();
		StringBuilder testDequeOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		boolean flipflop = false;
		for (int i = 0; i < TEST_SIZE; i++) {
			if (flipflop) {
				testDeque.enqueueFirst(i);
				testDequeOutput.insert(0, i);
			} else {
				testDeque.enqueueLast(i);
				testDequeOutput.append(i);
			}
			flipflop = !flipflop;
		}
		for (int i = 0; i < TEST_SIZE; i++) {
			compareToSB.append(testDeque.dequeueFirst());
		}
		assertEquals(true, testDequeOutput.toString().equals(compareToSB.toString()));
	}

	@Test
	public void makeTypeInt() {
		final int ENTRY_1 = 5;
		final int ENTRY_2 = 10;
		final int ENTRY_3 = 15;
		final int ENTRY_4 = 20;
		IDeque<Integer> testDeque = new Deque<>();
		IDeque<Integer> compareToDeque = new Deque<>();
		
		assertEquals(true, testDeque.isEmpty());
		assertEquals(0, testDeque.getSize());
		
		testDeque.enqueueFirst(ENTRY_1);
		assertEquals(false, testDeque.isEmpty());
		assertEquals(1, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_1));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_1));
		compareToDeque.enqueueFirst(ENTRY_1);
		
		testDeque.enqueueLast(ENTRY_2);
		assertEquals(false, testDeque.isEmpty());
		assertEquals(2, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_1));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_2));
		compareToDeque.enqueueLast(ENTRY_2);
		
		testDeque.enqueueFirst(ENTRY_3);
		assertEquals(false, testDeque.isEmpty());
		assertEquals(3, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_3));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_2));
		compareToDeque.enqueueFirst(ENTRY_3);
		
		testDeque.enqueueLast(ENTRY_4);
		assertEquals(false, testDeque.isEmpty());
		assertEquals(4, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_3));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_4));
		compareToDeque.enqueueLast(ENTRY_4);

		assertEquals(true, testDeque.dequeueFirst().equals(ENTRY_3));
		assertEquals(false, testDeque.isEmpty());
		assertEquals(3, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_1));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_4));
		assertEquals(true, compareToDeque.dequeueFirst().equals(ENTRY_3));
		
		assertEquals(true, testDeque.dequeueLast().equals(ENTRY_4));
		assertEquals(false, testDeque.isEmpty());
		assertEquals(2, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_1));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_2));
		assertEquals(true, compareToDeque.dequeueLast().equals(ENTRY_4));
		
		assertEquals(true, testDeque.dequeueFirst().equals(ENTRY_1));
		assertEquals(false, testDeque.isEmpty());
		assertEquals(1, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_2));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_2));
		assertEquals(true, compareToDeque.dequeueFirst().equals(ENTRY_1));
		
		assertEquals(true, testDeque.dequeueLast().equals(ENTRY_2));
		assertEquals(true, testDeque.isEmpty());
		assertEquals(0, testDeque.getSize());
		assertEquals(true, compareToDeque.dequeueLast().equals(ENTRY_2));
	}

	@Test
	public void makeTypeDouble() {
		final double ENTRY_1 = 5.0;
		final double ENTRY_2 = 10.0;
		final double ENTRY_3 = 15.0;
		final double ENTRY_4 = 20.0;
		IDeque<Double> testDeque = new Deque<>();
		IDeque<Double> compareToDeque = new Deque<>();
		
		assertEquals(true, testDeque.isEmpty());
		assertEquals(0, testDeque.getSize());
		
		testDeque.enqueueFirst(ENTRY_1);
		assertEquals(false, testDeque.isEmpty());
		assertEquals(1, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_1));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_1));
		compareToDeque.enqueueFirst(ENTRY_1);
		
		testDeque.enqueueLast(ENTRY_2);
		assertEquals(false, testDeque.isEmpty());
		assertEquals(2, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_1));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_2));
		compareToDeque.enqueueLast(ENTRY_2);
		
		testDeque.enqueueFirst(ENTRY_3);
		assertEquals(false, testDeque.isEmpty());
		assertEquals(3, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_3));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_2));
		compareToDeque.enqueueFirst(ENTRY_3);
		
		testDeque.enqueueLast(ENTRY_4);
		assertEquals(false, testDeque.isEmpty());
		assertEquals(4, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_3));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_4));
		compareToDeque.enqueueLast(ENTRY_4);

		assertEquals(true, testDeque.dequeueFirst().equals(ENTRY_3));
		assertEquals(false, testDeque.isEmpty());
		assertEquals(3, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_1));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_4));
		assertEquals(true, compareToDeque.dequeueFirst().equals(ENTRY_3));
		
		assertEquals(true, testDeque.dequeueLast().equals(ENTRY_4));
		assertEquals(false, testDeque.isEmpty());
		assertEquals(2, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_1));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_2));
		assertEquals(true, compareToDeque.dequeueLast().equals(ENTRY_4));
		
		assertEquals(true, testDeque.dequeueFirst().equals(ENTRY_1));
		assertEquals(false, testDeque.isEmpty());
		assertEquals(1, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_2));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_2));
		assertEquals(true, compareToDeque.dequeueFirst().equals(ENTRY_1));
		
		assertEquals(true, testDeque.dequeueLast().equals(ENTRY_2));
		assertEquals(true, testDeque.isEmpty());
		assertEquals(0, testDeque.getSize());
		assertEquals(true, compareToDeque.dequeueLast().equals(ENTRY_2));
	}

	@Test
	public void makeTypeBoolean() {
		final boolean ENTRY_1 = true;
		final boolean ENTRY_2 = false;
		final boolean ENTRY_3 = false;
		final boolean ENTRY_4 = true;
		IDeque<Boolean> testDeque = new Deque<>();
		IDeque<Boolean> compareToDeque = new Deque<>();
		
		assertEquals(true, testDeque.isEmpty());
		assertEquals(0, testDeque.getSize());
		
		testDeque.enqueueFirst(ENTRY_1);
		assertEquals(false, testDeque.isEmpty());
		assertEquals(1, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_1));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_1));
		compareToDeque.enqueueFirst(ENTRY_1);
		
		testDeque.enqueueLast(ENTRY_2);
		assertEquals(false, testDeque.isEmpty());
		assertEquals(2, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_1));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_2));
		compareToDeque.enqueueLast(ENTRY_2);
		
		testDeque.enqueueFirst(ENTRY_3);
		assertEquals(false, testDeque.isEmpty());
		assertEquals(3, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_3));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_2));
		compareToDeque.enqueueFirst(ENTRY_3);
		
		testDeque.enqueueLast(ENTRY_4);
		assertEquals(false, testDeque.isEmpty());
		assertEquals(4, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_3));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_4));
		compareToDeque.enqueueLast(ENTRY_4);

		assertEquals(true, testDeque.dequeueFirst().equals(ENTRY_3));
		assertEquals(false, testDeque.isEmpty());
		assertEquals(3, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_1));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_4));
		assertEquals(true, compareToDeque.dequeueFirst().equals(ENTRY_3));
		
		assertEquals(true, testDeque.dequeueLast().equals(ENTRY_4));
		assertEquals(false, testDeque.isEmpty());
		assertEquals(2, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_1));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_2));
		assertEquals(true, compareToDeque.dequeueLast().equals(ENTRY_4));
		
		assertEquals(true, testDeque.dequeueFirst().equals(ENTRY_1));
		assertEquals(false, testDeque.isEmpty());
		assertEquals(1, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_2));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_2));
		assertEquals(true, compareToDeque.dequeueFirst().equals(ENTRY_1));
		
		assertEquals(true, testDeque.dequeueLast().equals(ENTRY_2));
		assertEquals(true, testDeque.isEmpty());
		assertEquals(0, testDeque.getSize());
		assertEquals(true, compareToDeque.dequeueLast().equals(ENTRY_2));
	}

	@Test
	public void makeTypeChar() {
		final char ENTRY_1 = 'h';
		final char ENTRY_2 = 'e';
		final char ENTRY_3 = 'l';
		final char ENTRY_4 = 'p';
		IDeque<Character> testDeque = new Deque<>();
		IDeque<Character> compareToDeque = new Deque<>();
		
		assertEquals(true, testDeque.isEmpty());
		assertEquals(0, testDeque.getSize());
		
		testDeque.enqueueFirst(ENTRY_1);
		assertEquals(false, testDeque.isEmpty());
		assertEquals(1, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_1));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_1));
		compareToDeque.enqueueFirst(ENTRY_1);
		
		testDeque.enqueueLast(ENTRY_2);
		assertEquals(false, testDeque.isEmpty());
		assertEquals(2, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_1));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_2));
		compareToDeque.enqueueLast(ENTRY_2);
		
		testDeque.enqueueFirst(ENTRY_3);
		assertEquals(false, testDeque.isEmpty());
		assertEquals(3, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_3));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_2));
		compareToDeque.enqueueFirst(ENTRY_3);
		
		testDeque.enqueueLast(ENTRY_4);
		assertEquals(false, testDeque.isEmpty());
		assertEquals(4, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_3));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_4));
		compareToDeque.enqueueLast(ENTRY_4);

		assertEquals(true, testDeque.dequeueFirst().equals(ENTRY_3));
		assertEquals(false, testDeque.isEmpty());
		assertEquals(3, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_1));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_4));
		assertEquals(true, compareToDeque.dequeueFirst().equals(ENTRY_3));
		
		assertEquals(true, testDeque.dequeueLast().equals(ENTRY_4));
		assertEquals(false, testDeque.isEmpty());
		assertEquals(2, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_1));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_2));
		assertEquals(true, compareToDeque.dequeueLast().equals(ENTRY_4));
		
		assertEquals(true, testDeque.dequeueFirst().equals(ENTRY_1));
		assertEquals(false, testDeque.isEmpty());
		assertEquals(1, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_2));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_2));
		assertEquals(true, compareToDeque.dequeueFirst().equals(ENTRY_1));
		
		assertEquals(true, testDeque.dequeueLast().equals(ENTRY_2));
		assertEquals(true, testDeque.isEmpty());
		assertEquals(0, testDeque.getSize());
		assertEquals(true, compareToDeque.dequeueLast().equals(ENTRY_2));
	}

	@Test
	public void makeTypeString() {
		final String ENTRY_1 = "this";
		final String ENTRY_2 = "isnt";
		final String ENTRY_3 = "the";
		final String ENTRY_4 = "end";
		IDeque<String> testDeque = new Deque<>();
		IDeque<String> compareToDeque = new Deque<>();
		
		assertEquals(true, testDeque.isEmpty());
		assertEquals(0, testDeque.getSize());
		
		testDeque.enqueueFirst(ENTRY_1);
		assertEquals(false, testDeque.isEmpty());
		assertEquals(1, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_1));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_1));
		compareToDeque.enqueueFirst(ENTRY_1);
		
		testDeque.enqueueLast(ENTRY_2);
		assertEquals(false, testDeque.isEmpty());
		assertEquals(2, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_1));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_2));
		compareToDeque.enqueueLast(ENTRY_2);
		
		testDeque.enqueueFirst(ENTRY_3);
		assertEquals(false, testDeque.isEmpty());
		assertEquals(3, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_3));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_2));
		compareToDeque.enqueueFirst(ENTRY_3);
		
		testDeque.enqueueLast(ENTRY_4);
		assertEquals(false, testDeque.isEmpty());
		assertEquals(4, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_3));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_4));
		compareToDeque.enqueueLast(ENTRY_4);

		assertEquals(true, testDeque.dequeueFirst().equals(ENTRY_3));
		assertEquals(false, testDeque.isEmpty());
		assertEquals(3, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_1));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_4));
		assertEquals(true, compareToDeque.dequeueFirst().equals(ENTRY_3));
		
		assertEquals(true, testDeque.dequeueLast().equals(ENTRY_4));
		assertEquals(false, testDeque.isEmpty());
		assertEquals(2, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_1));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_2));
		assertEquals(true, compareToDeque.dequeueLast().equals(ENTRY_4));
		
		assertEquals(true, testDeque.dequeueFirst().equals(ENTRY_1));
		assertEquals(false, testDeque.isEmpty());
		assertEquals(1, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_2));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_2));
		assertEquals(true, compareToDeque.dequeueFirst().equals(ENTRY_1));
		
		assertEquals(true, testDeque.dequeueLast().equals(ENTRY_2));
		assertEquals(true, testDeque.isEmpty());
		assertEquals(0, testDeque.getSize());
		assertEquals(true, compareToDeque.dequeueLast().equals(ENTRY_2));
	}

	@Test
	public void makeTypeMyClass() {
		final MyClass ENTRY_1 = new MyClass();
		final MyClass ENTRY_2 = new MyClass();
		final MyClass ENTRY_3 = new MyClass();
		final MyClass ENTRY_4 = new MyClass();
		IDeque<MyClass> testDeque = new Deque<>();
		IDeque<MyClass> compareToDeque = new Deque<>();
		
		assertEquals(true, testDeque.isEmpty());
		assertEquals(0, testDeque.getSize());
		
		testDeque.enqueueFirst(ENTRY_1);
		assertEquals(false, testDeque.isEmpty());
		assertEquals(1, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().toString().equals(ENTRY_1.toString()));
		assertEquals(true, testDeque.peekLast().toString().equals(ENTRY_1.toString()));
		compareToDeque.enqueueFirst(ENTRY_1);
		
		testDeque.enqueueLast(ENTRY_2);
		assertEquals(false, testDeque.isEmpty());
		assertEquals(2, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().toString().equals(ENTRY_1.toString()));
		assertEquals(true, testDeque.peekLast().toString().equals(ENTRY_2.toString()));
		compareToDeque.enqueueLast(ENTRY_2);
		
		testDeque.enqueueFirst(ENTRY_3);
		assertEquals(false, testDeque.isEmpty());
		assertEquals(3, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().toString().equals(ENTRY_3.toString()));
		assertEquals(true, testDeque.peekLast().toString().equals(ENTRY_2.toString()));
		compareToDeque.enqueueFirst(ENTRY_3);
		
		testDeque.enqueueLast(ENTRY_4);
		assertEquals(false, testDeque.isEmpty());
		assertEquals(4, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().toString().equals(ENTRY_3.toString()));
		assertEquals(true, testDeque.peekLast().toString().equals(ENTRY_4.toString()));
		compareToDeque.enqueueLast(ENTRY_4);

		assertEquals(true, testDeque.dequeueFirst().toString().equals(ENTRY_3.toString()));
		assertEquals(false, testDeque.isEmpty());
		assertEquals(3, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().toString().equals(ENTRY_1.toString()));
		assertEquals(true, testDeque.peekLast().toString().equals(ENTRY_4.toString()));
		assertEquals(true, compareToDeque.dequeueFirst().toString().equals(ENTRY_3.toString()));
		
		assertEquals(true, testDeque.dequeueLast().toString().equals(ENTRY_4.toString()));
		assertEquals(false, testDeque.isEmpty());
		assertEquals(2, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().toString().equals(ENTRY_1.toString()));
		assertEquals(true, testDeque.peekLast().toString().equals(ENTRY_2.toString()));
		assertEquals(true, compareToDeque.dequeueLast().toString().equals(ENTRY_4.toString()));
		
		assertEquals(true, testDeque.dequeueFirst().toString().equals(ENTRY_1.toString()));
		assertEquals(false, testDeque.isEmpty());
		assertEquals(1, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().toString().equals(ENTRY_2.toString()));
		assertEquals(true, testDeque.peekLast().toString().equals(ENTRY_2.toString()));
		assertEquals(true, compareToDeque.dequeueFirst().toString().equals(ENTRY_1.toString()));
		
		assertEquals(true, testDeque.dequeueLast().toString().equals(ENTRY_2.toString()));
		assertEquals(true, testDeque.isEmpty());
		assertEquals(0, testDeque.getSize());
		assertEquals(true, compareToDeque.dequeueLast().toString().equals(ENTRY_2.toString()));
	}

	@Test
	public void makeTypeMySubClass() {
		final MySubClass ENTRY_1 = new MySubClass();
		final MySubClass ENTRY_2 = new MySubClass();
		final MySubClass ENTRY_3 = new MySubClass();
		final MySubClass ENTRY_4 = new MySubClass();
		IDeque<MySubClass> testDeque = new Deque<>();
		IDeque<MySubClass> compareToDeque = new Deque<>();
		
		assertEquals(true, testDeque.isEmpty());
		assertEquals(0, testDeque.getSize());
		
		testDeque.enqueueFirst(ENTRY_1);
		assertEquals(false, testDeque.isEmpty());
		assertEquals(1, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_1));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_1));
		compareToDeque.enqueueFirst(ENTRY_1);
		
		testDeque.enqueueLast(ENTRY_2);
		assertEquals(false, testDeque.isEmpty());
		assertEquals(2, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_1));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_2));
		compareToDeque.enqueueLast(ENTRY_2);
		
		testDeque.enqueueFirst(ENTRY_3);
		assertEquals(false, testDeque.isEmpty());
		assertEquals(3, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_3));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_2));
		compareToDeque.enqueueFirst(ENTRY_3);
		
		testDeque.enqueueLast(ENTRY_4);
		assertEquals(false, testDeque.isEmpty());
		assertEquals(4, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_3));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_4));
		compareToDeque.enqueueLast(ENTRY_4);

		assertEquals(true, testDeque.dequeueFirst().equals(ENTRY_3));
		assertEquals(false, testDeque.isEmpty());
		assertEquals(3, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_1));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_4));
		assertEquals(true, compareToDeque.dequeueFirst().equals(ENTRY_3));
		
		assertEquals(true, testDeque.dequeueLast().equals(ENTRY_4));
		assertEquals(false, testDeque.isEmpty());
		assertEquals(2, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_1));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_2));
		assertEquals(true, compareToDeque.dequeueLast().equals(ENTRY_4));
		
		assertEquals(true, testDeque.dequeueFirst().equals(ENTRY_1));
		assertEquals(false, testDeque.isEmpty());
		assertEquals(1, testDeque.getSize());
		assertEquals(true, testDeque.peekFirst().equals(ENTRY_2));
		assertEquals(true, testDeque.peekLast().equals(ENTRY_2));
		assertEquals(true, compareToDeque.dequeueFirst().equals(ENTRY_1));
		
		assertEquals(true, testDeque.dequeueLast().equals(ENTRY_2));
		assertEquals(true, testDeque.isEmpty());
		assertEquals(0, testDeque.getSize());
		assertEquals(true, compareToDeque.dequeueLast().equals(ENTRY_2));
	}
}
