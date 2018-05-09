package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.mccc.cos210.ds.ISinglyLinkedList;
import edu.mccc.cos210.ds.SinglyLinkedList;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.Symbol;
import edu.mccc.cos210.ds.fp.javaforth.util.IStackUpdatedEventListener;
import edu.mccc.cos210.ds.IStack;

public class ObservableStack implements IStack<Object>, Iterable<Object> {
	private ISinglyLinkedList<Object> theList = new SinglyLinkedList<>();
	private SinglyLinkedList<IStackUpdatedEventListener> listeners = new SinglyLinkedList<>();
	@Override
	public void push(Object data) {
		if (data instanceof Double || data instanceof Symbol) {
			theList.addFirst(data);
			listeners.forEach(l -> l.onStackUpdated(() -> theList.iterator()));
			return;
		}
		throw new RuntimeException("Only put double and Symbol into Forth Stack.");
	}
	@Override
	public Object pop() {
		try {
			Object value = theList.removeFirst();
			listeners.forEach(l -> l.onStackUpdated(() -> theList.iterator()));
			return value;
		} catch (NoSuchElementException ex) {
			throw new NoSuchElementException("Stack underflow.");
		}
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
	public int getHeight() {
		return this.theList.getSize();
	}
}
