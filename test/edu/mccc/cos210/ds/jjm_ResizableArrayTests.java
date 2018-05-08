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

public class jjm_ResizableArrayTests {
	//Setup
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
        report.append("\nTesting: ").append(ResizableArray.class.getSimpleName()).append("\n");
	}

	@AfterClass
	public static void tearDownClass() {
	    System.out.println(report.toString());
	}

	//Tests
	@Test
	public void resizing_check_null() {
		final int TEST_SIZE = 10;
		IResizableArray<Integer> testResizableArray = new ResizableArray<>(TEST_SIZE);
		testResizableArray.resize(TEST_SIZE * 2);
		for (int i = TEST_SIZE; i < TEST_SIZE * 2; i++) {
			assertEquals(null, testResizableArray.get(i));
		}
	}

	@Test
	public void resizing_check_init() {
		final int TEST_SIZE = 10;
		final int ENTRY = 5;

		IResizableArray<Integer> testResizableArray = new ResizableArray<>(TEST_SIZE);
		testResizableArray.resize(TEST_SIZE * 2, ENTRY);
		for (int i = TEST_SIZE; i < TEST_SIZE * 2; i++) {
			assertEquals(true, testResizableArray.get(i).equals(ENTRY));
		}
	}

	@Test
	public void resizing_newData_null() {
		final int TEST_SIZE = 10;

		IResizableArray<Integer> testResizableArray = new ResizableArray<>(TEST_SIZE);
		for (int i = 0; i < TEST_SIZE; i++) {
			testResizableArray.set(i, i);
		}
		testResizableArray.resize(TEST_SIZE * 2);
		for (int i = TEST_SIZE; i < TEST_SIZE * 2; i++) {
			testResizableArray.set(i, i);
		}
		for (int i = TEST_SIZE; i < TEST_SIZE * 2; i++) {
			assertEquals(true, testResizableArray.get(i).equals(i));
		}
	}

	@Test
	public void resizing_newData_init() {
		final int TEST_SIZE = 10;

		IResizableArray<Integer> testResizableArray = new ResizableArray<>(TEST_SIZE);
		for (int i = 0; i < TEST_SIZE; i++) {
			testResizableArray.set(i, i);
		}
		testResizableArray.resize(TEST_SIZE * 2, 8);
		for (int i = TEST_SIZE; i < TEST_SIZE * 2; i++) {
			testResizableArray.set(i, i);
		}
		for (int i = TEST_SIZE; i < TEST_SIZE * 2; i++) {
			assertEquals(true, testResizableArray.get(i).equals(i));
		}
	}

	@Test
	public void resizing_doesResize_null() {
		final int TEST_SIZE = 10;

		IResizableArray<Integer> testResizableArray = new ResizableArray<>(1);
		testResizableArray.resize(TEST_SIZE);
		assertEquals(TEST_SIZE, testResizableArray.getSize());
	}

	@Test
	public void resizing_doesResize_init() {
		final int TEST_SIZE = 20;

		IResizableArray<Integer> testResizableArray = new ResizableArray<>(1);
		testResizableArray.resize(TEST_SIZE, 0);
		assertEquals(TEST_SIZE, testResizableArray.getSize());
	}
	//resizing_init_doesRetain
	@Test
	public void resizing_doesRetain_null() {
		final int TEST_SIZE = 10;

		IResizableArray<Integer> testResizableArray = new ResizableArray<>(TEST_SIZE);
		for (int i = 0; i < TEST_SIZE; i++) {
			testResizableArray.set(i, i);
		}
		testResizableArray.resize(TEST_SIZE * 2);
		for (int i = TEST_SIZE; i < TEST_SIZE * 2; i++) {
			testResizableArray.set(i, i);
		}
		for (int i = 0; i < TEST_SIZE; i++) {
			assertEquals(true, testResizableArray.get(i).equals(i));
		}
	}

	@Test
	public void resizing_doesRetain_init() {
		final int TEST_SIZE = 10;

		IResizableArray<Integer> testResizableArray = new ResizableArray<>(TEST_SIZE);
		for (int i = 0; i < TEST_SIZE; i++) {
			testResizableArray.set(i, i);
		}
		testResizableArray.resize(TEST_SIZE * 2, 8);
		for (int i = TEST_SIZE; i < TEST_SIZE * 2; i++) {
			testResizableArray.set(i, i);
		}
		for (int i = 0; i < TEST_SIZE; i++) {
			assertEquals(true, testResizableArray.get(i).equals(i));
		}
	}

	public void resizingToZero_null() {
		IResizableArray<Integer> testResizableArray = new ResizableArray<>(1);
		testResizableArray.resize(0);
		assertEquals(0, testResizableArray.getSize());
	}

	public void resizingToZero_init() {
		IResizableArray<Integer> testResizableArray = new ResizableArray<>(1);
		testResizableArray.resize(0, 0);
		assertEquals(0, testResizableArray.getSize());
	}

	@Test(expected=NegativeArraySizeException.class)
	public void resizingToNegative_init() {
		IResizableArray<Integer> testResizableArray = new ResizableArray<>(1);
		testResizableArray.resize(-2, 0);
	}

	@Test(expected=NegativeArraySizeException.class)
	public void resizingToNegative_null() {
		IResizableArray<Integer> testResizableArray = new ResizableArray<>(1);
		testResizableArray.resize(-2);
	}

	@Test
	public void integrity_compareToClone_null() {
		final int TEST_SIZE = 10;

		IResizableArray<Integer> testResizableArray = new ResizableArray<>(TEST_SIZE);
		IResizableArray<Integer> compareToResizableArray = new ResizableArray<>(TEST_SIZE);
		for (int i = 0; i < TEST_SIZE; i++) {
			testResizableArray.set(i, i);
			compareToResizableArray.set(i, i);
		}
		testResizableArray.resize(TEST_SIZE * 2);
		compareToResizableArray.resize(TEST_SIZE * 2);
		assertEquals(true, testResizableArray.toString().equals(compareToResizableArray.toString()));
		for (int i = TEST_SIZE; i < TEST_SIZE * 2; i++) {
			testResizableArray.set(i, i);
			compareToResizableArray.set(i, i);
		}
		assertEquals(true, testResizableArray.toString().equals(compareToResizableArray.toString()));
	}

	@Test
	public void integrity_compareToClone_init() {
		final int TEST_SIZE = 10;

		IResizableArray<Integer> testResizableArray = new ResizableArray<>(TEST_SIZE);
		IResizableArray<Integer> compareToResizableArray = new ResizableArray<>(TEST_SIZE);
		for (int i = 0; i < TEST_SIZE; i++) {
			testResizableArray.set(i, i);
			compareToResizableArray.set(i, i);
		}
		testResizableArray.resize(TEST_SIZE * 2, 13);
		compareToResizableArray.resize(TEST_SIZE * 2, 13);
		assertEquals(true, testResizableArray.toString().equals(compareToResizableArray.toString()));
		for (int i = TEST_SIZE; i < TEST_SIZE * 2; i++) {
			testResizableArray.set(i, i);
			compareToResizableArray.set(i, i);
		}
		assertEquals(true, testResizableArray.toString().equals(compareToResizableArray.toString()));
	}

	@Test
	public void integrity_compareToSB_null() {
		final int TEST_SIZE = 10;

		IResizableArray<Integer> testResizableArray = new ResizableArray<>(TEST_SIZE);
		StringBuilder testOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		for (int i = 0; i < TEST_SIZE; i++) {
			testResizableArray.set(i, i);
			compareToSB.append(i);
		}
		testResizableArray.resize(TEST_SIZE * 2);
		for (int i = TEST_SIZE; i < TEST_SIZE * 2; i++) {
			testResizableArray.set(i, i);
			compareToSB.append(i);
		}
		for (int i = 0; i < TEST_SIZE * 2; i++) {
			testOutput.append(testResizableArray.get(i));
		}
		assertEquals(true, testOutput.toString().equals(compareToSB.toString()));
	}

	@Test
	public void integrity_compareToSB_init() {
		final int TEST_SIZE = 10;

		IResizableArray<Integer> testResizableArray = new ResizableArray<>(TEST_SIZE);
		StringBuilder testOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		for (int i = 0; i < TEST_SIZE; i++) {
			testResizableArray.set(i, i);
			compareToSB.append(i);
		}
		testResizableArray.resize(TEST_SIZE * 2, 0);
		for (int i = TEST_SIZE; i < TEST_SIZE * 2; i++) {
			testResizableArray.set(i, i);
			compareToSB.append(i);
		}
		for (int i = 0; i < TEST_SIZE * 2; i++) {
			testOutput.append(testResizableArray.get(i));
		}
		assertEquals(true, testOutput.toString().equals(compareToSB.toString()));
	}
	//makeTypeInt_null
	@Test
	public void makeTypeInt_null() {
		final int ENTRY_1 = 5;
		final int ENTRY_2 = 10;
		final int ENTRY_3 = 15;
		final int INIT = 0;
		IResizableArray<Integer> testResizableArray = new ResizableArray<>(2);
		IResizableArray<Integer> compareToRA_null = new ResizableArray<>(2);
		IResizableArray<Integer> compareToRA_init = new ResizableArray<>(2);
		StringBuilder testOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();

		assertEquals(2, testResizableArray.getSize());

		testResizableArray.set(0, ENTRY_1);
		assertEquals(true, testResizableArray.get(0).equals(ENTRY_1));
		compareToRA_null.set(0, ENTRY_1);
		compareToRA_init.set(0, ENTRY_1);
		compareToSB.append(ENTRY_1);

		testResizableArray.set(1, ENTRY_2);
		assertEquals(true, testResizableArray.get(1).equals(ENTRY_2));
		compareToRA_null.set(1, ENTRY_2);
		compareToRA_init.set(1, ENTRY_2);
		compareToSB.append(ENTRY_2);

		testResizableArray.resize(3);
		assertEquals(3, testResizableArray.getSize());
		compareToRA_null.resize(3);
		compareToRA_init.resize(3, INIT);

		assertEquals(3, testResizableArray.getSize());
		testResizableArray.set(2, ENTRY_3);
		assertEquals(true, testResizableArray.get(0).equals(ENTRY_1));
		assertEquals(true, testResizableArray.get(1).equals(ENTRY_2));
		assertEquals(true, testResizableArray.get(2).equals(ENTRY_3));
		compareToRA_null.set(2, ENTRY_3);
		compareToRA_init.set(2, ENTRY_3);
		compareToSB.append(ENTRY_3);

		assertEquals(true, testResizableArray.toString().equals(compareToRA_null.toString()));
		assertEquals(true, testResizableArray.toString().equals(compareToRA_init.toString()));

		for (int i = 0; i < 3; i++) {
			testOutput.append(testResizableArray.get(i));
		}
		assertEquals(true, testOutput.toString().equals(compareToSB.toString()));
	}

	@Test
	public void makeTypeInt_init() {
		final int ENTRY_1 = 5;
		final int ENTRY_2 = 10;
		final int ENTRY_3 = 15;
		final int INIT = 0;
		IResizableArray<Integer> testResizableArray = new ResizableArray<>(2);
		IResizableArray<Integer> compareToRA_null = new ResizableArray<>(2);
		IResizableArray<Integer> compareToRA_init = new ResizableArray<>(2);
		StringBuilder testOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();

		assertEquals(2, testResizableArray.getSize());

		testResizableArray.set(0, ENTRY_1);
		assertEquals(true, testResizableArray.get(0).equals(ENTRY_1));
		compareToRA_null.set(0, ENTRY_1);
		compareToRA_init.set(0, ENTRY_1);
		compareToSB.append(ENTRY_1);

		testResizableArray.set(1, ENTRY_2);
		assertEquals(true, testResizableArray.get(1).equals(ENTRY_2));
		compareToRA_null.set(1, ENTRY_2);
		compareToRA_init.set(1, ENTRY_2);
		compareToSB.append(ENTRY_2);

		testResizableArray.resize(3, INIT);
		assertEquals(3, testResizableArray.getSize());
		compareToRA_null.resize(3);
		compareToRA_init.resize(3, INIT);

		assertEquals(3, testResizableArray.getSize());
		testResizableArray.set(2, ENTRY_3);
		assertEquals(true, testResizableArray.get(0).equals(ENTRY_1));
		assertEquals(true, testResizableArray.get(1).equals(ENTRY_2));
		assertEquals(true, testResizableArray.get(2).equals(ENTRY_3));
		compareToRA_null.set(2, ENTRY_3);
		compareToRA_init.set(2, ENTRY_3);
		compareToSB.append(ENTRY_3);

		assertEquals(true, testResizableArray.toString().equals(compareToRA_null.toString()));
		assertEquals(true, testResizableArray.toString().equals(compareToRA_init.toString()));

		for (int i = 0; i < 3; i++) {
			testOutput.append(testResizableArray.get(i));
		}
		assertEquals(true, testOutput.toString().equals(compareToSB.toString()));
	}
	//makeTypeDouble
	@Test
	public void makeTypeDouble_null() {
		final double ENTRY_1 = 5.0;
		final double ENTRY_2 = 10.0;
		final double ENTRY_3 = 15.0;
		final double INIT = 0;
		IResizableArray<Double> testResizableArray = new ResizableArray<>(2);
		IResizableArray<Double> compareToRA_null = new ResizableArray<>(2);
		IResizableArray<Double> compareToRA_init = new ResizableArray<>(2);
		StringBuilder testOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();

		assertEquals(2, testResizableArray.getSize());

		testResizableArray.set(0, ENTRY_1);
		assertEquals(true, testResizableArray.get(0).equals(ENTRY_1));
		compareToRA_null.set(0, ENTRY_1);
		compareToRA_init.set(0, ENTRY_1);
		compareToSB.append(ENTRY_1);

		testResizableArray.set(1, ENTRY_2);
		assertEquals(true, testResizableArray.get(1).equals(ENTRY_2));
		compareToRA_null.set(1, ENTRY_2);
		compareToRA_init.set(1, ENTRY_2);
		compareToSB.append(ENTRY_2);

		testResizableArray.resize(3);
		assertEquals(3, testResizableArray.getSize());
		compareToRA_null.resize(3);
		compareToRA_init.resize(3, INIT);

		assertEquals(3, testResizableArray.getSize());
		testResizableArray.set(2, ENTRY_3);
		assertEquals(true, testResizableArray.get(0).equals(ENTRY_1));
		assertEquals(true, testResizableArray.get(1).equals(ENTRY_2));
		assertEquals(true, testResizableArray.get(2).equals(ENTRY_3));
		compareToRA_null.set(2, ENTRY_3);
		compareToRA_init.set(2, ENTRY_3);
		compareToSB.append(ENTRY_3);

		assertEquals(true, testResizableArray.toString().equals(compareToRA_null.toString()));
		assertEquals(true, testResizableArray.toString().equals(compareToRA_init.toString()));

		for (int i = 0; i < 3; i++) {
			testOutput.append(testResizableArray.get(i));
		}
		assertEquals(true, testOutput.toString().equals(compareToSB.toString()));
	}

	@Test
	public void makeTypeDouble_init() {
		final double ENTRY_1 = 5.0;
		final double ENTRY_2 = 10.0;
		final double ENTRY_3 = 15.0;
		final double INIT = 0;
		IResizableArray<Double> testResizableArray = new ResizableArray<>(2);
		IResizableArray<Double> compareToRA_null = new ResizableArray<>(2);
		IResizableArray<Double> compareToRA_init = new ResizableArray<>(2);
		StringBuilder testOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();

		assertEquals(2, testResizableArray.getSize());

		testResizableArray.set(0, ENTRY_1);
		assertEquals(true, testResizableArray.get(0).equals(ENTRY_1));
		compareToRA_null.set(0, ENTRY_1);
		compareToRA_init.set(0, ENTRY_1);
		compareToSB.append(ENTRY_1);

		testResizableArray.set(1, ENTRY_2);
		assertEquals(true, testResizableArray.get(1).equals(ENTRY_2));
		compareToRA_null.set(1, ENTRY_2);
		compareToRA_init.set(1, ENTRY_2);
		compareToSB.append(ENTRY_2);

		testResizableArray.resize(3, INIT);
		assertEquals(3, testResizableArray.getSize());
		compareToRA_null.resize(3);
		compareToRA_init.resize(3, INIT);

		assertEquals(3, testResizableArray.getSize());
		testResizableArray.set(2, ENTRY_3);
		assertEquals(true, testResizableArray.get(0).equals(ENTRY_1));
		assertEquals(true, testResizableArray.get(1).equals(ENTRY_2));
		assertEquals(true, testResizableArray.get(2).equals(ENTRY_3));
		compareToRA_null.set(2, ENTRY_3);
		compareToRA_init.set(2, ENTRY_3);
		compareToSB.append(ENTRY_3);

		assertEquals(true, testResizableArray.toString().equals(compareToRA_null.toString()));
		assertEquals(true, testResizableArray.toString().equals(compareToRA_init.toString()));

		for (int i = 0; i < 3; i++) {
			testOutput.append(testResizableArray.get(i));
		}
		assertEquals(true, testOutput.toString().equals(compareToSB.toString()));
	}
	//makeTypeBoolean
	@Test
	public void makeTypeBoolean_null() {
		final boolean ENTRY_1 = true;
		final boolean ENTRY_2 = false;
		final boolean ENTRY_3 = true;
		final boolean INIT = true;
		IResizableArray<Boolean> testResizableArray = new ResizableArray<>(2);
		IResizableArray<Boolean> compareToRA_null = new ResizableArray<>(2);
		IResizableArray<Boolean> compareToRA_init = new ResizableArray<>(2);
		StringBuilder testOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();

		assertEquals(2, testResizableArray.getSize());

		testResizableArray.set(0, ENTRY_1);
		assertEquals(true, testResizableArray.get(0).equals(ENTRY_1));
		compareToRA_null.set(0, ENTRY_1);
		compareToRA_init.set(0, ENTRY_1);
		compareToSB.append(ENTRY_1);

		testResizableArray.set(1, ENTRY_2);
		assertEquals(true, testResizableArray.get(1).equals(ENTRY_2));
		compareToRA_null.set(1, ENTRY_2);
		compareToRA_init.set(1, ENTRY_2);
		compareToSB.append(ENTRY_2);

		testResizableArray.resize(3);
		assertEquals(3, testResizableArray.getSize());
		compareToRA_null.resize(3);
		compareToRA_init.resize(3, INIT);

		assertEquals(3, testResizableArray.getSize());
		testResizableArray.set(2, ENTRY_3);
		assertEquals(true, testResizableArray.get(0).equals(ENTRY_1));
		assertEquals(true, testResizableArray.get(1).equals(ENTRY_2));
		assertEquals(true, testResizableArray.get(2).equals(ENTRY_3));
		compareToRA_null.set(2, ENTRY_3);
		compareToRA_init.set(2, ENTRY_3);
		compareToSB.append(ENTRY_3);

		assertEquals(true, testResizableArray.toString().equals(compareToRA_null.toString()));
		assertEquals(true, testResizableArray.toString().equals(compareToRA_init.toString()));

		for (int i = 0; i < 3; i++) {
			testOutput.append(testResizableArray.get(i));
		}
		assertEquals(true, testOutput.toString().equals(compareToSB.toString()));
	}

	@Test
	public void makeTypeBoolean_init() {
		final boolean ENTRY_1 = true;
		final boolean ENTRY_2 = false;
		final boolean ENTRY_3 = true;
		final boolean INIT = true;
		IResizableArray<Boolean> testResizableArray = new ResizableArray<>(2);
		IResizableArray<Boolean> compareToRA_null = new ResizableArray<>(2);
		IResizableArray<Boolean> compareToRA_init = new ResizableArray<>(2);
		StringBuilder testOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();

		assertEquals(2, testResizableArray.getSize());

		testResizableArray.set(0, ENTRY_1);
		assertEquals(true, testResizableArray.get(0).equals(ENTRY_1));
		compareToRA_null.set(0, ENTRY_1);
		compareToRA_init.set(0, ENTRY_1);
		compareToSB.append(ENTRY_1);

		testResizableArray.set(1, ENTRY_2);
		assertEquals(true, testResizableArray.get(1).equals(ENTRY_2));
		compareToRA_null.set(1, ENTRY_2);
		compareToRA_init.set(1, ENTRY_2);
		compareToSB.append(ENTRY_2);

		testResizableArray.resize(3, INIT);
		assertEquals(3, testResizableArray.getSize());
		compareToRA_null.resize(3);
		compareToRA_init.resize(3, INIT);

		assertEquals(3, testResizableArray.getSize());
		testResizableArray.set(2, ENTRY_3);
		assertEquals(true, testResizableArray.get(0).equals(ENTRY_1));
		assertEquals(true, testResizableArray.get(1).equals(ENTRY_2));
		assertEquals(true, testResizableArray.get(2).equals(ENTRY_3));
		compareToRA_null.set(2, ENTRY_3);
		compareToRA_init.set(2, ENTRY_3);
		compareToSB.append(ENTRY_3);

		assertEquals(true, testResizableArray.toString().equals(compareToRA_null.toString()));
		assertEquals(true, testResizableArray.toString().equals(compareToRA_init.toString()));

		for (int i = 0; i < 3; i++) {
			testOutput.append(testResizableArray.get(i));
		}
		assertEquals(true, testOutput.toString().equals(compareToSB.toString()));
	}
	//makeTypeChar
	@Test
	public void makeTypeChar_null() {
		final char ENTRY_1 = 'a';
		final char ENTRY_2 = 'g';
		final char ENTRY_3 = 'm';
		final char INIT = 'e';
		IResizableArray<Character> testResizableArray = new ResizableArray<>(2);
		IResizableArray<Character> compareToRA_null = new ResizableArray<>(2);
		IResizableArray<Character> compareToRA_init = new ResizableArray<>(2);
		StringBuilder testOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();

		assertEquals(2, testResizableArray.getSize());

		testResizableArray.set(0, ENTRY_1);
		assertEquals(true, testResizableArray.get(0).equals(ENTRY_1));
		compareToRA_null.set(0, ENTRY_1);
		compareToRA_init.set(0, ENTRY_1);
		compareToSB.append(ENTRY_1);

		testResizableArray.set(1, ENTRY_2);
		assertEquals(true, testResizableArray.get(1).equals(ENTRY_2));
		compareToRA_null.set(1, ENTRY_2);
		compareToRA_init.set(1, ENTRY_2);
		compareToSB.append(ENTRY_2);

		testResizableArray.resize(3);
		assertEquals(3, testResizableArray.getSize());
		compareToRA_null.resize(3);
		compareToRA_init.resize(3, INIT);

		assertEquals(3, testResizableArray.getSize());
		testResizableArray.set(2, ENTRY_3);
		assertEquals(true, testResizableArray.get(0).equals(ENTRY_1));
		assertEquals(true, testResizableArray.get(1).equals(ENTRY_2));
		assertEquals(true, testResizableArray.get(2).equals(ENTRY_3));
		compareToRA_null.set(2, ENTRY_3);
		compareToRA_init.set(2, ENTRY_3);
		compareToSB.append(ENTRY_3);

		assertEquals(true, testResizableArray.toString().equals(compareToRA_null.toString()));
		assertEquals(true, testResizableArray.toString().equals(compareToRA_init.toString()));

		for (int i = 0; i < 3; i++) {
			testOutput.append(testResizableArray.get(i));
		}
		assertEquals(true, testOutput.toString().equals(compareToSB.toString()));
	}

	@Test
	public void makeTypeChar_init() {
		final char ENTRY_1 = 'a';
		final char ENTRY_2 = 'g';
		final char ENTRY_3 = 'm';
		final char INIT = 'e';
		IResizableArray<Character> testResizableArray = new ResizableArray<>(2);
		IResizableArray<Character> compareToRA_null = new ResizableArray<>(2);
		IResizableArray<Character> compareToRA_init = new ResizableArray<>(2);
		StringBuilder testOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();

		assertEquals(2, testResizableArray.getSize());

		testResizableArray.set(0, ENTRY_1);
		assertEquals(true, testResizableArray.get(0).equals(ENTRY_1));
		compareToRA_null.set(0, ENTRY_1);
		compareToRA_init.set(0, ENTRY_1);
		compareToSB.append(ENTRY_1);

		testResizableArray.set(1, ENTRY_2);
		assertEquals(true, testResizableArray.get(1).equals(ENTRY_2));
		compareToRA_null.set(1, ENTRY_2);
		compareToRA_init.set(1, ENTRY_2);
		compareToSB.append(ENTRY_2);

		testResizableArray.resize(3, INIT);
		assertEquals(3, testResizableArray.getSize());
		compareToRA_null.resize(3);
		compareToRA_init.resize(3, INIT);

		assertEquals(3, testResizableArray.getSize());
		testResizableArray.set(2, ENTRY_3);
		assertEquals(true, testResizableArray.get(0).equals(ENTRY_1));
		assertEquals(true, testResizableArray.get(1).equals(ENTRY_2));
		assertEquals(true, testResizableArray.get(2).equals(ENTRY_3));
		compareToRA_null.set(2, ENTRY_3);
		compareToRA_init.set(2, ENTRY_3);
		compareToSB.append(ENTRY_3);

		assertEquals(true, testResizableArray.toString().equals(compareToRA_null.toString()));
		assertEquals(true, testResizableArray.toString().equals(compareToRA_init.toString()));

		for (int i = 0; i < 3; i++) {
			testOutput.append(testResizableArray.get(i));
		}
		assertEquals(true, testOutput.toString().equals(compareToSB.toString()));
	}
	//makeTypeString
	@Test
	public void makeTypeString_null() {
		final String ENTRY_1 = "hello";
		final String ENTRY_2 = "im";
		final String ENTRY_3 = "a";
		final String INIT = "dog";
		IResizableArray<String> testResizableArray = new ResizableArray<>(2);
		IResizableArray<String> compareToRA_null = new ResizableArray<>(2);
		IResizableArray<String> compareToRA_init = new ResizableArray<>(2);
		StringBuilder testOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();

		assertEquals(2, testResizableArray.getSize());

		testResizableArray.set(0, ENTRY_1);
		assertEquals(true, testResizableArray.get(0).equals(ENTRY_1));
		compareToRA_null.set(0, ENTRY_1);
		compareToRA_init.set(0, ENTRY_1);
		compareToSB.append(ENTRY_1);

		testResizableArray.set(1, ENTRY_2);
		assertEquals(true, testResizableArray.get(1).equals(ENTRY_2));
		compareToRA_null.set(1, ENTRY_2);
		compareToRA_init.set(1, ENTRY_2);
		compareToSB.append(ENTRY_2);

		testResizableArray.resize(3);
		assertEquals(3, testResizableArray.getSize());
		compareToRA_null.resize(3);
		compareToRA_init.resize(3, INIT);

		assertEquals(3, testResizableArray.getSize());
		testResizableArray.set(2, ENTRY_3);
		assertEquals(true, testResizableArray.get(0).equals(ENTRY_1));
		assertEquals(true, testResizableArray.get(1).equals(ENTRY_2));
		assertEquals(true, testResizableArray.get(2).equals(ENTRY_3));
		compareToRA_null.set(2, ENTRY_3);
		compareToRA_init.set(2, ENTRY_3);
		compareToSB.append(ENTRY_3);

		assertEquals(true, testResizableArray.toString().equals(compareToRA_null.toString()));
		assertEquals(true, testResizableArray.toString().equals(compareToRA_init.toString()));

		for (int i = 0; i < 3; i++) {
			testOutput.append(testResizableArray.get(i));
		}
		assertEquals(true, testOutput.toString().equals(compareToSB.toString()));
	}

	@Test
	public void makeTypeString_init() {
		final String ENTRY_1 = "hello";
		final String ENTRY_2 = "im";
		final String ENTRY_3 = "a";
		final String INIT = "dog";
		IResizableArray<String> testResizableArray = new ResizableArray<>(2);
		IResizableArray<String> compareToRA_null = new ResizableArray<>(2);
		IResizableArray<String> compareToRA_init = new ResizableArray<>(2);
		StringBuilder testOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();

		assertEquals(2, testResizableArray.getSize());

		testResizableArray.set(0, ENTRY_1);
		assertEquals(true, testResizableArray.get(0).equals(ENTRY_1));
		compareToRA_null.set(0, ENTRY_1);
		compareToRA_init.set(0, ENTRY_1);
		compareToSB.append(ENTRY_1);

		testResizableArray.set(1, ENTRY_2);
		assertEquals(true, testResizableArray.get(1).equals(ENTRY_2));
		compareToRA_null.set(1, ENTRY_2);
		compareToRA_init.set(1, ENTRY_2);
		compareToSB.append(ENTRY_2);

		testResizableArray.resize(3, INIT);
		assertEquals(3, testResizableArray.getSize());
		compareToRA_null.resize(3);
		compareToRA_init.resize(3, INIT);

		assertEquals(3, testResizableArray.getSize());
		testResizableArray.set(2, ENTRY_3);
		assertEquals(true, testResizableArray.get(0).equals(ENTRY_1));
		assertEquals(true, testResizableArray.get(1).equals(ENTRY_2));
		assertEquals(true, testResizableArray.get(2).equals(ENTRY_3));
		compareToRA_null.set(2, ENTRY_3);
		compareToRA_init.set(2, ENTRY_3);
		compareToSB.append(ENTRY_3);

		assertEquals(true, testResizableArray.toString().equals(compareToRA_null.toString()));
		assertEquals(true, testResizableArray.toString().equals(compareToRA_init.toString()));

		for (int i = 0; i < 3; i++) {
			testOutput.append(testResizableArray.get(i));
		}
		assertEquals(true, testOutput.toString().equals(compareToSB.toString()));
	}
	//makeTypeMyClass
	@Test
	public void makeTypeMyClass_null() {
		final MyClass ENTRY_1 = new MyClass();
		final MyClass ENTRY_2 = new MyClass();
		final MyClass ENTRY_3 = new MyClass();
		final MyClass INIT = new MyClass();
		IResizableArray<MyClass> testResizableArray = new ResizableArray<>(2);
		IResizableArray<MyClass> compareToRA_null = new ResizableArray<>(2);
		IResizableArray<MyClass> compareToRA_init = new ResizableArray<>(2);
		StringBuilder testOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();

		assertEquals(2, testResizableArray.getSize());

		testResizableArray.set(0, ENTRY_1);
		assertEquals(true, testResizableArray.get(0).toString().equals(ENTRY_1.toString()));
		compareToRA_null.set(0, ENTRY_1);
		compareToRA_init.set(0, ENTRY_1);
		compareToSB.append(ENTRY_1.toString());

		testResizableArray.set(1, ENTRY_2);
		assertEquals(true, testResizableArray.get(1).toString().equals(ENTRY_2.toString()));
		compareToRA_null.set(1, ENTRY_2);
		compareToRA_init.set(1, ENTRY_2);
		compareToSB.append(ENTRY_2.toString());

		testResizableArray.resize(3);
		assertEquals(3, testResizableArray.getSize());
		compareToRA_null.resize(3);
		compareToRA_init.resize(3, INIT);

		assertEquals(3, testResizableArray.getSize());
		testResizableArray.set(2, ENTRY_3);
		assertEquals(true, testResizableArray.get(0).toString().equals(ENTRY_1.toString()));
		assertEquals(true, testResizableArray.get(1).toString().equals(ENTRY_2.toString()));
		assertEquals(true, testResizableArray.get(2).toString().equals(ENTRY_3.toString()));
		compareToRA_null.set(2, ENTRY_3);
		compareToRA_init.set(2, ENTRY_3);
		compareToSB.append(ENTRY_3);

		assertEquals(true, testResizableArray.toString().equals(compareToRA_null.toString()));
		assertEquals(true, testResizableArray.toString().equals(compareToRA_init.toString()));

		for (int i = 0; i < 3; i++) {
			testOutput.append(testResizableArray.get(i).toString());
		}
		assertEquals(true, testOutput.toString().equals(compareToSB.toString()));
	}

	@Test
	public void makeTypeMyClass_init() {
		final MyClass ENTRY_1 = new MyClass();
		final MyClass ENTRY_2 = new MyClass();
		final MyClass ENTRY_3 = new MyClass();
		final MyClass INIT = new MyClass();
		IResizableArray<MyClass> testResizableArray = new ResizableArray<>(2);
		IResizableArray<MyClass> compareToRA_null = new ResizableArray<>(2);
		IResizableArray<MyClass> compareToRA_init = new ResizableArray<>(2);
		StringBuilder testOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();

		assertEquals(2, testResizableArray.getSize());

		testResizableArray.set(0, ENTRY_1);
		assertEquals(true, testResizableArray.get(0).toString().equals(ENTRY_1.toString()));
		compareToRA_null.set(0, ENTRY_1);
		compareToRA_init.set(0, ENTRY_1);
		compareToSB.append(ENTRY_1.toString());

		testResizableArray.set(1, ENTRY_2);
		assertEquals(true, testResizableArray.get(1).toString().equals(ENTRY_2.toString()));
		compareToRA_null.set(1, ENTRY_2);
		compareToRA_init.set(1, ENTRY_2);
		compareToSB.append(ENTRY_2.toString());

		testResizableArray.resize(3, INIT);
		assertEquals(3, testResizableArray.getSize());
		compareToRA_null.resize(3);
		compareToRA_init.resize(3, INIT);

		assertEquals(3, testResizableArray.getSize());
		testResizableArray.set(2, ENTRY_3);
		assertEquals(true, testResizableArray.get(0).toString().equals(ENTRY_1.toString()));
		assertEquals(true, testResizableArray.get(1).toString().equals(ENTRY_2.toString()));
		assertEquals(true, testResizableArray.get(2).toString().equals(ENTRY_3.toString()));
		compareToRA_null.set(2, ENTRY_3);
		compareToRA_init.set(2, ENTRY_3);
		compareToSB.append(ENTRY_3);

		assertEquals(true, testResizableArray.toString().equals(compareToRA_null.toString()));
		assertEquals(true, testResizableArray.toString().equals(compareToRA_init.toString()));

		for (int i = 0; i < 3; i++) {
			testOutput.append(testResizableArray.get(i).toString());
		}
		assertEquals(true, testOutput.toString().equals(compareToSB.toString()));
	}
	//makeTypeMySubclass
	@Test
	public void makeTypeMySubClass_null() {
		final MySubClass ENTRY_1 = new MySubClass();
		final MySubClass ENTRY_2 = new MySubClass();
		final MySubClass ENTRY_3 = new MySubClass();
		final MySubClass INIT = new MySubClass();
		IResizableArray<MySubClass> testResizableArray = new ResizableArray<>(2);
		IResizableArray<MySubClass> compareToRA_null = new ResizableArray<>(2);
		IResizableArray<MySubClass> compareToRA_init = new ResizableArray<>(2);
		StringBuilder testOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();

		assertEquals(2, testResizableArray.getSize());

		testResizableArray.set(0, ENTRY_1);
		assertEquals(true, testResizableArray.get(0).equals(ENTRY_1));
		compareToRA_null.set(0, ENTRY_1);
		compareToRA_init.set(0, ENTRY_1);
		compareToSB.append(ENTRY_1);

		testResizableArray.set(1, ENTRY_2);
		assertEquals(true, testResizableArray.get(1).equals(ENTRY_2));
		compareToRA_null.set(1, ENTRY_2);
		compareToRA_init.set(1, ENTRY_2);
		compareToSB.append(ENTRY_2);

		testResizableArray.resize(3);
		assertEquals(3, testResizableArray.getSize());
		compareToRA_null.resize(3);
		compareToRA_init.resize(3, INIT);

		assertEquals(3, testResizableArray.getSize());
		testResizableArray.set(2, ENTRY_3);
		assertEquals(true, testResizableArray.get(0).equals(ENTRY_1));
		assertEquals(true, testResizableArray.get(1).equals(ENTRY_2));
		assertEquals(true, testResizableArray.get(2).equals(ENTRY_3));
		compareToRA_null.set(2, ENTRY_3);
		compareToRA_init.set(2, ENTRY_3);
		compareToSB.append(ENTRY_3);

		assertEquals(true, testResizableArray.toString().equals(compareToRA_null.toString()));
		assertEquals(true, testResizableArray.toString().equals(compareToRA_init.toString()));

		for (int i = 0; i < 3; i++) {
			testOutput.append(testResizableArray.get(i));
		}
		assertEquals(true, testOutput.toString().equals(compareToSB.toString()));
	}

	@Test
	public void makeTypeMySubClass_init() {
		final MySubClass ENTRY_1 = new MySubClass();
		final MySubClass ENTRY_2 = new MySubClass();
		final MySubClass ENTRY_3 = new MySubClass();
		final MySubClass INIT = new MySubClass();
		IResizableArray<MySubClass> testResizableArray = new ResizableArray<>(2);
		IResizableArray<MySubClass> compareToRA_null = new ResizableArray<>(2);
		IResizableArray<MySubClass> compareToRA_init = new ResizableArray<>(2);
		StringBuilder testOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();

		assertEquals(2, testResizableArray.getSize());

		testResizableArray.set(0, ENTRY_1);
		assertEquals(true, testResizableArray.get(0).equals(ENTRY_1));
		compareToRA_null.set(0, ENTRY_1);
		compareToRA_init.set(0, ENTRY_1);
		compareToSB.append(ENTRY_1);

		testResizableArray.set(1, ENTRY_2);
		assertEquals(true, testResizableArray.get(1).equals(ENTRY_2));
		compareToRA_null.set(1, ENTRY_2);
		compareToRA_init.set(1, ENTRY_2);
		compareToSB.append(ENTRY_2);

		testResizableArray.resize(3, INIT);
		assertEquals(3, testResizableArray.getSize());
		compareToRA_null.resize(3);
		compareToRA_init.resize(3, INIT);

		assertEquals(3, testResizableArray.getSize());
		testResizableArray.set(2, ENTRY_3);
		assertEquals(true, testResizableArray.get(0).equals(ENTRY_1));
		assertEquals(true, testResizableArray.get(1).equals(ENTRY_2));
		assertEquals(true, testResizableArray.get(2).equals(ENTRY_3));
		compareToRA_null.set(2, ENTRY_3);
		compareToRA_init.set(2, ENTRY_3);
		compareToSB.append(ENTRY_3);

		assertEquals(true, testResizableArray.toString().equals(compareToRA_null.toString()));
		assertEquals(true, testResizableArray.toString().equals(compareToRA_init.toString()));

		for (int i = 0; i < 3; i++) {
			testOutput.append(testResizableArray.get(i));
		}
		assertEquals(true, testOutput.toString().equals(compareToSB.toString()));
	}
}
