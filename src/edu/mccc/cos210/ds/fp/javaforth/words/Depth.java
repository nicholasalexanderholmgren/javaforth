package edu.mccc.cos210.ds.fp.javaforth.words;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ObservableStack;
/**
 * Forth word that puts the height of the stack on top of the stack.
 * @author Jing-Chao Feng, Nicholas Holmgren, Ryan Hammound
 *
 */
public class Depth extends ForthWordBase {
	public Depth() {
		super("DEPTH", " -- n Leave the number of the quantity of 16-bit values contained in the data stack, before n added.");
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary, ITerminalOutput terminalOutput) {
		stack.push((double)((ObservableStack) stack).getHeight());
	}
}
