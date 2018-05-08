package edu.mccc.cos210.ds;

public class Vector<T> implements IVector<T> {
	private IArray<T> theArray;
	private int size = 0;
	private int capacity;
	public Vector() {
		this(0, 1);
	}
	public Vector(int size) {
		this(size, size);
	}
	public Vector(int size, int capacity) {
		this(size, capacity < size ? size : capacity, false);
	}
	public Vector(int size, int capacity, boolean debug) {
		this.size = size;
		this.capacity = capacity < size ? size : capacity;
		theArray = new Array<T>(capacity);
	}
	@Override
	public T get(int index) {
		checkBounds(index);
		return theArray.get(index);
	}
	@Override
	public void set(int index, T data) {
		checkBounds(index);
		theArray.set(index, data);
	}
	@Override
	public int getSize() {
		return size;
	}
	@Override
	public void pushBack(T data) {
		if (size == capacity) {
			capacity *= 2;
			IArray<T> newArray = new Array<T>(capacity);
			int index = 0;
			while (index < size) {
				newArray.set(index, theArray.get(index));
				theArray.set(index, null);
				index++;
			}
			theArray = newArray;
		}
		theArray.set(size, data);
		size++;
	}
	@Override
	public void popBack() {
		checkBounds(size - 1);
		size--;
	}
	@Override
	public T back() {
		checkBounds(size - 1);
		return theArray.get(size - 1);
	}
	@Override
	public int getCapacity() {
		return capacity;
	}
	@Override
	public void shrinkToFit() {
		IArray<T> newArray = new Array<>(size);
		int index = 0;
		while (index < size) {
			newArray.set(index, theArray.get(index));
			theArray.set(index, null);
			index++;
		}
		theArray = newArray;
		size = capacity = theArray.getSize();
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getSize() + " " + getCapacity() + " ");
		sb.append("[ ");
		for (int i = 0; i < getSize(); i++) {
			sb.append(get(i).toString() + " ");
		}
		sb.append("]");
		return sb.toString();
	}
	@Override
	public java.util.Iterator<T> iterator() {
		return new VectorIterator();
	}
	private class VectorIterator implements java.util.Iterator<T> {
		private int nextIndex = 0;
		@Override
		public boolean hasNext() {
			return nextIndex < getSize();
		}
		@Override
		public T next() {
			return get(nextIndex++);
		}
	}
	private void checkBounds(int index) {
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
	}
}
