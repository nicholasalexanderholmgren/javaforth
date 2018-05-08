package edu.mccc.cos210.ds;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class DequeTests {
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
		report.append("\nTesting: ").append(DequeTests.class.getSimpleName()).append("\n");
	}
	@AfterClass
	public static void tearDownClass() {
	    System.out.println(report.toString());
	}
	@Test
	public void newDequeIsEmpty() {
		IDeque<String> deque = new Deque<>();
		assertTrue(deque.isEmpty());
	}
	@Test
	public void enqueueFirstElementIsNotEmpty() {
		IDeque<String> deque = new Deque<>();
		deque.enqueueFirst("Duck");
		assertFalse(deque.isEmpty());
	}
	@Test
	public void peekFirstElementSucceeds() {
		IDeque<String> deque = new Deque<>();
		deque.enqueueFirst("Duck");
		deque.enqueueFirst("Sheep");
		assertEquals("Sheep", deque.peekFirst());
	}
	@Test
	public void dequeueFirstElementSucceeds() {
		IDeque<String> deque = new Deque<>();
		deque.enqueueFirst("Duck");
		assertFalse(deque.isEmpty());
		deque.dequeueFirst();
		assertTrue(deque.isEmpty());
	}
	@Test
	public void enqueueLastElementIsNotEmpty() {
		IDeque<String> deque = new Deque<>();
		deque.enqueueLast("Duck");
		assertFalse(deque.isEmpty());
	}
	@Test
	public void peekLastElementSucceeds() {
		IDeque<String> deque = new Deque<>();
		deque.enqueueLast("Duck");
		deque.enqueueLast("Sheep");
		assertEquals("Sheep", deque.peekLast());
	}
	@Test
	public void dequeueLastElementSucceeds() {
		IDeque<String> deque = new Deque<>();
		deque.enqueueLast("Duck");
		assertFalse(deque.isEmpty());
		deque.dequeueLast();
		assertTrue(deque.isEmpty());
	}
	@Test
	public void inFirstOutLast() {
		IDeque<String> deque = new Deque<>();
		deque.enqueueFirst("Duck");
		deque.enqueueFirst("Sheep");
		String s = deque.dequeueLast();
		assertEquals("Duck", s);
	}
	@Test
	public void inLastOutFirst() {
		IDeque<String> deque = new Deque<>();
		deque.enqueueLast("Duck");
		deque.enqueueLast("Sheep");
		String s = deque.dequeueFirst();
		assertEquals("Duck", s);
	}
	@Test(expected = java.util.NoSuchElementException.class)
	public void peekFirstElementFails() {
		IDeque<String> deque = new Deque<>();
		deque.peekFirst();
	}
	@Test(expected = java.util.NoSuchElementException.class)
	public void dequeueFirstElementFails() {
		IDeque<String> deque = new Deque<>();
		deque.dequeueFirst();
	}
	@Test(expected = java.util.NoSuchElementException.class)
	public void peekLastElementFails() {
		IDeque<String> deque = new Deque<>();
		deque.peekLast();
	}
	@Test(expected = java.util.NoSuchElementException.class)
	public void dequeueLastElementFails() {
		IDeque<String> deque = new Deque<>();
		deque.dequeueLast();
	}
	@Test
	public void dequeFirstWorksCorrectly() {
		IDeque<String> deque = new Deque<>();
		deque.enqueueFirst("Duck");
		deque.enqueueFirst("Sheep");
		deque.enqueueFirst("Cat");
		assertEquals("Cat", deque.peekFirst());
		deque.dequeueFirst();
		assertEquals("Sheep", deque.dequeueFirst());
		assertEquals("Duck", deque.peekFirst());
		assertEquals("Duck", deque.dequeueFirst());
		assertTrue(deque.isEmpty());
	}
	@Test
	public void dequeLastWorksCorrectly() {
		IDeque<String> deque = new Deque<>();
		deque.enqueueLast("Duck");
		deque.enqueueLast("Sheep");
		deque.enqueueLast("Cat");
		assertEquals("Cat", deque.peekLast());
		deque.dequeueLast();
		assertEquals("Sheep", deque.dequeueLast());
		assertEquals("Duck", deque.peekLast());
		assertEquals("Duck", deque.dequeueLast());
		assertTrue(deque.isEmpty());
	}
}
