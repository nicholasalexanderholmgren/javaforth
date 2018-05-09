package edu.mccc.cos210.ds.fp.javaforth.words;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;
/**
 * Forth word for EMIT. Takes the top value of the stack, translates it into the ASCII character space, and then transmits the
 * equivilant ASCII character to the terminal.
 * @author Jing-Chao Feng, Nicholas Holmgren, Ryan Hammound
 *
 */
public class Emit extends ForthWordBase {
	public Emit() {
		super("emit", "(c1 -- ) Pops the top value of the stack and displays it to the terminal as an "+
	"ASCII character");
	}

	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary, ITerminalOutput terminalOutput) {
		try {
			int c1 = ((Double) stack.pop()).intValue();
			c1 %= 256;
			char c = (char)c1;
			terminalOutput.writeLine(""+c+ "\n");
		}catch(Exception e) {
			throw new RuntimeException("Error on word EMIT");
		}
	}
}
