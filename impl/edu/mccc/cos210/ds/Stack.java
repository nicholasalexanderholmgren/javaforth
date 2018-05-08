package edu.mccc.cos210.ds;

public class Stack<T> implements IStack<T> {
	private ISinglyLinkedList<T> theList = new SinglyLinkedList<>();
	@Override
	public void push(T data) {
		theList.addFirst(data);
	}
	@Override
	public T pop() {
		return theList.removeFirst();
	}
	@Override
	public T peek() {
		return theList.getFirst();
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
