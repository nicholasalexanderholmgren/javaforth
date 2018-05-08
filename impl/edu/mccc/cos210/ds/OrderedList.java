package edu.mccc.cos210.ds;

public class OrderedList<T> extends DoublyLinkedList<T> implements IOrderedList<T> {
	@Override
	public void add(T data) {
		addLast(data);
	}
	@Override
	public boolean contains(T data) {
		boolean found = false;
		java.util.Iterator<T> it = iterator();
		while (it.hasNext()) {
			T item = it.next();
			if (item != null) {
				if (item.equals(data)) {
					found = true;
					break;
				}
			} else {
				if (data == null) {
					found = true;
					break;
				}
			}
		}
		return found;
	}
}
