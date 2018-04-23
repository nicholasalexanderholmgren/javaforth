package edu.mccc.cos210.ds.fp.javaforth.machineModel;

public abstract class AbstractWord {
	private boolean immediate;
	private boolean isPrimitive;
	private int numberOfArguments;
	public AbstractWord(int numberOfArgument, boolean isPrimitive, boolean isImmediate) {
		numberOfArguments = numberOfArgument;
		this.isPrimitive = isPrimitive;
		immediate = isImmediate;
	}
	public boolean isImmediate() {
		return immediate;
	}
	public int getNumberOfArguments() {
		return numberOfArguments;
	}
	public boolean isPrimitive() {
		return isPrimitive;
	}
	public abstract String toString();
	public abstract byte[] evaluate(byte[] args);
}
