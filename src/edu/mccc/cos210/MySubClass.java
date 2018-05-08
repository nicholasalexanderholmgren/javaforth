package edu.mccc.cos210;

public class MySubClass extends MyClass implements java.lang.Comparable<MySubClass> {
	@Override
	public int compareTo(MySubClass that) {
		return this.hashCode() - that.hashCode();
	}
}
