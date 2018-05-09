package edu.mccc.cos210.ds.fp.javaforth.controlwords;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;

public class SemiColon extends ForthWordBase {
	public SemiColon() {
		super(";", "Terminate  a  colon  definition  and  stop  compilation.    If compiling  from mass storage and the input stream is exhausted before encountering ; an error condition exists.");
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary, ITerminalOutput terminalOutput) {
		// TODO Auto-generated method stub
	}
}
