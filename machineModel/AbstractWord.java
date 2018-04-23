package edu.mccc.cos210.ds.fp.javaforth.machineModel;

public abstract class AbstractWord {
	private boolean immediate;
	private boolean isPrimitive;
	private int numberOfArguments;
	public abstract String toString();
	public abstract byte[] evaluate(byte[] args);
}
