package edu.mccc.cos210.ds;

public interface ISortedList<T> extends IOrderedList<T> {
	public void setComparator(java.util.Comparator<? super T> comparator);
	public java.util.Comparator<? super T> getComparator();
}
