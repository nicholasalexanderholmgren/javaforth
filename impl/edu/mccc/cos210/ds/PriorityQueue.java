package edu.mccc.cos210.ds;

public class PriorityQueue<T extends Comparable<T>> implements IPriorityQueue<T> {
	private Vector<T> theVector = new Vector<>(0, 1);
	private int size = 0;
	@Override
	public void insert(T element) {
		theVector.pushBack(element);
		size++;
		bubbleUp();
	}
	@Override
	public T peekTop() throws java.util.NoSuchElementException {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		return theVector.get(0);
	}
	@Override
	public T getTop() throws java.util.NoSuchElementException {
		T top = null;
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		top = theVector.get(0);
		theVector.set(0, null);
		size--;
		if (!isEmpty()) {
			theVector.set(0, theVector.get(size));
		}
		theVector.popBack();
		bubbleDown();
		return top;
	}
	@Override
	public int getSize() {
		return size;
	}
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	private void bubbleUp() {
		int currentIndex = size - 1;
		while (true) {
			if (size == 1) {
				break;
			}
			int parentIndex = getParentIndex(currentIndex);
			T parentValue = getValue(parentIndex);
			T currentValue = getValue(currentIndex);
			if (parentValue.compareTo(currentValue) <= 0) {
				break;
			}
			theVector.set(parentIndex, currentValue);
			theVector.set(currentIndex, parentValue);
			currentIndex = parentIndex;
			if (parentIndex == 0) {
				break;
			}
		}
	}
	private void bubbleDown() {
		int currentIndex = 0;
		while (true) {
			if (currentIndex * 2 + 1 >= size) {
				break;
			}
			int childIndex = getMinimumChildIndex(currentIndex);
			T currentValue = getValue(currentIndex);
			T childValue = getValue(childIndex);
			if (childValue.compareTo(currentValue) >= 0) {
				break;
			}
			theVector.set(currentIndex, childValue);
			theVector.set(childIndex, currentValue);
			currentIndex = childIndex;
		}
	}
	private boolean isRoot(int index) {
		return index == 0;
	}
	private boolean isLeaf(int index) {
		int leftIndex = getLeftChildIndex(index);
		int rightIndex = getRightChildIndex(index);
		return leftIndex >= size && rightIndex >= size;
	}
	private T getValue(int index) {
		return theVector.get(index);
	}
	private T getParentValue(int index) throws java.lang.IllegalArgumentException {
		if (isRoot(index)) {
			throw new IllegalArgumentException();
		}
		return theVector.get(getParentIndex(index));
	}
	private int getParentIndex(int index) {
		return (index - 1) / 2;
	}
	private int getLeftChildIndex(int index) {
		return index * 2 + 1;
	}
	private int getRightChildIndex(int index) {
		return index * 2 + 2;
	}
	private int getMinimumChildIndex(int index) {
		int leftIndex = getLeftChildIndex(index);
		T leftValue = getValue(leftIndex);
		int rightIndex = getRightChildIndex(index);
		int minChildIndex = leftIndex;
		if (rightIndex < theVector.getSize()) {
			T rightValue = getValue(rightIndex);
			if (rightValue != null && leftValue.compareTo(rightValue) > 0) {
				minChildIndex = rightIndex;
			}
		}
		return minChildIndex;
	}
	@Override
	public String toString() {
		String s = theVector.toString();
		return s.substring(s.indexOf("["));
	}
	boolean isHeap() {
		boolean b = true;
		for (int i = 0; b && i < size; i++) {
			int index = i;
			if (getValue(index) == null) {
				b = false;
				break;
			}
			if (isLeaf(index)) {
				while (!isRoot(index)) {
					T parentValue = getParentValue(index);
					T value = getValue(index);
					if (value.compareTo(parentValue) >= 0) {
						index = getParentIndex(index);
					} else {
						b = false;
						break;
					}
				}
			}
		}
		return b;
	}
}
