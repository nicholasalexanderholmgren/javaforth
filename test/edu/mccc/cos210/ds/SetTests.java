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

public class SetTests {
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
		report.append("\nTesting: ").append(SetTests.class.getSimpleName()).append("\n");
	}
	@AfterClass
	public static void tearDownClass() {
	    System.out.println(report.toString());
	}
	@Test
	public void newSetIsEmpty() {
		ISet<String> theSet = new Set<>();
		assertTrue(theSet.isEmpty());
		assertEquals(0, theSet.getSize());
	}
	@Test
	public void addToSetSizeIsCorrect() {
		ISet<String> theSet = new Set<>();
		theSet.add("Sheep");
		assertTrue(!theSet.isEmpty());
		assertEquals(1, theSet.getSize());
	}
	@Test
	public void addToSetTwoDifferentItemsSizeIsCorrect() {
		ISet<String> theSet = new Set<>();
		theSet.add("Sheep");
		theSet.add("Fish");
		assertTrue(!theSet.isEmpty());
		assertEquals(2, theSet.getSize());
	}
	@Test
	public void addToSetTwoSameItemsSizeIsCorrect() {
		ISet<String> theSet = new Set<>();
		theSet.add("Dog");
		theSet.add("Dog");
		assertTrue(!theSet.isEmpty());
		assertEquals(1, theSet.getSize());
	}
	@Test
	public void addToSetLotsSizeIsCorrect() {
		ISet<String> theSet = new Set<>();
		theSet.add("Sheep");
		theSet.add("Dog");
		theSet.add("Cat");
		theSet.add("Mouse");
		theSet.add("Cow");
		theSet.add("Fish");
		theSet.add("Duck");
		theSet.add("Bee");
		theSet.add("Horse");
		theSet.add("Cat");
		assertTrue(!theSet.isEmpty());
		assertEquals(9, theSet.getSize());
	}
	@Test
	public void addEmptySetToEmptySet() {
		ISet<String> theSet1 = new Set<>();
		ISet<String> theSet2 = new Set<>();
		theSet2.add(theSet1);
		assertTrue(theSet1.isEmpty());
		assertTrue(theSet2.isEmpty());
	}
	@Test
	public void addSetToEmptySet() {
		ISet<String> theSet1 = new Set<>();
		theSet1.add("Sheep");
		ISet<String> theSet2 = new Set<>();
		theSet2.add(theSet1);
		assertEquals(1, theSet1.getSize());
		assertEquals(1, theSet2.getSize());
	}
	@Test
	public void addEmptySetToSet() {
		ISet<String> theSet1 = new Set<>();
		ISet<String> theSet2 = new Set<>();
		theSet2.add("Sheep");
		theSet2.add(theSet1);
		assertEquals(0, theSet1.getSize());
		assertEquals(1, theSet2.getSize());
	}
	@Test
	public void addSetToSet() {
		ISet<String> theSet1 = new Set<>();
		theSet1.add("Sheep");
		ISet<String> theSet2 = new Set<>();
		theSet2.add("Dog");
		theSet2.add(theSet1);
		assertEquals(1, theSet1.getSize());
		assertEquals(2, theSet2.getSize());
	}
	@Test
	public void addSetToSetDup() {
		ISet<String> theSet1 = new Set<>();
		theSet1.add("Sheep");
		ISet<String> theSet2 = new Set<>();
		theSet2.add("Sheep");
		theSet2.add(theSet1);
		assertEquals(1, theSet1.getSize());
		assertEquals(1, theSet2.getSize());
	}
	@Test
	public void addSetToSetManyWithDups() {
		ISet<String> theSet1 = new Set<>();
		theSet1.add("Sheep");
		theSet1.add("Dog");
		theSet1.add("Cat");
		theSet1.add("Mouse");
		theSet1.add("Cow");
		theSet1.add("Fish");
		theSet1.add("Duck");
		theSet1.add("Bee");
		theSet1.add("Horse");
		theSet1.add("Pig");
		ISet<String> theSet2 = new Set<>();
		theSet2.add("Bird");
		theSet2.add("Dog");
		theSet2.add("Snake");
		theSet2.add("Bull");
		theSet2.add("Cow");
		theSet2.add("Frog");
		theSet2.add("Coyote");
		theSet2.add("Bee");
		theSet2.add("Elephant");
		theSet2.add("Giraffe");
		theSet2.add(theSet1);
		assertEquals(10, theSet1.getSize());
		assertEquals(17, theSet2.getSize());
	}
	@Test
	public void emptySetContains() {
		ISet<String> theSet = new Set<>();
		assertFalse(theSet.contains("Sheep"));
	}
	@Test
	public void setContains() {
		ISet<String> theSet = new Set<>();
		theSet.add("Sheep");
		assertTrue(theSet.contains("Sheep"));
	}
	@Test
	public void setDoesntContains() {
		ISet<String> theSet = new Set<>();
		theSet.add("Dog");
		assertFalse(theSet.contains("Sheep"));
	}
	@Test
	public void setContainsSetExact() {
		ISet<String> theSet1 = new Set<>();
		theSet1.add("Dog");
		theSet1.add("Sheep");
		ISet<String> theSet2 = new Set<>();
		theSet2.add("Dog");
		theSet2.add("Sheep");
		assertTrue(theSet1.contains(theSet2));
	}
	@Test
	public void setContainsSet() {
		ISet<String> theSet1 = new Set<>();
		theSet1.add("Dog");
		theSet1.add("Sheep");
		theSet1.add("Cat");
		ISet<String> theSet2 = new Set<>();
		theSet2.add("Dog");
		theSet2.add("Sheep");
		assertTrue(theSet1.contains(theSet2));
	}
	@Test
	public void setDoesntContainsSet() {
		ISet<String> theSet1 = new Set<>();
		theSet1.add("Dog");
		theSet1.add("Sheep");
		ISet<String> theSet2 = new Set<>();
		theSet2.add("Dog");
		theSet2.add("Sheep");
		theSet2.add("Cat");
		assertFalse(theSet1.contains(theSet2));
	}
	@Test
	public void removeOnlyItemFromEmptySet() {
		ISet<String> theSet = new Set<>();
		assertEquals(0, theSet.getSize());
		theSet.remove("Sheep");
		assertEquals(0, theSet.getSize());
	}
	@Test
	public void removeFromSet() {
		ISet<String> theSet = new Set<>();
		theSet.add("Sheep");
		assertEquals(1, theSet.getSize());
		theSet.remove("Sheep");
		assertEquals(0, theSet.getSize());
	}
	@Test
	public void removeMissingItemFromSet() {
		ISet<String> theSet = new Set<>();
		theSet.add("Sheep");
		assertEquals(1, theSet.getSize());
		theSet.remove("Frog");
		assertEquals(1, theSet.getSize());
	}
	@Test
	public void removeFirstItemFromEmptySet() {
		ISet<String> theSet = new Set<>();
		theSet.add("Sheep");
		theSet.add("Frog");
		assertEquals(2, theSet.getSize());
		theSet.remove("Sheep");
		assertEquals(1, theSet.getSize());
		assertFalse(theSet.contains("Sheep"));
	}
	@Test
	public void removeLastItemFromEmptySet() {
		ISet<String> theSet = new Set<>();
		theSet.add("Sheep");
		theSet.add("Frog");
		assertEquals(2, theSet.getSize());
		theSet.remove("Frog");
		assertEquals(1, theSet.getSize());
		assertFalse(theSet.contains("Frog"));
	}
	@Test
	public void removeMiddleItemFromEmptySet() {
		ISet<String> theSet = new Set<>();
		theSet.add("Sheep");
		theSet.add("Duck");
		theSet.add("Frog");
		assertEquals(3, theSet.getSize());
		theSet.remove("Duck");
		assertEquals(2, theSet.getSize());
		assertTrue(theSet.contains("Sheep"));
		assertTrue(theSet.contains("Frog"));
		assertFalse(theSet.contains("Duck"));
	}
	@Test
	public void removeTwoMiddleItemFromEmptySet() {
		ISet<String> theSet = new Set<>();
		theSet.add("Sheep");
		theSet.add("Duck");
		theSet.add("Frog");
		theSet.add("Goose");
		assertEquals(4, theSet.getSize());
		theSet.remove("Duck");
		assertEquals(3, theSet.getSize());
		theSet.remove("Frog");
		assertEquals(2, theSet.getSize());
		assertTrue(theSet.contains("Sheep"));
		assertTrue(theSet.contains("Goose"));
		assertFalse(theSet.contains("Duck"));
		assertFalse(theSet.contains("Frog"));
	}
	@Test
	public void union() {
		ISet<String> theSet1 = new Set<>();
		theSet1.add("Sheep");
		theSet1.add("Duck");
		theSet1.add("Frog");
		ISet<String> theSet2 = new Set<>();
		theSet2.add("Dog");
		theSet2.add("Sheep");
		theSet2.add("Cat");
		ISet<String> theSet = theSet1.union(theSet2);
		assertEquals(5, theSet.getSize());
		assertTrue(theSet.contains("Sheep"));
		assertTrue(theSet.contains("Duck"));
		assertTrue(theSet.contains("Frog"));
		assertTrue(theSet.contains("Dog"));
		assertTrue(theSet.contains("Cat"));
	}
	@Test
	public void intersection() {
		ISet<String> theSet1 = new Set<>();
		theSet1.add("Sheep");
		theSet1.add("Duck");
		theSet1.add("Frog");
		ISet<String> theSet2 = new Set<>();
		theSet2.add("Dog");
		theSet2.add("Sheep");
		theSet2.add("Cat");
		ISet<String> theSet = theSet1.intersection(theSet2);
		assertEquals(1, theSet.getSize());
		assertTrue(theSet.contains("Sheep"));
		assertFalse(theSet.contains("Duck"));
		assertFalse(theSet.contains("Frog"));
		assertFalse(theSet.contains("Dog"));
		assertFalse(theSet.contains("Cat"));
	}
}
