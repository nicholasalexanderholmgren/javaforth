package edu.mccc.cos210.ds;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class jjm_PriorityQueueTests {
	//DEBUG - Checks EXPECTED arrays and testPQs against isHeap
	final boolean VERIFY_RESULTS = false;
	
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
        report.append("\nTesting: ").append(PriorityQueue.class.getSimpleName()).append("\n");
	}
	
	@AfterClass
	public static void tearDownClass() {
	    System.out.println(report.toString());
	}
	
//TESTING
	//bubbleUp and bubbleDown can handle negative values
//	@Test
//	public void inserting_bubbleUp_negativeIntegers() {
//		final Integer[] ENTRIES = {0, -1};
//		final Integer[] EXPECTED_BEFORE = {-1, 0};
//		final Integer NEGATIVE_ENTRY = -3;
//		final Integer[] EXPECTED_AFTER = {-3, 0, -1};
//		
//		IPriorityQueue<Integer> testPQ = new PriorityQueue<>();
//		Arrays.stream(ENTRIES).forEach(i -> testPQ.insert(i));
//		assertEquals(true, testPQ.toString().equals(formatAsToString(EXPECTED_BEFORE)));
//	
//		testPQ.insert(NEGATIVE_ENTRY);
//		assertEquals(true, testPQ.toString().equals(formatAsToString(EXPECTED_AFTER)));
//		
//		if (VERIFY_RESULTS) {
//			assertEquals(true, isHeap(EXPECTED_BEFORE));
//			assertEquals(true, isHeap(EXPECTED_AFTER));
//			assertEquals(true, isHeap(shrink(testPQ)));
//		}
//	}
//	
//	@Test
//	public void getting_bubbleDown_negativeIntegers() {
//		final Integer[] ENTRIES = {-2, -4, -1, -5, -3,};
//		final Integer[] EXPECTED_BEFORE = {-5, -4, -1, -2, -3};
//		final Integer NEGATIVE_ENTRY = -6;
//		final Integer[] EXPECTED_AFTER = {-6, -4, -5, -2, -3, -1};
//		
//		IPriorityQueue<Integer> testPQ = new PriorityQueue<>();
//		Arrays.stream(ENTRIES).forEach(i -> testPQ.insert(i));
//		assertEquals(true, testPQ.toString().equals(formatAsToString(EXPECTED_BEFORE)));
//	
//		testPQ.insert(NEGATIVE_ENTRY);
//		assertEquals(true, testPQ.toString().equals(formatAsToString(EXPECTED_AFTER)));
//	
//	if (VERIFY_RESULTS) {
//		assertEquals(true, isHeap(EXPECTED_BEFORE));
//		assertEquals(true, isHeap(EXPECTED_AFTER));
//		assertEquals(true, isHeap(shrink(testPQ)));
//	}
//	}
	
	//Check toStrings
	@Test
	public void check_ToStrings() {
		final Integer[] ENTRIES = {0, 1, 2, 4};
		
		IPriorityQueue<Integer> testPQ = new PriorityQueue<>();
		testPQ.insert(ENTRIES[0]);
		assertEquals(true, testPQ.toString().equals("[ " + ENTRIES[0] + " ]"));
		assertEquals(true, testPQ.toString().equals(formatAsToString(new Integer[] {ENTRIES[0]})));
		
		testPQ.insert(ENTRIES[1]);
		assertEquals(true, testPQ.toString().equals("[ " + ENTRIES[0] + " " 
														 + ENTRIES[1] + " ]"));
		assertEquals(true, testPQ.toString().equals(formatAsToString(new Integer[] {ENTRIES[0],
																					ENTRIES[1]})));
		testPQ.insert(ENTRIES[2]);
		assertEquals(true, testPQ.toString().equals("[ " + ENTRIES[0] + " " 
														 + ENTRIES[1] + " "
														 + ENTRIES[2] + " ]"));
		assertEquals(true, testPQ.toString().equals(formatAsToString(new Integer[] {ENTRIES[0],
																					ENTRIES[1],
																					ENTRIES[2]})));
	}
	
	//Instantiation
	@Test
	public void instantiation_startsEmpty() {
		IPriorityQueue<Integer> testPQ = new PriorityQueue<>();
		assertEquals(true, testPQ.isEmpty());
	}
	
	@Test
	public void instantiation_startsZeroSized() {
		IPriorityQueue<Integer> testPQ = new PriorityQueue<>();
		assertEquals(0, testPQ.getSize());
	}
	
	//Inserting
	@Test
	public void inserting_increasesSize() {
		final int TEST_SIZE = 10;
		
		IPriorityQueue<Integer> testPQ = new PriorityQueue<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testPQ.insert(i);
			assertEquals(i + 1, testPQ.getSize());
		}
		
		if (VERIFY_RESULTS) {
			assertEquals(true, isHeap(shrink(testPQ)));
		}
	}
	
	@Test
	public void inserting_falsifiesIsEmpty() {
		final int TEST_SIZE = 5;
		
		IPriorityQueue<Integer> testPQ = new PriorityQueue<>();
		assertEquals(true, testPQ.isEmpty());
		for (int i = 0; i < TEST_SIZE; i++) {
			testPQ.insert(i);
			assertEquals(false, testPQ.isEmpty());
		}
	}
	
	@Test
	public void inserting_rootElement() {
		final int ROOT = 5;
		final String EXPECTED = "[ " + ROOT + " ]";
		
		IPriorityQueue<Integer> testPQ = new PriorityQueue<>();
		testPQ.insert(ROOT);
		assertEquals(true, testPQ.toString().equals(EXPECTED));
		
		if (VERIFY_RESULTS) {
			assertEquals(true, isHeap(shrink(testPQ)));
		}
	}
	
	@Test
	public void inserting_bubbleUp_lesserElementSwapsWithParent() {
		final Integer[] ENTRIES = {1, 4, 2};
		final Integer[] EXPECTED_BEFORE = {1, 4, 2};
		final Integer LESSER_ENTRY = 3;
		final Integer[] EXPECTED_AFTER = {1, 3, 2, 4};
		
		IPriorityQueue<Integer> testPQ = new PriorityQueue<>();
		Arrays.stream(ENTRIES).forEach(i -> testPQ.insert(i));
		
		assertEquals(true, testPQ.toString().equals(formatAsToString(EXPECTED_BEFORE)));
		testPQ.insert(LESSER_ENTRY);
		assertEquals(true, testPQ.toString().equals(formatAsToString(EXPECTED_AFTER)));
		
		if (VERIFY_RESULTS) {
			assertEquals(true, isHeap(EXPECTED_BEFORE));
			assertEquals(true, isHeap(EXPECTED_AFTER));
			assertEquals(true, isHeap(shrink(testPQ)));
		}
	}
	
	@Test
	public void inserting_bubbleUp_lesserElementSwapsWithParent_char() {
		final Character[] ENTRIES = {'a', 'f', 'e'};
		final Character[] EXPECTED_BEFORE = {'a', 'f', 'e'};
		final Character LESSER_ENTRY = 'b';
		final Character[] EXPECTED_AFTER = {'a', 'b', 'e', 'f'};
		
		IPriorityQueue<Character> testPQ = new PriorityQueue<>();
		Arrays.stream(ENTRIES).forEach(i -> testPQ.insert(i));
		assertEquals(true, testPQ.toString().equals(formatAsToString(EXPECTED_BEFORE)));
	
		testPQ.insert(LESSER_ENTRY);
		assertEquals(true, testPQ.toString().equals(formatAsToString(EXPECTED_AFTER)));
		
		if (VERIFY_RESULTS) {
			assertEquals(true, isHeap(EXPECTED_BEFORE));
			assertEquals(true, isHeap(EXPECTED_AFTER));
			assertEquals(true, isHeap(shrink(testPQ)));
		}
	}
	
	@Test
	public void inserting_bubbleUp_sameElement() {
		final Integer[] ENTRIES = {3, 4, 5};
		final Integer[] EXPECTED_BEFORE = {3, 4, 5};
		final Integer REPEATED_ENTRY = 3;
		final Integer[] EXPECTED_AFTER = {3, 3, 5, 4};
		
		IPriorityQueue<Integer> testPQ = new PriorityQueue<>();
		Arrays.stream(ENTRIES).forEach(i -> testPQ.insert(i));
		
		assertEquals(true, testPQ.toString().equals(formatAsToString(EXPECTED_BEFORE)));
		testPQ.insert(REPEATED_ENTRY);
		assertEquals(true, testPQ.toString().equals(formatAsToString(EXPECTED_AFTER)));
		
		if (VERIFY_RESULTS) {
			assertEquals(true, isHeap(EXPECTED_BEFORE));
			assertEquals(true, isHeap(EXPECTED_AFTER));
			assertEquals(true, isHeap(shrink(testPQ)));
		}
	}
	
	@Test
	public void inserting_bubbleUp_greatestElement() {
		final Integer[] ENTRIES = {2, 5, 4};
		final Integer[] EXPECTED_BEFORE = {2, 5, 4};
		final Integer GREATEST_ENTRY = 6;
		final Integer[] EXPECTED_AFTER = {2, 5, 4, 6};
		
		IPriorityQueue<Integer> testPQ = new PriorityQueue<>();
		Arrays.stream(ENTRIES).forEach(i -> testPQ.insert(i));
		assertEquals(true, testPQ.toString().equals(formatAsToString(EXPECTED_BEFORE)));
		testPQ.insert(GREATEST_ENTRY);
		assertEquals(true, testPQ.toString().equals(formatAsToString(EXPECTED_AFTER)));
		
		if (VERIFY_RESULTS) {
			assertEquals(true, isHeap(EXPECTED_BEFORE));
			assertEquals(true, isHeap(EXPECTED_AFTER));
			assertEquals(true, isHeap(shrink(testPQ)));
		}
	}
	
	@Test
	public void inserting_bubbleUp_leastElement() {
		final Integer[] ENTRIES = {2, 3, 4};
		final Integer[] EXPECTED_BEFORE = {2, 3, 4};
		final Integer LEAST_ENTRY = 1;
		final Integer[] EXPECTED_AFTER = {1, 2, 4, 3};
		
		IPriorityQueue<Integer> testPQ = new PriorityQueue<>();
		Arrays.stream(ENTRIES).forEach(i -> testPQ.insert(i));
		assertEquals(true, testPQ.toString().equals(formatAsToString(EXPECTED_BEFORE)));
		testPQ.insert(LEAST_ENTRY);
		assertEquals(true, testPQ.toString().equals(formatAsToString(EXPECTED_AFTER)));
		
		if (VERIFY_RESULTS) {
			assertEquals(true, isHeap(EXPECTED_BEFORE));
			assertEquals(true, isHeap(EXPECTED_AFTER));
			assertEquals(true, isHeap(shrink(testPQ)));
		}
	}
	
	@Test
	public void inserting_incrementingOrder() {
		final Integer[] ENTRIES = {0, 1, 2, 3, 4};
		final Integer[][] EXPECTED = {
				{0},
				{0, 1},
				{0, 1, 2},
				{0, 1, 2, 3},
				{0, 1, 2, 3, 4}
		};
		
		IPriorityQueue<Integer> testPQ = new PriorityQueue<>();
		for (int i = 0; i < ENTRIES.length; i++) {
			testPQ.insert(ENTRIES[i]);
			assertEquals(true, testPQ.toString().equals(formatAsToString(EXPECTED[i])));
		}
		
		if (VERIFY_RESULTS) {
			Arrays.stream(EXPECTED).forEach(a -> assertEquals(true, isHeap(a)));
			assertEquals(true, isHeap(shrink(testPQ)));
		}
	}
	
	@Test
	public void inserting_decrementingOrder() {
		final Integer[] ENTRIES = {4, 3, 2, 1, 0};
		final Integer[][] EXPECTED = {
				{4},
				{3, 4},
				{2, 4, 3},
				{1, 2, 3, 4},
				{0, 1, 3, 4, 2}
		};
		
		IPriorityQueue<Integer> testPQ = new PriorityQueue<>();
		for (int i = 0; i < ENTRIES.length; i++) {
			testPQ.insert(ENTRIES[i]);  
			assertEquals(true, testPQ.toString().equals(formatAsToString(EXPECTED[i])));
		}
		
		if (VERIFY_RESULTS) {
			Arrays.stream(EXPECTED).forEach(a -> assertEquals(true, isHeap(a)));
			assertEquals(true, isHeap(shrink(testPQ)));
		}
	}
	
	@Test
	public void inserting_alternatingOrder() {
		final Integer[] ENTRIES = {0, 4, 1, 3, 2};
		final Integer[][] EXPECTED = {
				{0},
				{0, 4},
				{0, 4, 1},
				{0, 3, 1, 4},
				{0, 2, 1, 4, 3}
		};
		
		IPriorityQueue<Integer> testPQ = new PriorityQueue<>();
		for (int i = 0; i < ENTRIES.length; i++) {
			testPQ.insert(ENTRIES[i]);
			assertEquals(true, testPQ.toString().equals(formatAsToString(EXPECTED[i])));
		}
		
		if (VERIFY_RESULTS) {
			Arrays.stream(EXPECTED).forEach(a -> assertEquals(true, isHeap(a)));
			assertEquals(true, isHeap(shrink(testPQ)));
		}
	}
	
	public void inserting_bubbleUpToRoot_oneLevel() {
		final Integer[] ENTRIES = {1, 2};
		final Integer MIN = 0;
		final Integer[] EXPECTED = {0, 1, 2};
		
		IPriorityQueue<Integer> testPQ = new PriorityQueue<>();
		Arrays.stream(ENTRIES).forEach(i -> testPQ.insert(i));
		testPQ.insert(MIN);
		assertEquals(true, testPQ.toString().equals(formatAsToString(EXPECTED)));
		assertEquals(true, testPQ.peekTop().equals(MIN));
		
		if (VERIFY_RESULTS) {
			assertEquals(true, isHeap(EXPECTED));
			assertEquals(true, isHeap(shrink(testPQ)));
		}
	}
	
	public void inserting_bubbleUpToRoot_twoLevels() {
		final Integer[] ENTRIES = {1, 2, 3, 4, 5, 6, 7};
		final Integer MIN = 0;
		final Integer[] EXPECTED = {0, 2, 1, 4, 5, 6, 3, 7};
		
		IPriorityQueue<Integer> testPQ = new PriorityQueue<>();
		Arrays.stream(ENTRIES).forEach(i -> testPQ.insert(i));
		testPQ.insert(MIN);
		assertEquals(true, testPQ.toString().equals(formatAsToString(EXPECTED)));
		assertEquals(true, testPQ.peekTop().equals(MIN));
		
		if (VERIFY_RESULTS) {
			assertEquals(true, isHeap(EXPECTED));
			assertEquals(true, isHeap(shrink(testPQ)));
		}
	}
	
	public void inserting_bubbleUpToRoot_threeLevels() {
		final Integer[] ENTRIES = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		final Integer MIN = 0;
		final Integer[] EXPECTED = {0, 1, 3, 2, 5, 6, 7, 4, 9, 10, 11, 12 ,13 ,14 ,15, 8};
		
		IPriorityQueue<Integer> testPQ = new PriorityQueue<>();
		Arrays.stream(ENTRIES).forEach(i -> testPQ.insert(i));
		testPQ.insert(MIN);
		assertEquals(true, testPQ.toString().equals(formatAsToString(EXPECTED)));
		assertEquals(true, testPQ.peekTop().equals(MIN));
		
		if (VERIFY_RESULTS) {
			assertEquals(true, isHeap(EXPECTED));
			assertEquals(true, isHeap(shrink(testPQ)));
		}
	}
	
	//Getting
	@Test
	public void getting_returnsRoot() {
		final Integer[] ENTRIES = {0, 1, 2};
		final Integer EXPECTED_ROOT = 0;
		
		IPriorityQueue<Integer> testPQ = new PriorityQueue<>();
		Arrays.stream(ENTRIES).forEach(i -> testPQ.insert(i));
		assertEquals(true, testPQ.getTop().equals(EXPECTED_ROOT));
	}
	
	@Test
	public void getting_reducesSize() {
		final Integer[] ENTRIES = {0, 1, 2, 3, 4};
		
		IPriorityQueue<Integer> testPQ = new PriorityQueue<>();
		Arrays.stream(ENTRIES).forEach(i -> testPQ.insert(i));
		assertEquals(testPQ.getSize(), ENTRIES.length);
		for (int i = ENTRIES.length ; i > 0; i--) {
			testPQ.getTop();
			assertEquals(testPQ.getSize(), i - 1);
		}
	}
	
	@Test
	public void getting_lastElementMakesEmpty() {
		final Integer ROOT = 0;
		
		IPriorityQueue<Integer> testPQ = new PriorityQueue<>();
		testPQ.insert(ROOT);
		assertEquals(false, testPQ.isEmpty());
		testPQ.getTop();
		assertEquals(true, testPQ.isEmpty());
	}
	
	@Test
	public void getting_returnsInExpectedOrder() {
		final Integer[] ENTRIES = {1, 4, 3, 2, 0, 5, 7, 6};
		final Integer[] INORDER = {0, 1, 2, 3, 4, 5, 6, 7};
		
		IPriorityQueue<Integer> testPQ = new PriorityQueue<>();
		Arrays.stream(ENTRIES).forEach(i -> testPQ.insert(i));
		for (int i = 0; i < testPQ.getSize(); i++) {
			assertEquals(true, testPQ.getTop().equals(INORDER[i]));
		}
	}
	
	@Test
	public void getting_bubbleDown_putsMinAtRoot() {
		final Integer[] ENTRIES = {1, 4, 3, 2, 5};
		final Integer INITIAL_ROOT = 1;
		final Integer EXPECTED_ROOT = 2;
		
		IPriorityQueue<Integer> testPQ = new PriorityQueue<>();
		Arrays.stream(ENTRIES).forEach(i -> testPQ.insert(i));
		assertEquals(true, testPQ.peekTop().equals(INITIAL_ROOT));
		testPQ.getTop();
		assertEquals(true, testPQ.peekTop().equals(EXPECTED_ROOT));
	}
	
	@Test
	public void getting_bubbleDown_swapsMinChild() {
		final Integer[] ENTRIES = {1, 3, 2};
		final Integer[] EXPECTED = {2, 3};
		
		IPriorityQueue<Integer> testPQ = new PriorityQueue<>();
		Arrays.stream(ENTRIES).forEach(i -> testPQ.insert(i));
		testPQ.getTop();
		assertEquals(true, testPQ.toString().equals(formatAsToString(EXPECTED)));
		
		if (VERIFY_RESULTS) {
			assertEquals(true, isHeap(EXPECTED));
			assertEquals(true, isHeap(shrink(testPQ)));
		}
	}
	
	@Test
	public void getting_bubbleDown_swapsMinChild_char() {
		final Character[] ENTRIES = {'a', 'e', 'c'};
		final Character[] EXPECTED = {'c', 'e'};
		
		IPriorityQueue<Character> testPQ = new PriorityQueue<>();
		Arrays.stream(ENTRIES).forEach(i -> testPQ.insert(i));
		testPQ.getTop();
		assertEquals(true, testPQ.toString().equals(formatAsToString(EXPECTED)));
		
		if (VERIFY_RESULTS) {
			assertEquals(true, isHeap(EXPECTED));
			assertEquals(true, isHeap(shrink(testPQ)));
		}
	}
	
	@Test
	public void getting_bubbleDown_oneLevel() {
		final Integer[] ENTRIES = {1, 2, 3, 4};
		final Integer[] EXPECTED = {2, 4, 3};
		
		IPriorityQueue<Integer> testPQ = new PriorityQueue<>();
		Arrays.stream(ENTRIES).forEach(i -> testPQ.insert(i));
		testPQ.getTop();
		assertEquals(true, testPQ.toString().equals(formatAsToString(EXPECTED)));
		
		if (VERIFY_RESULTS) {
			assertEquals(true, isHeap(EXPECTED));
			assertEquals(true, isHeap(shrink(testPQ)));
		}
	}
	
	@Test
	public void getting_bubbleDown_twoLevels() {
		final Integer[] ENTRIES = {1, 2, 3, 4, 5, 6, 7, 8};
		final Integer[] EXPECTED = {2, 4, 3, 8, 5, 6, 7};
		
		IPriorityQueue<Integer> testPQ = new PriorityQueue<>();
		Arrays.stream(ENTRIES).forEach(i -> testPQ.insert(i));
		testPQ.getTop();
		assertEquals(true, testPQ.toString().equals(formatAsToString(EXPECTED)));
		
		if (VERIFY_RESULTS) {
			assertEquals(true, isHeap(EXPECTED));
			assertEquals(true, isHeap(shrink(testPQ)));
		}
	}
	
	@Test
	public void getting_bubbleDown_threeLevels() {
		final Integer[] ENTRIES = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
		final Integer[] EXPECTED = {2, 4, 3, 8, 5, 6, 7, 16, 9, 10, 11, 12, 13, 14, 15};
		
		IPriorityQueue<Integer> testPQ = new PriorityQueue<>();
		Arrays.stream(ENTRIES).forEach(i -> testPQ.insert(i));
		testPQ.getTop();
		assertEquals(true, testPQ.toString().equals(formatAsToString(EXPECTED)));
		
		if (VERIFY_RESULTS) {
			assertEquals(true, isHeap(EXPECTED));
			assertEquals(true, isHeap(shrink(testPQ)));
		}
	}
	
	//Exceptions
	@Test(expected=NoSuchElementException.class)
	public void exception_noSuchElement_getTop() {
		IPriorityQueue<Integer> testPQ = new PriorityQueue<>();
		assertEquals(true, testPQ.isEmpty());
		testPQ.getTop();
	}
	
	@Test(expected=NoSuchElementException.class)
	public void exception_noSuchElement_peekTop() {
		IPriorityQueue<Integer> testPQ = new PriorityQueue<>();
		assertEquals(true, testPQ.isEmpty());
		testPQ.peekTop();
	}
	
	//Integrity
	public void integrity_compareToClone() {
		final Integer[] ENTRIES = {2, 7, 2, 8, 16, 1, 15};
		
		IPriorityQueue<Integer> testPQ = new PriorityQueue<>();
		IPriorityQueue<Integer> compareToPQ = new PriorityQueue<>();
		for (Integer entry : ENTRIES) {
			testPQ.insert(entry);
			compareToPQ.insert(entry);
			assertEquals(true, testPQ.toString().equals(compareToPQ.toString()));
		}
	}
	
	//makeTypes
	@Test
	public void makeTypeInt() {
		IPriorityQueue<Integer> testPQ = new PriorityQueue<>();
		final Integer[] ENTRIES = {2, 5, 3, 6, 2};
		final Integer[] INORDER = {2, 2, 3, 5, 6};
		final Integer[][] INSERTING = {
			{2},
			{2, 5},
			{2, 5, 3},
			{2, 5, 3, 6},
			{2, 2, 3, 6, 5}
		};
		
		final Integer[][] GETTING = {
			{2, 5, 3 ,6},
			{3, 5, 6},
			{5, 6},
			{6},
			{}
		};

		assertEquals(true, testPQ.isEmpty());
		assertEquals(true, testPQ.toString().equals("[ ]"));
		for (int i = 0; i < ENTRIES.length; i++) {
			testPQ.insert(ENTRIES[i]);
			assertEquals(true, testPQ.toString().equals(formatAsToString(INSERTING[i])));
			assertEquals(i + 1, testPQ.getSize());
		}
		
		assertEquals(false, testPQ.isEmpty());
		for (int i = 0; i < ENTRIES.length; i++) {
			assertEquals(true, testPQ.getTop().equals(INORDER[i]));
			assertEquals(true, testPQ.toString().equals(formatAsToString(GETTING[i])));
			assertEquals(ENTRIES.length - 1 - i, testPQ.getSize());
		}
		
		assertEquals(true, testPQ.isEmpty());
		
		if (VERIFY_RESULTS) {
			Arrays.stream(INSERTING).forEach(a -> assertEquals(true, isHeap(a)));
			Arrays.stream(GETTING).forEach(a -> assertEquals(true, isHeap(a))); //see isHeapTests
		}
	}
	
	@Test
	public void makeTypeDouble() {
		IPriorityQueue<Double> testPQ = new PriorityQueue<>();
		final Double[] ENTRIES = {3.0, 1.0, 1.0, 5.0, 2.5};
		final Double[] INORDER = {1.0, 1.0, 2.5, 3.0, 5.0};
		final Double[][] INSERTING = {
			{3.0},
			{1.0, 3.0},
			{1.0, 3.0, 1.0},
			{1.0, 3.0, 1.0, 5.0},
			{1.0, 2.5, 1.0, 5.0, 3.0}
		};
		
		final Double[][] GETTING = {
			{1.0, 2.5, 3.0, 5.0},
		 	{2.5, 5.0, 3.0},
		 	{3.0, 5.0},
		 	{5.0},
		 	{}
		};

		assertEquals(true, testPQ.isEmpty());
		assertEquals(true, testPQ.toString().equals("[ ]"));
		for (int i = 0; i < ENTRIES.length; i++) {
			testPQ.insert(ENTRIES[i]);
			assertEquals(true, testPQ.toString().equals(formatAsToString(INSERTING[i])));
			assertEquals(i + 1, testPQ.getSize());
		}
		
		assertEquals(false, testPQ.isEmpty());
		for (int i = 0; i < ENTRIES.length; i++) {
			assertEquals(true, testPQ.getTop().equals(INORDER[i]));
			assertEquals(true, testPQ.toString().equals(formatAsToString(GETTING[i])));
			assertEquals(ENTRIES.length - 1 - i, testPQ.getSize());
		}
		
		assertEquals(true, testPQ.isEmpty());
		
		if (VERIFY_RESULTS) {
			Arrays.stream(INSERTING).forEach(a -> assertEquals(true, isHeap(a)));
			Arrays.stream(GETTING).forEach(a -> assertEquals(true, isHeap(a))); //see isHeapTests
		}
	}
	
	@Test
	public void makeTypeBoolean() { //False-heavy
		IPriorityQueue<Boolean> testPQ = new PriorityQueue<>();
		final Boolean[] ENTRIES = {true, false, true, false, true};
		final Boolean[] INORDER = {false, false, true, true, true};
		final Boolean[][] INSERTING = {
			{true},
			{false, true},
			{false, true, true},
			{false, false, true, true},
			{false, false, true, true, true}
		};
		
		final Boolean[][] GETTING = {
			{false, true, true, true},
		 	{true, true, true},
		 	{true, true},
		 	{true},
		 	{}
		};
		
		assertEquals(true, testPQ.isEmpty());
		assertEquals(true, testPQ.toString().equals("[ ]"));
		for (int i = 0; i < ENTRIES.length; i++) {
			testPQ.insert(ENTRIES[i]);
			assertEquals(true, testPQ.toString().equals(formatAsToString(INSERTING[i])));
			assertEquals(i + 1, testPQ.getSize());
		}
		
		assertEquals(false, testPQ.isEmpty());
		for (int i = 0; i < ENTRIES.length; i++) {
			assertEquals(true, testPQ.getTop().equals(INORDER[i]));
			assertEquals(true, testPQ.toString().equals(formatAsToString(GETTING[i])));
			assertEquals(ENTRIES.length - 1 - i, testPQ.getSize());
		}
		
		assertEquals(true, testPQ.isEmpty());
		
		if (VERIFY_RESULTS) {
			Arrays.stream(INSERTING).forEach(a -> assertEquals(true, isHeap(a)));
			Arrays.stream(GETTING).forEach(a -> assertEquals(true, isHeap(a))); //see isHeapTests
		}
	}
	
	@Test
	public void makeTypeChar() {
		IPriorityQueue<Character> testPQ = new PriorityQueue<>();
		final Character[] ENTRIES = {'a', 'd', 'c', 'a', 'e'};
		final Character[] INORDER = {'a', 'a', 'c', 'd', 'e'};
		final Character[][] INSERTING = {
			{'a'},
			{'a', 'd'},
			{'a', 'd', 'c'},
			{'a', 'a', 'c', 'd'},
			{'a', 'a', 'c', 'd', 'e'}
		};
		
		final Character[][] GETTING = {
			{'a', 'd', 'c', 'e'},
			{'c', 'd', 'e'},
			{'d', 'e'},
			{'e'},
			{}
		};

		assertEquals(true, testPQ.isEmpty());
		assertEquals(true, testPQ.toString().equals("[ ]"));
		for (int i = 0; i < ENTRIES.length; i++) {
			testPQ.insert(ENTRIES[i]);
			assertEquals(true, testPQ.toString().equals(formatAsToString(INSERTING[i])));
			assertEquals(i + 1, testPQ.getSize());
		}
		
		assertEquals(false, testPQ.isEmpty());
		for (int i = 0; i < ENTRIES.length; i++) {
			assertEquals(true, testPQ.getTop().equals(INORDER[i]));
			assertEquals(true, testPQ.toString().equals(formatAsToString(GETTING[i])));
			assertEquals(ENTRIES.length - 1 - i, testPQ.getSize());
		}
		
		assertEquals(true, testPQ.isEmpty());
		
		if (VERIFY_RESULTS) {
			Arrays.stream(INSERTING).forEach(a -> assertEquals(true, isHeap(a)));
			Arrays.stream(GETTING).forEach(a -> assertEquals(true, isHeap(a))); //see isHeapTests
		}
	}
	
	@Test
	public void makeTypeString() {
		IPriorityQueue<String> testPQ = new PriorityQueue<>();
		final String[] ENTRIES = {"cat", "dog", "cat", "loaf", "aard"};
		final String[] INORDER = {"aard", "cat", "cat", "dog", "loaf"};
		final String[][] INSERTING = {
			{"cat"},
			{"cat", "dog"},
			{"cat", "dog", "cat"},
			{"cat", "dog", "cat", "loaf"},
			{"aard", "cat", "cat", "loaf", "dog"}
		};
		final String[][] GETTING = {
			{"cat", "dog", "cat", "loaf"},
			{"cat", "dog", "loaf"},
			{"dog", "loaf"},
			{"loaf"},
			{}
		};

		assertEquals(true, testPQ.isEmpty());
		assertEquals(true, testPQ.toString().equals("[ ]"));
		for (int i = 0; i < ENTRIES.length; i++) {
			testPQ.insert(ENTRIES[i]);
			assertEquals(true, testPQ.toString().equals(formatAsToString(INSERTING[i])));
			assertEquals(i + 1, testPQ.getSize());
		}
		
		assertEquals(false, testPQ.isEmpty());
		for (int i = 0; i < ENTRIES.length; i++) {
			assertEquals(true, testPQ.getTop().equals(INORDER[i]));
			assertEquals(true, testPQ.toString().equals(formatAsToString(GETTING[i])));
			assertEquals(ENTRIES.length - 1 - i, testPQ.getSize());
		}
		
		assertEquals(true, testPQ.isEmpty());
		
		if (VERIFY_RESULTS) {
			Arrays.stream(INSERTING).forEach(a -> assertEquals(true, isHeap(a)));
			Arrays.stream(GETTING).forEach(a -> assertEquals(true, isHeap(a))); //see isHeapTests
		}
	}
	
	//Private Methods
	private <T extends Comparable<T>> String formatAsToString(T[] input) {
		StringBuilder output = new StringBuilder();
		output.append("[ ");
		for (T entry : input) {
			output.append(entry + " ");
		}
		output.append(']');
		return output.toString();
	}
	
	@SuppressWarnings("unchecked") // Called inside Eclipse for Debugging
	private <T extends Comparable<T>> T[] shrink(IPriorityQueue<T> pq) throws IllegalArgumentException {
		T e = pq.peekTop();
		String s = pq.toString();
		String[] sArray = s.substring(2, s.length() - 2).split(" ");
		
		switch (e.getClass().getSimpleName()) {
		case "Integer":
			return (T[]) Arrays.stream(sArray).map(Integer::parseInt).toArray(size -> new Integer[size]);
		case "Double":
			return (T[]) Arrays.stream(sArray).map(Double::parseDouble).toArray(size -> new Double[size]);
		case "Boolean":
			return (T[]) Arrays.stream(sArray).map(Boolean::parseBoolean).toArray(size -> new Boolean[size]);
		case "Character":
			return (T[]) Arrays.stream(sArray).map(a -> a.charAt(0)).toArray(size -> new Character[size]);
		case "String": //NOTE: Strings should not contain whitespace
			return (T[]) sArray;
		default:
			throw new IllegalArgumentException();
		}
	}
	
//	@Test
//	public void testShrink() {
//		IPriorityQueue<Integer> testPQ_int = new PriorityQueue<>();
//		final Integer[] ENTRIES_int = {2, 5, 1, 3, 0};
//		Arrays.stream(ENTRIES_int).forEach(i -> testPQ_int.insert(i));
//		assertEquals(true, testPQ_int.toString().equals(formatAsToString(shrink(testPQ_int))));
//		
//		IPriorityQueue<Double> testPQ_double = new PriorityQueue<>();
//		final Double[] ENTRIES_double = {3.0, 1.0, 2.5, 3.1, 0.5};
//		Arrays.stream(ENTRIES_double).forEach(d -> testPQ_double.insert(d));
//		assertEquals(true, testPQ_double.toString().equals(formatAsToString(shrink(testPQ_double))));
//		
//		IPriorityQueue<Boolean> testPQ_boolean = new PriorityQueue<>();
//		final Boolean[] ENTRIES_boolean = {true, true, false, false, true};
//		Arrays.stream(ENTRIES_boolean).forEach(b -> testPQ_boolean.insert(b));
//		assertEquals(true, testPQ_boolean.toString().equals(formatAsToString(shrink(testPQ_boolean))));
//		
//		IPriorityQueue<Character> testPQ_char = new PriorityQueue<>();
//		final Character[] ENTRIES_char = {'b', 'e', 'd', 'a', 'm'};
//		Arrays.stream(ENTRIES_char).forEach(c -> testPQ_char.insert(c));
//		assertEquals(true, testPQ_char.toString().equals(formatAsToString(shrink(testPQ_char))));
//		
//		IPriorityQueue<String> testPQ_String = new PriorityQueue<>();
//		final String[] ENTRIES_String = {"ham", "slam", "cram", "jam", "bam"};
//		Arrays.stream(ENTRIES_String).forEach(s -> testPQ_String.insert(s));
//		assertEquals(true, testPQ_String.toString().equals(formatAsToString(shrink(testPQ_String))));
//	}
	
	private <T extends Comparable<T>> boolean isHeap(T[] input) { // Called inside Eclipse for Debugging
		final int bound = input.length;
		boolean isHeap = true;
		int leftChild = 0;
		int rightChild = 0;
		
		for (int i = 0; i < bound; i++) {
			leftChild = 2 * i + 1;
			rightChild = 2 * i + 2;
			if (leftChild < bound && input[i].compareTo(input[leftChild]) > 0) {
				isHeap = false;
				break;
			} else {
				if (rightChild < bound && input[i].compareTo(input[rightChild]) >  0) {
					isHeap = false;
					break;
				} else {
					if (leftChild >= bound) {
						break;
					}
				}
			}
		}
		
		return isHeap;
	}
	
//	@Test
//	public void testIsHeap() {
//	//Allows null-referencing Arrays to keep formatting in makeTypes; isHeap never called when
//	//testing null-referencing Arrays
//	final Integer[] NOTABLE_ENTRY = {};
//		assertEquals(true, isHeap(NOTABLE_ENTRY));
//		
//		final Integer[] HEAP_int1 = {0, 1, 2, 5, 3};
//		assertEquals(true, isHeap(HEAP_int1));
//		final Integer[] HEAP_int2 = {3, 4, 8, 5, 9, 8, 8};
//		assertEquals(true, isHeap(HEAP_int2));
//		final Integer[] NOTHEAP_int1 = {1, 0, 3, 5, 6, 7};
//		assertEquals(false, isHeap(NOTHEAP_int1));
//		final Integer[] NOTHEAP_int2 = {5, 6, 8, 9, 7, 4};
//		assertEquals(false, isHeap(NOTHEAP_int2));
//		
//		final Double[] HEAP_double1 = {0.5, 1.5, 2.5, 5.0, 3.0};
//		assertEquals(true, isHeap(HEAP_double1));
//		final Double[] HEAP_double2 = {1.8, 2.5, 3.0};
//		assertEquals(true, isHeap(HEAP_double2));
//		final Double[] NOTHEAP_double1 = {1.0, 4.5, 3.0, 5.5, 2.0, 2.5};
//		assertEquals(false, isHeap(NOTHEAP_double1));
//		final Double[] NOTHEAP_double2 = {1.0, 2.5, 10.0, 2.5, 3.8, 0.5};
//		assertEquals(false, isHeap(NOTHEAP_double2));
//
//		final Boolean[] HEAP_boolean1 = {false, false, true, true, false};
//		assertEquals(true, isHeap(HEAP_boolean1));
//		final Boolean[] HEAP_boolean2 = {false};
//		assertEquals(true, isHeap(HEAP_boolean2));
//		final Boolean[] NOTHEAP_boolean1 = {false, true, false, false, true};
//		assertEquals(false, isHeap(NOTHEAP_boolean1));
//		final Boolean[] NOTHEAP_boolean2 = {true, true, true, true, false};
//		assertEquals(false, isHeap(NOTHEAP_boolean2));
//
//		final Character[] HEAP_char1 = {'a', 'e', 'g', 's', 'g'};
//		assertEquals(true, isHeap(HEAP_char1));
//		final Character[] HEAP_char2 = {'b', 'e', 'e', 's', 'r', 'u', 'v', 'v', 'x', 'z'};
//		assertEquals(true, isHeap(HEAP_char2));
//		final Character[] NOTHEAP_char1 = {'c', 'c', 'a'};
//		assertEquals(false, isHeap(NOTHEAP_char1));
//		final Character[] NOTHEAP_char2 = {'e', 't', 'm', 'z', 'z', 'd'};
//		assertEquals(false, isHeap(NOTHEAP_char2));
//		
//		
//		final String[] HEAP_String1 = {"elo", "esa", "letro", "panks", "mega"};
//		assertEquals(true, isHeap(HEAP_String1));
//		final String[] HEAP_String2 = {"baser", "boar", "beam", "buster", "bye", "brom"};
//		assertEquals(true, isHeap(HEAP_String2));
//		final String[] NOTHEAP_String1 = {"wesker", "wesker", "wesker", "reksew"};
//		assertEquals(false, isHeap(NOTHEAP_String1));
//		final String[] NOTHEAP_String2 = {"esa", "panks", "letro", "mega", "elo"};
//		assertEquals(false, isHeap(NOTHEAP_String2));
//	}
}
