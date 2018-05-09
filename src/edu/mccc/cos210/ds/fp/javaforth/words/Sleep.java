package edu.mccc.cos210.ds.fp.javaforth.words;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;

public class Sleep extends ForthWordBase {
	public Sleep() {
		super("SLEEP", " n1 -- Sleep for n1 milliseconds.");
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary, ITerminalOutput terminalOutput) {
		double n1 = (double) stack.pop();
		try {
			Thread.sleep((long)n1);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
