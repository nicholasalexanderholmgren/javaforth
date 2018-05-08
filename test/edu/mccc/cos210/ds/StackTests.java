package edu.mccc.cos210.ds;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class StackTests {
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
		report.append("\nTesting: ").append(StackTests.class.getSimpleName()).append("\n");
	}
	@AfterClass
	public static void tearDownClass() {
	    System.out.println(report.toString());
	}
	@Test
	public void newStackIsEmpty() {
		IStack<Double> stack = new Stack<>();
		assertEquals(true, stack.isEmpty());
	}
	@Test (expected=java.util.NoSuchElementException.class)
	public void pushNoneCheckPeek() {
		IStack<Double> stack = new Stack<>();
		@SuppressWarnings("unused")
		Double d = stack.peek();
	}
	@Test (expected=java.util.NoSuchElementException.class)
	public void pushNoneCheckPop() {
		IStack<Double> stack = new Stack<>();
		@SuppressWarnings("unused")
		Double d = stack.pop();
	}
	@Test
	public void pushOneCheckPeek() {
		IStack<Double> stack = new Stack<>();
		stack.push(Math.PI);
		assertEquals("3.14159", stack.peek().toString().substring(0, 7));
	}
	@Test
	public void pushOneCheckPop() {
		IStack<Double> stack = new Stack<>();
		stack.push(Math.PI);
		assertEquals("3.14159", stack.pop().toString().substring(0, 7));
	}
	@Test
	public void pushTwoCheckPeek() {
		IStack<Double> stack = new Stack<>();
		stack.push(Math.PI);
		stack.push(Math.E);
		assertEquals("2.7182", stack.peek().toString().substring(0, 6));	}
	@Test
	public void pushTwoCheckPop() {
		IStack<Double> stack = new Stack<>();
		stack.push(Math.PI);
		stack.push(Math.E);
		assertEquals("2.7182", stack.pop().toString().substring(0, 6));	}
}
