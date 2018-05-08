package edu.mccc.cos210.ds;

public interface IPriorityQueue<T extends Comparable<T>> {
	public void insert(T element);
	public T peekTop() throws java.util.NoSuchElementException;
	public T getTop() throws java.util.NoSuchElementException;
	public int getSize();
	public boolean isEmpty();
}
