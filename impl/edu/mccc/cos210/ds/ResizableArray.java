package edu.mccc.cos210.ds;

public class ResizableArray<T> extends Array<T> implements IResizableArray<T> {
	public ResizableArray() {
		this(0);
	}
	public ResizableArray(int size) {
		super(size);
	}
	@Override
	public void resize(int size) {
		resize(size, null);
	}
	@Override
	public void resize(int size, T init) {
		Object[] theOldArray = theArray;
		theArray = new Object[size];
		for (int index = 0; index < theOldArray.length; index++) {
			theArray[index] = theOldArray[index];
		}
		for (int index = theOldArray.length; index < theArray.length; index++) {
			theArray[index] = init;
		}
	}
}
