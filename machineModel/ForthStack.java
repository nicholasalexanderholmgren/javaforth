package edu.mccc.cos210.ds.fp.javaforth.machineModel;


//If we write the dictionary well enough, does
//that mean that the Forth stack can only be
//altered with forth code? or will primitive
//words be able to directly modify this?
public class ForthStack extends AbstractMemorySegment {

	private Object[] memory;
	public ForthStack(Object[] memory, int stackPointer) {
		super(stackPointer);
		this.memory = memory;
	}
	public String stackAsString() {
		return "";
	}
	public int stackHeight() {
		return 0;
	}
}
