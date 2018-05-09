package edu.mccc.cos210.ds.fp.javaforth.words;

import java.util.NoSuchElementException;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;
/**
 * Forth word for =. Pops the top two items of the stack as integers, and tests for equality.
 * @author Jing-Chao Feng, Nicholas Holmgren, Ryan Hammound
 *
 */
public class Equals extends ForthWordBase {
	public Equals() {
		super("=", "n1 n2 -- flag True if n1 is equal n2.");
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dict, edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput terminalOutput) {
		try {
			int n1 = ((Double) stack.pop()).intValue();
			int n2 = ((Double) stack.pop()).intValue();
			if (n1 == n2) {
				stack.push(1.0);
			} else {
				stack.push(0.0);
			}
		} catch (NoSuchElementException e) {
			throw new RuntimeException("Stack underflow error on word =");
		} catch (Exception e) {
			throw new RuntimeException("Error on word =");
		}
	}
}
