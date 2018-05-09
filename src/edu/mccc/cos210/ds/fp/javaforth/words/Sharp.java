package edu.mccc.cos210.ds.fp.javaforth.words;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

public class Sharp extends ForthWordBase {
	public Sharp() {
		super("#", "ud1 -- ud2 Generate  from  an unsigned double number d1,  the next  ASCII character which is placed in an output string.   Result d2  is the  quotient after division by BASE is maintained for further	processing.  Used between <# and #>", false);
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dict, edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput terminalOutput) {
	}
}
