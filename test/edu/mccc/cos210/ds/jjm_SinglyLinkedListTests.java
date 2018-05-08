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

public class jjm_SinglyLinkedListTests {
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
        report.append("\nTesting: ").append(SinglyLinkedList.class.getSimpleName()).append("\n");
	}
	
	@AfterClass
	public static void tearDownClass() {
	    System.out.println(report.toString());
	}
	
//TESTING
	@Test
	public void addingGettingFirst() {
		final int TEST_SIZE = 20;
		
		ISinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testList.addFirst(i);
			assertEquals(i, (int) testList.getFirst());
		}
	}
	
	@Test
	public void removingFirst() {
		final int TEST_SIZE = 20;
		
		ISinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
		StringBuilder testListOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		for (int i = 0; i < TEST_SIZE; i++) {
			testList.addFirst(i);
			compareToSB.insert(0, i);
		}
		for (int i = 0; i < TEST_SIZE; i++) {
			testListOutput.append(testList.removeFirst());
		}
		assertEquals(true, compareToSB.toString().equals(testListOutput.toString()));
	}
	
	@Test
	public void addingGettingLast() {
		final int TEST_SIZE = 20;
		
		ISinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testList.addLast(i);
			assertEquals(i, (int) testList.getLast());
		}
	}
	
	@Test
	public void listSizing() {
		final int TEST_SIZE = 20;
		
		ISinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			assertEquals(i, testList.getSize());
			testList.addFirst(i);
		}
		assertEquals(TEST_SIZE, testList.getSize());
	}
	
	@Test
	public void doesListEmpty() {
		final int TEST_SIZE = 20;
		
		ISinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
		testList.addFirst(0);
		assertEquals(false, testList.isEmpty());
		testList.removeFirst();
		assertEquals(true, testList.isEmpty());
		for (int i = 0; i < TEST_SIZE; i++) {
			testList.addFirst(i);
		}
		assertEquals(false, testList.isEmpty());
		for (int i = 0; i < TEST_SIZE; i++) {
			testList.removeFirst();
		}
		assertEquals(true, testList.isEmpty());
	}
	
	@Test (expected=java.util.NoSuchElementException.class)
	public void getFirstWhileEmpty() {
		ISinglyLinkedList<MyClass> testList = new SinglyLinkedList<>();
		assertEquals(true, testList.isEmpty());

		@SuppressWarnings("unused")
		MyClass testMyClass = testList.getFirst();
	}
	
	@Test (expected=java.util.NoSuchElementException.class)
	public void getLastWhileEmpty() {
		ISinglyLinkedList<MyClass> testList = new SinglyLinkedList<>();
		assertEquals(true, testList.isEmpty());

		@SuppressWarnings("unused")
		MyClass testMyClass = testList.getLast();
	}
	
	@Test (expected=java.util.NoSuchElementException.class)
	public void removeFirstWhileEmpty() {
		ISinglyLinkedList<MyClass> testList = new SinglyLinkedList<>();
		assertEquals(true, testList.isEmpty());

		@SuppressWarnings("unused")
		MyClass testMyClass = testList.removeFirst();
	}
	
	@Test
	public void integrityOverLargeListFirst() {
		final int TEST_SIZE = 100;
		
		ISinglyLinkedList<Integer> largeList = new SinglyLinkedList<>();
		ISinglyLinkedList<Integer> compareToList = new SinglyLinkedList<>();
		StringBuilder largeListOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		for (int i = 0; i <= TEST_SIZE; i++) {
			largeList.addFirst(i);
			compareToList.addFirst(i);
			compareToSB.append(TEST_SIZE - i);
		}
		int largeListEntry;
		for (int i = 0; i <= TEST_SIZE; i++) {
			largeListEntry = largeList.removeFirst();
			largeListOutput.append(largeListEntry);
			assertEquals(largeListEntry, (int) compareToList.removeFirst());
		}
		assertEquals(true, largeListOutput.toString().equals(compareToSB.toString()));
	}
	
	@Test
	public void integrityOverLargeListLast() {
		final int TEST_SIZE = 100;
		
		ISinglyLinkedList<Integer> largeList = new SinglyLinkedList<>();
		ISinglyLinkedList<Integer> compareToList = new SinglyLinkedList<>();
		StringBuilder largeListOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		for (int i = 0; i <= TEST_SIZE; i++) {
			largeList.addLast(i);
			compareToList.addLast(i);
			compareToSB.append(i);
		}
		int largeListEntry;
		for (int i = 0; i <= TEST_SIZE; i++) {
			largeListEntry = largeList.removeFirst();
			largeListOutput.append(largeListEntry);
			assertEquals(largeListEntry, (int) compareToList.removeFirst());
		}
		assertEquals(true, largeListOutput.toString().equals(compareToSB.toString()));
	}
	
	@Test
	public void integrityOverLargeListAlternate() {
		final int TEST_SIZE = 100;
		
		ISinglyLinkedList<Integer> largeList = new SinglyLinkedList<>();
		StringBuilder largeListOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		boolean flipflop = false;
		for (int i = 0; i <= TEST_SIZE; i++) {
			flipflop = !flipflop;
			if (flipflop == true) {
				largeList.addFirst(i);
				compareToSB.insert(0, i);
			} else {
				largeList.addLast(i);
				compareToSB.append(i);
			}
		}
		for (int i = 0; i <= TEST_SIZE; i++) {
			largeListOutput.append(largeList.removeFirst());
		}
		assertEquals(true, largeListOutput.toString().equals(compareToSB.toString()));
	}
	
	@Test
	public void makeTypeInt() {
		ISinglyLinkedList<Integer> intList = new SinglyLinkedList<>();
		assertEquals(true, intList.isEmpty());
		intList.addFirst(5);
		assertEquals(false, intList.isEmpty());
		assertEquals(1, intList.getSize());
		assertEquals((Integer) 5, intList.getFirst());
		assertEquals((Integer) 5, intList.getLast());
		intList.addLast(6);
		assertEquals(false, intList.isEmpty());
		assertEquals(2, intList.getSize());
		assertEquals((Integer) 5, intList.getFirst());
		assertEquals((Integer) 6, intList.getLast());
		intList.addFirst(4);
		assertEquals(false, intList.isEmpty());
		assertEquals(3, intList.getSize());
		assertEquals((Integer) 4, intList.getFirst());
		assertEquals((Integer) 6, intList.getLast());
		StringBuilder intResult = new StringBuilder();
		for(Integer e : intList) {
			intResult.append(e);
		}
		assertEquals("456", intResult.toString());
	}
	
	@Test
	public void makeTypeString() {
		ISinglyLinkedList<String> stringList = new SinglyLinkedList<>();
		assertEquals(true, stringList.isEmpty());
		stringList.addFirst("BBBBB");
		assertEquals(false, stringList.isEmpty());
		assertEquals(1, stringList.getSize());
		assertEquals("BBBBB", stringList.getFirst());
		assertEquals("BBBBB", stringList.getLast());
		stringList.addLast("CCCCC");
		assertEquals(false, stringList.isEmpty());
		assertEquals(2, stringList.getSize());
		assertEquals("BBBBB", stringList.getFirst());
		assertEquals("CCCCC", stringList.getLast());
		stringList.addFirst("AAAAA");
		assertEquals(false, stringList.isEmpty());
		assertEquals(3, stringList.getSize());
		assertEquals("AAAAA", stringList.getFirst());
		assertEquals("CCCCC", stringList.getLast());
	}
	
	@Test
	public void makeTypeBool() {
		ISinglyLinkedList<Boolean> boolList = new SinglyLinkedList<>();
		assertEquals(true, boolList.isEmpty());
		boolList.addFirst(true);
		assertEquals(false, boolList.isEmpty());
		assertEquals(1, boolList.getSize());
		assertEquals(true, boolList.getFirst());
		assertEquals(true, boolList.getLast());
		boolList.addLast(false);
		assertEquals(false, boolList.isEmpty());
		assertEquals(2, boolList.getSize());
		assertEquals(true, boolList.getFirst());
		assertEquals(false, boolList.getLast());
		boolList.addFirst(false);
		assertEquals(false, boolList.isEmpty());
		assertEquals(3, boolList.getSize());
		assertEquals(false, boolList.getFirst());
		assertEquals(false, boolList.getLast());
	}
	@Test
	public void makeTypeDouble() {
		ISinglyLinkedList<Double> doubleList = new SinglyLinkedList<>();
		assertEquals(true, doubleList.isEmpty());
		doubleList.addFirst(5.0);
		assertEquals(false, doubleList.isEmpty());
		assertEquals(1, doubleList.getSize());
		assertEquals((Double) 5.0, doubleList.getFirst());
		assertEquals((Double) 5.0, doubleList.getLast());
		doubleList.addLast(6.0);
		assertEquals(false, doubleList.isEmpty());
		assertEquals(2, doubleList.getSize());
		assertEquals((Double) 5.0, doubleList.getFirst());
		assertEquals((Double) 6.0, doubleList.getLast());
		doubleList.addFirst(4.0);
		assertEquals(false, doubleList.isEmpty());
		assertEquals(3, doubleList.getSize());
		assertEquals((Double) 4.0, doubleList.getFirst());
		assertEquals((Double) 6.0, doubleList.getLast());
		StringBuilder intResult = new StringBuilder();
		for(Double e : doubleList) {
			intResult.append(e);
		}
		assertEquals("4.05.06.0", intResult.toString());
	}
	
	@Test
	public void makeTypeChar() {
		ISinglyLinkedList<Character> charList = new SinglyLinkedList<>();
		assertEquals(true, charList.isEmpty());
		charList.addFirst('b');
		assertEquals(false, charList.isEmpty());
		assertEquals(1, charList.getSize());
		assertEquals((Character) 'b', charList.getFirst());
		assertEquals((Character) 'b', charList.getLast());
		charList.addLast('c');
		assertEquals(false, charList.isEmpty());
		assertEquals(2, charList.getSize());
		assertEquals((Character) 'b', charList.getFirst());
		assertEquals((Character) 'c', charList.getLast());
		charList.addFirst('a');
		assertEquals(false, charList.isEmpty());
		assertEquals(3, charList.getSize());
		assertEquals((Character) 'a', charList.getFirst());
		assertEquals((Character) 'c', charList.getLast());
		StringBuilder intResult = new StringBuilder();
		for(Character e : charList) {
			intResult.append(e);
		}
		assertEquals("abc", intResult.toString());
	}
	
	@Test
	public void makeTypeMyClass() {
		ISinglyLinkedList<MyClass> myClassList = new SinglyLinkedList<>();
		assertEquals(true, myClassList.isEmpty());
		MyClass myClass1 = new MyClass();
		myClassList.addFirst(myClass1);
		assertEquals(false, myClassList.isEmpty());
		assertEquals(1, myClassList.getSize());
		assertEquals(myClass1.hashCode(), myClassList.getFirst().hashCode());
		MyClass myClass2 = new MyClass();
		myClassList.addLast(myClass2);
		assertEquals(2, myClassList.getSize());
		assertEquals(myClass2.hashCode(), myClassList.getLast().hashCode());
		assertEquals(myClass1.hashCode(), myClassList.getFirst().hashCode());
		MyClass myClass3 = new MyClass();
		myClassList.addFirst(myClass3);
		assertEquals(3, myClassList.getSize());
		assertEquals(myClass3.hashCode(), myClassList.getFirst().hashCode());
		assertEquals(myClass2.hashCode(), myClassList.getLast().hashCode());
	}
	
	@Test
	public void makeTypeMySubClass() {
		ISinglyLinkedList<MySubClass> mySubClassList = new SinglyLinkedList<>();
		assertEquals(true, mySubClassList.isEmpty());
		MySubClass mySubClass1 = new MySubClass();
		mySubClassList.addFirst(mySubClass1);
		assertEquals(false, mySubClassList.isEmpty());
		assertEquals(1, mySubClassList.getSize());
		assertEquals(mySubClass1, mySubClassList.getFirst());
		MySubClass mySubClass2 = new MySubClass();
		mySubClassList.addLast(mySubClass2);
		assertEquals(2, mySubClassList.getSize());
		assertEquals(mySubClass2, mySubClassList.getLast());
		assertEquals(mySubClass1, mySubClassList.getFirst());
		MySubClass mySubClass3 = new MySubClass();
		mySubClassList.addFirst(mySubClass3);
		assertEquals(3, mySubClassList.getSize());
		assertEquals(mySubClass3, mySubClassList.getFirst());
		assertEquals(mySubClass2, mySubClassList.getLast());
	}
}
