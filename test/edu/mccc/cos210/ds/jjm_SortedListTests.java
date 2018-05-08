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

public class jjm_SortedListTests {
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
	        report.append("\nTesting: ").append(SortedList.class.getSimpleName()).append("\n");
		}

		@AfterClass
		public static void tearDownClass() {
		    System.out.println(report.toString());
		}

	//TESTING
//	@Test
//	public void sorting_insertion_sizing() {
//		final int ENTRY_1 = 8;
//		final int ENTRY_2 = 16;
//		final int ENTRY_3 = 10;
//
//		ISortedList<Integer> testSortedList = new SortedList<>();
//		assertEquals(0, testSortedList.getSize());
//		testSortedList.add(ENTRY_1);
//		assertEquals(1, testSortedList.getSize());
//		testSortedList.add(ENTRY_2);
//		assertEquals(2, testSortedList.getSize());
//		testSortedList.add(ENTRY_3);
//		assertEquals(3, testSortedList.getSize());
//	}
//



	@Test
	public void checkToString() {
		ISortedList<Integer> testSortedList = new SortedList<>();
		testSortedList.add(1);
		assertEquals(true, testSortedList.toString().equals("[ 1 ]"));
		testSortedList.add(2);
		assertEquals(true, testSortedList.toString().equals("[ 1 2 ]"));
	}

	@Test
	public void adding_check_oneElement() {
		final int ENTRY = 0;
		final String expected = "[ " + ENTRY + " ]";

		ISortedList<Integer> testSortedList = new SortedList<>();
		testSortedList.add(ENTRY);
		assertEquals(true, testSortedList.toString().equals(expected));
	}

	@Test
	public void adding_check_manyElements() {
		final int TEST_SIZE = 10;
		StringBuilder expected = new StringBuilder();
		expected.append("[ ");

		ISortedList<Integer> testSortedList = new SortedList<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testSortedList.add(i);
			expected.append(i + " ");
		}
		expected.append("]");
		assertEquals(true, testSortedList.toString().equals(expected.toString()));
	}

	@Test(expected=IllegalArgumentException.class)
	public void adding_incomparableType() {
		ISortedList<MyClass> testSortedList = new SortedList<>();
		testSortedList.add(new MyClass());
	}

	@Test
	public void instantiating_noComparator() {
		ISortedList<Integer> testSortedList = new SortedList<>();
		assertEquals(null, testSortedList.getComparator());
	}

	@Test
	public void instantiating_withComparator() {
		ISortedList<Integer> testSortedList = new SortedList<>(new TestComparator<Integer>());
		assertEquals(false, testSortedList.getComparator() == null);
	}

	@Test
	public void gettingComparator() {
		TestComparator<Integer> testComparator = new TestComparator<>();
		ISortedList<Integer> testSortedList = new SortedList<>(testComparator);
		assertEquals(true, testSortedList.getComparator().equals(testComparator));
	}

		@Test
		public void settingGettingComparator() {
			TestComparator<Integer> testComparator = new TestComparator<>();
			ISortedList<Integer> testSortedList = new SortedList<>();
			testSortedList.setComparator(testComparator);
			assertEquals(true, testSortedList.getComparator().equals(testComparator));
		}

	@Test
	public void integrity_incremental() {
		final int TEST_SIZE = 10;

		ISortedList<Integer> testSortedList = new SortedList<>();
		ISortedList<Integer> compareTo = new SortedList<>();
		StringBuilder expected = new StringBuilder();
		expected.append("[ ");
		for (int i = 0; i < TEST_SIZE; i++) {
			testSortedList.add(i);
			expected.append(i + " ");
		}
		for (int i = TEST_SIZE - 1; i > -1; i--) {
			compareTo.add(i);
		}
		expected.append("]");
		assertEquals(true, testSortedList.toString().equals(compareTo.toString()));
		assertEquals(true, testSortedList.toString().equals(expected.toString()));
	}

	@Test
	public void integrity_alternatingOrder() {
		final int ENTRY_1 = 5;
		final int ENTRY_2 = 10;
		final int ENTRY_3 = 15;
		final int ENTRY_4 = 20;
		final int ENTRY_5 = 25;
		String expected = "[ 5 10 15 20 25 ]";

		ISortedList<Integer> testSortedList = new SortedList<>();
		ISortedList<Integer> compareTo = new SortedList<>();
		testSortedList.add(ENTRY_1);
		compareTo.add(ENTRY_5);
		testSortedList.add(ENTRY_5);
		compareTo.add(ENTRY_1);
		testSortedList.add(ENTRY_2);
		compareTo.add(ENTRY_4);
		testSortedList.add(ENTRY_4);
		compareTo.add(ENTRY_2);
		testSortedList.add(ENTRY_3);
		compareTo.add(ENTRY_3);
		assertEquals(true, testSortedList.toString().equals(compareTo.toString()));
		assertEquals(true, testSortedList.toString().equals(expected.toString()));
	}

	@Test
	public void integrity_compareConstructors() {
		final int TEST_SIZE = 10;
		final int ENTRY_1 = 5;
		final int ENTRY_2 = 7;

		ISortedList<Integer> testNull = new SortedList<>();
		ISortedList<Integer> testComp = new SortedList<>(
			new TestComparator<Integer>()
		);

		for (int i = 0; i < TEST_SIZE; i++) {
			if (i == ENTRY_1) {
			} else {
				testNull.add(i);
				testComp.add(i);
			}
		}
		assertEquals(true, testNull.toString().equals(testComp.toString()));
		testNull.add(ENTRY_1);
		testComp.add(ENTRY_1);
		assertEquals(true, testNull.toString().equals(testComp.toString()));
		testNull.add(ENTRY_2);
		testComp.add(ENTRY_2);
		assertEquals(true, testNull.toString().equals(testComp.toString()));
	}

	@Test
	public void sorting_lowerFirst() {
		final int ENTRY_1 = 0;
		final int ENTRY_2 = 10;
		final String expected = "[ 0 10 ]";

		ISortedList<Integer> testSortedList = new SortedList<>();
		testSortedList.add(ENTRY_1);
		testSortedList.add(ENTRY_2);
		assertEquals(true, testSortedList.toString().equals(expected));
	}

	@Test
	public void sorting_lowerFirst_sizing() {
		final int ENTRY_1 = 0;
		final int ENTRY_2 = 10;

		ISortedList<Integer> testSortedList = new SortedList<>();
		testSortedList.add(ENTRY_1);
		testSortedList.add(ENTRY_2);
		assertEquals(2, testSortedList.getSize());
	}

	@Test
	public void sorting_higherFirst() {
		final int ENTRY_1 = 8;
		final int ENTRY_2 = 3;
		final String expected = "[ 3 8 ]";

		ISortedList<Integer> testSortedList = new SortedList<>();
		testSortedList.add(ENTRY_1);
		testSortedList.add(ENTRY_2);
		assertEquals(true, testSortedList.toString().equals(expected));
	}

	@Test
	public void sorting_sameElements() {
		final int ENTRY_1 = 3;
		final int ENTRY_2 = 5;
		final int ENTRY_3 = 4;
		final String expected = "[ 3 4 4 5 ]";

		ISortedList<Integer> testSortedList = new SortedList<>();
		testSortedList.add(ENTRY_1);
		testSortedList.add(ENTRY_3);
		testSortedList.add(ENTRY_2);
		testSortedList.add(ENTRY_3);
		assertEquals(true, testSortedList.toString().equals(expected));
	}
	//sorting_insertion
	@Test
	public void sorting_insertion() {
		final int ENTRY_1 = 15;
		final int ENTRY_2 = 20;
		final int ENTRY_3 = 17;
		final String expected = "[ 15 17 20 ]";

		ISortedList<Integer> testSortedList = new SortedList<>();
		testSortedList.add(ENTRY_1);
		testSortedList.add(ENTRY_2);
		testSortedList.add(ENTRY_3);
		assertEquals(true, testSortedList.toString().equals(expected));
	}

	@Test
	public void sorting_lowerFirst_reverseComparator() {
		final int ENTRY_1 = 0;
		final int ENTRY_2 = 10;
		final String expected = "[ 10 0 ]";

		ISortedList<Integer> testSortedList = new SortedList<>(
			(i1, i2) -> i2.compareTo(i1)
		);
		testSortedList.add(ENTRY_1);
		testSortedList.add(ENTRY_2);
		assertEquals(true, testSortedList.toString().equals(expected));
	}

	@Test
	public void sorting_higherFirst_reverseComparator() {
		final int ENTRY_1 = 8;
		final int ENTRY_2 = 3;
		final String expected = "[ 8 3 ]";

		ISortedList<Integer> testSortedList = new SortedList<>(
			(i1, i2) -> i2.compareTo(i1)
		);
		testSortedList.add(ENTRY_1);
		testSortedList.add(ENTRY_2);
		assertEquals(true, testSortedList.toString().equals(expected));
	}

	@Test
	public void sorting_sameElements_reverseComparator() {
		final int ENTRY_1 = 3;
		final int ENTRY_2 = 5;
		final int ENTRY_3 = 4;
		final String expected = "[ 5 4 4 3 ]";

		ISortedList<Integer> testSortedList = new SortedList<>(
			(i1, i2) -> i2.compareTo(i1)
		);
		testSortedList.add(ENTRY_1);
		testSortedList.add(ENTRY_3);
		testSortedList.add(ENTRY_2);
		testSortedList.add(ENTRY_3);
		assertEquals(true, testSortedList.toString().equals(expected));
	}

	@Test
	public void sorting_insertion_reverseComparator() {
		final int ENTRY_1 = 15;
		final int ENTRY_2 = 20;
		final int ENTRY_3 = 17;
		final String expected = "[ 20 17 15 ]";

		ISortedList<Integer> testSortedList = new SortedList<>(
			(i1, i2) -> i2.compareTo(i1)
		);
		testSortedList.add(ENTRY_1);
		testSortedList.add(ENTRY_2);
		testSortedList.add(ENTRY_3);
		assertEquals(true, testSortedList.toString().equals(expected));
	}

	@Test
	public void makeTypeInt() {
		final int ENTRY_1 = 5;
		final int ENTRY_2A = 10;
		final int ENTRY_2B = 10;
		final int ENTRY_3 = 15;
		ISortedList<Integer> testSortedListNull = new SortedList<>();
		ISortedList<Integer> testSortedListComp = new SortedList<>(null);
		TestComparator<Integer> testComparator = new TestComparator<>();
		ISortedList<Integer> testSortedListReverse = new SortedList<>(
			(e1, e2) -> e2.compareTo(e1)
		);

		final String expected = "[ " + ENTRY_1 + " "
									 + ENTRY_2A + " "
									 + ENTRY_2B + " "
									 + ENTRY_3 + " ]";
		final String expectedR = "[ " + ENTRY_3 + " "
									  + ENTRY_2A + " "
									  + ENTRY_2B+ " "
									  + ENTRY_1 + " ]";

		testSortedListComp.setComparator(testComparator);
		assertEquals(true, testSortedListComp.getComparator().equals(testComparator));

		testSortedListNull.add(ENTRY_1);
		testSortedListComp.add(ENTRY_1);
		assertEquals(true, testSortedListNull.toString().equals(testSortedListComp.toString()));
		testSortedListReverse.add(ENTRY_1);

		testSortedListNull.add(ENTRY_3);
		testSortedListComp.add(ENTRY_3);
		assertEquals(true, testSortedListNull.toString().equals(testSortedListComp.toString()));
		testSortedListReverse.add(ENTRY_3);

		testSortedListNull.add(ENTRY_2A);
		testSortedListComp.add(ENTRY_2A);
		assertEquals(true, testSortedListNull.toString().equals(testSortedListComp.toString()));
		testSortedListReverse.add(ENTRY_2A);

		testSortedListNull.add(ENTRY_2B);
		testSortedListComp.add(ENTRY_2B);
		assertEquals(true, testSortedListNull.toString().equals(testSortedListComp.toString()));
		testSortedListReverse.add(ENTRY_2B);

		assertEquals(true, testSortedListNull.toString().equals(expected));
		assertEquals(true, testSortedListComp.toString().equals(expected));
		assertEquals(true, testSortedListReverse.toString().equals(expectedR));

//		assertEquals(4, testSortedListNull.getSize());
//		assertEquals(4, testSortedListComp.getSize());
//		assertEquals(4, testSortedListReverse.getSize());
	}

	@Test
	public void makeTypeDouble() {
		final double ENTRY_1 = 7.0;
		final double ENTRY_2A = 12.0;
		final double ENTRY_2B = 12.0;
		final double ENTRY_3 = 17.0;
		ISortedList<Double> testSortedListNull = new SortedList<>();
		ISortedList<Double> testSortedListComp = new SortedList<>(null);
		TestComparator<Double> testComparator = new TestComparator<>();
		ISortedList<Double> testSortedListReverse = new SortedList<>(
			(e1, e2) -> e2.compareTo(e1)
		);

		final String expected = "[ " + ENTRY_1 + " "
									 + ENTRY_2A + " "
									 + ENTRY_2B + " "
									 + ENTRY_3 + " ]";
		final String expectedR = "[ " + ENTRY_3 + " "
									  + ENTRY_2A + " "
									  + ENTRY_2B+ " "
									  + ENTRY_1 + " ]";

		testSortedListComp.setComparator(testComparator);
		assertEquals(true, testSortedListComp.getComparator().equals(testComparator));

		testSortedListNull.add(ENTRY_1);
		testSortedListComp.add(ENTRY_1);
		assertEquals(true, testSortedListNull.toString().equals(testSortedListComp.toString()));
		testSortedListReverse.add(ENTRY_1);

		testSortedListNull.add(ENTRY_3);
		testSortedListComp.add(ENTRY_3);
		assertEquals(true, testSortedListNull.toString().equals(testSortedListComp.toString()));
		testSortedListReverse.add(ENTRY_3);

		testSortedListNull.add(ENTRY_2A);
		testSortedListComp.add(ENTRY_2A);
		assertEquals(true, testSortedListNull.toString().equals(testSortedListComp.toString()));
		testSortedListReverse.add(ENTRY_2A);

		testSortedListNull.add(ENTRY_2B);
		testSortedListComp.add(ENTRY_2B);
		assertEquals(true, testSortedListNull.toString().equals(testSortedListComp.toString()));
		testSortedListReverse.add(ENTRY_2B);

		assertEquals(true, testSortedListNull.toString().equals(expected));
		assertEquals(true, testSortedListComp.toString().equals(expected));
		assertEquals(true, testSortedListReverse.toString().equals(expectedR));

//		assertEquals(4, testSortedListNull.getSize());
//		assertEquals(4, testSortedListComp.getSize());
//		assertEquals(4, testSortedListReverse.getSize());
	}

	@Test
	public void makeTypeBoolean() {
		final boolean ENTRY_1 = false;
		final boolean ENTRY_2 = true;
		final boolean ENTRY_3 = false;
		final boolean ENTRY_4 = true;
		ISortedList<Boolean> testSortedListNull = new SortedList<>();
		ISortedList<Boolean> testSortedListComp = new SortedList<>(null);
		TestComparator<Boolean> testComparator = new TestComparator<>();
		ISortedList<Boolean> testSortedListReverse = new SortedList<>(
			(e1, e2) -> e2.compareTo(e1)
		);

		final String expected = "[ " + ENTRY_1 + " "
									 + ENTRY_3 + " "
									 + ENTRY_2 + " "
									 + ENTRY_4 + " ]";
		final String expectedR = "[ " + ENTRY_2 + " "
									  + ENTRY_4 + " "
									  + ENTRY_1 + " "
									  + ENTRY_3 + " ]";

		testSortedListComp.setComparator(testComparator);
		assertEquals(true, testSortedListComp.getComparator().equals(testComparator));

		testSortedListNull.add(ENTRY_1);
		testSortedListComp.add(ENTRY_1);
		assertEquals(true, testSortedListNull.toString().equals(testSortedListComp.toString()));
		testSortedListReverse.add(ENTRY_1);

		testSortedListNull.add(ENTRY_2);
		testSortedListComp.add(ENTRY_2);
		assertEquals(true, testSortedListNull.toString().equals(testSortedListComp.toString()));
		testSortedListReverse.add(ENTRY_2);

		testSortedListNull.add(ENTRY_3);
		testSortedListComp.add(ENTRY_3);
		assertEquals(true, testSortedListNull.toString().equals(testSortedListComp.toString()));
		testSortedListReverse.add(ENTRY_3);

		testSortedListNull.add(ENTRY_4);
		testSortedListComp.add(ENTRY_4);
		assertEquals(true, testSortedListNull.toString().equals(testSortedListComp.toString()));
		testSortedListReverse.add(ENTRY_4);

		assertEquals(true, testSortedListNull.toString().equals(expected));
		assertEquals(true, testSortedListComp.toString().equals(expected));
		assertEquals(true, testSortedListReverse.toString().equals(expectedR));

//		assertEquals(4, testSortedListNull.getSize());
//		assertEquals(4, testSortedListComp.getSize());
//		assertEquals(4, testSortedListReverse.getSize());
	}

	@Test
	public void makeTypeChar() {
		final char ENTRY_1 = 'e';
		final char ENTRY_2A = 'm';
		final char ENTRY_2B = 'm';
		final char ENTRY_3 = 'o';
		ISortedList<Character> testSortedListNull = new SortedList<>();
		ISortedList<Character> testSortedListComp = new SortedList<>(null);
		TestComparator<Character> testComparator = new TestComparator<>();
		ISortedList<Character> testSortedListReverse = new SortedList<>(
			(e1, e2) -> e2.compareTo(e1)
		);

		final String expected = "[ " + ENTRY_1 + " "
									 + ENTRY_2A + " "
									 + ENTRY_2B + " "
									 + ENTRY_3 + " ]";
		final String expectedR = "[ " + ENTRY_3 + " "
									  + ENTRY_2A + " "
									  + ENTRY_2B+ " "
									  + ENTRY_1 + " ]";

		testSortedListComp.setComparator(testComparator);
		assertEquals(true, testSortedListComp.getComparator().equals(testComparator));

		testSortedListNull.add(ENTRY_1);
		testSortedListComp.add(ENTRY_1);
		assertEquals(true, testSortedListNull.toString().equals(testSortedListComp.toString()));
		testSortedListReverse.add(ENTRY_1);

		testSortedListNull.add(ENTRY_3);
		testSortedListComp.add(ENTRY_3);
		assertEquals(true, testSortedListNull.toString().equals(testSortedListComp.toString()));
		testSortedListReverse.add(ENTRY_3);

		testSortedListNull.add(ENTRY_2A);
		testSortedListComp.add(ENTRY_2A);
		assertEquals(true, testSortedListNull.toString().equals(testSortedListComp.toString()));
		testSortedListReverse.add(ENTRY_2A);

		testSortedListNull.add(ENTRY_2B);
		testSortedListComp.add(ENTRY_2B);
		assertEquals(true, testSortedListNull.toString().equals(testSortedListComp.toString()));
		testSortedListReverse.add(ENTRY_2B);

		assertEquals(true, testSortedListNull.toString().equals(expected));
		assertEquals(true, testSortedListComp.toString().equals(expected));
		assertEquals(true, testSortedListReverse.toString().equals(expectedR));

//		assertEquals(4, testSortedListNull.getSize());
//		assertEquals(4, testSortedListComp.getSize());
//		assertEquals(4, testSortedListReverse.getSize());
	}

	@Test
	public void makeTypeString() {
		final String ENTRY_1 = "can";
		final String ENTRY_2A = "elk";
		final String ENTRY_2B = "elk";
		final String ENTRY_3 = "lasagna";
		ISortedList<String> testSortedListNull = new SortedList<>();
		ISortedList<String> testSortedListComp = new SortedList<>(null);
		TestComparator<String> testComparator = new TestComparator<>();
		ISortedList<String> testSortedListReverse = new SortedList<>(
			(e1, e2) -> e2.compareTo(e1)
		);

		final String expected = "[ " + ENTRY_1 + " "
									 + ENTRY_2A + " "
									 + ENTRY_2B + " "
									 + ENTRY_3 + " ]";
		final String expectedR = "[ " + ENTRY_3 + " "
									  + ENTRY_2A + " "
									  + ENTRY_2B+ " "
									  + ENTRY_1 + " ]";

		testSortedListComp.setComparator(testComparator);
		assertEquals(true, testSortedListComp.getComparator().equals(testComparator));

		testSortedListNull.add(ENTRY_1);
		testSortedListComp.add(ENTRY_1);
		assertEquals(true, testSortedListNull.toString().equals(testSortedListComp.toString()));
		testSortedListReverse.add(ENTRY_1);

		testSortedListNull.add(ENTRY_3);
		testSortedListComp.add(ENTRY_3);
		assertEquals(true, testSortedListNull.toString().equals(testSortedListComp.toString()));
		testSortedListReverse.add(ENTRY_3);

		testSortedListNull.add(ENTRY_2A);
		testSortedListComp.add(ENTRY_2A);
		assertEquals(true, testSortedListNull.toString().equals(testSortedListComp.toString()));
		testSortedListReverse.add(ENTRY_2A);

		testSortedListNull.add(ENTRY_2B);
		testSortedListComp.add(ENTRY_2B);
		assertEquals(true, testSortedListNull.toString().equals(testSortedListComp.toString()));
		testSortedListReverse.add(ENTRY_2B);

		assertEquals(true, testSortedListNull.toString().equals(expected));
		assertEquals(true, testSortedListComp.toString().equals(expected));
		assertEquals(true, testSortedListReverse.toString().equals(expectedR));

//		assertEquals(4, testSortedListNull.getSize());
//		assertEquals(4, testSortedListComp.getSize());
//		assertEquals(4, testSortedListReverse.getSize());
	}

	@Test
	public void makeTypeMyClass() {
		final MyClass MSC1 = new MyClass();
		final MyClass MSC2 = new MyClass();
		final MyClass MSC3 = new MyClass();
		MyClass ENTRY_1;
		MyClass ENTRY_2A;
		MyClass ENTRY_2B;
		MyClass ENTRY_3;
		String expected;
		String expectedR;

		if (MSC1.hashCode() - MSC2.hashCode() > 0 && MSC1.hashCode() - MSC3.hashCode() > 0) {
			if (MSC2.hashCode() - MSC3.hashCode() > 0) {
				ENTRY_1 = MSC3;
				ENTRY_2A = MSC2;
				ENTRY_2B = MSC2;
				ENTRY_3 = MSC1;
				expected = "[ " + MSC3 + " "
						 		+ MSC2 + " "
						 		+ MSC2 + " "
						 		+ MSC1+ " ]";
				expectedR = "[ " + MSC1 + " "
						 		 + MSC2 + " "
						 		 + MSC2 + " "
						 		 + MSC3+ " ]";
			} else {
				ENTRY_1 = MSC2;
				ENTRY_2A = MSC3;
				ENTRY_2B = MSC3;
				ENTRY_3 = MSC1;
				expected = "[ " + MSC2 + " "
						 		+ MSC3 + " "
						 		+ MSC3 + " "
						 		+ MSC1+ " ]";
				expectedR = "[ " + MSC1 + " "
						 		 + MSC3 + " "
						 		 + MSC3 + " "
						 		 + MSC2 + " ]";
					}
		} else {
			if (MSC2.hashCode() - MSC1.hashCode() > 0 && MSC2.hashCode() - MSC3.hashCode() > 0) {
				if (MSC1.hashCode() - MSC3.hashCode() > 0) {
					ENTRY_1 = MSC3;
					ENTRY_2A = MSC1;
					ENTRY_2B = MSC1;
					ENTRY_3 = MSC2;
					expected = "[ " + MSC3 + " "
							 		+ MSC1 + " "
							 		+ MSC1 + " "
							 		+ MSC2+ " ]";
					expectedR = "[ " + MSC2 + " "
							 		 + MSC1 + " "
							 		 + MSC1 + " "
							 		 + MSC3+ " ]";
				} else {
					ENTRY_1 = MSC1;
					ENTRY_2A = MSC3;
					ENTRY_2B = MSC3;
					ENTRY_3 = MSC2;
					expected = "[ " + MSC1 + " "
							 		+ MSC3 + " "
							 		+ MSC3 + " "
							 		+ MSC2+ " ]";
					expectedR = "[ " + MSC2 + " "
							 		 + MSC3 + " "
							 		 + MSC3 + " "
							 		 + MSC1+ " ]";
				}
			} else {
				if (MSC1.hashCode() - MSC2.hashCode() > 0) {
					ENTRY_1 = MSC2;
					ENTRY_2A = MSC1;
					ENTRY_2B = MSC1;
					ENTRY_3 = MSC3;
					expected = "[ " + MSC2 + " "
							 		+ MSC1 + " "
							 		+ MSC1 + " "
							 		+ MSC3+ " ]";
					expectedR = "[ " + MSC3 + " "
							 		 + MSC1 + " "
							 		 + MSC1 + " "
							 		 + MSC2+ " ]";
				} else {
					ENTRY_1 = MSC1;
					ENTRY_2A = MSC2;
					ENTRY_2B = MSC2;
					ENTRY_3 = MSC3;
					expected = "[ " + MSC1 + " "
							 		+ MSC2 + " "
							 		+ MSC2 + " "
							 		+ MSC3+ " ]";
					expectedR = "[ " + MSC3 + " "
							 		 + MSC2 + " "
							 		 + MSC2 + " "
							 		 + MSC1+ " ]";
				}
			}
		}
		ISortedList<MyClass> testSortedList = new SortedList<>(
			(m1, m2) -> m1.hashCode() - m2.hashCode()
		);
		ISortedList<MyClass> testSortedListReverse = new SortedList<>(
			(m1, m2) -> m2.hashCode() - m1.hashCode()
		);

		testSortedList.add(ENTRY_1);
		testSortedListReverse.add(ENTRY_1);

		testSortedList.add(ENTRY_3);
		testSortedListReverse.add(ENTRY_3);

		testSortedList.add(ENTRY_2A);
		testSortedListReverse.add(ENTRY_2A);

		testSortedList.add(ENTRY_2B);
		testSortedListReverse.add(ENTRY_2B);

		assertEquals(true, testSortedList.toString().equals(expected));
		assertEquals(true, testSortedListReverse.toString().equals(expectedR));

//		assertEquals(4, testSortedListNull.getSize());
//		assertEquals(4, testSortedListComp.getSize());
//		assertEquals(4, testSortedListReverse.getSize());
	}

	@Test
	public void makeTypeMySubClass() {
		final MySubClass MSC1 = new MySubClass();
		final MySubClass MSC2 = new MySubClass();
		final MySubClass MSC3 = new MySubClass();
		MySubClass ENTRY_1;
		MySubClass ENTRY_2A;
		MySubClass ENTRY_2B;
		MySubClass ENTRY_3;
		String expected;
		String expectedR;

		if (MSC1.compareTo(MSC2) > 0 && MSC1.compareTo(MSC3) > 0) {
			if (MSC2.compareTo(MSC3) > 0) {
				ENTRY_1 = MSC3;
				ENTRY_2A = MSC2;
				ENTRY_2B = MSC2;
				ENTRY_3 = MSC1;
				expected = "[ " + MSC3 + " "
						 		+ MSC2 + " "
						 		+ MSC2 + " "
						 		+ MSC1+ " ]";
				expectedR = "[ " + MSC1 + " "
						 		 + MSC2 + " "
						 		 + MSC2 + " "
						 		 + MSC3+ " ]";
			} else {
				ENTRY_1 = MSC2;
				ENTRY_2A = MSC3;
				ENTRY_2B = MSC3;
				ENTRY_3 = MSC1;
				expected = "[ " + MSC2 + " "
						 		+ MSC3 + " "
						 		+ MSC3 + " "
						 		+ MSC1+ " ]";
				expectedR = "[ " + MSC1 + " "
						 		 + MSC3 + " "
						 		 + MSC3 + " "
						 		 + MSC2 + " ]";
					}
		} else {
			if (MSC2.compareTo(MSC1) > 0 && MSC2.compareTo(MSC3) > 0) {
				if (MSC1.compareTo(MSC3) > 0) {
					ENTRY_1 = MSC3;
					ENTRY_2A = MSC1;
					ENTRY_2B = MSC1;
					ENTRY_3 = MSC2;
					expected = "[ " + MSC3 + " "
							 		+ MSC1 + " "
							 		+ MSC1 + " "
							 		+ MSC2+ " ]";
					expectedR = "[ " + MSC2 + " "
							 		 + MSC1 + " "
							 		 + MSC1 + " "
							 		 + MSC3+ " ]";
				} else {
					ENTRY_1 = MSC1;
					ENTRY_2A = MSC3;
					ENTRY_2B = MSC3;
					ENTRY_3 = MSC2;
					expected = "[ " + MSC1 + " "
							 		+ MSC3 + " "
							 		+ MSC3 + " "
							 		+ MSC2+ " ]";
					expectedR = "[ " + MSC2 + " "
							 		 + MSC3 + " "
							 		 + MSC3 + " "
							 		 + MSC1+ " ]";
				}
			} else {
				if (MSC1.compareTo(MSC2) > 0) {
					ENTRY_1 = MSC2;
					ENTRY_2A = MSC1;
					ENTRY_2B = MSC1;
					ENTRY_3 = MSC3;
					expected = "[ " + MSC2 + " "
							 		+ MSC1 + " "
							 		+ MSC1 + " "
							 		+ MSC3+ " ]";
					expectedR = "[ " + MSC3 + " "
							 		 + MSC1 + " "
							 		 + MSC1 + " "
							 		 + MSC2+ " ]";
				} else {
					ENTRY_1 = MSC1;
					ENTRY_2A = MSC2;
					ENTRY_2B = MSC2;
					ENTRY_3 = MSC3;
					expected = "[ " + MSC1 + " "
							 		+ MSC2 + " "
							 		+ MSC2 + " "
							 		+ MSC3+ " ]";
					expectedR = "[ " + MSC3 + " "
							 		 + MSC2 + " "
							 		 + MSC2 + " "
							 		 + MSC1+ " ]";
				}
			}
		}
		ISortedList<MySubClass> testSortedListNull = new SortedList<>();
		ISortedList<MySubClass> testSortedListComp = new SortedList<>(null);
		TestComparator<MySubClass> testComparator = new TestComparator<>();
		ISortedList<MySubClass> testSortedListReverse = new SortedList<>(
			(e1, e2) -> e2.compareTo(e1)
		);

		testSortedListComp.setComparator(testComparator);
		assertEquals(true, testSortedListComp.getComparator().equals(testComparator));

		testSortedListNull.add(ENTRY_1);
		testSortedListComp.add(ENTRY_1);
		assertEquals(true, testSortedListNull.toString().equals(testSortedListComp.toString()));
		testSortedListReverse.add(ENTRY_1);

		testSortedListNull.add(ENTRY_3);
		testSortedListComp.add(ENTRY_3);
		assertEquals(true, testSortedListNull.toString().equals(testSortedListComp.toString()));
		testSortedListReverse.add(ENTRY_3);

		testSortedListNull.add(ENTRY_2A);
		testSortedListComp.add(ENTRY_2A);
		assertEquals(true, testSortedListNull.toString().equals(testSortedListComp.toString()));
		testSortedListReverse.add(ENTRY_2A);

		testSortedListNull.add(ENTRY_2B);
		testSortedListComp.add(ENTRY_2B);
		assertEquals(true, testSortedListNull.toString().equals(testSortedListComp.toString()));
		testSortedListReverse.add(ENTRY_2B);

		assertEquals(expected, testSortedListNull.toString());
		assertEquals(true, testSortedListComp.toString().equals(expected));
		assertEquals(true, testSortedListReverse.toString().equals(expectedR));

//		assertEquals(4, testSortedListNull.getSize());
//		assertEquals(4, testSortedListComp.getSize());
//		assertEquals(4, testSortedListReverse.getSize());
	}

	static class TestComparator<T extends Comparable<T>> implements java.util.Comparator<T> {
		@Override
		public int compare(T left, T right) {
			return left.compareTo(right);
		}
	}
}
