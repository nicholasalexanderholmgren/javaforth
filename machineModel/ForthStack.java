package edu.mccc.cos210.ds.fp.javaforth.machineModel;

public class ForthStack extends AbstractMemorySegment {
	private Object[] memory;
	public ForthStack(Object[] memory, int stackPointer) {
		super(stackPointer);
		this.memory = memory;
	}
	public String stackAsString() {
		return "";
	}
}
