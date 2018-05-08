package edu.mccc.cos210.ds;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class PriorityQueueTests {
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
		report.append("\nTesting: ").append(PriorityQueueTests.class.getSimpleName()).append("\n");
	}
	@AfterClass
	public static void tearDownClass() {
	    System.out.println(report.toString());
	}
	@Test
	public void newPQisEmpty() {
		PriorityQueue<Integer> thePQ = new PriorityQueue<>();
		assertTrue(thePQ.isEmpty());
		assertEquals(0, thePQ.getSize());
		assertTrue(thePQ.isHeap());
	}
	@Test
	public void insertItemPQSizeIsCorrect() {
		PriorityQueue<Integer> thePQ = new PriorityQueue<>();
		thePQ.insert(10);
		assertFalse(thePQ.isEmpty());
		assertEquals(1, thePQ.getSize());
		assertTrue(thePQ.isHeap());
	}
	@Test
	public void insertTwoItemsPQSizeIsCorrect() {
		PriorityQueue<Integer> thePQ = new PriorityQueue<>();
		thePQ.insert(10);
		thePQ.insert(20);
		assertFalse(thePQ.isEmpty());
		assertEquals(2, thePQ.getSize());
		assertTrue(thePQ.isHeap());
	}
	@Test
	public void insertTwoItemsInReverseOrder() {
		PriorityQueue<Integer> thePQ = new PriorityQueue<>();
		thePQ.insert(40);
		thePQ.insert(30);
		thePQ.insert(20);
		thePQ.insert(10);
		assertEquals(10, thePQ.peekTop().intValue());
		assertTrue(thePQ.isHeap());
	}
	@Test
	public void insertTenItemsPQSizeIsCorrect() {
		PriorityQueue<Integer> thePQ = new PriorityQueue<>();
		thePQ.insert(10);
		thePQ.insert(20);
		thePQ.insert(30);
		thePQ.insert(40);
		thePQ.insert(50);
		thePQ.insert(60);
		thePQ.insert(70);
		thePQ.insert(80);
		thePQ.insert(90);
		thePQ.insert(100);
		assertFalse(thePQ.isEmpty());
		assertEquals(10, thePQ.getSize());
		assertTrue(thePQ.isHeap());
	}
	@Test
	public void insertTenItemsPQSizeReverseOrderIsCorrect() {
		PriorityQueue<Integer> thePQ = new PriorityQueue<>();
		thePQ.insert(100);
		thePQ.insert(90);
		thePQ.insert(80);
		thePQ.insert(70);
		thePQ.insert(60);
		thePQ.insert(50);
		thePQ.insert(40);
		thePQ.insert(30);
		thePQ.insert(20);
		thePQ.insert(10);
		assertFalse(thePQ.isEmpty());
		assertEquals(10, thePQ.getSize());
		assertTrue(thePQ.isHeap());
	}
	@Test
	public void insertOneItemGetTopOnePQSizeIsCorrect() {
		PriorityQueue<Integer> thePQ = new PriorityQueue<>();
		thePQ.insert(10);
		thePQ.getTop();
		assertTrue(thePQ.isEmpty());
		assertEquals(0, thePQ.getSize());
		assertTrue(thePQ.isHeap());
	}
	@Test
	public void insertTwoItemGetTopTwoPQSizeIsCorrect() {
		PriorityQueue<Integer> thePQ = new PriorityQueue<>();
		thePQ.insert(10);
		thePQ.insert(20);
		thePQ.getTop();
		thePQ.getTop();
		assertTrue(thePQ.isEmpty());
		assertEquals(0, thePQ.getSize());
		assertTrue(thePQ.isHeap());
	}
	@Test
	public void insertTwoItemGetTopOnePQSizeIsCorrect() {
		PriorityQueue<Integer> thePQ = new PriorityQueue<>();
		thePQ.insert(20);
		thePQ.insert(10);
		thePQ.insert(30);
		thePQ.getTop();
		assertFalse(thePQ.isEmpty());
		assertEquals(2, thePQ.getSize());
		assertTrue(thePQ.isHeap());
	}
	@Test
	public void testDoubleBubbleDown() {
		PriorityQueue<Integer> thePQ = new PriorityQueue<>();
		thePQ.insert(1);
		thePQ.insert(2);
		thePQ.insert(3);
		thePQ.insert(4);
		thePQ.insert(5);
		thePQ.insert(6);
		thePQ.insert(7);
		thePQ.insert(8);
		assertTrue(thePQ.isHeap());
		int i = thePQ.getTop();
		assertEquals(1, i);
		assertTrue(thePQ.isHeap());
	}
	@Test
	public void testRightSwap() {
		PriorityQueue<Integer> thePQ = new PriorityQueue<>();
		thePQ.insert(1);
		thePQ.insert(3);
		thePQ.insert(2);
		assertTrue(thePQ.isHeap());
		int i = thePQ.getTop();
		assertEquals(1, i);
		assertTrue(thePQ.isHeap());
		i = thePQ.getTop();
		assertEquals(2, i);
		assertTrue(thePQ.isHeap());
	}
	@Test
	public void testRightRightSwap() {
		PriorityQueue<Integer> thePQ = new PriorityQueue<>();
		thePQ.insert(1);
		thePQ.insert(3);
		thePQ.insert(2);
		thePQ.insert(4);
		thePQ.insert(5);
		thePQ.insert(7);
		thePQ.insert(6);
		assertTrue(thePQ.isHeap());
		int i = thePQ.getTop();
		assertEquals(1, i);
		assertTrue(thePQ.isHeap());
		i = thePQ.getTop();
		assertEquals(2, i);
		assertTrue(thePQ.isHeap());
	}
	@Test
	public void testRightLeftSwap() {
		PriorityQueue<Integer> thePQ = new PriorityQueue<>();
		//System.out.println(thePQ);
		thePQ.insert(1);
		//System.out.println(thePQ);
		thePQ.insert(3);
		//System.out.println(thePQ);
		thePQ.insert(4);
		//System.out.println(thePQ);
		thePQ.insert(5);
		//System.out.println(thePQ);
		thePQ.insert(2);
		//System.out.println(thePQ);
		thePQ.insert(7);
		//System.out.println(thePQ);
		thePQ.insert(6);
		assertTrue(thePQ.isHeap());
		//System.out.println(thePQ);
		int i = thePQ.getTop();
		assertEquals(1, i);
		assertTrue(thePQ.isHeap());
		//System.out.println(thePQ);
		i = thePQ.getTop();
		assertEquals(2, i);
		assertTrue(thePQ.isHeap());
		//System.out.println(thePQ);
		i = thePQ.getTop();
		assertEquals(3, i);
		assertTrue(thePQ.isHeap());
		//System.out.println(thePQ);
		i = thePQ.getTop();
		assertEquals(4, i);
		assertTrue(thePQ.isHeap());
		//System.out.println(thePQ);
		i = thePQ.getTop();
		assertEquals(5, i);
		assertTrue(thePQ.isHeap());
		//System.out.println(thePQ);
		i = thePQ.getTop();
		assertEquals(6, i);
		assertTrue(thePQ.isHeap());
		//System.out.println(thePQ);
		i = thePQ.getTop();
		assertEquals(7, i);
		assertTrue(thePQ.isHeap());
		//System.out.println(thePQ);
		assertTrue(thePQ.isEmpty());
	}

}
