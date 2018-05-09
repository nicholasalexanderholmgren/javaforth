package edu.mccc.cos210.ds.fp.javaforth.words;

import java.util.NoSuchElementException;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;
/**
 * Nucleus word for doing bitwise OR to a pair of integers from the data stack.
 * @author Jing-Chao Feng, Nicholas Holmgren, Ryan Hammound
 *
 */
public class BitwiseOr extends ForthWordBase {
	public BitwiseOr() {
		super("OR", "n1 n2 -- n3 Leave the bitwise inclusive-or of two numbers.");
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dict, ITerminalOutput terminalOutput) {
		try {
			int n1 = ((Double) stack.pop()).intValue();
			int n2 = ((Double) stack.pop()).intValue();
			stack.push(((Integer) (n1 | n2)).doubleValue());
		} catch (NoSuchElementException e) {
			throw new RuntimeException("Stack underflow error on word OR.");
		}
	}
}
