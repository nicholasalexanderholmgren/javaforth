package edu.mccc.cos210.ds.fp.javaforth.words;

import java.util.NoSuchElementException;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;

/**
 * Forthword for ?dup. Pops the top flag from the stack, then if it was true, executes dup.
 * @see Dup.java
 * @author Jing-Chao Feng, Nicholas Holmgren, Ryan Hammound.
 *
 */
public class ConDup extends ForthWordBase {
	public ConDup() {
		super("?dup", "(flag n1 -- n1 n1) Conditionally duplicates the second from the top element of the stack");
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary, ITerminalOutput terminalOutput) {
		try {
			double flag;
			if (stack.peek() instanceof Double) {
				flag = (double) stack.pop();
			} else {
				throw new RuntimeException("Error, top of stack is not a number for conditional.");
			}
			if (flag != 0.0) {
				new Dup().execute(stack, dictionary, terminalOutput);
			}
		} catch (NoSuchElementException e) {
			throw new RuntimeException("Stack underflow error on word ?Dup");
		} catch (Exception e) {
			throw new RuntimeException("Error on word ?Dup");
		}
	}
}
