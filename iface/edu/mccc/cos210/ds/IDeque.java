package edu.mccc.cos210.ds;

public interface IDeque<T> {
	public void enqueueFirst(T data);
	public void enqueueLast(T data);
	public T peekFirst();
	public T peekLast();
	public T dequeueFirst();
	public T dequeueLast();
	public IDoublyLinkedList<T> asList();
	public int getSize();
	public boolean isEmpty();
}
