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

public class jjm_QueueTests {
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
        report.append("\nTesting: ").append(Queue.class.getSimpleName()).append("\n");
	}
	
	@AfterClass
	public static void tearDownClass() {
	    System.out.println(report.toString());
	}
	
	//Tests
	@Test
	public void enqueueingPeeking() {
		final int TEST_SIZE = 30;
		final int ENTRY_1 = 31;
		
		IQueue<Integer> testQueue = new Queue<>();
		testQueue.enqueue(ENTRY_1);
		for (int i = 0; i < TEST_SIZE; i++) {
			testQueue.enqueue(i);
			assertEquals(true, testQueue.peek().equals((Integer) ENTRY_1));
		}
	}
	
	@Test
	public void dequeueingPeeking() {
		final int TEST_SIZE = 30;
		
		IQueue<Integer> testQueue = new Queue<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testQueue.enqueue(i);
		}
		for (int i = 0; i < TEST_SIZE; i++) {
			assertEquals(true, testQueue.dequeue().equals((Integer) i));
		}
	}
	
	@Test
	public void startsEmpty() {
		IQueue<Integer> testQueue = new Queue<>();
		assertEquals(true, testQueue.isEmpty());
	}
	
	@Test
	public void doesFill() {
		final int TEST_SIZE = 20;
		
		IQueue<Integer> testQueue = new Queue<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testQueue.enqueue(i);
			assertEquals(false, testQueue.isEmpty());
		}
	}
	
	@Test
	public void doesEmpty() {
		final int TEST_SIZE = 20;
		
		IQueue<Integer> testQueue = new Queue<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testQueue.enqueue(i);
		}
		for (int i = 0; i < TEST_SIZE; i++) {
			testQueue.dequeue();
		}
		assertEquals(true, testQueue.isEmpty());
	}
	
	@Test(expected=NoSuchElementException.class)
	public void dequeueingEmpty() {
		IQueue<Integer> testQueue = new Queue<>();
		testQueue.dequeue();
	}
	
	@Test(expected=NoSuchElementException.class)
	public void peekingEmpty() {
		IQueue<Integer> testQueue = new Queue<>();
		testQueue.peek();
	}
	
	@Test
	public void integrity_compareToQueue() {
		final int TEST_SIZE = 20;
		
		IQueue<Integer> testQueue = new Queue<>();
		IQueue<Integer> compareToQueue = new Queue<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testQueue.enqueue(i);
			compareToQueue.enqueue(i);
		}
		assertEquals(true, testQueue.toString().equals(compareToQueue.toString()));
		for (int i = 0; i < TEST_SIZE / 2; i++) {
			testQueue.dequeue();
			compareToQueue.dequeue();
		}
		assertEquals(true, testQueue.toString().equals(compareToQueue.toString()));
	}
	
	@Test
	public void integerity_compareToSB() {
		final int TEST_SIZE = 20;
		
		IQueue<Integer> testQueue = new Queue<>();
		StringBuilder testQueueOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		for (int i = 0; i < TEST_SIZE; i++) {
			testQueue.enqueue(i);
			compareToSB.append(i);
		}
		for (int i = 0; i < TEST_SIZE; i++) {
			testQueueOutput.append(testQueue.dequeue());
		}
		assertEquals(true, testQueueOutput.toString().equals(compareToSB.toString()));
	}
	
	@Test
	public void makeTypeInt() {
		final int ENTRY_1 = 5;
		final int ENTRY_2 = 10;
		final int ENTRY_3 = 15;
		
		IQueue<Integer> testQueue = new Queue<>();
		IQueue<Integer> compareToQueue = new Queue<>();
		StringBuilder testQueueOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		
		assertEquals(true, testQueue.isEmpty());
		
		testQueue.enqueue(ENTRY_1);
		assertEquals(false, testQueue.isEmpty());
		assertEquals(true, testQueue.peek().equals(ENTRY_1));
		compareToQueue.enqueue(ENTRY_1);
		compareToSB.append(ENTRY_1);
		
		testQueue.enqueue(ENTRY_2);
		assertEquals(false, testQueue.isEmpty());
		assertEquals(true, testQueue.peek().equals(ENTRY_1));
		compareToQueue.enqueue(ENTRY_2);
		compareToSB.append(ENTRY_2);
		
		testQueue.enqueue(ENTRY_3);
		assertEquals(false, testQueue.isEmpty());
		assertEquals(true, testQueue.peek().equals(ENTRY_1));
		compareToQueue.enqueue(ENTRY_3);
		compareToSB.append(ENTRY_3);
		
		assertEquals(true, testQueue.toString().equals(compareToQueue.toString()));
		
		for (int i = 0 ; i < 3; i++) {
			testQueueOutput.append(testQueue.dequeue());
		}
		assertEquals(true, testQueueOutput.toString().equals(compareToSB.toString()));
	}
	
	@Test
	public void makeTypeDouble() {
		final double ENTRY_1 = 5.0;
		final double ENTRY_2 = 10.0;
		final double ENTRY_3 = 15.0;
		
		IQueue<Double> testQueue = new Queue<>();
		IQueue<Double> compareToQueue = new Queue<>();
		StringBuilder testQueueOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		
		assertEquals(true, testQueue.isEmpty());
		
		testQueue.enqueue(ENTRY_1);
		assertEquals(false, testQueue.isEmpty());
		assertEquals(true, testQueue.peek().equals(ENTRY_1));
		compareToQueue.enqueue(ENTRY_1);
		compareToSB.append(ENTRY_1);
		
		testQueue.enqueue(ENTRY_2);
		assertEquals(false, testQueue.isEmpty());
		assertEquals(true, testQueue.peek().equals(ENTRY_1));
		compareToQueue.enqueue(ENTRY_2);
		compareToSB.append(ENTRY_2);
		
		testQueue.enqueue(ENTRY_3);
		assertEquals(false, testQueue.isEmpty());
		assertEquals(true, testQueue.peek().equals(ENTRY_1));
		compareToQueue.enqueue(ENTRY_3);
		compareToSB.append(ENTRY_3);
		
		assertEquals(true, testQueue.toString().equals(compareToQueue.toString()));
		
		for (int i = 0 ; i < 3; i++) {
			testQueueOutput.append(testQueue.dequeue());
		}
		assertEquals(true, testQueueOutput.toString().equals(compareToSB.toString()));
	}
	
	@Test
	public void makeTypeBoolean() {
		final boolean ENTRY_1 = false;
		final boolean ENTRY_2 = true;
		final boolean ENTRY_3 = false;
		
		IQueue<Boolean> testQueue = new Queue<>();
		IQueue<Boolean> compareToQueue = new Queue<>();
		StringBuilder testQueueOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		
		assertEquals(true, testQueue.isEmpty());
		
		testQueue.enqueue(ENTRY_1);
		assertEquals(false, testQueue.isEmpty());
		assertEquals(true, testQueue.peek().equals(ENTRY_1));
		compareToQueue.enqueue(ENTRY_1);
		compareToSB.append(ENTRY_1);
		
		testQueue.enqueue(ENTRY_2);
		assertEquals(false, testQueue.isEmpty());
		assertEquals(true, testQueue.peek().equals(ENTRY_1));
		compareToQueue.enqueue(ENTRY_2);
		compareToSB.append(ENTRY_2);
		
		testQueue.enqueue(ENTRY_3);
		assertEquals(false, testQueue.isEmpty());
		assertEquals(true, testQueue.peek().equals(ENTRY_1));
		compareToQueue.enqueue(ENTRY_3);
		compareToSB.append(ENTRY_3);
		
		assertEquals(true, testQueue.toString().equals(compareToQueue.toString()));
		
		for (int i = 0 ; i < 3; i++) {
			testQueueOutput.append(testQueue.dequeue());
		}
		assertEquals(true, testQueueOutput.toString().equals(compareToSB.toString()));
	}
	
	@Test
	public void makeTypeChar() {
		final char ENTRY_1 = 'a';
		final char ENTRY_2 = 'm';
		final char ENTRY_3 = 'z';
		
		IQueue<Character> testQueue = new Queue<>();
		IQueue<Character> compareToQueue = new Queue<>();
		StringBuilder testQueueOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		
		assertEquals(true, testQueue.isEmpty());
		
		testQueue.enqueue(ENTRY_1);
		assertEquals(false, testQueue.isEmpty());
		assertEquals(true, testQueue.peek().equals(ENTRY_1));
		compareToQueue.enqueue(ENTRY_1);
		compareToSB.append(ENTRY_1);
		
		testQueue.enqueue(ENTRY_2);
		assertEquals(false, testQueue.isEmpty());
		assertEquals(true, testQueue.peek().equals(ENTRY_1));
		compareToQueue.enqueue(ENTRY_2);
		compareToSB.append(ENTRY_2);
		
		testQueue.enqueue(ENTRY_3);
		assertEquals(false, testQueue.isEmpty());
		assertEquals(true, testQueue.peek().equals(ENTRY_1));
		compareToQueue.enqueue(ENTRY_3);
		compareToSB.append(ENTRY_3);
		
		assertEquals(true, testQueue.toString().equals(compareToQueue.toString()));
		
		for (int i = 0 ; i < 3; i++) {
			testQueueOutput.append(testQueue.dequeue());
		}
		assertEquals(true, testQueueOutput.toString().equals(compareToSB.toString()));
	}
	
	@Test
	public void makeTypeString() {
		final String ENTRY_1 = "quick";
		final String ENTRY_2 = "drown";
		final String ENTRY_3 = "grox";
		
		IQueue<String> testQueue = new Queue<>();
		IQueue<String> compareToQueue = new Queue<>();
		StringBuilder testQueueOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		
		assertEquals(true, testQueue.isEmpty());
		
		testQueue.enqueue(ENTRY_1);
		assertEquals(false, testQueue.isEmpty());
		assertEquals(true, testQueue.peek().equals(ENTRY_1));
		compareToQueue.enqueue(ENTRY_1);
		compareToSB.append(ENTRY_1);
		
		testQueue.enqueue(ENTRY_2);
		assertEquals(false, testQueue.isEmpty());
		assertEquals(true, testQueue.peek().equals(ENTRY_1));
		compareToQueue.enqueue(ENTRY_2);
		compareToSB.append(ENTRY_2);
		
		testQueue.enqueue(ENTRY_3);
		assertEquals(false, testQueue.isEmpty());
		assertEquals(true, testQueue.peek().equals(ENTRY_1));
		compareToQueue.enqueue(ENTRY_3);
		compareToSB.append(ENTRY_3);
		
		assertEquals(true, testQueue.toString().equals(compareToQueue.toString()));
		
		for (int i = 0 ; i < 3; i++) {
			testQueueOutput.append(testQueue.dequeue());
		}
		assertEquals(true, testQueueOutput.toString().equals(compareToSB.toString()));
	}
	
	@Test
	public void makeTypeMyClass() {
		final MyClass ENTRY_1 = new MyClass();
		final MyClass ENTRY_2 = new MyClass();
		final MyClass ENTRY_3 = new MyClass();
		
		IQueue<MyClass> testQueue = new Queue<>();
		IQueue<MyClass> compareToQueue = new Queue<>();
		StringBuilder testQueueOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		
		assertEquals(true, testQueue.isEmpty());
		
		testQueue.enqueue(ENTRY_1);
		assertEquals(false, testQueue.isEmpty());
		assertEquals(true, testQueue.peek().toString().equals(ENTRY_1.toString()));
		compareToQueue.enqueue(ENTRY_1);
		compareToSB.append(ENTRY_1);
		
		testQueue.enqueue(ENTRY_2);
		assertEquals(false, testQueue.isEmpty());
		assertEquals(true, testQueue.peek().toString().equals(ENTRY_1.toString()));
		compareToQueue.enqueue(ENTRY_2);
		compareToSB.append(ENTRY_2);
		
		testQueue.enqueue(ENTRY_3);
		assertEquals(false, testQueue.isEmpty());
		assertEquals(true, testQueue.peek().toString().equals(ENTRY_1.toString()));
		compareToQueue.enqueue(ENTRY_3);
		compareToSB.append(ENTRY_3);
		
		assertEquals(true, testQueue.toString().equals(compareToQueue.toString()));
		
		for (int i = 0 ; i < 3; i++) {
			testQueueOutput.append(testQueue.dequeue());
		}
		assertEquals(true, testQueueOutput.toString().equals(compareToSB.toString()));
	}
	
	public void makeTypeMySubclass() {
		final MySubClass ENTRY_1 = new MySubClass();
		final MySubClass ENTRY_2 = new MySubClass();
		final MySubClass ENTRY_3 = new MySubClass();
		
		IQueue<MySubClass> testQueue = new Queue<>();
		IQueue<MySubClass> compareToQueue = new Queue<>();
		StringBuilder testQueueOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		
		assertEquals(true, testQueue.isEmpty());
		
		testQueue.enqueue(ENTRY_1);
		assertEquals(false, testQueue.isEmpty());
		assertEquals(true, testQueue.peek().equals(ENTRY_1));
		compareToQueue.enqueue(ENTRY_1);
		compareToSB.append(ENTRY_1);
		
		testQueue.enqueue(ENTRY_2);
		assertEquals(false, testQueue.isEmpty());
		assertEquals(true, testQueue.peek().equals(ENTRY_1));
		compareToQueue.enqueue(ENTRY_2);
		compareToSB.append(ENTRY_2);
		
		testQueue.enqueue(ENTRY_3);
		assertEquals(false, testQueue.isEmpty());
		assertEquals(true, testQueue.peek().equals(ENTRY_1));
		compareToQueue.enqueue(ENTRY_3);
		compareToSB.append(ENTRY_3);
		
		assertEquals(true, testQueue.toString().equals(compareToQueue.toString()));
		
		for (int i = 0 ; i < 3; i++) {
			testQueueOutput.append(testQueue.dequeue());
		}
		assertEquals(true, testQueueOutput.toString().equals(compareToSB.toString()));
	}
	
}
