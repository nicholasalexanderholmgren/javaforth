package edu.mccc.cos210.ds.fp.javaforth.controlwords;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;

public class IPrime extends I {
	public IPrime() {
		super("I'", " -- n Used within a colon-definition executed only from within a DO- LOOP to return the corresponding loop index.");
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary, ITerminalOutput terminalOutput) {
	}
}
