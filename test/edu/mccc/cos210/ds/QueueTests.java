package edu.mccc.cos210.ds;

import static org.junit.Assert.assertEquals;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class QueueTests {
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
		report.append("\nTesting: ").append(QueueTests.class.getSimpleName()).append("\n");
	}
	@AfterClass
	public static void tearDownClass() {
	    System.out.println(report.toString());
	}
	@Test
	public void newQueueIsEmpty() {
		IQueue<Double> queue = new Queue<>();
		assertEquals(true, queue.isEmpty());
	}
	@Test (expected=java.util.NoSuchElementException.class)
	public void enqueueNoneCheckPeek() {
		IQueue<Double> queue = new Queue<>();
		@SuppressWarnings("unused")
		Double d = queue.peek();
	}
	@Test (expected=java.util.NoSuchElementException.class)
	public void enqueueNoneCheckDequeue() {
		IQueue<Double> queue = new Queue<>();
		@SuppressWarnings("unused")
		Double d = queue.dequeue();
	}
	@Test
	public void enqueueOneCheckPeek() {
		IQueue<Double> queue = new Queue<>();
		queue.enqueue(Math.PI);
		assertEquals("3.14159", queue.peek().toString().substring(0, 7));
	}
	@Test
	public void enqueueOneCheckDequeue() {
		IQueue<Double> queue = new Queue<>();
		queue.enqueue(Math.PI);
		assertEquals("3.14159", queue.dequeue().toString().substring(0, 7));
	}
	@Test
	public void enqueueTwoCheckPeek() {
		IQueue<Double> queue = new Queue<>();
		queue.enqueue(Math.PI);
		queue.enqueue(Math.E);
		assertEquals("3.14159", queue.peek().toString().substring(0, 7));
	}
	@Test
	public void enqueueTwoCheckDequeue() {
		IQueue<Double> queue = new Queue<>();
		queue.enqueue(Math.PI);
		queue.enqueue(Math.E);
		assertEquals("3.14159", queue.dequeue().toString().substring(0, 7));
	}
}
