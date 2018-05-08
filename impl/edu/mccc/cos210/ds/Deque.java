package edu.mccc.cos210.ds;

public class Deque<T> implements IDeque<T> {
	private IDoublyLinkedList<T> theList = new DoublyLinkedList<>();
	@Override
	public void enqueueFirst(T data) {
		theList.addFirst(data);
	}
	@Override
	public void enqueueLast(T data) {
		theList.addLast(data);
	}
	@Override
	public T peekFirst() {
		return theList.getFirst();
	}
	@Override
	public T peekLast() {
		return theList.getLast();
	}
	@Override
	public T dequeueFirst() {
		return theList.removeFirst();
	}
	@Override
	public T dequeueLast() {
		return theList.removeLast();
	}
	@Override
	public IDoublyLinkedList<T> asList() {
		return theList;
	}
	@Override
	public int getSize() {
		return theList.getSize();
	}
	@Override
	public boolean isEmpty() {
		return theList.isEmpty();
	}
	@Override
	public String toString() {
		return theList.toString();
	}
}
