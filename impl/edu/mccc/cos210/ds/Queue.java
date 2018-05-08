package edu.mccc.cos210.ds;

public class Queue<T> implements IQueue<T> {
	private IDoublyLinkedList<T> theList = new DoublyLinkedList<>();
	@Override
	public void enqueue(T data) {
		theList.addFirst(data);
	}
	@Override
	public T dequeue() {
		return theList.removeLast();
	}
	@Override
	public T peek() {
		return theList.getLast();
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
