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

public class jjm_VectorTests {
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
        report.append("\nTesting: ").append(Vector.class.getSimpleName()).append("\n");
	}
	
	@AfterClass
	public static void tearDownClass() {
	    System.out.println(report.toString());
	}

	//Tests
//	@Test
//	public void shinkingToFit_empty() {
//		IVector<Integer> testVector = new Vector<>();
//		assertEquals(1, testVector.getCapacity());
//		assertEquals(0, testVector.getSize());
//		testVector.shrinkToFit();
//		assertEquals(1, testVector.getCapacity());
//	}
//	
//	@Test
//	public void instantiatingZeroCapacitance_size() {
//		IVector<Integer> testVector = new Vector<>(0);
//		assertEquals(1, testVector.getCapacity());
//	}
//	
//	@Test
//	public void instantiatingZeroCapacitance_capacity() {
//		IVector<Integer> testVector = new Vector<>(0, 0);
//		assertEquals(1, testVector.getCapacity());
//	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void cannotGetMembersPastBack() {
		final int TEST_SIZE = 5;
		final int INDEX = TEST_SIZE + 1;
		
		IVector<Integer> testVector = new Vector<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testVector.pushBack(i);
		}
		assertEquals(true, testVector.getSize() < INDEX);
		assertEquals(true, testVector.getCapacity() > INDEX);
		testVector.get(INDEX);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void cannotSetMembersPastBack() {
		final int TEST_SIZE = 5;
		final int INDEX = TEST_SIZE + 1;
		
		IVector<Integer> testVector = new Vector<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testVector.pushBack(i);
		}
		assertEquals(true, testVector.getSize() < INDEX);
		assertEquals(true, testVector.getCapacity() > INDEX);
		testVector.set(INDEX, 0);
	}
	
	@Test
	public void pushingBack() {
		final int ENTRY = 0;
		
		IVector<Integer> testVector = new Vector<>();
		testVector.pushBack(ENTRY);
		assertEquals((Integer)ENTRY, testVector.back());
	}
	
	@Test
	public void sizing_pushingBack() {
		final int TEST_SIZE = 10;
		
		IVector<Integer> testVector = new Vector<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testVector.pushBack(i);
			assertEquals(testVector.getSize(), i + 1);
		}
		assertEquals(TEST_SIZE, testVector.getSize());
	}
	
	@Test
	public void poppingBack() {
		final int ENTRY_1 = 0;
		final int ENTRY_2 = 5;
		
		IVector<Integer> testVector = new Vector<>();
		testVector.pushBack(ENTRY_1);
		testVector.pushBack(ENTRY_2);
		assertEquals(false, testVector.back().equals((Integer) ENTRY_1));
		testVector.popBack();
		assertEquals(true, testVector.back().equals((Integer) ENTRY_1));
	}
	
	@Test
	public void sizing_poppingBack() {
		final int TEST_SIZE = 10;
		
		IVector<Integer> testVector = new Vector<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testVector.pushBack(i);
		}
		for (int i = TEST_SIZE - 1; i > -1; i--) {
			testVector.popBack();
			assertEquals(testVector.getSize(), i);
		}
	}
	
	@Test
	public void sizing_fromConstructor_none() {
		IVector<Integer> testVector = new Vector<>();
		assertEquals(true, testVector.getSize() == 0);
	}
	
	public void sizing_fromConstructor_size() {
		final int INITIAL_SIZE = 5;
		
		IVector<Integer> testVector = new Vector<>(INITIAL_SIZE);
		assertEquals(true, testVector.getSize() == INITIAL_SIZE);
	}
	
	public void sizing_fromConstructor_capacity() {
		final int INITIAL_SIZE = 5;
		final int INITIAL_CAPACITY = INITIAL_SIZE * 2;
		
		IVector<Integer> testVector = new Vector<>(INITIAL_SIZE, INITIAL_CAPACITY);
		assertEquals(true, testVector.getSize() == INITIAL_SIZE);
	}
	
	@Test
	public void capacity_fromConstructor_none() {
		IVector<Integer> testVector = new Vector<>();
		assertEquals(1, testVector.getCapacity());
	}
	
	@Test
	public void capacity_fromConstructor_capacity() {
		final int SIZE = 5;
		final int CAPACITY = 10;
		
		IVector<Integer> testVector = new Vector<>(SIZE, CAPACITY);
		assertEquals(CAPACITY, testVector.getCapacity());
	}
	
	@Test
	public void capacity_checkResized() {
		final int TEST_SIZE = 4;
		final int ENTRY = 5;
		
		IVector<Integer> testVector = new Vector<>(0, TEST_SIZE);
		for (int i = 0; i < TEST_SIZE; i++) {
			testVector.pushBack(i);
		}
		assertEquals(true, testVector.getCapacity() == TEST_SIZE);
		testVector.pushBack(ENTRY);
		assertEquals(true, testVector.getCapacity() == TEST_SIZE * 2);
	}
	
	@Test
	public void capacity_fromConstructor_size() {
		final int SIZE = 5;
		
		IVector<Integer> testVector = new Vector<>(SIZE);
		assertEquals(SIZE, testVector.getCapacity());
	}
	
	@Test
	public void instantiatingFillsNull_fromConstructor_size() {
		final int SIZE = 5;
		
		IVector<Integer> testVector = new Vector<>(SIZE);
		for (int i = 0; i < SIZE; i++) {
			assertEquals(true, testVector.get(i) == null);
		}
	}
	
	@Test
	public void instantiatingFillsNull_fromConstructor_capacity() {
		final int SIZE = 5;
		
		IVector<Integer> testVector = new Vector<>(SIZE, SIZE);
		for (int i = 0; i < SIZE; i++) {
			assertEquals(true, testVector.get(i) == null);
		}
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void capacity_checkReallocated_zeroSized() {
		IVector<Integer> testVector = new Vector<>(0);
		testVector.pushBack(0);
	}
	
	@Test
	public void shrinkingToFit_changesCapacity() {
		final int TEST_SIZE = 10;
		final int INITIAL_CAPACITY = TEST_SIZE * 2;
		
		IVector<Integer> testVector = new Vector<>(0, INITIAL_CAPACITY);
		assertEquals(false, testVector.getCapacity() == TEST_SIZE);
		for (int i = 0; i < TEST_SIZE; i++) {
			testVector.pushBack(i);
		}
		testVector.shrinkToFit();
		assertEquals(TEST_SIZE, testVector.getCapacity());
	}
	
	@Test
	public void shrinkingToFit_maintainsSize() {
		final int TEST_SIZE = 10;
		
		IVector<Integer> testVector = new Vector<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testVector.pushBack(i);
		}
		assertEquals(TEST_SIZE, testVector.getSize());
		testVector.shrinkToFit();
		assertEquals(TEST_SIZE, testVector.getSize());
	}
	
	@Test
	public void shrinkingToFit_maintainsIntegrity() {
		final int TEST_SIZE = 10;
		
		IVector<Integer> testVector = new Vector<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testVector.pushBack(i);
		}
		testVector.shrinkToFit();
		for (int i = 0; i < TEST_SIZE; i++) {
			assertEquals(i, (int) testVector.get(i));
		}
	}
	
	@Test
	public void checkToString() {
		final int ENTRY_1 = 1;
		final int ENTRY_2 = 2;
		final int ENTRY_3 = 3;
		final String expected_1 = "1 1 [ " + ENTRY_1 + " ]";
		final String expected_2 = "2 2 [ " + ENTRY_1
								     + " " + ENTRY_2 + " ]";
		final String expected_3 = "3 4 [ " + ENTRY_1
								     + " " + ENTRY_2
								     + " " + ENTRY_3 + " ]";
		
		IVector<Integer> testVector = new Vector<>();
		testVector.pushBack(ENTRY_1);
		assertEquals(true, testVector.toString().equals(expected_1));
		testVector.pushBack(ENTRY_2);
		assertEquals(true, testVector.toString().equals(expected_2));
		testVector.pushBack(ENTRY_3);
		assertEquals(true, testVector.toString().equals(expected_3));
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void poppingBack_empty() {
		IVector<Integer> testVector = new Vector<>();
		testVector.popBack();
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void checkingBack_empty() {
		IVector<Integer> testVector = new Vector<>();
		testVector.back();
	}
	
	@Test
	public void integrity_compareConstructors() {
		final int ENTRY_1 = 5;
		final int ENTRY_2 = 10;
		final int ENTRY_3 = 15;
		
		IVector<Integer> testVector_none = new Vector<>();
		IVector<Integer> testVector_size = new Vector<>(1);
		IVector<Integer> testVector_capacity = new Vector<>(0, 1);
//		assertEquals(testVector_none.getSize(), testVector_size.getSize());
		assertEquals(testVector_none.getSize(), testVector_capacity.getSize());
//		assertEquals(testVector_size.getSize(), testVector_capacity.getSize());
//		assertEquals(testVector_none.getSize(), testVector_size.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_capacity.getCapacity());
//		assertEquals(testVector_size.getSize(), testVector_capacity.getSize());
		
		testVector_none.pushBack(ENTRY_1);
		testVector_size.pushBack(ENTRY_1);
		testVector_capacity.pushBack(ENTRY_1);
//		assertEquals(testVector_none.getSize(), testVector_size.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_capacity.getCapacity());
//		assertEquals(testVector_size.getSize(), testVector_capacity.getSize());
//		assertEquals(testVector_none.getSize(), testVector_size.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_capacity.getCapacity());
//		assertEquals(testVector_size.getSize(), testVector_capacity.getSize());
		assertEquals((Integer)ENTRY_1, testVector_none.back());
		assertEquals((Integer)ENTRY_1, testVector_size.back());
		assertEquals((Integer)ENTRY_1, testVector_capacity.back());
		
		testVector_none.pushBack(ENTRY_2);
		testVector_size.pushBack(ENTRY_2);
		testVector_capacity.pushBack(ENTRY_2);
//		assertEquals(testVector_none.getSize(), testVector_size.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_capacity.getCapacity());
//		assertEquals(testVector_size.getSize(), testVector_capacity.getSize());
//		assertEquals(testVector_none.getSize(), testVector_size.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_capacity.getCapacity());
//		assertEquals(testVector_size.getSize(), testVector_capacity.getSize());
		assertEquals((Integer)ENTRY_2, testVector_none.back());
		assertEquals((Integer)ENTRY_2, testVector_size.back());
		assertEquals((Integer)ENTRY_2, testVector_capacity.back());
		
		testVector_none.pushBack(ENTRY_3);
		testVector_size.pushBack(ENTRY_3);
		testVector_capacity.pushBack(ENTRY_3);
//		assertEquals(testVector_none.getSize(), testVector_size.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_capacity.getCapacity());
//		assertEquals(testVector_size.getSize(), testVector_capacity.getSize());
//		assertEquals(testVector_none.getSize(), testVector_size.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_capacity.getCapacity());
//		assertEquals(testVector_size.getSize(), testVector_capacity.getSize());
		assertEquals((Integer)ENTRY_3, testVector_none.back());
		assertEquals((Integer)ENTRY_3, testVector_size.back());
		assertEquals((Integer)ENTRY_3, testVector_capacity.back());
		
		testVector_none.shrinkToFit();
		testVector_size.shrinkToFit();
		testVector_capacity.shrinkToFit();
//		assertEquals(testVector_none.getSize(), testVector_size.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_capacity.getCapacity());
//		assertEquals(testVector_size.getSize(), testVector_capacity.getSize());
//		assertEquals(testVector_none.getSize(), testVector_size.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_capacity.getCapacity());
//		assertEquals(testVector_size.getSize(), testVector_capacity.getSize());
		assertEquals((Integer)ENTRY_3, testVector_none.back());
		assertEquals((Integer)ENTRY_3, testVector_size.back());
		assertEquals((Integer)ENTRY_3, testVector_capacity.back());
		
		testVector_none.popBack();
		testVector_size.popBack();
		testVector_capacity.popBack();
//		assertEquals(testVector_none.getSize(), testVector_size.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_capacity.getCapacity());
//		assertEquals(testVector_size.getSize(), testVector_capacity.getSize());
//		assertEquals(testVector_none.getSize(), testVector_size.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_capacity.getCapacity());
//		assertEquals(testVector_size.getSize(), testVector_capacity.getSize());
		assertEquals((Integer)ENTRY_2, testVector_none.back());
		assertEquals((Integer)ENTRY_2, testVector_size.back());
		assertEquals((Integer)ENTRY_2, testVector_capacity.back());
	}
	
	@Test
	public void integrity_compareToClone_none() {
		final int ENTRY_1 = 5;
		final int ENTRY_2 = 10;
		final int ENTRY_3 = 15;
		
		IVector<Integer> testVector_none = new Vector<>();
		IVector<Integer> testVector_none_clone = new Vector<>();
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		testVector_none.pushBack(ENTRY_1);
		testVector_none_clone.pushBack(ENTRY_1);
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(testVector_none.back(), (Integer) ENTRY_1);
		assertEquals(testVector_none.back(), testVector_none_clone.back());
		testVector_none.pushBack(ENTRY_2);
		testVector_none_clone.pushBack(ENTRY_2);
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(testVector_none.back(), (Integer) ENTRY_2);
		assertEquals(testVector_none.back(), testVector_none_clone.back());
		testVector_none.pushBack(ENTRY_3);
		testVector_none_clone.pushBack(ENTRY_3);
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(testVector_none.back(), (Integer) ENTRY_3);
		assertEquals(testVector_none.back(), testVector_none_clone.back());
		testVector_none.shrinkToFit();
		testVector_none_clone.shrinkToFit();
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(testVector_none.back(), (Integer) ENTRY_3);
		assertEquals(testVector_none.back(), testVector_none_clone.back());
		testVector_none.popBack();
		testVector_none_clone.popBack();
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(testVector_none.back(), (Integer) ENTRY_2);
		assertEquals(testVector_none.back(), testVector_none_clone.back());
	}
	
	@Test
	public void integrity_compareToClone_size() {
		final int ENTRY_1 = 5;
		final int ENTRY_2 = 10;
		final int ENTRY_3 = 15;
		final int SIZE = 1;
		
		IVector<Integer> testVector_size = new Vector<>(SIZE);
		IVector<Integer> testVector_size_clone = new Vector<>(SIZE);
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		testVector_size.pushBack(ENTRY_1);
		testVector_size_clone.pushBack(ENTRY_1);
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(testVector_size.back(), (Integer) ENTRY_1);
		assertEquals(testVector_size.back(), testVector_size_clone.back());
		testVector_size.pushBack(ENTRY_2);
		testVector_size_clone.pushBack(ENTRY_2);
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(testVector_size.back(), (Integer) ENTRY_2);
		assertEquals(testVector_size.back(), testVector_size_clone.back());
		testVector_size.pushBack(ENTRY_3);
		testVector_size_clone.pushBack(ENTRY_3);
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(testVector_size.back(), (Integer) ENTRY_3);
		assertEquals(testVector_size.back(), testVector_size_clone.back());
		testVector_size.shrinkToFit();
		testVector_size_clone.shrinkToFit();
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(testVector_size.back(), (Integer) ENTRY_3);
		assertEquals(testVector_size.back(), testVector_size_clone.back());
		testVector_size.popBack();
		testVector_size_clone.popBack();
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(testVector_size.back(), (Integer) ENTRY_2);
		assertEquals(testVector_size.back(), testVector_size_clone.back());
	}
	
	@Test
	public void integrity_compareToClone_capacity() {
		final int ENTRY_1 = 5;
		final int ENTRY_2 = 10;
		final int ENTRY_3 = 15;
		final int SIZE = 0;
		final int CAPACITY = 1;
		
		IVector<Integer> testVector_capacity = new Vector<>(SIZE, CAPACITY);
		IVector<Integer> testVector_capacity_clone = new Vector<>(SIZE, CAPACITY);
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		testVector_capacity.pushBack(ENTRY_1);
		testVector_capacity_clone.pushBack(ENTRY_1);
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(testVector_capacity.back(), (Integer) ENTRY_1);
		assertEquals(testVector_capacity.back(), testVector_capacity_clone.back());
		testVector_capacity.pushBack(ENTRY_2);
		testVector_capacity_clone.pushBack(ENTRY_2);
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(testVector_capacity.back(), (Integer) ENTRY_2);
		assertEquals(testVector_capacity.back(), testVector_capacity_clone.back());
		testVector_capacity.pushBack(ENTRY_3);
		testVector_capacity_clone.pushBack(ENTRY_3);
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(testVector_capacity.back(), (Integer) ENTRY_3);
		assertEquals(testVector_capacity.back(), testVector_capacity_clone.back());
		testVector_capacity.shrinkToFit();
		testVector_capacity_clone.shrinkToFit();
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(testVector_capacity.back(), (Integer) ENTRY_3);
		assertEquals(testVector_capacity.back(), testVector_capacity_clone.back());
		testVector_capacity.popBack();
		testVector_capacity_clone.popBack();
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(testVector_capacity.back(), (Integer) ENTRY_2);
		assertEquals(testVector_capacity.back(), testVector_capacity_clone.back());
	}
	
	@Test
	public void integrity_compareToString_none() {
		final int ENTRY_1 = 5;
		final int ENTRY_2 = 10;
		final int ENTRY_3 = 15;
		final String expected = "3 4 [ " + ENTRY_1
								   + " " + ENTRY_2
							  	   + " " + ENTRY_3 + " ]";
		
		IVector<Integer> testVector_none = new Vector<>();
		testVector_none.pushBack(ENTRY_1);
		testVector_none.pushBack(ENTRY_2);
		testVector_none.pushBack(ENTRY_3);
		assertEquals(true, testVector_none.toString().equals(expected));
	}

	@Test
	public void integrity_compareToString_size() {
		final int ENTRY_1 = 5;
		final int ENTRY_2 = 10;
		final int ENTRY_3 = 15;
		final String expected = "3 4 [ " + ENTRY_1
								   + " " + ENTRY_2
							  	   + " " + ENTRY_3 + " ]";
		
		IVector<Integer> testVector_size = new Vector<>(1);
//		testVector_size.pushBack(ENTRY_1);
		testVector_size.set(0, ENTRY_1);
		testVector_size.pushBack(ENTRY_2);
		testVector_size.pushBack(ENTRY_3);
		assertEquals(true, testVector_size.toString().equals(expected));
	}
	
	@Test
	public void integrity_compareToString_capacity() {
		final int ENTRY_1 = 5;
		final int ENTRY_2 = 10;
		final int ENTRY_3 = 15;
		final String expected = "3 4 [ " + ENTRY_1
								   + " " + ENTRY_2
							  	   + " " + ENTRY_3 + " ]";
		
		IVector<Integer> testVector_capacity = new Vector<>(0, 1);
		testVector_capacity.pushBack(ENTRY_1);
		testVector_capacity.pushBack(ENTRY_2);
		testVector_capacity.pushBack(ENTRY_3);
		assertEquals(true, testVector_capacity.toString().equals(expected));
	}
	
	@Test
	public void makeTypeInt() {
		final int ENTRY_1 = 10;
		final int ENTRY_2 = 20;
		final int ENTRY_3 = 30;
		IVector<Integer> testVector_none = new Vector<>();
		IVector<Integer> testVector_none_clone = new Vector<>();
		IVector<Integer> testVector_size = new Vector<>(1);
		IVector<Integer> testVector_size_clone = new Vector<>(1);
		IVector<Integer> testVector_capacity = new Vector<>(0, 1);
		IVector<Integer> testVector_capacity_clone = new Vector<>(0, 1);
		
		final String expected = "2 3 [ " + ENTRY_1
								   + " " + ENTRY_2 + " ]";
		
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		testVector_none.pushBack(ENTRY_1);
		testVector_none_clone.pushBack(ENTRY_1);
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(testVector_none.back(), (Integer) ENTRY_1);
		assertEquals(testVector_none.back(), testVector_none_clone.back());
		testVector_none.pushBack(ENTRY_2);
		testVector_none_clone.pushBack(ENTRY_2);
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(testVector_none.back(), (Integer) ENTRY_2);
		assertEquals(testVector_none.back(), testVector_none_clone.back());
		testVector_none.pushBack(ENTRY_3);
		testVector_none_clone.pushBack(ENTRY_3);
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(testVector_none.back(), (Integer) ENTRY_3);
		assertEquals(testVector_none.back(), testVector_none_clone.back());
		testVector_none.shrinkToFit();
		testVector_none_clone.shrinkToFit();
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(testVector_none.back(), (Integer) ENTRY_3);
		assertEquals(testVector_none.back(), testVector_none_clone.back());
		testVector_none.popBack();
		testVector_none_clone.popBack();
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(testVector_none.back(), (Integer) ENTRY_2);
		assertEquals(testVector_none.back(), testVector_none_clone.back());
		assertEquals(true, testVector_none.toString().equals(expected));
		assertEquals(true, testVector_none.toString().equals(testVector_none_clone.toString()));
		
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
//		testVector_size.pushBack(ENTRY_1);
//		testVector_size_clone.pushBack(ENTRY_1);
		testVector_size.set(0, ENTRY_1);
		testVector_size_clone.set(0, ENTRY_1);
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(testVector_size.back(), (Integer) ENTRY_1);
		assertEquals(testVector_size.back(), testVector_size_clone.back());
		testVector_size.pushBack(ENTRY_2);
		testVector_size_clone.pushBack(ENTRY_2);
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(testVector_size.back(), (Integer) ENTRY_2);
		assertEquals(testVector_size.back(), testVector_size_clone.back());
		testVector_size.pushBack(ENTRY_3);
		testVector_size_clone.pushBack(ENTRY_3);
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(testVector_size.back(), (Integer) ENTRY_3);
		assertEquals(testVector_size.back(), testVector_size_clone.back());
		testVector_size.shrinkToFit();
		testVector_size_clone.shrinkToFit();
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(testVector_size.back(), (Integer) ENTRY_3);
		assertEquals(testVector_size.back(), testVector_size_clone.back());
		testVector_size.popBack();
		testVector_size_clone.popBack();
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(testVector_size.back(), (Integer) ENTRY_2);
		assertEquals(testVector_size.back(), testVector_size_clone.back());
		assertEquals(true, testVector_size.toString().equals(expected));
		assertEquals(true, testVector_size.toString().equals(testVector_size_clone.toString()));
		
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		testVector_capacity.pushBack(ENTRY_1);
		testVector_capacity_clone.pushBack(ENTRY_1);
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(testVector_capacity.back(), (Integer) ENTRY_1);
		assertEquals(testVector_capacity.back(), testVector_capacity_clone.back());
		testVector_capacity.pushBack(ENTRY_2);
		testVector_capacity_clone.pushBack(ENTRY_2);
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(testVector_capacity.back(), (Integer) ENTRY_2);
		assertEquals(testVector_capacity.back(), testVector_capacity_clone.back());
		testVector_capacity.pushBack(ENTRY_3);
		testVector_capacity_clone.pushBack(ENTRY_3);
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(testVector_capacity.back(), (Integer) ENTRY_3);
		assertEquals(testVector_capacity.back(), testVector_capacity_clone.back());
		testVector_capacity.shrinkToFit();
		testVector_capacity_clone.shrinkToFit();
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(testVector_capacity.back(), (Integer) ENTRY_3);
		assertEquals(testVector_capacity.back(), testVector_capacity_clone.back());
		testVector_capacity.popBack();
		testVector_capacity_clone.popBack();
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(testVector_capacity.back(), (Integer) ENTRY_2);
		assertEquals(testVector_capacity.back(), testVector_capacity_clone.back());
		assertEquals(true, testVector_capacity.toString().equals(expected));
		assertEquals(true, testVector_capacity.toString().equals(testVector_capacity_clone.toString()));
	}
	
	@Test
	public void makeTypeDouble() {
		final double ENTRY_1 = 5.0;
		final double ENTRY_2 = 7.0;
		final double ENTRY_3 = 9.0;
		IVector<Double> testVector_none = new Vector<>();
		IVector<Double> testVector_none_clone = new Vector<>();
		IVector<Double> testVector_size = new Vector<>(1);
		IVector<Double> testVector_size_clone = new Vector<>(1);
		IVector<Double> testVector_capacity = new Vector<>(0, 1);
		IVector<Double> testVector_capacity_clone = new Vector<>(0, 1);
		
		final String expected = "2 3 [ " + ENTRY_1
								   + " " + ENTRY_2 + " ]";
		
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		testVector_none.pushBack(ENTRY_1);
		testVector_none_clone.pushBack(ENTRY_1);
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(true, testVector_none.back().equals(ENTRY_1));
		assertEquals(true, testVector_none.back().equals(testVector_none_clone.back()));
		testVector_none.pushBack(ENTRY_2);
		testVector_none_clone.pushBack(ENTRY_2);
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(true, testVector_none.back().equals(ENTRY_2));
		assertEquals(true, testVector_none.back().equals(testVector_none_clone.back()));
		testVector_none.pushBack(ENTRY_3);
		testVector_none_clone.pushBack(ENTRY_3);
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(true, testVector_none.back().equals(ENTRY_3));
		assertEquals(true, testVector_none.back().equals(testVector_none_clone.back()));
		testVector_none.shrinkToFit();
		testVector_none_clone.shrinkToFit();
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(true, testVector_none.back().equals(ENTRY_3));
		assertEquals(true, testVector_none.back().equals(testVector_none_clone.back()));
		testVector_none.popBack();
		testVector_none_clone.popBack();
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(true, testVector_none.back().equals(ENTRY_2));
		assertEquals(true, testVector_none.back().equals(testVector_none_clone.back()));
		assertEquals(true, testVector_none.toString().equals(expected));
		assertEquals(true, testVector_none.toString().equals(testVector_none_clone.toString()));
		
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
//		testVector_size.pushBack(ENTRY_1);
//		testVector_size_clone.pushBack(ENTRY_1);
		testVector_size.set(0, ENTRY_1);
		testVector_size_clone.set(0, ENTRY_1);
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(true, testVector_size.back().equals(ENTRY_1));
		assertEquals(true, testVector_size.back().equals(testVector_size_clone.back()));
		testVector_size.pushBack(ENTRY_2);
		testVector_size_clone.pushBack(ENTRY_2);
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(true, testVector_size.back().equals(ENTRY_2));
		assertEquals(true, testVector_size.back().equals(testVector_size_clone.back()));
		testVector_size.pushBack(ENTRY_3);
		testVector_size_clone.pushBack(ENTRY_3);
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(true, testVector_size.back().equals(ENTRY_3));
		assertEquals(true, testVector_size.back().equals(testVector_size_clone.back()));
		testVector_size.shrinkToFit();
		testVector_size_clone.shrinkToFit();
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(true, testVector_size.back().equals(ENTRY_3));
		assertEquals(true, testVector_size.back().equals(testVector_size_clone.back()));
		testVector_size.popBack();
		testVector_size_clone.popBack();
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(true, testVector_size.back().equals(ENTRY_2));
		assertEquals(true, testVector_size.back().equals(testVector_size_clone.back()));
		assertEquals(true, testVector_size.toString().equals(expected));
		assertEquals(true, testVector_size.toString().equals(testVector_size_clone.toString()));
		
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		testVector_capacity.pushBack(ENTRY_1);
		testVector_capacity_clone.pushBack(ENTRY_1);
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(true, testVector_capacity.back().equals(ENTRY_1));
		assertEquals(true, testVector_capacity.back().equals(testVector_capacity_clone.back()));
		testVector_capacity.pushBack(ENTRY_2);
		testVector_capacity_clone.pushBack(ENTRY_2);
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(true, testVector_capacity.back().equals(ENTRY_2));
		assertEquals(true, testVector_capacity.back().equals(testVector_capacity_clone.back()));
		testVector_capacity.pushBack(ENTRY_3);
		testVector_capacity_clone.pushBack(ENTRY_3);
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(true, testVector_capacity.back().equals(ENTRY_3));
		assertEquals(true, testVector_capacity.back().equals(testVector_capacity_clone.back()));
		testVector_capacity.shrinkToFit();
		testVector_capacity_clone.shrinkToFit();
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(true, testVector_capacity.back().equals(ENTRY_3));
		assertEquals(true, testVector_capacity.back().equals(testVector_capacity_clone.back()));
		testVector_capacity.popBack();
		testVector_capacity_clone.popBack();
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(true, testVector_capacity.back().equals(ENTRY_2));
		assertEquals(true, testVector_capacity.back().equals(testVector_capacity_clone.back()));
		assertEquals(true, testVector_capacity.toString().equals(expected));
		assertEquals(true, testVector_capacity.toString().equals(testVector_capacity_clone.toString()));
	}
	
	@Test
	public void makeTypeBoolean() {
		final boolean ENTRY_1 = true;
		final boolean ENTRY_2 = false;
		final boolean ENTRY_3 = true;
		IVector<Boolean> testVector_none = new Vector<>();
		IVector<Boolean> testVector_none_clone = new Vector<>();
		IVector<Boolean> testVector_size = new Vector<>(1);
		IVector<Boolean> testVector_size_clone = new Vector<>(1);
		IVector<Boolean> testVector_capacity = new Vector<>(0, 1);
		IVector<Boolean> testVector_capacity_clone = new Vector<>(0, 1);
		
		final String expected = "2 3 [ " + ENTRY_1
								   + " " + ENTRY_2 + " ]";
		
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		testVector_none.pushBack(ENTRY_1);
		testVector_none_clone.pushBack(ENTRY_1);
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(true, testVector_none.back().equals(ENTRY_1));
		assertEquals(true, testVector_none.back().equals(testVector_none_clone.back()));
		testVector_none.pushBack(ENTRY_2);
		testVector_none_clone.pushBack(ENTRY_2);
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(true, testVector_none.back().equals(ENTRY_2));
		assertEquals(true, testVector_none.back().equals(testVector_none_clone.back()));
		testVector_none.pushBack(ENTRY_3);
		testVector_none_clone.pushBack(ENTRY_3);
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(true, testVector_none.back().equals(ENTRY_3));
		assertEquals(true, testVector_none.back().equals(testVector_none_clone.back()));
		testVector_none.shrinkToFit();
		testVector_none_clone.shrinkToFit();
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(true, testVector_none.back().equals(ENTRY_3));
		assertEquals(true, testVector_none.back().equals(testVector_none_clone.back()));
		testVector_none.popBack();
		testVector_none_clone.popBack();
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(true, testVector_none.back().equals(ENTRY_2));
		assertEquals(true, testVector_none.back().equals(testVector_none_clone.back()));
		assertEquals(true, testVector_none.toString().equals(expected));
		assertEquals(true, testVector_none.toString().equals(testVector_none_clone.toString()));
		
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
//		testVector_size.pushBack(ENTRY_1);
//		testVector_size_clone.pushBack(ENTRY_1);
		testVector_size.set(0, ENTRY_1);
		testVector_size_clone.set(0, ENTRY_1);
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(true, testVector_size.back().equals(ENTRY_1));
		assertEquals(true, testVector_size.back().equals(testVector_size_clone.back()));
		testVector_size.pushBack(ENTRY_2);
		testVector_size_clone.pushBack(ENTRY_2);
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(true, testVector_size.back().equals(ENTRY_2));
		assertEquals(true, testVector_size.back().equals(testVector_size_clone.back()));
		testVector_size.pushBack(ENTRY_3);
		testVector_size_clone.pushBack(ENTRY_3);
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(true, testVector_size.back().equals(ENTRY_3));
		assertEquals(true, testVector_size.back().equals(testVector_size_clone.back()));
		testVector_size.shrinkToFit();
		testVector_size_clone.shrinkToFit();
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(true, testVector_size.back().equals(ENTRY_3));
		assertEquals(true, testVector_size.back().equals(testVector_size_clone.back()));
		testVector_size.popBack();
		testVector_size_clone.popBack();
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(true, testVector_size.back().equals(ENTRY_2));
		assertEquals(true, testVector_size.back().equals(testVector_size_clone.back()));
		assertEquals(true, testVector_size.toString().equals(expected));
		assertEquals(true, testVector_size.toString().equals(testVector_size_clone.toString()));
		
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		testVector_capacity.pushBack(ENTRY_1);
		testVector_capacity_clone.pushBack(ENTRY_1);
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(true, testVector_capacity.back().equals(ENTRY_1));
		assertEquals(true, testVector_capacity.back().equals(testVector_capacity_clone.back()));
		testVector_capacity.pushBack(ENTRY_2);
		testVector_capacity_clone.pushBack(ENTRY_2);
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(true, testVector_capacity.back().equals(ENTRY_2));
		assertEquals(true, testVector_capacity.back().equals(testVector_capacity_clone.back()));
		testVector_capacity.pushBack(ENTRY_3);
		testVector_capacity_clone.pushBack(ENTRY_3);
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(true, testVector_capacity.back().equals(ENTRY_3));
		assertEquals(true, testVector_capacity.back().equals(testVector_capacity_clone.back()));
		testVector_capacity.shrinkToFit();
		testVector_capacity_clone.shrinkToFit();
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(true, testVector_capacity.back().equals(ENTRY_3));
		assertEquals(true, testVector_capacity.back().equals(testVector_capacity_clone.back()));
		testVector_capacity.popBack();
		testVector_capacity_clone.popBack();
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(true, testVector_capacity.back().equals(ENTRY_2));
		assertEquals(true, testVector_capacity.back().equals(testVector_capacity_clone.back()));
		assertEquals(true, testVector_capacity.toString().equals(expected));
		assertEquals(true, testVector_capacity.toString().equals(testVector_capacity_clone.toString()));
	}
	
	@Test
	public void makeTypeChar() {
		final char ENTRY_1 = 'b';
		final char ENTRY_2 = 'c';
		final char ENTRY_3 = 'a';
		IVector<Character> testVector_none = new Vector<>();
		IVector<Character> testVector_none_clone = new Vector<>();
		IVector<Character> testVector_size = new Vector<>(1);
		IVector<Character> testVector_size_clone = new Vector<>(1);
		IVector<Character> testVector_capacity = new Vector<>(0, 1);
		IVector<Character> testVector_capacity_clone = new Vector<>(0, 1);
		
		final String expected = "2 3 [ " + ENTRY_1
								   + " " + ENTRY_2 + " ]";
		
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		testVector_none.pushBack(ENTRY_1);
		testVector_none_clone.pushBack(ENTRY_1);
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(true, testVector_none.back().equals(ENTRY_1));
		assertEquals(true, testVector_none.back().equals(testVector_none_clone.back()));
		testVector_none.pushBack(ENTRY_2);
		testVector_none_clone.pushBack(ENTRY_2);
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(true, testVector_none.back().equals(ENTRY_2));
		assertEquals(true, testVector_none.back().equals(testVector_none_clone.back()));
		testVector_none.pushBack(ENTRY_3);
		testVector_none_clone.pushBack(ENTRY_3);
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(true, testVector_none.back().equals(ENTRY_3));
		assertEquals(true, testVector_none.back().equals(testVector_none_clone.back()));
		testVector_none.shrinkToFit();
		testVector_none_clone.shrinkToFit();
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(true, testVector_none.back().equals(ENTRY_3));
		assertEquals(true, testVector_none.back().equals(testVector_none_clone.back()));
		testVector_none.popBack();
		testVector_none_clone.popBack();
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(true, testVector_none.back().equals(ENTRY_2));
		assertEquals(true, testVector_none.back().equals(testVector_none_clone.back()));
		assertEquals(true, testVector_none.toString().equals(expected));
		assertEquals(true, testVector_none.toString().equals(testVector_none_clone.toString()));
		
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
//		testVector_size.pushBack(ENTRY_1);
//		testVector_size_clone.pushBack(ENTRY_1);
		testVector_size.set(0, ENTRY_1);
		testVector_size_clone.set(0, ENTRY_1);
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(true, testVector_size.back().equals(ENTRY_1));
		assertEquals(true, testVector_size.back().equals(testVector_size_clone.back()));
		testVector_size.pushBack(ENTRY_2);
		testVector_size_clone.pushBack(ENTRY_2);
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(true, testVector_size.back().equals(ENTRY_2));
		assertEquals(true, testVector_size.back().equals(testVector_size_clone.back()));
		testVector_size.pushBack(ENTRY_3);
		testVector_size_clone.pushBack(ENTRY_3);
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(true, testVector_size.back().equals(ENTRY_3));
		assertEquals(true, testVector_size.back().equals(testVector_size_clone.back()));
		testVector_size.shrinkToFit();
		testVector_size_clone.shrinkToFit();
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(true, testVector_size.back().equals(ENTRY_3));
		assertEquals(true, testVector_size.back().equals(testVector_size_clone.back()));
		testVector_size.popBack();
		testVector_size_clone.popBack();
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(true, testVector_size.back().equals(ENTRY_2));
		assertEquals(true, testVector_size.back().equals(testVector_size_clone.back()));
		assertEquals(true, testVector_size.toString().equals(expected));
		assertEquals(true, testVector_size.toString().equals(testVector_size_clone.toString()));
		
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		testVector_capacity.pushBack(ENTRY_1);
		testVector_capacity_clone.pushBack(ENTRY_1);
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(true, testVector_capacity.back().equals(ENTRY_1));
		assertEquals(true, testVector_capacity.back().equals(testVector_capacity_clone.back()));
		testVector_capacity.pushBack(ENTRY_2);
		testVector_capacity_clone.pushBack(ENTRY_2);
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(true, testVector_capacity.back().equals(ENTRY_2));
		assertEquals(true, testVector_capacity.back().equals(testVector_capacity_clone.back()));
		testVector_capacity.pushBack(ENTRY_3);
		testVector_capacity_clone.pushBack(ENTRY_3);
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(true, testVector_capacity.back().equals(ENTRY_3));
		assertEquals(true, testVector_capacity.back().equals(testVector_capacity_clone.back()));
		testVector_capacity.shrinkToFit();
		testVector_capacity_clone.shrinkToFit();
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(true, testVector_capacity.back().equals(ENTRY_3));
		assertEquals(true, testVector_capacity.back().equals(testVector_capacity_clone.back()));
		testVector_capacity.popBack();
		testVector_capacity_clone.popBack();
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(true, testVector_capacity.back().equals(ENTRY_2));
		assertEquals(true, testVector_capacity.back().equals(testVector_capacity_clone.back()));
		assertEquals(true, testVector_capacity.toString().equals(expected));
		assertEquals(true, testVector_capacity.toString().equals(testVector_capacity_clone.toString()));
	}
	
	@Test
	public void makeTypeString() {
		final String ENTRY_1 = "can";
		final String ENTRY_2 = "you";
		final String ENTRY_3 = "jump";
		IVector<String> testVector_none = new Vector<>();
		IVector<String> testVector_none_clone = new Vector<>();
		IVector<String> testVector_size = new Vector<>(1);
		IVector<String> testVector_size_clone = new Vector<>(1);
		IVector<String> testVector_capacity = new Vector<>(0, 1);
		IVector<String> testVector_capacity_clone = new Vector<>(0, 1);
		
		final String expected = "2 3 [ " + ENTRY_1
								   + " " + ENTRY_2 + " ]";
		
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		testVector_none.pushBack(ENTRY_1);
		testVector_none_clone.pushBack(ENTRY_1);
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(true, testVector_none.back().equals(ENTRY_1));
		assertEquals(true, testVector_none.back().equals(testVector_none_clone.back()));
		testVector_none.pushBack(ENTRY_2);
		testVector_none_clone.pushBack(ENTRY_2);
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(true, testVector_none.back().equals(ENTRY_2));
		assertEquals(true, testVector_none.back().equals(testVector_none_clone.back()));
		testVector_none.pushBack(ENTRY_3);
		testVector_none_clone.pushBack(ENTRY_3);
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(true, testVector_none.back().equals(ENTRY_3));
		assertEquals(true, testVector_none.back().equals(testVector_none_clone.back()));
		testVector_none.shrinkToFit();
		testVector_none_clone.shrinkToFit();
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(true, testVector_none.back().equals(ENTRY_3));
		assertEquals(true, testVector_none.back().equals(testVector_none_clone.back()));
		testVector_none.popBack();
		testVector_none_clone.popBack();
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(true, testVector_none.back().equals(ENTRY_2));
		assertEquals(true, testVector_none.back().equals(testVector_none_clone.back()));
		assertEquals(true, testVector_none.toString().equals(expected));
		assertEquals(true, testVector_none.toString().equals(testVector_none_clone.toString()));
		
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
//		testVector_size.pushBack(ENTRY_1);
//		testVector_size_clone.pushBack(ENTRY_1);
		testVector_size.set(0, ENTRY_1);
		testVector_size_clone.set(0, ENTRY_1);
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(true, testVector_size.back().equals(ENTRY_1));
		assertEquals(true, testVector_size.back().equals(testVector_size_clone.back()));
		testVector_size.pushBack(ENTRY_2);
		testVector_size_clone.pushBack(ENTRY_2);
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(true, testVector_size.back().equals(ENTRY_2));
		assertEquals(true, testVector_size.back().equals(testVector_size_clone.back()));
		testVector_size.pushBack(ENTRY_3);
		testVector_size_clone.pushBack(ENTRY_3);
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(true, testVector_size.back().equals(ENTRY_3));
		assertEquals(true, testVector_size.back().equals(testVector_size_clone.back()));
		testVector_size.shrinkToFit();
		testVector_size_clone.shrinkToFit();
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(true, testVector_size.back().equals(ENTRY_3));
		assertEquals(true, testVector_size.back().equals(testVector_size_clone.back()));
		testVector_size.popBack();
		testVector_size_clone.popBack();
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(true, testVector_size.back().equals(ENTRY_2));
		assertEquals(true, testVector_size.back().equals(testVector_size_clone.back()));
		assertEquals(true, testVector_size.toString().equals(expected));
		assertEquals(true, testVector_size.toString().equals(testVector_size_clone.toString()));
		
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		testVector_capacity.pushBack(ENTRY_1);
		testVector_capacity_clone.pushBack(ENTRY_1);
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(true, testVector_capacity.back().equals(ENTRY_1));
		assertEquals(true, testVector_capacity.back().equals(testVector_capacity_clone.back()));
		testVector_capacity.pushBack(ENTRY_2);
		testVector_capacity_clone.pushBack(ENTRY_2);
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(true, testVector_capacity.back().equals(ENTRY_2));
		assertEquals(true, testVector_capacity.back().equals(testVector_capacity_clone.back()));
		testVector_capacity.pushBack(ENTRY_3);
		testVector_capacity_clone.pushBack(ENTRY_3);
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(true, testVector_capacity.back().equals(ENTRY_3));
		assertEquals(true, testVector_capacity.back().equals(testVector_capacity_clone.back()));
		testVector_capacity.shrinkToFit();
		testVector_capacity_clone.shrinkToFit();
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(true, testVector_capacity.back().equals(ENTRY_3));
		assertEquals(true, testVector_capacity.back().equals(testVector_capacity_clone.back()));
		testVector_capacity.popBack();
		testVector_capacity_clone.popBack();
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(true, testVector_capacity.back().equals(ENTRY_2));
		assertEquals(true, testVector_capacity.back().equals(testVector_capacity_clone.back()));
		assertEquals(true, testVector_capacity.toString().equals(expected));
		assertEquals(true, testVector_capacity.toString().equals(testVector_capacity_clone.toString()));
	}
	
	@Test
	public void makeTypeMyClass() {
		final MyClass ENTRY_1 = new MyClass();
		final MyClass ENTRY_2 = new MyClass();
		final MyClass ENTRY_3 = new MyClass();
		IVector<MyClass> testVector_none = new Vector<>();
		IVector<MyClass> testVector_none_clone = new Vector<>();
		IVector<MyClass> testVector_size = new Vector<>(1);
		IVector<MyClass> testVector_size_clone = new Vector<>(1);
		IVector<MyClass> testVector_capacity = new Vector<>(0, 1);
		IVector<MyClass> testVector_capacity_clone = new Vector<>(0, 1);
		
		final String expected = "2 3 [ " + ENTRY_1
								   + " " + ENTRY_2 + " ]";
		
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		testVector_none.pushBack(ENTRY_1);
		testVector_none_clone.pushBack(ENTRY_1);
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(true, testVector_none.back().toString().equals(ENTRY_1.toString()));
		assertEquals(true, testVector_none.back().toString().equals(testVector_none_clone.back().toString()));
		testVector_none.pushBack(ENTRY_2);
		testVector_none_clone.pushBack(ENTRY_2);
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(true, testVector_none.back().toString().equals(ENTRY_2.toString()));
		assertEquals(true, testVector_none.back().toString().equals(testVector_none_clone.back().toString()));
		testVector_none.pushBack(ENTRY_3);
		testVector_none_clone.pushBack(ENTRY_3);
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(true, testVector_none.back().toString().equals(ENTRY_3.toString()));
		assertEquals(true, testVector_none.back().toString().equals(testVector_none_clone.back().toString()));
		testVector_none.shrinkToFit();
		testVector_none_clone.shrinkToFit();
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(true, testVector_none.back().toString().equals(ENTRY_3.toString()));
		assertEquals(true, testVector_none.back().toString().equals(testVector_none_clone.back().toString()));
		testVector_none.popBack();
		testVector_none_clone.popBack();
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(true, testVector_none.back().toString().equals(ENTRY_2.toString()));
		assertEquals(true, testVector_none.back().toString().equals(testVector_none_clone.back().toString()));
		assertEquals(true, testVector_none.toString().equals(expected));
		assertEquals(true, testVector_none.toString().equals(testVector_none_clone.toString()));
		
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
//		testVector_size.pushBack(ENTRY_1);
//		testVector_size_clone.pushBack(ENTRY_1);
		testVector_size.set(0, ENTRY_1);
		testVector_size_clone.set(0, ENTRY_1);
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(true, testVector_size.back().toString().equals(ENTRY_1.toString()));
		assertEquals(true, testVector_size.back().toString().equals(testVector_size_clone.back().toString()));
		testVector_size.pushBack(ENTRY_2);
		testVector_size_clone.pushBack(ENTRY_2);
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(true, testVector_size.back().toString().equals(ENTRY_2.toString()));
		assertEquals(true, testVector_size.back().toString().equals(testVector_size_clone.back().toString()));
		testVector_size.pushBack(ENTRY_3);
		testVector_size_clone.pushBack(ENTRY_3);
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(true, testVector_size.back().toString().equals(ENTRY_3.toString()));
		assertEquals(true, testVector_size.back().toString().equals(testVector_size_clone.back().toString()));
		testVector_size.shrinkToFit();
		testVector_size_clone.shrinkToFit();
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(true, testVector_size.back().toString().equals(ENTRY_3.toString()));
		assertEquals(true, testVector_size.back().toString().equals(testVector_size_clone.back().toString()));
		testVector_size.popBack();
		testVector_size_clone.popBack();
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(true, testVector_size.back().toString().equals(ENTRY_2.toString()));
		assertEquals(true, testVector_size.back().toString().equals(testVector_size_clone.back().toString()));
		assertEquals(true, testVector_size.toString().equals(expected));
		assertEquals(true, testVector_size.toString().equals(testVector_size_clone.toString()));
		
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		testVector_capacity.pushBack(ENTRY_1);
		testVector_capacity_clone.pushBack(ENTRY_1);
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(true, testVector_capacity.back().toString().equals(ENTRY_1.toString()));
		assertEquals(true, testVector_capacity.back().toString().equals(testVector_capacity_clone.back().toString()));
		testVector_capacity.pushBack(ENTRY_2);
		testVector_capacity_clone.pushBack(ENTRY_2);
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(true, testVector_capacity.back().toString().equals(ENTRY_2.toString()));
		assertEquals(true, testVector_capacity.back().toString().equals(testVector_capacity_clone.back().toString()));
		testVector_capacity.pushBack(ENTRY_3);
		testVector_capacity_clone.pushBack(ENTRY_3);
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(true, testVector_capacity.back().toString().equals(ENTRY_3.toString()));
		assertEquals(true, testVector_capacity.back().toString().equals(testVector_capacity_clone.back().toString()));
		testVector_capacity.shrinkToFit();
		testVector_capacity_clone.shrinkToFit();
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(true, testVector_capacity.back().toString().equals(ENTRY_3.toString()));
		assertEquals(true, testVector_capacity.back().toString().equals(testVector_capacity_clone.back().toString()));
		testVector_capacity.popBack();
		testVector_capacity_clone.popBack();
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(true, testVector_capacity.back().toString().equals(ENTRY_2.toString()));
		assertEquals(true, testVector_capacity.back().toString().equals(testVector_capacity_clone.back().toString()));
		assertEquals(true, testVector_capacity.toString().equals(expected));
		assertEquals(true, testVector_capacity.toString().equals(testVector_capacity_clone.toString()));
	}
	
	@Test
	public void makeTypeMySubClass() {
		final MySubClass ENTRY_1 = new MySubClass();
		final MySubClass ENTRY_2 = new MySubClass();
		final MySubClass ENTRY_3 = new MySubClass();
		IVector<MySubClass> testVector_none = new Vector<>();
		IVector<MySubClass> testVector_none_clone = new Vector<>();
		IVector<MySubClass> testVector_size = new Vector<>(1);
		IVector<MySubClass> testVector_size_clone = new Vector<>(1);
		IVector<MySubClass> testVector_capacity = new Vector<>(0, 1);
		IVector<MySubClass> testVector_capacity_clone = new Vector<>(0, 1);
		
		final String expected = "2 3 [ " + ENTRY_1
								   + " " + ENTRY_2 + " ]";
		
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		testVector_none.pushBack(ENTRY_1);
		testVector_none_clone.pushBack(ENTRY_1);
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(true, testVector_none.back().equals(ENTRY_1));
		assertEquals(true, testVector_none.back().equals(testVector_none_clone.back()));
		testVector_none.pushBack(ENTRY_2);
		testVector_none_clone.pushBack(ENTRY_2);
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(true, testVector_none.back().equals(ENTRY_2));
		assertEquals(true, testVector_none.back().equals(testVector_none_clone.back()));
		testVector_none.pushBack(ENTRY_3);
		testVector_none_clone.pushBack(ENTRY_3);
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(true, testVector_none.back().equals(ENTRY_3));
		assertEquals(true, testVector_none.back().equals(testVector_none_clone.back()));
		testVector_none.shrinkToFit();
		testVector_none_clone.shrinkToFit();
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(true, testVector_none.back().equals(ENTRY_3));
		assertEquals(true, testVector_none.back().equals(testVector_none_clone.back()));
		testVector_none.popBack();
		testVector_none_clone.popBack();
		assertEquals(testVector_none.getSize(), testVector_none_clone.getSize());
		assertEquals(testVector_none.getCapacity(), testVector_none_clone.getCapacity());
		assertEquals(true, testVector_none.back().equals(ENTRY_2));
		assertEquals(true, testVector_none.back().equals(testVector_none_clone.back()));
		assertEquals(true, testVector_none.toString().equals(expected));
		assertEquals(true, testVector_none.toString().equals(testVector_none_clone.toString()));
		
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
//		testVector_size.pushBack(ENTRY_1);
//		testVector_size_clone.pushBack(ENTRY_1);
		testVector_size.set(0, ENTRY_1);
		testVector_size_clone.set(0, ENTRY_1);
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(true, testVector_size.back().equals(ENTRY_1));
		assertEquals(true, testVector_size.back().equals(testVector_size_clone.back()));
		testVector_size.pushBack(ENTRY_2);
		testVector_size_clone.pushBack(ENTRY_2);
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(true, testVector_size.back().equals(ENTRY_2));
		assertEquals(true, testVector_size.back().equals(testVector_size_clone.back()));
		testVector_size.pushBack(ENTRY_3);
		testVector_size_clone.pushBack(ENTRY_3);
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(true, testVector_size.back().equals(ENTRY_3));
		assertEquals(true, testVector_size.back().equals(testVector_size_clone.back()));
		testVector_size.shrinkToFit();
		testVector_size_clone.shrinkToFit();
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(true, testVector_size.back().equals(ENTRY_3));
		assertEquals(true, testVector_size.back().equals(testVector_size_clone.back()));
		testVector_size.popBack();
		testVector_size_clone.popBack();
		assertEquals(testVector_size.getSize(), testVector_size_clone.getSize());
		assertEquals(testVector_size.getCapacity(), testVector_size_clone.getCapacity());
		assertEquals(true, testVector_size.back().equals(ENTRY_2));
		assertEquals(true, testVector_size.back().equals(testVector_size_clone.back()));
		assertEquals(true, testVector_size.toString().equals(expected));
		assertEquals(true, testVector_size.toString().equals(testVector_size_clone.toString()));
		
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		testVector_capacity.pushBack(ENTRY_1);
		testVector_capacity_clone.pushBack(ENTRY_1);
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(true, testVector_capacity.back().equals(ENTRY_1));
		assertEquals(true, testVector_capacity.back().equals(testVector_capacity_clone.back()));
		testVector_capacity.pushBack(ENTRY_2);
		testVector_capacity_clone.pushBack(ENTRY_2);
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(true, testVector_capacity.back().equals(ENTRY_2));
		assertEquals(true, testVector_capacity.back().equals(testVector_capacity_clone.back()));
		testVector_capacity.pushBack(ENTRY_3);
		testVector_capacity_clone.pushBack(ENTRY_3);
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(true, testVector_capacity.back().equals(ENTRY_3));
		assertEquals(true, testVector_capacity.back().equals(testVector_capacity_clone.back()));
		testVector_capacity.shrinkToFit();
		testVector_capacity_clone.shrinkToFit();
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(true, testVector_capacity.back().equals(ENTRY_3));
		assertEquals(true, testVector_capacity.back().equals(testVector_capacity_clone.back()));
		testVector_capacity.popBack();
		testVector_capacity_clone.popBack();
		assertEquals(testVector_capacity.getSize(), testVector_capacity_clone.getSize());
		assertEquals(testVector_capacity.getCapacity(), testVector_capacity_clone.getCapacity());
		assertEquals(true, testVector_capacity.back().equals(ENTRY_2));
		assertEquals(true, testVector_capacity.back().equals(testVector_capacity_clone.back()));
		assertEquals(true, testVector_capacity.toString().equals(expected));
		assertEquals(true, testVector_capacity.toString().equals(testVector_capacity_clone.toString()));
	}
}
