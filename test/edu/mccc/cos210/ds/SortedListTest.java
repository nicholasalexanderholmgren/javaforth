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

public class SortedListTest {
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
		report.append("\nTesting: ").append(SortedListTest.class.getSimpleName()).append("\n");
	}
	@AfterClass
	public static void tearDownClass() {
	    System.out.println(report.toString());
	}
	@Test
	public void dumbTest() {
		ISortedList<String> list1 = new SortedList<>();
		list1.add("Dog");
		list1.add("Cat");
		list1.add("Horse");
		list1.add("Elephant");
		list1.add("Goose");
		assertEquals(false, list1.isEmpty());
		StringBuilder sb1 = new StringBuilder();
		for (String s : list1) {
			sb1.append(s);
		}
		assertEquals("CatDogElephantGooseHorse", sb1.toString());

		ISortedList<String> list2 = new SortedList<>(new ReverseComparator());
		list2.add("Dog");
		list2.add("Cat");
		list2.add("Horse");
		list2.add("Elephant");
		list2.add("Goose");
		assertEquals(false, list2.isEmpty());
		StringBuilder sb2 = new StringBuilder();
		for (String s : list2) {
			sb2.append(s);
		}
		assertEquals("HorseGooseElephantDogCat", sb2.toString());

		ISortedList<Float> list3 = new SortedList<>((left, right) -> right.compareTo(left));
		list3.add(4.1f);
		list3.add(5.0f);
		list3.add(4.75f);
		list3.add(1.25f);
		assertEquals(false, list3.isEmpty());
		StringBuilder sb3 = new StringBuilder();
		for (Float f : list3) {
			sb3.append(f.toString());
		}
		assertEquals("5.04.754.11.25", sb3.toString());

		ISortedList<MyClass> list4 = new SortedList<>((left, right) -> left.hashCode() - right.hashCode());
		MyClass mc1 = new MyClass();
		MyClass mc2 = new MyClass();
		@SuppressWarnings("unused")
		String s1 = "";
		if (mc1.toString().compareTo(mc2.toString()) <= 0) {
			s1 = mc2.toString() + mc1.toString();
		} else {
			s1 = mc1.toString() + mc2.toString();
		}
		list4.add(mc1);
		list4.add(mc2);
		assertEquals(false, list4.isEmpty());
		StringBuilder sb4 = new StringBuilder();
		for (MyClass mc : list4) {
			sb4.append(mc.toString());
		}
		//assertEquals(s1, sb4.toString());

		ISortedList<MyClass> list5 = new SortedList<>();
		MySubClass msc1 = new MySubClass();
		MySubClass msc2 = new MySubClass();
		@SuppressWarnings("unused")
		String s2 = "";
		if (msc1.toString().compareTo(msc2.toString()) <= 0) {
			s2 = msc1.toString() + msc2.toString();
		} else {
			s2 = msc2.toString() + msc1.toString();
		}
		list5.add(msc1);
		list5.add(msc2);
		assertEquals(false, list5.isEmpty());
		StringBuilder sb5 = new StringBuilder();
		for (MyClass mc : list5) {
			sb5.append(mc.toString());
		}
		//assertEquals(s2, sb5.toString());
	}
	class ReverseComparator implements java.util.Comparator<String> {
		@Override
		public int compare(String left, String right) {
			return right.compareTo(left);
		}
	}
}
