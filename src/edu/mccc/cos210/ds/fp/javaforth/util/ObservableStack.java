package edu.mccc.cos210.ds.fp.javaforth.util;

import java.util.Iterator;
import edu.mccc.cos210.ds.ISinglyLinkedList;
import edu.mccc.cos210.ds.SinglyLinkedList;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.Symbol;
import edu.mccc.cos210.ds.IStack;

public class ObservableStack implements IStack<Object>, Iterable<Object> {
	private ISinglyLinkedList<Object> theList = new SinglyLinkedList<>();
	private SinglyLinkedList<IStackUpdatedEventListener> listeners = new SinglyLinkedList<>();
	@Override
	public void push(Object data) {
		if(data instanceof Boolean || data instanceof Double || data instanceof Symbol) {
			listeners.forEach(l -> l.onStackUpdated(() -> theList.iterator()));
			theList.addFirst(data);	
		}
		throw new RuntimeException("Only put boolean double and Symbol into Forth Stack.");
	}
	@Override
	public Object pop() {
		listeners.forEach(l -> l.onStackUpdated(() -> theList.iterator()));
		return theList.removeFirst();
	}
	public void addStackUpdatedEventListener(IStackUpdatedEventListener listener) {
		this.listeners.addFirst(listener);
	}
	@Override
	public Object peek() {
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
	@Override
	public Iterator<Object> iterator() {
		return this.theList.iterator();
	}
}
