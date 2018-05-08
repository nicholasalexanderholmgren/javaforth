package edu.mccc.cos210.ds;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import edu.mccc.cos210.MyClass;
import edu.mccc.cos210.MySubClass;

public class jjm_OrderedListTests {
private static StringBuilder report = new StringBuilder();

	//SETUP
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
	        report.append("\nTesting: ").append(OrderedList.class.getSimpleName()).append("\n");
		}

		@AfterClass
		public static void tearDownClass() {
		    System.out.println(report.toString());
		}

	//TESTING
	//addingContains
	@Test
	public void addingContains_singleEntry() {
		final int ENTRY_1 = 5;

		IOrderedList<Integer> testOrderedList = new OrderedList<>();
		testOrderedList.add(ENTRY_1);
		assertEquals(true, testOrderedList.contains(ENTRY_1));
	}

	@Test
	public void addingContains_manyEntries() {
		final int TEST_SIZE = 10;

		IOrderedList<Integer> testOrderedList = new OrderedList<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testOrderedList.add(i);
			assertEquals(true, testOrderedList.contains(i));
		}
	}

	@Test
	public void addingDoesNotContain() {
		final int TEST_SIZE = 5;

		IOrderedList<Integer> testOrderedList = new OrderedList<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testOrderedList.add(i);
		}
		assertEquals(false, testOrderedList.contains(TEST_SIZE));
	}

	@Test
	public void emptyListContains() {
		IOrderedList<Integer> testOrderedList = new OrderedList<>();
		assertEquals(false, testOrderedList.contains(0));
	}
	//addingAddsLast
	@Test
	public void addingAddsLast() {
		final int ENTRY_1 = 5;
		final int ENTRY_2 = 7;

		IOrderedList<Integer> testOrderedList = new OrderedList<>();
		testOrderedList.add(ENTRY_1);
		assertEquals(true, testOrderedList.getLast().equals(ENTRY_1));
		testOrderedList.add(ENTRY_2);
		assertEquals(true, testOrderedList.getLast().equals(ENTRY_2));
	}
	//integrity_compareToClone
	@Test
	public void integrity_compareToClone() {
		final int TEST_SIZE = 10;

		IOrderedList<Integer> testOrderedList = new OrderedList<>();
		IOrderedList<Integer> compareToOrderedList = new OrderedList<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testOrderedList.add(i);
			compareToOrderedList.add(i);
		}
		assertEquals(true, testOrderedList.toString().equals(compareToOrderedList.toString()));
	}
	//integrity_compareToSB
	@Test
	public void integrity_compareToSB() {
		final int TEST_SIZE = 10;

		IOrderedList<Integer> testOrderedList = new OrderedList<>();
		StringBuilder testOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		for (int i = 0; i < TEST_SIZE; i++) {
			testOrderedList.add(i);
			compareToSB.append(i);
		}
		for (int i = 0; i < TEST_SIZE; i++) {
			testOutput.append(testOrderedList.removeFirst());
		}
		assertEquals(true, testOutput.toString().equals(compareToSB.toString()));
	}
	//makeTypeInt
	@Test
	public void makeTypeInt() {
		final int ENTRY_1 = 5;
		final int ENTRY_2 = 10;
		final int ENTRY_3 = 15;
		IOrderedList<Integer> testOrderedList = new OrderedList<>();
		IOrderedList<Integer> compareToOrderedList = new OrderedList<>();
		StringBuilder testOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();

		testOrderedList.add(ENTRY_1);
		assertEquals(true, testOrderedList.contains(ENTRY_1));
		assertEquals(true, testOrderedList.getLast().equals(ENTRY_1));
		compareToOrderedList.add(ENTRY_1);
		compareToSB.append(ENTRY_1);

		testOrderedList.add(ENTRY_2);
		assertEquals(true, testOrderedList.contains(ENTRY_2));
		assertEquals(true, testOrderedList.getLast().equals(ENTRY_2));
		compareToOrderedList.add(ENTRY_2);
		compareToSB.append(ENTRY_2);

		testOrderedList.add(ENTRY_3);
		assertEquals(true, testOrderedList.contains(ENTRY_3));
		assertEquals(true, testOrderedList.getLast().equals(ENTRY_3));
		compareToOrderedList.add(ENTRY_3);
		compareToSB.append(ENTRY_3);

		assertEquals(true, testOrderedList.toString().equals(compareToOrderedList.toString()));
		for (int i = 0; i < 3; i++) {
			testOutput.append(testOrderedList.removeFirst());
		}
		assertEquals(true, testOutput.toString().equals(compareToSB.toString()));
	}
	//makeTypeDouble
	@Test
	public void makeTypeDouble() {
		final double ENTRY_1 = 5.0;
		final double ENTRY_2 = 10.0;
		final double ENTRY_3 = 15.0;
		IOrderedList<Double> testOrderedList = new OrderedList<>();
		IOrderedList<Double> compareToOrderedList = new OrderedList<>();
		StringBuilder testOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();

		testOrderedList.add(ENTRY_1);
		assertEquals(true, testOrderedList.contains(ENTRY_1));
		assertEquals(true, testOrderedList.getLast().equals(ENTRY_1));
		compareToOrderedList.add(ENTRY_1);
		compareToSB.append(ENTRY_1);

		testOrderedList.add(ENTRY_2);
		assertEquals(true, testOrderedList.contains(ENTRY_2));
		assertEquals(true, testOrderedList.getLast().equals(ENTRY_2));
		compareToOrderedList.add(ENTRY_2);
		compareToSB.append(ENTRY_2);

		testOrderedList.add(ENTRY_3);
		assertEquals(true, testOrderedList.contains(ENTRY_3));
		assertEquals(true, testOrderedList.getLast().equals(ENTRY_3));
		compareToOrderedList.add(ENTRY_3);
		compareToSB.append(ENTRY_3);

		assertEquals(true, testOrderedList.toString().equals(compareToOrderedList.toString()));
		for (int i = 0; i < 3; i++) {
			testOutput.append(testOrderedList.removeFirst());
		}
		assertEquals(true, testOutput.toString().equals(compareToSB.toString()));
	}
	//makeTypeBoolean
	@Test
	public void makeTypeBoolean() {
		final boolean ENTRY_1 = false;
		final boolean ENTRY_2 = true;
		final boolean ENTRY_3 = false;
		IOrderedList<Boolean> testOrderedList = new OrderedList<>();
		IOrderedList<Boolean> compareToOrderedList = new OrderedList<>();
		StringBuilder testOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();

		testOrderedList.add(ENTRY_1);
		assertEquals(true, testOrderedList.contains(ENTRY_1));
		assertEquals(true, testOrderedList.getLast().equals(ENTRY_1));
		compareToOrderedList.add(ENTRY_1);
		compareToSB.append(ENTRY_1);

		testOrderedList.add(ENTRY_2);
		assertEquals(true, testOrderedList.contains(ENTRY_2));
		assertEquals(true, testOrderedList.getLast().equals(ENTRY_2));
		compareToOrderedList.add(ENTRY_2);
		compareToSB.append(ENTRY_2);

		testOrderedList.add(ENTRY_3);
		assertEquals(true, testOrderedList.contains(ENTRY_3));
		assertEquals(true, testOrderedList.getLast().equals(ENTRY_3));
		compareToOrderedList.add(ENTRY_3);
		compareToSB.append(ENTRY_3);

		assertEquals(true, testOrderedList.toString().equals(compareToOrderedList.toString()));
		for (int i = 0; i < 3; i++) {
			testOutput.append(testOrderedList.removeFirst());
		}
		assertEquals(true, testOutput.toString().equals(compareToSB.toString()));
	}
	//makeTypeChar
	@Test
	public void makeTypeChar() {
		final char ENTRY_1 = 'a';
		final char ENTRY_2 = 'x';
		final char ENTRY_3 = 'e';
		IOrderedList<Character> testOrderedList = new OrderedList<>();
		IOrderedList<Character> compareToOrderedList = new OrderedList<>();
		StringBuilder testOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();

		testOrderedList.add(ENTRY_1);
		assertEquals(true, testOrderedList.contains(ENTRY_1));
		assertEquals(true, testOrderedList.getLast().equals(ENTRY_1));
		compareToOrderedList.add(ENTRY_1);
		compareToSB.append(ENTRY_1);

		testOrderedList.add(ENTRY_2);
		assertEquals(true, testOrderedList.contains(ENTRY_2));
		assertEquals(true, testOrderedList.getLast().equals(ENTRY_2));
		compareToOrderedList.add(ENTRY_2);
		compareToSB.append(ENTRY_2);

		testOrderedList.add(ENTRY_3);
		assertEquals(true, testOrderedList.contains(ENTRY_3));
		assertEquals(true, testOrderedList.getLast().equals(ENTRY_3));
		compareToOrderedList.add(ENTRY_3);
		compareToSB.append(ENTRY_3);

		assertEquals(true, testOrderedList.toString().equals(compareToOrderedList.toString()));
		for (int i = 0; i < 3; i++) {
			testOutput.append(testOrderedList.removeFirst());
		}
		assertEquals(true, testOutput.toString().equals(compareToSB.toString()));
	}
	//makeTypeString
	@Test
	public void makeTypeString() {
		final String ENTRY_1 = "hello";
		final String ENTRY_2 = "mister";
		final String ENTRY_3 = "bilgof";
		IOrderedList<String> testOrderedList = new OrderedList<>();
		IOrderedList<String> compareToOrderedList = new OrderedList<>();
		StringBuilder testOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();

		testOrderedList.add(ENTRY_1);
		assertEquals(true, testOrderedList.contains(ENTRY_1));
		assertEquals(true, testOrderedList.getLast().equals(ENTRY_1));
		compareToOrderedList.add(ENTRY_1);
		compareToSB.append(ENTRY_1);

		testOrderedList.add(ENTRY_2);
		assertEquals(true, testOrderedList.contains(ENTRY_2));
		assertEquals(true, testOrderedList.getLast().equals(ENTRY_2));
		compareToOrderedList.add(ENTRY_2);
		compareToSB.append(ENTRY_2);

		testOrderedList.add(ENTRY_3);
		assertEquals(true, testOrderedList.contains(ENTRY_3));
		assertEquals(true, testOrderedList.getLast().equals(ENTRY_3));
		compareToOrderedList.add(ENTRY_3);
		compareToSB.append(ENTRY_3);

		assertEquals(true, testOrderedList.toString().equals(compareToOrderedList.toString()));
		for (int i = 0; i < 3; i++) {
			testOutput.append(testOrderedList.removeFirst());
		}
		assertEquals(true, testOutput.toString().equals(compareToSB.toString()));
	}
	//makeTypeMyClass
	public void makeTypeMyClass() {
		final MyClass ENTRY_1 = new MyClass();
		final MyClass ENTRY_2 = new MyClass();
		final MyClass ENTRY_3 = new MyClass();
		IOrderedList<MyClass> testOrderedList = new OrderedList<>();
		IOrderedList<MyClass> compareToOrderedList = new OrderedList<>();
		StringBuilder testOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();

		testOrderedList.add(ENTRY_1);
		assertEquals(true, testOrderedList.contains(ENTRY_1));
		assertEquals(true, testOrderedList.getLast().toString().equals(ENTRY_1.toString()));
		compareToOrderedList.add(ENTRY_1);
		compareToSB.append(ENTRY_1);

		testOrderedList.add(ENTRY_2);
		assertEquals(true, testOrderedList.contains(ENTRY_2));
		assertEquals(true, testOrderedList.getLast().toString().equals(ENTRY_2.toString()));
		compareToOrderedList.add(ENTRY_2);
		compareToSB.append(ENTRY_2);

		testOrderedList.add(ENTRY_3);
		assertEquals(true, testOrderedList.contains(ENTRY_3));
		assertEquals(true, testOrderedList.getLast().toString().equals(ENTRY_3.toString()));
		compareToOrderedList.add(ENTRY_3);
		compareToSB.append(ENTRY_3);

		assertEquals(true, testOrderedList.toString().equals(compareToOrderedList.toString()));
		for (int i = 0; i < 3; i++) {
			testOutput.append(testOrderedList.removeFirst().toString());
		}
		assertEquals(true, testOutput.toString().equals(compareToSB.toString()));
	}
	//makeTypeMySubClass
	public void makeTypeMySubclass() {
		final MySubClass ENTRY_1 = new MySubClass();
		final MySubClass ENTRY_2 = new MySubClass();
		final MySubClass ENTRY_3 = new MySubClass();
		IOrderedList<MySubClass> testOrderedList = new OrderedList<>();
		IOrderedList<MySubClass> compareToOrderedList = new OrderedList<>();
		StringBuilder testOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();

		testOrderedList.add(ENTRY_1);
		assertEquals(true, testOrderedList.contains(ENTRY_1));
		assertEquals(true, testOrderedList.getLast().equals(ENTRY_1));
		compareToOrderedList.add(ENTRY_1);
		compareToSB.append(ENTRY_1);

		testOrderedList.add(ENTRY_2);
		assertEquals(true, testOrderedList.contains(ENTRY_2));
		assertEquals(true, testOrderedList.getLast().equals(ENTRY_2));
		compareToOrderedList.add(ENTRY_2);
		compareToSB.append(ENTRY_2);

		testOrderedList.add(ENTRY_3);
		assertEquals(true, testOrderedList.contains(ENTRY_3));
		assertEquals(true, testOrderedList.getLast().equals(ENTRY_3));
		compareToOrderedList.add(ENTRY_3);
		compareToSB.append(ENTRY_3);

		assertEquals(true, testOrderedList.toString().equals(compareToOrderedList.toString()));
		for (int i = 0; i < 3; i++) {
			testOutput.append(testOrderedList.removeFirst());
		}
		assertEquals(true, testOutput.toString().equals(compareToSB.toString()));
	}
}
