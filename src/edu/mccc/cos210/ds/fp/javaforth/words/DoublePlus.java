package edu.mccc.cos210.ds.fp.javaforth.words;

import java.util.NoSuchElementException;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;
/**
 * Forth word for D+. Performs addition of doubles using the top two elements of the stack.
 * @author Jing-Chao Feng, Nicholas Holmgren, Ryan Hammound
 *
 */
public class DoublePlus extends ForthWordBase {
	public DoublePlus() {
		super("D+", "d1 d2 -- d3 Leave the arithmetic sum of d1 plus d2.");
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dict, edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput terminalOutput) {
		try {
			double n1 = ((Double) stack.pop());
			double n2 = ((Double) stack.pop());
			stack.push(Double.valueOf((n1 + n2)));
		} catch (NoSuchElementException e) {
			throw new RuntimeException("Stack underflow error on word +");
		} catch (ClassCastException e) {
			throw new RuntimeException("Typing error on +");
		} catch (Exception e) {
			throw new RuntimeException("Unknown error on +");
		}
	}

}
