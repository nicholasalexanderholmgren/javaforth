package edu.mccc.cos210.ds;

public class SortedList<T> extends OrderedList<T> implements ISortedList<T> {
	private java.util.Comparator<? super T> comparator;
	public SortedList() {
	}
	public SortedList(java.util.Comparator<? super T> comparator) {
		super();
		this.comparator = comparator;
	}
	@Override
	public void add(T data) {
		if (!(data instanceof java.lang.Comparable) && this.comparator == null) {
			throw new java.lang.IllegalArgumentException();
		}
		if (head == null) {
			addFirst(data);
			return;
		}
		DNode current = head;
		while (compare(current.getData(), data) < 1) {
			current = current.getNextNode();
			if (current == null) {
				addLast(data);
				return;
			}
		}
		DNode previous = current.getPrevNode();
		if (previous == null) {
			addFirst(data);
			return;
		}
		DNode newNode = new DNode(data);
		previous.setNextNode(newNode);
		newNode.setPrevNode(previous);
		newNode.setNextNode(current);
		current.setPrevNode(newNode);
	}
	private int compare(T first, T second) {
		if (this.comparator != null) {
			return comparator.compare(first, second);
		} else {
			@SuppressWarnings("unchecked")
			java.lang.Comparable<T> comparable = (java.lang.Comparable<T>) first;
			return comparable.compareTo(second);
		}
	}
	@Override
	public void setComparator(java.util.Comparator<? super T> comparator) {
		this.comparator = comparator;
	}
	@Override
	public java.util.Comparator<? super T> getComparator() {
		return this.comparator;
	}
}
