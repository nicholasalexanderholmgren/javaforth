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

public class jjm_ArrayTests {
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
	        report.append("\nTesting: ").append(Array.class.getSimpleName()).append("\n");
		}
		
		@AfterClass
		public static void tearDownClass() {
		    System.out.println(report.toString());
		}
		
	//TESTING
		@Test
		public void settingAndGetting() {
			final int TEST_SIZE = 20;
			
			IArray<Integer> testArray = new Array<>(TEST_SIZE);
			StringBuilder testArrayOutput = new StringBuilder();
			StringBuilder compareTo = new StringBuilder();
			for (int i = 0; i < TEST_SIZE; i++) {
				testArray.set(i, i);
				compareTo.append(i);
			}
			for (int i = 0; i < TEST_SIZE; i++) {
				testArrayOutput.append(testArray.get(i));
			}
			assertEquals(true, testArrayOutput.toString().equals(compareTo.toString()));
		}
		
		@Test
		public void sizing() {
			final int TEST_SIZE = 20;
			
			IArray<Integer> testArray = new Array<>(TEST_SIZE);
			assertEquals(testArray.getSize(), TEST_SIZE);
			for (int i = 0; i < TEST_SIZE; i++) {
				testArray.set(i, 0);
				assertEquals(testArray.getSize(), TEST_SIZE);
			}
		}
		
		@Test
		public void canCreateZeroSized() {
			IArray<Integer> testArray = new Array<>(0);
			assertEquals(0, testArray.getSize());
		}
		
		@Test
		public void compareToClone() {
			final int TEST_SIZE = 10;
			
			IArray<Integer> testArray = new Array<>(TEST_SIZE);
			IArray<Integer> compareToArray = new Array<>(TEST_SIZE);
			for (int i = 0; i < TEST_SIZE; i++) {
				testArray.set(i, i);
				compareToArray.set(i, i);
				assertEquals(testArray.get(i), (compareToArray).get(i));
			}
			assertEquals(true, testArray.toString().equals(compareToArray.toString()));
		}
		
		@Test
		public void compareToSB() {
			final int TEST_SIZE = 10;
			
			IArray<Integer> testArray = new Array<>(TEST_SIZE);
			StringBuilder testArrayOutput = new StringBuilder();
			StringBuilder compareToSB = new StringBuilder();
			for (int i = 0; i < TEST_SIZE; i++) {
				testArray.set(i, i);
				compareToSB.append(i);
			}
			for (Integer i : testArray) {
				testArrayOutput.append(i);
			}
			assertEquals(true, testArrayOutput.toString().equals(compareToSB.toString()));
		}
		
		@Test (expected=NegativeArraySizeException.class)
		public void negativeSized() {
			@SuppressWarnings("unused")
			IArray<Integer> testArray = new Array<>(-5);
		}
		
		@Test
		public void gettingUnsetEntry() {
			IArray<Integer> testArray = new Array<>(1);
			assertEquals(null, testArray.get(0));
		}
		
		@Test (expected=ArrayIndexOutOfBoundsException.class)
		public void getIndexOutOfBounds() {
			final int TEST_SIZE = 10;
			
			IArray<Integer> testArray = new Array<>(TEST_SIZE);
			testArray.get(TEST_SIZE + 2);
		}
		
		@Test (expected=ArrayIndexOutOfBoundsException.class)
		public void setIndexOutOfBounds() {
			final int TEST_SIZE = 10;
			
			IArray<Integer> testArray = new Array<>(TEST_SIZE);
			testArray.set(TEST_SIZE + 1, 0);
		}
		
		@Test (expected=ArrayIndexOutOfBoundsException.class)
		public void getNegativeIndex() {
			final int TEST_SIZE = 10;
			
			IArray<Integer> testArray = new Array<>(TEST_SIZE);
			testArray.get(-TEST_SIZE);
		}
		
		@Test (expected=ArrayIndexOutOfBoundsException.class)
		public void setZeroSizedIndex() {
			IArray<Integer> testArray = new Array<>(0);
			testArray.set(0, 0);
		}
		
		@Test (expected=ArrayIndexOutOfBoundsException.class)
		public void getZeroSizedIndex() {
			IArray<Integer> testArray = new Array<>(0);
			testArray.get(0);
		}
		
		@Test (expected=ArrayIndexOutOfBoundsException.class)
		public void setNegativeIndex() {
			final int TEST_SIZE = 10;
			
			IArray<Integer> testArray = new Array<>(TEST_SIZE);
			testArray.set(-TEST_SIZE, 0);
		}
		
		@Test
		public void makeTypeInt() {
			final int TEST_SIZE = 3;
			final int ENTRY_1 = 5;
			final int ENTRY_2 = 10;
			final int ENTRY_3 = 15;
			
			IArray<Integer> intArray = new Array<>(TEST_SIZE);
			StringBuilder intArrayOutput = new StringBuilder();
			IArray<Integer> compareToArray = new Array<>(TEST_SIZE);
			StringBuilder compareToSB = new StringBuilder();
			assertEquals(TEST_SIZE, intArray.getSize());
			
			intArray.set(0, ENTRY_1);
			compareToArray.set(0, ENTRY_1);
			assertEquals(intArray.get(0), compareToArray.get(0));
			compareToSB.append(ENTRY_1);
			
			intArray.set(1, ENTRY_2);
			compareToArray.set(1, ENTRY_2);
			assertEquals(intArray.get(1), compareToArray.get(1));
			compareToSB.append(ENTRY_2);
			
			intArray.set(2, ENTRY_3);
			compareToArray.set(2, ENTRY_3);
			assertEquals(intArray.get(2), compareToArray.get(2));
			compareToSB.append(ENTRY_3);
			
			for (int i = 0; i < TEST_SIZE; i++) {
				intArrayOutput.append(intArray.get(i));
			}
			assertEquals(true, intArrayOutput.toString().equals(compareToSB.toString()));
		}
		
		@Test
		public void makeTypeBoolean() {
			final int TEST_SIZE = 3;
			final boolean ENTRY_1 = false;
			final boolean ENTRY_2 = true;
			final boolean ENTRY_3 = true;
			
			IArray<Boolean> booleanArray = new Array<>(TEST_SIZE);
			StringBuilder booleanArrayOutput = new StringBuilder();
			IArray<Boolean> compareToArray = new Array<>(TEST_SIZE);
			StringBuilder compareToSB = new StringBuilder();
			assertEquals(TEST_SIZE, booleanArray.getSize());
			
			booleanArray.set(0, ENTRY_1);
			compareToArray.set(0, ENTRY_1);
			assertEquals(booleanArray.get(0), compareToArray.get(0));
			compareToSB.append(ENTRY_1);
			
			booleanArray.set(1, ENTRY_2);
			compareToArray.set(1, ENTRY_2);
			assertEquals(booleanArray.get(1), compareToArray.get(1));
			compareToSB.append(ENTRY_2);
			
			booleanArray.set(2, ENTRY_3);
			compareToArray.set(2, ENTRY_3);
			assertEquals(booleanArray.get(2), compareToArray.get(2));
			compareToSB.append(ENTRY_3);
			
			for (int i = 0; i < TEST_SIZE; i++) {
				booleanArrayOutput.append(booleanArray.get(i));
			}
			assertEquals(true, booleanArrayOutput.toString().equals(compareToSB.toString()));
		}
		
		@Test
		public void makeTypeDouble() {
			final int TEST_SIZE = 3;
			final double ENTRY_1 = 5.0;
			final double ENTRY_2 = 10.0;
			final double ENTRY_3 = 15.0;
			
			IArray<Double> doubleArray = new Array<>(TEST_SIZE);
			StringBuilder doubleArrayOutput = new StringBuilder();
			IArray<Double> compareToArray = new Array<>(TEST_SIZE);
			StringBuilder compareToSB = new StringBuilder();
			assertEquals(TEST_SIZE, doubleArray.getSize());
			
			doubleArray.set(0, ENTRY_1);
			compareToArray.set(0, ENTRY_1);
			assertEquals(doubleArray.get(0), compareToArray.get(0));
			compareToSB.append(ENTRY_1);
			
			doubleArray.set(1, ENTRY_2);
			compareToArray.set(1, ENTRY_2);
			assertEquals(doubleArray.get(1), compareToArray.get(1));
			compareToSB.append(ENTRY_2);
			
			doubleArray.set(2, ENTRY_3);
			compareToArray.set(2, ENTRY_3);
			assertEquals(doubleArray.get(2), compareToArray.get(2));
			compareToSB.append(ENTRY_3);
			
			for (int i = 0; i < TEST_SIZE; i++) {
				doubleArrayOutput.append(doubleArray.get(i));
			}
			assertEquals(true, doubleArrayOutput.toString().equals(compareToSB.toString()));
		}

		@Test
		public void makeTypeString() {
			final int TEST_SIZE = 3;
			final String ENTRY_1 = "eric";
			final String ENTRY_2 = "garrett";
			final String ENTRY_3 = "kevin";
			
			IArray<String> stringArray = new Array<>(TEST_SIZE);
			StringBuilder stringArrayOutput = new StringBuilder();
			IArray<String> compareToArray = new Array<>(TEST_SIZE);
			StringBuilder compareToSB = new StringBuilder();
			assertEquals(TEST_SIZE, stringArray.getSize());
			
			stringArray.set(0, ENTRY_1);
			compareToArray.set(0, ENTRY_1);
			assertEquals(true, compareToArray.get(0).equals(stringArray.get(0)));
			compareToSB.append(ENTRY_1);
			
			stringArray.set(1, ENTRY_2);
			compareToArray.set(1, ENTRY_2);
			assertEquals(true, compareToArray.get(1).equals(stringArray.get(1)));
			compareToSB.append(ENTRY_2);
			
			stringArray.set(2, ENTRY_3);
			compareToArray.set(2, ENTRY_3);
			assertEquals(true, compareToArray.get(2).equals(stringArray.get(2)));
			compareToSB.append(ENTRY_3);
			
			for (int i = 0; i < TEST_SIZE; i++) {
				stringArrayOutput.append(stringArray.get(i));
			}
			assertEquals(true, stringArrayOutput.toString().equals(compareToSB.toString()));
		}
		
		@Test
		public void makeTypeChar() {
			final int TEST_SIZE = 3;
			final char ENTRY_1 = 'a';
			final char ENTRY_2 = 'b';
			final char ENTRY_3 = 'c';
			
			IArray<Character> charArray = new Array<>(TEST_SIZE);
			StringBuilder charArrayOutput = new StringBuilder();
			IArray<Character> compareToArray = new Array<>(TEST_SIZE);
			StringBuilder compareToSB = new StringBuilder();
			assertEquals(TEST_SIZE, charArray.getSize());
			
			charArray.set(0, ENTRY_1);
			compareToArray.set(0, ENTRY_1);
			assertEquals(charArray.get(0), compareToArray.get(0));
			compareToSB.append(ENTRY_1);
			
			charArray.set(1, ENTRY_2);
			compareToArray.set(1, ENTRY_2);
			assertEquals(charArray.get(1), compareToArray.get(1));
			compareToSB.append(ENTRY_2);
			
			charArray.set(2, ENTRY_3);
			compareToArray.set(2, ENTRY_3);
			assertEquals(charArray.get(2), compareToArray.get(2));
			compareToSB.append(ENTRY_3);
			
			for (int i = 0; i < TEST_SIZE; i++) {
				charArrayOutput.append(charArray.get(i));
			}
			assertEquals(true, charArrayOutput.toString().equals(compareToSB.toString()));
		}
		
		@Test
		public void makeTypeMyClass() {
			final int TEST_SIZE = 3;
			final MyClass ENTRY_1 = new MyClass();
			final MyClass ENTRY_2 = new MyClass();
			final MyClass ENTRY_3 = new MyClass();
			
			IArray<MyClass> myClassArray = new Array<>(TEST_SIZE);
			StringBuilder myClassArrayOutput = new StringBuilder();
			IArray<MyClass> compareToArray = new Array<>(TEST_SIZE);
			StringBuilder compareToSB = new StringBuilder();
			assertEquals(TEST_SIZE, myClassArray.getSize());
			
			myClassArray.set(0, ENTRY_1);
			compareToArray.set(0, ENTRY_1);
			assertEquals(myClassArray.get(0).hashCode(), compareToArray.get(0).hashCode());
			compareToSB.append(ENTRY_1);
			
			myClassArray.set(1, ENTRY_2);
			compareToArray.set(1, ENTRY_2);
			assertEquals(myClassArray.get(1).hashCode(), compareToArray.get(1).hashCode());
			compareToSB.append(ENTRY_2);
			
			myClassArray.set(2, ENTRY_3);
			compareToArray.set(2, ENTRY_3);
			assertEquals(myClassArray.get(2).hashCode(), compareToArray.get(2).hashCode());
			compareToSB.append(ENTRY_3);
			
			for (int i = 0; i < TEST_SIZE; i++) {
				myClassArrayOutput.append(myClassArray.get(i).hashCode());
			}
			assertEquals(true, myClassArrayOutput.toString().equals(compareToSB.toString()));
		}
		
		@Test
		public void makeTypeMySubclass() {
			final int TEST_SIZE = 3;
			final MySubClass ENTRY_1 = new MySubClass();
			final MySubClass ENTRY_2 = new MySubClass();
			final MySubClass ENTRY_3 = new MySubClass();
			
			IArray<MySubClass> mySubClassArray = new Array<>(TEST_SIZE);
			StringBuilder mySubClassArrayOutput = new StringBuilder();
			IArray<MySubClass> compareToArray = new Array<>(TEST_SIZE);
			StringBuilder compareToSB = new StringBuilder();
			assertEquals(TEST_SIZE, mySubClassArray.getSize());
			
			mySubClassArray.set(0, ENTRY_1);
			compareToArray.set(0, ENTRY_1);
			assertEquals(mySubClassArray.get(0), compareToArray.get(0));
			compareToSB.append(ENTRY_1);
			
			mySubClassArray.set(1, ENTRY_2);
			compareToArray.set(1, ENTRY_2);
			assertEquals(mySubClassArray.get(1), compareToArray.get(1));
			compareToSB.append(ENTRY_2);
			
			mySubClassArray.set(2, ENTRY_3);
			compareToArray.set(2, ENTRY_3);
			assertEquals(mySubClassArray.get(2), compareToArray.get(2));
			compareToSB.append(ENTRY_3);
			
			for (int i = 0; i < TEST_SIZE; i++) {
				mySubClassArrayOutput.append(mySubClassArray.get(i));
			}
			assertEquals(true, mySubClassArrayOutput.toString().equals(compareToSB.toString()));
		}
}
