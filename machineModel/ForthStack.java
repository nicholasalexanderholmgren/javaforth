package edu.mccc.cos210.ds.fp.javaforth.machineModel;

/**
 * Forthstack is a memory segment implementation that is further specialized to aid in managing the stack
 * region of the ForthMachine's memory.
 * The pointers here are not values on the stack, nor does it enforce stack behavior, but rather this class is a tool for keeping track of 
 * where the stack is in memory.
 */
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
	/**
	 * This methods reads from the provided memory to create a string that represents the stack.
	 * The first character of the string is the top element of the stack, the last element is the bottom.
	 * @return String This returns a string representing the stack.
	 */
	public String stackAsString() {
		return "";
	}
	/**
	 * This method calculates the height of the stack, which is likely useful for debugging and preventing errors that could happen when a word
	 * requires more arguments than there are elements on the stack.
	 * The stack's height means the number of bytes on the stack, not the number of integers. If a word needs multiple integers then make sure
	 * the stack has two times the number of needed integers in height. 
	 * @return int the number of bytes on the stack.
	 */
	public int stackHeight() {
		return 0;
	}
}
