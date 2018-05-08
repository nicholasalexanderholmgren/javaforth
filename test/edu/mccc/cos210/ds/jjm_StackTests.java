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

public class jjm_StackTests {
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
        report.append("\nTesting: ").append(Stack.class.getSimpleName()).append("\n");
	}
	
	@AfterClass
	public static void tearDownClass() {
	    System.out.println(report.toString());
	}
	
	//Tests
	@Test
	public void pushingPeeking() {
		final int TEST_SIZE = 20;
		
		IStack<Integer> testStack = new Stack<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testStack.push(i);
			assertEquals(true, testStack.peek().equals((Integer) i));
		}
	}
	
	@Test
	public void poppingPeeking() {
		final int TEST_SIZE = 20;
		
		IStack<Integer> testStack = new Stack<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testStack.push(i);
		}
		for (int i = TEST_SIZE - 1; i < -1; i--) {
			assertEquals(true, testStack.pop().equals((Integer) i));
		}
	}
	
	@Test
	public void startsEmpty() {
		IStack<Integer> testStack = new Stack<>();
		assertEquals(true, testStack.isEmpty());
	}
	
	@Test
	public void doesEmpty() {
		final int TEST_SIZE = 20;
		
		IStack<Integer> testStack = new Stack<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testStack.push(i);
		}
		assertEquals(false, testStack.isEmpty());
		for (int i = 0; i < TEST_SIZE; i++) {
			testStack.pop();
		}
		assertEquals(true, testStack.isEmpty());
	}
	
	@Test
	public void doesFill() {
		final int TEST_SIZE = 10;
		
		IStack<Integer> testStack = new Stack<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testStack.push(i);
			assertEquals(false, testStack.isEmpty());
		}
	}
	
	@Test(expected=NoSuchElementException.class)
	public void peekingEmpty() {
		IStack<Integer> testStack = new Stack<>();
		testStack.peek();
	}
	
	@Test(expected=NoSuchElementException.class)
	public void poppingEmpty() {
		IStack<Integer> testStack = new Stack<>();
		testStack.pop();
	}
	
	@Test
	public void integrity_compareToClone() {
		final int TEST_SIZE = 10;
		
		IStack<Integer> testStack = new Stack<>();
		IStack<Integer> compareToStack = new Stack<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testStack.push(i);
			compareToStack.push(i);
		}
		assertEquals(true, testStack.toString().equals(compareToStack.toString()));
		for (int i = 0; i < TEST_SIZE / 2; i++) {
			testStack.pop();
			compareToStack.pop();
		}
		assertEquals(true, testStack.toString().equals(compareToStack.toString()));
	}
	
	@Test
	public void integrity_compareToSB() {
		final int TEST_SIZE = 10;
		
		IStack<Integer> testStack = new Stack<>();
		StringBuilder testStackOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		for (int i = 0; i < TEST_SIZE; i++) {
			testStack.push(i);
			compareToSB.insert(0, i);
		}
		for (int i = 0; i < TEST_SIZE; i++) {
			testStackOutput.append(testStack.pop());
		}
		assertEquals(compareToSB.toString(), testStackOutput.toString());
	}
	
	@Test
	public void makeTypeInt() {
		final int ENTRY_1 = 5;
		final int ENTRY_2 = 10;
		final int ENTRY_3 = 15;
		IStack<Integer> testStack = new Stack<>();
		IStack<Integer> compareToStack = new Stack<>();
		StringBuilder testStackOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		
		assertEquals(true, testStack.isEmpty());
		
		testStack.push(ENTRY_1);
		assertEquals(false, testStack.isEmpty());
		assertEquals(true, testStack.peek().equals((Integer) ENTRY_1));
		compareToStack.push(ENTRY_1);
		compareToSB.insert(0, ENTRY_1);
		
		testStack.push(ENTRY_2);
		assertEquals(false, testStack.isEmpty());
		assertEquals(true, testStack.peek().equals((Integer) ENTRY_2));
		compareToStack.push(ENTRY_2);
		compareToSB.insert(0, ENTRY_2);
		
		testStack.push(ENTRY_3);
		assertEquals(false, testStack.isEmpty());
		assertEquals(true, testStack.peek().equals((Integer) ENTRY_3));
		compareToStack.push(ENTRY_3);
		compareToSB.insert(0, ENTRY_3);
		
		assertEquals(true, testStack.toString().equals(compareToStack.toString()));
		
		for (int i = 0; i < 3; i++) {
			testStackOutput.append(testStack.pop());
		}
		assertEquals(true, testStackOutput.toString().equals(compareToSB.toString()));	
	}
	
	@Test
	public void makeTypDouble() {
		final double ENTRY_1 = 5.0;
		final double ENTRY_2 = 10.0;
		final double ENTRY_3 = 15.0;
		IStack<Double> testStack = new Stack<>();
		IStack<Double> compareToStack = new Stack<>();
		StringBuilder testStackOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		
		assertEquals(true, testStack.isEmpty());
		
		testStack.push(ENTRY_1);
		assertEquals(false, testStack.isEmpty());
		assertEquals(true, testStack.peek().equals((Double) ENTRY_1));
		compareToStack.push(ENTRY_1);
		compareToSB.insert(0, ENTRY_1);
		
		testStack.push(ENTRY_2);
		assertEquals(false, testStack.isEmpty());
		assertEquals(true, testStack.peek().equals((Double) ENTRY_2));
		compareToStack.push(ENTRY_2);
		compareToSB.insert(0, ENTRY_2);
		
		testStack.push(ENTRY_3);
		assertEquals(false, testStack.isEmpty());
		assertEquals(true, testStack.peek().equals((Double) ENTRY_3));
		compareToStack.push(ENTRY_3);
		compareToSB.insert(0, ENTRY_3);
		
		assertEquals(true, testStack.toString().equals(compareToStack.toString()));
		
		for (int i = 0; i < 3; i++) {
			testStackOutput.append(testStack.pop());
		}
		assertEquals(true, testStackOutput.toString().equals(compareToSB.toString()));	
	}
	
	@Test
	public void makeTypeBoolean() {
		final boolean ENTRY_1 = true;
		final boolean ENTRY_2 = false;
		final boolean ENTRY_3 = true;
		IStack<Boolean> testStack = new Stack<>();
		IStack<Boolean> compareToStack = new Stack<>();
		StringBuilder testStackOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		
		assertEquals(true, testStack.isEmpty());
		
		testStack.push(ENTRY_1);
		assertEquals(false, testStack.isEmpty());
		assertEquals(true, testStack.peek().equals((Boolean) ENTRY_1));
		compareToStack.push(ENTRY_1);
		compareToSB.insert(0, ENTRY_1);
		
		testStack.push(ENTRY_2);
		assertEquals(false, testStack.isEmpty());
		assertEquals(true, testStack.peek().equals((Boolean) ENTRY_2));
		compareToStack.push(ENTRY_2);
		compareToSB.insert(0, ENTRY_2);
		
		testStack.push(ENTRY_3);
		assertEquals(false, testStack.isEmpty());
		assertEquals(true, testStack.peek().equals((Boolean) ENTRY_3));
		compareToStack.push(ENTRY_3);
		compareToSB.insert(0, ENTRY_3);
		
		assertEquals(true, testStack.toString().equals(compareToStack.toString()));
		
		for (int i = 0; i < 3; i++) {
			testStackOutput.append(testStack.pop());
		}
		assertEquals(true, testStackOutput.toString().equals(compareToSB.toString()));	
	}
	
	@Test
	public void makeTypeString() {
		final String ENTRY_1 = "who";
		final String ENTRY_2 = "is";
		final String ENTRY_3 = "bluefairy";
		IStack<String> testStack = new Stack<>();
		IStack<String> compareToStack = new Stack<>();
		StringBuilder testStackOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		
		assertEquals(true, testStack.isEmpty());
		
		testStack.push(ENTRY_1);
		assertEquals(false, testStack.isEmpty());
		assertEquals(true, testStack.peek().equals(ENTRY_1));
		compareToStack.push(ENTRY_1);
		compareToSB.insert(0, ENTRY_1);
		
		testStack.push(ENTRY_2);
		assertEquals(false, testStack.isEmpty());
		assertEquals(true, testStack.peek().equals(ENTRY_2));
		compareToStack.push(ENTRY_2);
		compareToSB.insert(0, ENTRY_2);
		
		testStack.push(ENTRY_3);
		assertEquals(false, testStack.isEmpty());
		assertEquals(true, testStack.peek().equals(ENTRY_3));
		compareToStack.push(ENTRY_3);
		compareToSB.insert(0, ENTRY_3);
		
		assertEquals(true, testStack.toString().equals(compareToStack.toString()));
		
		for (int i = 0; i < 3; i++) {
			testStackOutput.append(testStack.pop());
		}
		assertEquals(true, testStackOutput.toString().equals(compareToSB.toString()));	
	}
	
	@Test
	public void makeTypeChar() {
		final char ENTRY_1 = 'd';
		final char ENTRY_2 = 'o';
		final char ENTRY_3 = 'g';
		IStack<Character> testStack = new Stack<>();
		IStack<Character> compareToStack = new Stack<>();
		StringBuilder testStackOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		
		assertEquals(true, testStack.isEmpty());
		
		testStack.push(ENTRY_1);
		assertEquals(false, testStack.isEmpty());
		assertEquals(true, testStack.peek().equals((Character) ENTRY_1));
		compareToStack.push(ENTRY_1);
		compareToSB.insert(0, ENTRY_1);
		
		testStack.push(ENTRY_2);
		assertEquals(false, testStack.isEmpty());
		assertEquals(true, testStack.peek().equals((Character) ENTRY_2));
		compareToStack.push(ENTRY_2);
		compareToSB.insert(0, ENTRY_2);
		
		testStack.push(ENTRY_3);
		assertEquals(false, testStack.isEmpty());
		assertEquals(true, testStack.peek().equals((Character) ENTRY_3));
		compareToStack.push(ENTRY_3);
		compareToSB.insert(0, ENTRY_3);
		
		assertEquals(true, testStack.toString().equals(compareToStack.toString()));
		
		for (int i = 0; i < 3; i++) {
			testStackOutput.append(testStack.pop());
		}
		assertEquals(true, testStackOutput.toString().equals(compareToSB.toString()));	
	}
	
	@Test
	public void makeTypeMyClass() {
		final MyClass ENTRY_1 = new MyClass();
		final MyClass ENTRY_2 = new MyClass();
		final MyClass ENTRY_3 = new MyClass();
		IStack<MyClass> testStack = new Stack<>();
		IStack<MyClass> compareToStack = new Stack<>();
		StringBuilder testStackOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		
		assertEquals(true, testStack.isEmpty());
		
		testStack.push(ENTRY_1);
		assertEquals(false, testStack.isEmpty());
		assertEquals(true, testStack.peek().equals(ENTRY_1));
		compareToStack.push(ENTRY_1);
		compareToSB.insert(0, ENTRY_1);
		
		testStack.push(ENTRY_2);
		assertEquals(false, testStack.isEmpty());
		assertEquals(true, testStack.peek().equals(ENTRY_2));
		compareToStack.push(ENTRY_2);
		compareToSB.insert(0, ENTRY_2);
		
		testStack.push(ENTRY_3);
		assertEquals(false, testStack.isEmpty());
		assertEquals(true, testStack.peek().equals(ENTRY_3));
		compareToStack.push(ENTRY_3);
		compareToSB.insert(0, ENTRY_3);
		
		assertEquals(true, testStack.toString().equals(compareToStack.toString()));
		
		for (int i = 0; i < 3; i++) {
			testStackOutput.append(testStack.pop());
		}
		assertEquals(true, testStackOutput.toString().equals(compareToSB.toString()));
	}
	
	@Test
	public void makeTypeMySubClass() {
		final MySubClass ENTRY_1 = new MySubClass();
		final MySubClass ENTRY_2 = new MySubClass();
		final MySubClass ENTRY_3 = new MySubClass();
		IStack<MySubClass> testStack = new Stack<>();
		IStack<MySubClass> compareToStack = new Stack<>();
		StringBuilder testStackOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		
		assertEquals(true, testStack.isEmpty());
		
		testStack.push(ENTRY_1);
		assertEquals(false, testStack.isEmpty());
		assertEquals(true, testStack.peek().equals(ENTRY_1));
		compareToStack.push(ENTRY_1);
		compareToSB.insert(0, ENTRY_1);
		
		testStack.push(ENTRY_2);
		assertEquals(false, testStack.isEmpty());
		assertEquals(true, testStack.peek().equals(ENTRY_2));
		compareToStack.push(ENTRY_2);
		compareToSB.insert(0, ENTRY_2);
		
		testStack.push(ENTRY_3);
		assertEquals(false, testStack.isEmpty());
		assertEquals(true, testStack.peek().equals(ENTRY_3));
		compareToStack.push(ENTRY_3);
		compareToSB.insert(0, ENTRY_3);
		
		assertEquals(true, testStack.toString().equals(compareToStack.toString()));
		
		for (int i = 0; i < 3; i++) {
			testStackOutput.append(testStack.pop());
		}
		assertEquals(true, testStackOutput.toString().equals(compareToSB.toString()));	
	}
}
