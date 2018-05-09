package edu.mccc.cos210.ds.fp.javaforth.controlwords;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;

public class PutNumberOnStackWord extends ForthWordBase {
	private double number;
	public PutNumberOnStackWord(double number) {
		super(null, null);
		this.number = number;
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary, ITerminalOutput terminalOutput) {
		stack.push(number);
	}
}
