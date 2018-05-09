package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;

public abstract class ForthWordBase {
	private String name;
	private String description;
	private boolean compileModeOnly;
	public String getDescription() {
		return description;
	}
	public ForthWordBase(String name, String description, boolean compileModeOnly) {
		this(name, description);
		this.compileModeOnly = compileModeOnly;
	}
	public ForthWordBase(String name, String description) {
		super();
		if (name != null) {
			this.name = name.toUpperCase();
		}
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public abstract void execute(IStack<Object> stack, ForthDictionary dictionary, ITerminalOutput terminalOutput);
	public boolean isCompileModeOnly() {
		return compileModeOnly;
	}
}
