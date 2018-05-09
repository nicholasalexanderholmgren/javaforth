package edu.mccc.cos210.ds.fp.javaforth.words;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;
/**
 * Forth word for ABS. Pops the top value of the stack and pushes its absolute value as an integer
 * @author Jing-Chao Feng, Nicholas Holmgren, Ryan Hammound
 *
 */
public class Abs extends ForthWordBase {
	public Abs() {
		super("ABS", "(n -- |n|) Pops the top integer of the stack and pushes its absolute value.");
	}

	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary, ITerminalOutput terminalOutput) {
		int n1 = ((Double)stack.pop()).intValue();
		stack.push(Math.abs(n1)*1.0);

	}

}
