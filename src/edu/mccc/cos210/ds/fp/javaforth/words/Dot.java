package edu.mccc.cos210.ds.fp.javaforth.words;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;

public class Dot extends ForthWordBase {
	public Dot() {
		super(".", "n -- Display  n converted according to BASE in a free field  format with one trailing blank.  Display only a negative sign.");
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary, ITerminalOutput terminalOutput) {
		terminalOutput.writeLine(stack.pop().toString());
	}
}
