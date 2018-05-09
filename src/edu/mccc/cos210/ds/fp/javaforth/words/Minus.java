package edu.mccc.cos210.ds.fp.javaforth.words;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;
/**
 * Forth word for -. Performs integer subtraction on the top two elements of the stack.
 * @author Jing-Chao Feng, Nicholas Holmgren, Ryan Hammound
 *
 */
public class Minus extends ForthWordBase {
	public Minus() {
		super("-", "n1 n2 -- n3 Subtract n2 from n1 and leave the difference n3.");
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dict, edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput terminalOutput) {
		try {
			int n2 = ((Double) stack.pop()).intValue();
			int n1 = ((Double) stack.pop()).intValue();
			stack.push(Double.valueOf((n1 - n2)));
		} catch (Exception e) {
			throw new RuntimeException("Error on word -");
		}
	}
}
