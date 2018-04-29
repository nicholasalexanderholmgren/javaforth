package edu.mccc.cos210.ds.fp.javaforth.machineModel;

public class ForthDictionary extends AbstractMemorySegment {
	private Object[] memory;
	private int size;
	
	public ForthDictionary(Object[] memory, int dictPointer) {
		super(dictPointer);
		this.memory = memory;
	}
	
	public int getCurrentPointer() {
		return currentPointer;
	}
	
	public int setCurrentPointer(int newPointer) {
		currentPointer = newPointer;
		return currentPointer;
	}
	
}
