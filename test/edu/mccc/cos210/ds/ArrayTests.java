package edu.mccc.cos210.ds;

import static org.junit.Assert.assertEquals;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class ArrayTests {
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
		report.append("\nTesting: ").append(ArrayTests.class.getSimpleName()).append("\n");
	}
	@AfterClass
	public static void tearDownClass() {
	    System.out.println(report.toString());
	}
	@Test
	public void createArraySize() {
		for (int i = 0; i < 8; i++) {
			IArray<Integer> array = new Array<>(i);
			assertEquals(i, array.getSize());
		}
	}
	@Test
	public void createZeroSize() {
		IArray<Integer> array = new Array<>(0);		
		assertEquals(0, array.getSize());
	}
	@Test (expected=java.lang.NegativeArraySizeException.class)
	public void createNegativeSize() {
		new Array<>(-1);		
	}
	@Test
	public void setAndCheckValues() {
		IArray<Integer> array = new Array<>(4);
		array.set(0, 1);
		array.set(1, 3);
		array.set(2, 5);
		array.set(3, 7);
		assertEquals(Integer.valueOf(1), array.get(0));
		assertEquals(Integer.valueOf(3), array.get(1));
		assertEquals(Integer.valueOf(5), array.get(2));
		assertEquals(Integer.valueOf(7), array.get(3));
	}
	@Test
	public void setAndReplaceValue() {
		IArray<Integer> array = new Array<>(2);
		array.set(1, 1);
		assertEquals(Integer.valueOf(1), array.get(1));
		array.set(1, 3);
		assertEquals(Integer.valueOf(3), array.get(1));
	}
	@Test (expected=java.lang.ArrayIndexOutOfBoundsException.class)
	public void setBadIndex() {
		IArray<Integer> array = new Array<>(3);
		array.set(3, 1);
	}
	@Test (expected=java.lang.ArrayIndexOutOfBoundsException.class)
	public void setNegativeIndex() {
		IArray<Integer> array = new Array<>(3);
		array.set(3, 1);
	}
	@Test (expected=java.lang.ArrayIndexOutOfBoundsException.class)
	public void setZeroIndexZeroSize() {
		IArray<Integer> array = new Array<>(0);
		array.set(0, 1);
	}
	@Test
	public void setAndGetZeroIndexOneSize() {
		IArray<Integer> array = new Array<>(1);
		array.set(0, 1);
		assertEquals(Integer.valueOf(1), array.get(0));
	}
	@Test (expected=java.lang.ArrayIndexOutOfBoundsException.class)
	public void getZeroIndexZeroSize() {
		IArray<Integer> array = new Array<>(0);
		array.get(0);
	}
	@Test (expected=java.lang.ArrayIndexOutOfBoundsException.class)
	public void getBadIndex() {
		IArray<Integer> array = new Array<>(3);
		array.get(3);
	}
	@Test (expected=java.lang.ArrayIndexOutOfBoundsException.class)
	public void getNegativeIndex() {
		IArray<Integer> array = new Array<>(3);
		array.get(-1);
	}
	@Test
	public void arrayIteratorWorks() {
		IArray<String> array = new Array<>(4);
		array.set(0, "Dog");
		array.set(1, "Cat");
		array.set(2, "Horse");
		array.set(3, "Cow");
		StringBuilder sb = new StringBuilder();
		for (String s : array) {
			sb.append(s);
		}
		assertEquals("DogCatHorseCow", sb.toString());
	}
}
