package edu.mccc.cos210.ds.fp.javaforth.words;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;
/**
 * Forthword for D-. Subtracts the top double of the stack from the next double of the stack.
 * @author Jing-Chao Feng, Nicholas Holmgren, Ryan Hammound
 *
 */
public class DoubleMinus extends ForthWordBase {
	public DoubleMinus() {
		super("D-", "d1 d2 -- d3 Subtract d2 from d1 and leave the difference d3.");
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dict, edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput terminalOutput) {
		try {
			double d2 = ((Double) stack.pop());
			double d1 = ((Double) stack.pop());
			stack.push(Double.valueOf((d1 - d2)));
		} catch (Exception e) {
			throw new RuntimeException("Error on word D-");
		}
	}

}
