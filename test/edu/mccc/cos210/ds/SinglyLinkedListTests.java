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

public class SinglyLinkedListTests {
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
        report.append("\nTesting: ").append(SinglyLinkedList.class.getSimpleName()).append("\n");
	}
	@AfterClass
	public static void tearDownClass() {
	    System.out.println(report.toString());
	}
	@Test
	public void makeTypeString() {
		ISinglyLinkedList<String> list = new SinglyLinkedList<>();
		assertEquals(true, list.isEmpty());
		list.addFirst("Hello");
		assertEquals(false, list.isEmpty());
		assertEquals(1, list.getSize());
		String s = list.getFirst();
		assertEquals("Hello", s);
	}
	@Test
	public void makeTypeInteger() {
		ISinglyLinkedList<Integer> list = new SinglyLinkedList<>();
		assertEquals(true, list.isEmpty());
		list.addFirst(123);
		assertEquals(false, list.isEmpty());
		assertEquals(1, list.getSize());
		int n = list.getFirst();
		assertEquals(123, n);
	}
	@Test
	public void makeTypeMyClass() {
		ISinglyLinkedList<MyClass> list = new SinglyLinkedList<>();
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
		ISinglyLinkedList<MySubClass> list = new SinglyLinkedList<>();
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
		ISinglyLinkedList<String> list = new SinglyLinkedList<>();
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
	}
	@Test
	public void addAndIterateMyClass() {
		ISinglyLinkedList<MyClass> list = new SinglyLinkedList<>();
		list.addFirst(new MyClass());
		list.addFirst(new MyClass());
		list.addFirst(new MyClass());
		assertEquals(3, list.getSize());
	}
	@Test
	public void addFirstAndLast() {
		ISinglyLinkedList<String> list1 = new SinglyLinkedList<>();
		list1.addFirst("1");
		ISinglyLinkedList<String> list2 = new SinglyLinkedList<>();
		list2.addLast("1");
		ISinglyLinkedList<String> list3 = new SinglyLinkedList<>();
		list3.addFirst("2");
		list3.addFirst("1");
		ISinglyLinkedList<String> list4 = new SinglyLinkedList<>();
		list4.addLast("1");
		list4.addLast("2");
		ISinglyLinkedList<String> list5 = new SinglyLinkedList<>();
		list5.addFirst("1");
		list5.addLast("2");
		ISinglyLinkedList<String> list6 = new SinglyLinkedList<>();
		list6.addLast("2");
		list6.addFirst("1");
		ISinglyLinkedList<String> list7 = new SinglyLinkedList<>();
		list7.addFirst("2");
		list7.addLast("3");
		list7.addFirst("1");
		list7.addLast("4");
		StringBuilder sb1 = new StringBuilder();
		for (String s : list1) {
			sb1.append(s);
		}
		assertEquals("1", sb1.toString());
		StringBuilder sb2 = new StringBuilder();
		for (String s : list2) {
			sb2.append(s);
		}
		assertEquals("1", sb2.toString());
		StringBuilder sb3 = new StringBuilder();
		for (String s : list3) {
			sb3.append(s);
		}
		assertEquals("12", sb3.toString());
		StringBuilder sb4 = new StringBuilder();
		for (String s : list4) {
			sb4.append(s);
		}
		assertEquals("12", sb4.toString());
		StringBuilder sb5 = new StringBuilder();
		for (String s : list5) {
			sb5.append(s);
		}
		assertEquals("12", sb5.toString());
		StringBuilder sb6 = new StringBuilder();
		for (String s : list6) {
			sb6.append(s);
		}
		assertEquals("12", sb6.toString());
		StringBuilder sb7 = new StringBuilder();
		for (String s : list7) {
			sb7.append(s);
		}
		assertEquals("1234", sb7.toString());
	}
}
