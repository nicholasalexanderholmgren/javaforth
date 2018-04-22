package edu.mccc.cos210.ds.fp.javaforth.machineModel;

public abstract class AbstractMemorySegment implements IMemorySegment {
	int INITIAL_POINTER;
	int currentPointer;
	public AbstractMemorySegment(int initialPointer) {
		INITIAL_POINTER = initialPointer;
		currentPointer = initialPointer;
	}
	public int getCurrentPointer() {
		return currentPointer;
	}
	public int setCurrentPointer(int newPointer) {
		int temp = currentPointer;
		currentPointer = newPointer;
		return temp;
	}
}
