package edu.mccc.cos210.ds.fp.javaforth.machineModel;
/**
 * This class is the abstract form of the forth words that are stored in the Forth Machine's emulated memory.
 * Individual words will be defined either at start up or during runtime, thus there are currently no non-abstract word objects.
 */
public abstract class AbstractWord {
	private boolean immediate;
	private boolean isPrimitive;
	private int numberOfArguments;
	/**Constructor provided to make word definition take less time
	 * 
	 * @param int numberOfArguments is the number bytes the word takes as its argument.
	 * @param boolean isPrimitive is true when the word is in the nucleus word set. 
	 * @param isImmediate is true when the word is flagged as immediate.
	 */
	public AbstractWord(int numberOfArgument, boolean isPrimitive, boolean isImmediate) {
		numberOfArguments = numberOfArgument;
		this.isPrimitive = isPrimitive;
		immediate = isImmediate;
	}
	/**
	 * Getter method for the immediate flag.
	 * @return boolean 
	 */
	public boolean isImmediate() {
		return immediate;
	}
	/**
	 * Getter method for the number of arguments field.
	 * @return int 
	 */
	public int getNumberOfArguments() {
		return numberOfArguments;
	}/**
	 * Getter method for the primitive flag.
	 * @return boolean 
	 */
	public boolean isPrimitive() {
		return isPrimitive;
	}
	public abstract String toString();
	public abstract int evaluate(int[] args);

}
