package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;

public abstract class ForthWordBase {
	private String name;
	private String description;
	public String getDescription() {
		return description;
	}
	public ForthWordBase(String name, String description, boolean immediate) {
		super();
		if (!name.toUpperCase().equals(name)) {
			throw new UnsupportedOperationException("Forth word name " + name + " has to be all uppercase.");
		}
		this.name = name;
		this.description = description;
	}
	public ForthWordBase(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public abstract void execute(IStack<Object> stack, ForthDictionary dictionary);
	/* 
	 * override this if you need to output to terminal.
	 */
	public void execute(IStack<Object> stack, ForthDictionary dictionary, ITerminalOutput terminalOutput) {
	}
}
