package edu.mccc.cos210.ds;

import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import edu.mccc.cos210.MyClass;
import edu.mccc.cos210.MySubClass;

public class jjm_DoublyLinkedListTests {
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
        report.append("\nTesting: ").append(DoublyLinkedList.class.getSimpleName()).append("\n");
	}

	@AfterClass
	public static void tearDownClass() {
	    System.out.println(report.toString());
	}

	//Tests
	@Test
	public void startsEmpty() {
		IDoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();
		assertEquals(0, testDLL.getSize());
		assertEquals(true, testDLL.isEmpty());
	}

	public void doesEmpty() {
		final int TEST_SIZE = 10;

		IDoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testDLL.addFirst(i);
		}
		assertEquals(false, testDLL.isEmpty());
		for (int i = 0; i < TEST_SIZE; i++) {
			testDLL.removeFirst();
		}
		assertEquals(true, testDLL.isEmpty());
	}

	@Test
	public void addingGettingFirst() {
		final int TEST_SIZE = 10;

		IDoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testDLL.addFirst(i);
			assertEquals(i, (int) testDLL.getFirst());
		}
	}

	@Test
	public void addingGettingLast() {
		final int TEST_SIZE = 10;

		IDoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testDLL.addLast(i);
			assertEquals(i, (int) testDLL.getLast());
		}
	}

	@Test
	public void addingFirst_removingFirst() {
		final int TEST_SIZE = 10;

		IDoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();
		StringBuilder testDLLOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		for (int i = 0; i < TEST_SIZE; i++) {
			testDLL.addFirst(i);
			compareToSB.insert(0, i);
		}
		for (int i = 0; i < TEST_SIZE; i++) {
			testDLLOutput.append(testDLL.removeFirst());
		}
		assertEquals(true, testDLLOutput.toString().equals(compareToSB.toString()));
	}

	@Test
	public void addingFirst_removingLast() {
		final int TEST_SIZE = 10;

		IDoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();
		StringBuilder testDLLOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		for (int i = 0; i < TEST_SIZE; i++) {
			testDLL.addLast(i);
			compareToSB.append(i);
		}
		for (int i = 0; i < TEST_SIZE; i++) {
			testDLLOutput.append(testDLL.removeFirst());
		}
		assertEquals(true, testDLLOutput.toString().equals(compareToSB.toString()));
	}

	@Test
	public void addingLast_removingFirst() {
		final int TEST_SIZE = 10;

		IDoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();
		StringBuilder testDLLOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		for (int i = 0; i < TEST_SIZE; i++) {
			testDLL.addLast(i);
			compareToSB.append(i);
		}
		for (int i = 0; i < TEST_SIZE; i++) {
			testDLLOutput.append(testDLL.removeFirst());
		}
		assertEquals(true, testDLLOutput.toString().equals(compareToSB.toString()));
	}

	@Test
	public void addingLast_removingLast() {
		final int TEST_SIZE = 10;

		IDoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();
		StringBuilder testDLLOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		for (int i = 0; i < TEST_SIZE; i++) {
			testDLL.addLast(i);
			compareToSB.append(i);
		}
		for (int i = 0; i < TEST_SIZE; i++) {
			testDLLOutput.insert(0, testDLL.removeLast());
		}
		assertEquals(true, testDLLOutput.toString().equals(compareToSB.toString()));
	}

	@Test
	public void sizingAddingFirst() {
		final int TEST_SIZE = 10;

		IDoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			assertEquals(i, testDLL.getSize());
			testDLL.addFirst(i);
		}
	}

	@Test
	public void sizingRemovingFirst() {
		final int TEST_SIZE = 10;

		IDoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testDLL.addFirst(i);
		}
		for (int i = 0; i < TEST_SIZE; i++) {
			assertEquals(TEST_SIZE - i, testDLL.getSize());
			testDLL.removeFirst();
		}
	}

	@Test
	public void sizingAddingLast() {
		final int TEST_SIZE = 10;

		IDoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			assertEquals(i, testDLL.getSize());
			testDLL.addLast(i);
		}
	}

	@Test
	public void sizingRemovingLast() {
		final int TEST_SIZE = 10;

		IDoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testDLL.addLast(i);
		}
		for (int i = 0; i < TEST_SIZE; i++) {
			assertEquals(TEST_SIZE - i, testDLL.getSize());
			testDLL.removeLast();
		}
	}

	@Test
	public void addingAlternating_CheckWithSB() {
		final int TEST_SIZE = 50;

		IDoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();
		StringBuilder testDLLOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		boolean flipflop = false;
		for (int i = 0; i < TEST_SIZE; i++) {
			if (flipflop == true) {
				testDLL.addFirst(i);
				compareToSB.insert(0, i);
			} else {
				testDLL.addLast(i);
				compareToSB.append(i);
			}
			flipflop = !flipflop;
		}
		for (int i = 0; i < TEST_SIZE; i++) {
			testDLLOutput.append(testDLL.removeFirst());
		}
		assertEquals(true, testDLLOutput.toString().equals(compareToSB.toString()));
	}

	@Test
	public void addingAlternating_CheckAgainstClone() {
		final int TEST_SIZE = 50;

		IDoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();
		IDoublyLinkedList<Integer> compareToDLL = new DoublyLinkedList<>();
		boolean flipflop = false;
		for (int i = 0; i < TEST_SIZE; i++) {
			if (flipflop == true) {
				testDLL.addFirst(i);
				compareToDLL.addFirst(i);
			} else {
				testDLL.addLast(i);
				compareToDLL.addLast(i);
			}
			flipflop = !flipflop;
		}
		assertEquals(true, testDLL.toString().equals(compareToDLL.toString()));
	}

	public void removingAlternating() {
		final int TEST_SIZE = 50;

		IDoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testDLL.addFirst(i);
		}
		assertEquals(false, testDLL.isEmpty());
		boolean flipflop = false;
		for (int i = 0; i < TEST_SIZE; i++) {
			if (flipflop == true) {
				testDLL.removeFirst();
			} else {
				testDLL.removeLast();
			}
			flipflop = !flipflop;
		}
		assertEquals(true, testDLL.isEmpty());
	}

	@Test(expected=NoSuchElementException.class)
		public void removingFirstEmpty() {
			IDoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();
			assertEquals(true, testDLL.isEmpty());
			testDLL.removeFirst();
		}

	@Test(expected=NoSuchElementException.class)
		public void removingLastEmpty() {
			IDoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();
			assertEquals(true, testDLL.isEmpty());
			testDLL.removeLast();
		}

	@Test(expected=NoSuchElementException.class)
		public void gettingFirstEmpty() {
			IDoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();
			assertEquals(true, testDLL.isEmpty());
			testDLL.getFirst();
		}

	@Test(expected=NoSuchElementException.class)
		public void gettingLastEmpty() {
			IDoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();
			assertEquals(true, testDLL.isEmpty());
			testDLL.getLast();
		}

	@Test
	public void iteratingRemoveHasEntry() {
		final int TEST_SIZE = 10;
		final int DESIRED_ENTRY = 7;

		IDoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();
		StringBuilder testDLLOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		for (int i = 0; i < TEST_SIZE; i++) {
			testDLL.addLast(i);
			compareToSB.append(i);
		}
		java.util.Iterator<Integer> it = testDLL.iterator();
		while (it.hasNext()) {
			if (it.next() == DESIRED_ENTRY) {
				it.remove();
			}
		}
		for (int i : testDLL) {
			testDLLOutput.append(i);
		}
		assertEquals(false, testDLLOutput.toString().equals(compareToSB.toString()));
	}

	@Test
	public void iteratingRemoveDoesNotHaveEntry() {
		final int TEST_SIZE = 10;
		final int DESIRED_ENTRY = 15;

		IDoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();
		StringBuilder testDLLOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		for (int i = 0; i < TEST_SIZE; i++) {
			testDLL.addLast(i);
			compareToSB.append(i);
		}
		java.util.Iterator<Integer> it = testDLL.iterator();
		while (it.hasNext()) {
			if (it.next() == DESIRED_ENTRY) {
				it.remove();
			}
		}
		for (int i : testDLL) {
			testDLLOutput.append(i);
		}
		assertEquals(true, testDLLOutput.toString().equals(compareToSB.toString()));
	}

	@Test (expected=NoSuchElementException.class)
	public void iteratingRemoveEmpty() {
		IDoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();
		java.util.Iterator<Integer> it = testDLL.iterator();
		assertEquals(true, testDLL.isEmpty());
		it.remove();
	}

	@Test
	public void iteratingRemoveAll() {
		final int TEST_SIZE = 10;

		IDoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();
		for (int i = 0; i < TEST_SIZE; i++) {
			testDLL.addLast(i);
		}
		for (int i = TEST_SIZE - 1; i >= 0; i--) {
			java.util.Iterator<Integer> it = testDLL.iterator();
			while (it.hasNext()) {
				if (it.next().equals(i)) {
					it.remove();
				}
			}
		}
		assertEquals(0, testDLL.getSize());
	}

	public void makeTypeInt() {
		final int ENTRY_1 = 5;
		final int ENTRY_2 = 10;
		final int ENTRY_3 = 15;

		IDoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();
		IDoublyLinkedList<Integer> compareToDLL = new DoublyLinkedList<>();
		StringBuilder testDLLOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		assertEquals(0, testDLL.getSize());
		assertEquals(true, testDLL.isEmpty());

		testDLL.addFirst(ENTRY_1);
		compareToDLL.addFirst(ENTRY_1);
		compareToSB.insert(0, ENTRY_1);
		assertEquals(true, testDLL.getFirst().equals(compareToDLL.getFirst()));
		assertEquals(true, testDLL.getLast().equals(compareToDLL.getLast()));
		assertEquals(1, testDLL.getSize());
		assertEquals(false, testDLL.isEmpty());

		testDLL.addLast(ENTRY_2);
		compareToDLL.addLast(ENTRY_2);
		compareToSB.append(ENTRY_2);
		assertEquals(true, testDLL.getFirst().equals(compareToDLL.getFirst()));
		assertEquals(true, testDLL.getLast().equals(compareToDLL.getLast()));
		assertEquals(2, testDLL.getSize());
		assertEquals(false, testDLL.isEmpty());

		testDLL.addFirst(ENTRY_3);
		compareToDLL.addFirst(ENTRY_3);
		compareToSB.insert(0, ENTRY_3);
		assertEquals(true, testDLL.getFirst().equals(compareToDLL.getFirst()));
		assertEquals(true, testDLL.getLast().equals(compareToDLL.getLast()));
		assertEquals(3, testDLL.getSize());
		assertEquals(false, testDLL.isEmpty());
		assertEquals(true, testDLL.toString().equals(compareToDLL.toString()));

		java.util.Iterator<Integer> it = testDLL.iterator();
		while (it.hasNext()) {
			if (it.next().equals(ENTRY_3)) {
				testDLLOutput.append(ENTRY_3);
				it.remove();
				break;
			}
		}
		assertEquals(2, testDLL.getSize());
		testDLLOutput.append(testDLL.removeFirst());
		assertEquals(1, testDLL.getSize());
		testDLLOutput.append(testDLL.removeLast());
		assertEquals(0, testDLL.getSize());
		assertEquals(true, testDLLOutput.toString().equals(compareToSB.toString()));
	}

	public void makeTypeBoolean() {
		final boolean ENTRY_1 = false;
		final boolean ENTRY_2 = false;
		final boolean ENTRY_3 = true;

		IDoublyLinkedList<Boolean> testDLL = new DoublyLinkedList<>();
		IDoublyLinkedList<Boolean> compareToDLL = new DoublyLinkedList<>();
		StringBuilder testDLLOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		assertEquals(0, testDLL.getSize());
		assertEquals(true, testDLL.isEmpty());

		testDLL.addFirst(ENTRY_1);
		compareToDLL.addFirst(ENTRY_1);
		compareToSB.insert(0, ENTRY_1);
		assertEquals(true, testDLL.getFirst().equals(compareToDLL.getFirst()));
		assertEquals(true, testDLL.getLast().equals(compareToDLL.getLast()));
		assertEquals(1, testDLL.getSize());
		assertEquals(false, testDLL.isEmpty());

		testDLL.addLast(ENTRY_2);
		compareToDLL.addLast(ENTRY_2);
		compareToSB.append(ENTRY_2);
		assertEquals(true, testDLL.getFirst().equals(compareToDLL.getFirst()));
		assertEquals(true, testDLL.getLast().equals(compareToDLL.getLast()));
		assertEquals(2, testDLL.getSize());
		assertEquals(false, testDLL.isEmpty());

		testDLL.addFirst(ENTRY_3);
		compareToDLL.addFirst(ENTRY_3);
		compareToSB.insert(0, ENTRY_3);
		assertEquals(true, testDLL.getFirst().equals(compareToDLL.getFirst()));
		assertEquals(true, testDLL.getLast().equals(compareToDLL.getLast()));
		assertEquals(3, testDLL.getSize());
		assertEquals(false, testDLL.isEmpty());
		assertEquals(true, testDLL.toString().equals(compareToDLL.toString()));

		java.util.Iterator<Boolean> it = testDLL.iterator();
		while (it.hasNext()) {
			if (it.next().equals(ENTRY_3)) {
				testDLLOutput.append(ENTRY_3);
				it.remove();
				break;
			}
		}
		assertEquals(2, testDLL.getSize());
		testDLLOutput.append(testDLL.removeFirst());
		assertEquals(1, testDLL.getSize());
		testDLLOutput.append(testDLL.removeLast());
		assertEquals(0, testDLL.getSize());
		assertEquals(true, testDLLOutput.toString().equals(compareToSB.toString()));
	}

	public void makeTypeDouble() {
		final double ENTRY_1 = 5.0;
		final double ENTRY_2 = 7.0;
		final double ENTRY_3 = 6.0;

		IDoublyLinkedList<Double> testDLL = new DoublyLinkedList<>();
		IDoublyLinkedList<Double> compareToDLL = new DoublyLinkedList<>();
		StringBuilder testDLLOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		assertEquals(0, testDLL.getSize());
		assertEquals(true, testDLL.isEmpty());

		testDLL.addFirst(ENTRY_1);
		compareToDLL.addFirst(ENTRY_1);
		compareToSB.insert(0, ENTRY_1);
		assertEquals(true, testDLL.getFirst().equals(compareToDLL.getFirst()));
		assertEquals(true, testDLL.getLast().equals(compareToDLL.getLast()));
		assertEquals(1, testDLL.getSize());
		assertEquals(false, testDLL.isEmpty());

		testDLL.addLast(ENTRY_2);
		compareToDLL.addLast(ENTRY_2);
		compareToSB.append(ENTRY_2);
		assertEquals(true, testDLL.getFirst().equals(compareToDLL.getFirst()));
		assertEquals(true, testDLL.getLast().equals(compareToDLL.getLast()));
		assertEquals(2, testDLL.getSize());
		assertEquals(false, testDLL.isEmpty());

		testDLL.addFirst(ENTRY_3);
		compareToDLL.addFirst(ENTRY_3);
		compareToSB.insert(0, ENTRY_3);
		assertEquals(true, testDLL.getFirst().equals(compareToDLL.getFirst()));
		assertEquals(true, testDLL.getLast().equals(compareToDLL.getLast()));
		assertEquals(3, testDLL.getSize());
		assertEquals(false, testDLL.isEmpty());
		assertEquals(true, testDLL.toString().equals(compareToDLL.toString()));

		java.util.Iterator<Double> it = testDLL.iterator();
		while (it.hasNext()) {
			if (it.next().equals(ENTRY_3)) {
				testDLLOutput.append(ENTRY_3);
				it.remove();
				break;
			}
		}
		assertEquals(2, testDLL.getSize());
		testDLLOutput.append(testDLL.removeFirst());
		assertEquals(1, testDLL.getSize());
		testDLLOutput.append(testDLL.removeLast());
		assertEquals(0, testDLL.getSize());
		assertEquals(true, testDLLOutput.toString().equals(compareToSB.toString()));
	}

	public void makeTypeString() {
		final String ENTRY_1 = "rocco";
		final String ENTRY_2 = "derrick";
		final String ENTRY_3 = "shawn";

		IDoublyLinkedList<String> testDLL = new DoublyLinkedList<>();
		IDoublyLinkedList<String> compareToDLL = new DoublyLinkedList<>();
		StringBuilder testDLLOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		assertEquals(0, testDLL.getSize());
		assertEquals(true, testDLL.isEmpty());

		testDLL.addFirst(ENTRY_1);
		compareToDLL.addFirst(ENTRY_1);
		compareToSB.insert(0, ENTRY_1);
		assertEquals(true, testDLL.getFirst().equals(compareToDLL.getFirst()));
		assertEquals(true, testDLL.getLast().equals(compareToDLL.getLast()));
		assertEquals(1, testDLL.getSize());
		assertEquals(false, testDLL.isEmpty());

		testDLL.addLast(ENTRY_2);
		compareToDLL.addLast(ENTRY_2);
		compareToSB.append(ENTRY_2);
		assertEquals(true, testDLL.getFirst().equals(compareToDLL.getFirst()));
		assertEquals(true, testDLL.getLast().equals(compareToDLL.getLast()));
		assertEquals(2, testDLL.getSize());
		assertEquals(false, testDLL.isEmpty());

		testDLL.addFirst(ENTRY_3);
		compareToDLL.addFirst(ENTRY_3);
		compareToSB.insert(0, ENTRY_3);
		assertEquals(true, testDLL.getFirst().equals(compareToDLL.getFirst()));
		assertEquals(true, testDLL.getLast().equals(compareToDLL.getLast()));
		assertEquals(3, testDLL.getSize());
		assertEquals(false, testDLL.isEmpty());
		assertEquals(true, testDLL.toString().equals(compareToDLL.toString()));

		java.util.Iterator<String> it = testDLL.iterator();
		while (it.hasNext()) {
			if (it.next().equals(ENTRY_3)) {
				testDLLOutput.append(ENTRY_3);
				it.remove();
				break;
			}
		}
		assertEquals(2, testDLL.getSize());
		testDLLOutput.append(testDLL.removeFirst());
		assertEquals(1, testDLL.getSize());
		testDLLOutput.append(testDLL.removeLast());
		assertEquals(0, testDLL.getSize());
		assertEquals(true, testDLLOutput.toString().equals(compareToSB.toString()));
	}

	public void makeTypeChar() {
		final char ENTRY_1 = 'y';
		final char ENTRY_2 = 'j';
		final char ENTRY_3 = 'm';

		IDoublyLinkedList<Character> testDLL = new DoublyLinkedList<>();
		IDoublyLinkedList<Character> compareToDLL = new DoublyLinkedList<>();
		StringBuilder testDLLOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		assertEquals(0, testDLL.getSize());
		assertEquals(true, testDLL.isEmpty());

		testDLL.addFirst(ENTRY_1);
		compareToDLL.addFirst(ENTRY_1);
		compareToSB.insert(0, ENTRY_1);
		assertEquals(true, testDLL.getFirst().equals(compareToDLL.getFirst()));
		assertEquals(true, testDLL.getLast().equals(compareToDLL.getLast()));
		assertEquals(1, testDLL.getSize());
		assertEquals(false, testDLL.isEmpty());

		testDLL.addLast(ENTRY_2);
		compareToDLL.addLast(ENTRY_2);
		compareToSB.append(ENTRY_2);
		assertEquals(true, testDLL.getFirst().equals(compareToDLL.getFirst()));
		assertEquals(true, testDLL.getLast().equals(compareToDLL.getLast()));
		assertEquals(2, testDLL.getSize());
		assertEquals(false, testDLL.isEmpty());

		testDLL.addFirst(ENTRY_3);
		compareToDLL.addFirst(ENTRY_3);
		compareToSB.insert(0, ENTRY_3);
		assertEquals(true, testDLL.getFirst().equals(compareToDLL.getFirst()));
		assertEquals(true, testDLL.getLast().equals(compareToDLL.getLast()));
		assertEquals(3, testDLL.getSize());
		assertEquals(false, testDLL.isEmpty());
		assertEquals(true, testDLL.toString().equals(compareToDLL.toString()));

		java.util.Iterator<Character> it = testDLL.iterator();
		while (it.hasNext()) {
			if (it.next().equals(ENTRY_3)) {
				testDLLOutput.append(ENTRY_3);
				it.remove();
				break;
			}
		}
		assertEquals(2, testDLL.getSize());
		testDLLOutput.append(testDLL.removeFirst());
		assertEquals(1, testDLL.getSize());
		testDLLOutput.append(testDLL.removeLast());
		assertEquals(0, testDLL.getSize());
		assertEquals(true, testDLLOutput.toString().equals(compareToSB.toString()));
	}

	public void makeTypeMyClass() {
		final MyClass ENTRY_1 = new MyClass();
		final MyClass ENTRY_2 = new MyClass();
		final MyClass ENTRY_3 = new MyClass();

		IDoublyLinkedList<MyClass> testDLL = new DoublyLinkedList<>();
		IDoublyLinkedList<MyClass> compareToDLL = new DoublyLinkedList<>();
		StringBuilder testDLLOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		assertEquals(0, testDLL.getSize());
		assertEquals(true, testDLL.isEmpty());

		testDLL.addFirst(ENTRY_1);
		compareToDLL.addFirst(ENTRY_1);
		compareToSB.insert(0, ENTRY_1);
		assertEquals(testDLL.getFirst().hashCode(), compareToDLL.getFirst().hashCode());
		assertEquals(testDLL.getLast().hashCode(), compareToDLL.getLast().hashCode());
		assertEquals(1, testDLL.getSize());
		assertEquals(false, testDLL.isEmpty());

		testDLL.addLast(ENTRY_2);
		compareToDLL.addLast(ENTRY_2);
		compareToSB.append(ENTRY_2);
		assertEquals(testDLL.getFirst().hashCode(), compareToDLL.getFirst().hashCode());
		assertEquals(testDLL.getLast().hashCode(), compareToDLL.getLast().hashCode());
		assertEquals(2, testDLL.getSize());
		assertEquals(false, testDLL.isEmpty());

		testDLL.addFirst(ENTRY_3);
		compareToDLL.addFirst(ENTRY_3);
		compareToSB.insert(0, ENTRY_3);
		assertEquals(testDLL.getFirst().hashCode(), compareToDLL.getFirst().hashCode());
		assertEquals(testDLL.getLast().hashCode(), compareToDLL.getLast().hashCode());
		assertEquals(3, testDLL.getSize());
		assertEquals(false, testDLL.isEmpty());
		assertEquals(true, testDLL.toString().equals(compareToDLL.toString()));

		java.util.Iterator<MyClass> it = testDLL.iterator();
		while (it.hasNext()) {
			if (it.next().hashCode() == ENTRY_3.hashCode()) {
				testDLLOutput.append(ENTRY_3.hashCode());
				it.remove();
				break;
			}
		}
		assertEquals(2, testDLL.getSize());
		testDLLOutput.append(testDLL.removeFirst().hashCode());
		assertEquals(1, testDLL.getSize());
		testDLLOutput.append(testDLL.removeLast().hashCode());
		assertEquals(0, testDLL.getSize());
		assertEquals(true, testDLLOutput.toString().equals(compareToSB.toString()));
	}

	public void makeTypeMySubClass() {
		final MySubClass ENTRY_1 = new MySubClass();
		final MySubClass ENTRY_2 = new MySubClass();
		final MySubClass ENTRY_3 = new MySubClass();

		IDoublyLinkedList<MySubClass> testDLL = new DoublyLinkedList<>();
		IDoublyLinkedList<MySubClass> compareToDLL = new DoublyLinkedList<>();
		StringBuilder testDLLOutput = new StringBuilder();
		StringBuilder compareToSB = new StringBuilder();
		assertEquals(0, testDLL.getSize());
		assertEquals(true, testDLL.isEmpty());

		testDLL.addFirst(ENTRY_1);
		compareToDLL.addFirst(ENTRY_1);
		compareToSB.insert(0, ENTRY_1);
		assertEquals(true, testDLL.getFirst().equals(compareToDLL.getFirst()));
		assertEquals(true, testDLL.getLast().equals(compareToDLL.getLast()));
		assertEquals(1, testDLL.getSize());
		assertEquals(false, testDLL.isEmpty());

		testDLL.addLast(ENTRY_2);
		compareToDLL.addLast(ENTRY_2);
		compareToSB.append(ENTRY_2);
		assertEquals(true, testDLL.getFirst().equals(compareToDLL.getFirst()));
		assertEquals(true, testDLL.getLast().equals(compareToDLL.getLast()));
		assertEquals(2, testDLL.getSize());
		assertEquals(false, testDLL.isEmpty());

		testDLL.addFirst(ENTRY_3);
		compareToDLL.addFirst(ENTRY_3);
		compareToSB.insert(0, ENTRY_3);
		assertEquals(true, testDLL.getFirst().equals(compareToDLL.getFirst()));
		assertEquals(true, testDLL.getLast().equals(compareToDLL.getLast()));
		assertEquals(3, testDLL.getSize());
		assertEquals(false, testDLL.isEmpty());
		assertEquals(true, testDLL.toString().equals(compareToDLL.toString()));

		java.util.Iterator<MySubClass> it = testDLL.iterator();
		while (it.hasNext()) {
			if (it.next().equals(ENTRY_3)) {
				testDLLOutput.append(ENTRY_3);
				it.remove();
				break;
			}
		}
		assertEquals(2, testDLL.getSize());
		testDLLOutput.append(testDLL.removeFirst());
		assertEquals(1, testDLL.getSize());
		testDLLOutput.append(testDLL.removeLast());
		assertEquals(0, testDLL.getSize());
		assertEquals(true, testDLLOutput.toString().equals(compareToSB.toString()));
	}
}
