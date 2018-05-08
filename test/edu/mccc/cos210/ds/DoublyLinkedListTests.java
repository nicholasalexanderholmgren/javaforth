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

public class DoublyLinkedListTests {
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
	       report.append("\nTesting: ").append(DoublyLinkedListTests.class.getSimpleName()).append("\n");
	}
	@AfterClass
	public static void tearDownClass() {
	    System.out.println(report.toString());
	}
	@Test
	public void makeTypeString() {
		IDoublyLinkedList<String> list = new DoublyLinkedList<>();
		assertEquals(true, list.isEmpty());
		list.addFirst("Hello");
		assertEquals(false, list.isEmpty());
		assertEquals(1, list.getSize());
		String s = list.getFirst();
		assertEquals("Hello", s);
	}
	@Test
	public void makeTypeInteger() {
		IDoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		assertEquals(true, list.isEmpty());
		list.addFirst(123);
		assertEquals(false, list.isEmpty());
		assertEquals(1, list.getSize());
		int n = list.getFirst();
		assertEquals(123, n);
	}
	@Test
	public void makeTypeMyClass() {
		IDoublyLinkedList<MyClass> list = new DoublyLinkedList<>();
		MyClass myClass = new MyClass();
		MySubClass mySubClass = new MySubClass();
		assertEquals(true, list.isEmpty());
		list.addFirst(myClass);
		assertEquals(false, list.isEmpty());
		assertEquals(1, list.getSize());
		MyClass mc = list.getFirst();
		assertEquals(myClass.hashCode(), mc.hashCode());
		list.addFirst(mySubClass);
		assertEquals(false, list.isEmpty());
		assertEquals(2, list.getSize());
		MyClass msc = list.getFirst();
		assertEquals(mySubClass.hashCode(), msc.hashCode());
		msc = list.removeFirst();
		assertEquals(mySubClass.hashCode(), msc.hashCode());
		mc = list.getFirst();
		assertEquals(myClass.hashCode(), mc.hashCode());
	}
	@Test (expected=java.util.NoSuchElementException.class)
	public void makeTypeMySubClass() {
		IDoublyLinkedList<MySubClass> list = new DoublyLinkedList<>();
		MySubClass mySubClass = new MySubClass();
		assertEquals(true, list.isEmpty());
		list.addFirst(mySubClass);
		assertEquals(false, list.isEmpty());
		assertEquals(1, list.getSize());
		MySubClass msc = list.removeFirst();
		assertEquals(mySubClass.hashCode(), msc.hashCode());
		list.removeFirst();
		list.getFirst();
	}
	@Test
	public void addAndIterateString() {
		IDoublyLinkedList<String> list = new DoublyLinkedList<>();
		list.addFirst("Apple");
		list.addFirst("Orange");
		list.addFirst("Peach");
		list.addFirst("Pear");
		list.addFirst("Lemon");
		StringBuilder sb1 = new StringBuilder();
		for (String s : list) {
			sb1.append(s);
		}
		assertEquals("LemonPearPeachOrangeApple", sb1.toString());
		final StringBuilder sb2 = new StringBuilder();
		list.stream().filter(s -> s.matches(".*[Pp].*")).forEach(t -> sb2.append(t));
		assertEquals("PearPeachApple", sb2.toString());
		StringBuilder sb3 = new StringBuilder();
		java.util.Iterator<String> rit = list.reverseIterator();
		while (rit.hasNext()) {
			sb3.append(rit.next());
		}
		assertEquals("AppleOrangePeachPearLemon", sb3.toString());
	}
	@Test
	public void addFirstAndLast() {
		IDoublyLinkedList<String> list1 = new DoublyLinkedList<>();
		list1.addFirst("1");
		assertEquals("1", list1.getFirst());
		assertEquals("1", list1.getLast());
		IDoublyLinkedList<String> list2 = new DoublyLinkedList<>();
		list2.addLast("1");
		assertEquals("1", list2.getFirst());
		assertEquals("1", list2.getLast());
		IDoublyLinkedList<String> list3 = new DoublyLinkedList<>();
		list3.addFirst("2");
		list3.addFirst("1");
		assertEquals("1", list3.getFirst());
		assertEquals("2", list3.getLast());
		IDoublyLinkedList<String> list4 = new DoublyLinkedList<>();
		list4.addLast("1");
		list4.addLast("2");
		assertEquals("1", list4.getFirst());
		assertEquals("2", list4.getLast());
		IDoublyLinkedList<String> list5 = new DoublyLinkedList<>();
		list5.addFirst("1");
		list5.addLast("2");
		assertEquals("1", list5.getFirst());
		assertEquals("2", list5.getLast());
		IDoublyLinkedList<String> list6 = new DoublyLinkedList<>();
		list6.addLast("2");
		list6.addFirst("1");
		assertEquals("1", list6.getFirst());
		assertEquals("2", list6.getLast());
		IDoublyLinkedList<String> list7 = new DoublyLinkedList<>();
		list7.addFirst("2");
		list7.addLast("3");
		list7.addFirst("1");
		list7.addLast("4");
		assertEquals("1", list7.getFirst());
		assertEquals("4", list7.getLast());
	}
	@Test
	public void iteratorRemove() {
		IDoublyLinkedList<String> list1 = new DoublyLinkedList<>();
		list1.addFirst("A");
		list1.addFirst("B");
		list1.addFirst("C");
		java.util.Iterator<String> it = list1.iterator();
		while (it.hasNext()) {
			if ("B".equals(it.next())) {
				it.remove();
				break;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (String s : list1) {
			sb.append(s);
		}
		assertEquals("CA", sb.toString());
	}
}
