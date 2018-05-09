package edu.mccc.cos210.ds.fp.javaforth.words;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;

public class False extends ForthWordBase {
	public False() {
		super("FALSE", "flag A zero value represents the false condition flag.");
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary, ITerminalOutput terminalOutput) {
		stack.push(0.0);
	}
}
