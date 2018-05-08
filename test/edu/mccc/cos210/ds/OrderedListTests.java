package edu.mccc.cos210.ds;

import static org.junit.Assert.assertEquals;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class OrderedListTests {
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
		report.append("\nTesting: ").append(OrderedListTests.class.getSimpleName()).append("\n");
	}
	@AfterClass
	public static void tearDownClass() {
	    System.out.println(report.toString());
	}
	@Test
	public void dumbTest() {
		IOrderedList<String> list = new OrderedList<>();
		assertEquals(0, list.getSize());
		list.add("Mister");
		list.add("Fish");
		list.add("goes");
		list.add("to");
		list.add("Town");
		assertEquals(5, list.getSize());
		StringBuilder sb = new StringBuilder();
		for (String s : list) {
			sb.append(s);
		}
		assertEquals("MisterFishgoestoTown", sb.toString());
		assertEquals(true, list.contains("Fish"));
		assertEquals(false, list.contains("Duck"));
		assertEquals(false, list.contains("town"));
		assertEquals(true, list.contains("Mister"));
	}
}
