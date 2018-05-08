package edu.mccc.cos210.ds;

public class Array<T> implements IArray<T> {
	protected Object[] theArray = null;
	public Array(int size) {
		if (size < 0) {
			throw new java.lang.NegativeArraySizeException();
		}
		theArray = new Object[size];
	}
	@SuppressWarnings("unchecked")
	@Override
	public T get(int index) {
		if (index < 0 || index > getSize() - 1) {
			throw new java.lang.ArrayIndexOutOfBoundsException();
		}
		return (T) theArray[index];
	}
	@Override
	public void set(int index, T data) {
		if (index < 0 || index > getSize() - 1) {
			throw new java.lang.ArrayIndexOutOfBoundsException();
		}
		theArray[index] = data;
	}
	@Override
	public int getSize() {
		return theArray.length;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		for (int index = 0; index < theArray.length; index++) {
			sb.append(theArray[index] + " ");
		}
		sb.append("]");
		return sb.toString();
	}
	@Override
	public java.util.Iterator<T> iterator() {
		return new ArrayListIterator();
	}
	private class ArrayListIterator implements java.util.Iterator<T> {
		private int nextIndex = 0;
		@Override
		public boolean hasNext() {
			if (nextIndex > theArray.length - 1) {
				return false;
			}
			return true;
		}
		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			T data = (T) theArray[nextIndex];
			nextIndex++;
			return data;
		}
	}
}
