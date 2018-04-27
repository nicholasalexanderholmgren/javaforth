package edu.mccc.cos210.ds.fp.javaforth.machineModel;

public class ForthDictionary extends AbstractMemorySegment {
	private Object[] memory;
	public ForthDictionary(Object[] memory, int dictPointer) {
		super(dictPointer);
		this.memory = memory;
	}
	
}
