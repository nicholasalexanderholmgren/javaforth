package edu.mccc.cos210.ds.fp.javaforth.machineModel;

public abstract class InterpreterWord extends AbstractWord {
	
	String name;

	public InterpreterWord(int numberOfArgument, boolean isPrimitive, boolean isImmediate) {
		super(numberOfArgument, true, false);
	}
	
	public InterpreterWord(String name, int numberOfArguments) {
		super (numberOfArguments , true, false);
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
	
	
}
