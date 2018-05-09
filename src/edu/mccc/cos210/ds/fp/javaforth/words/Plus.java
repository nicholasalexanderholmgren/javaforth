package edu.mccc.cos210.ds.fp.javaforth.words;

import java.util.NoSuchElementException;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

public class Plus extends ForthWordBase {
	public Plus() {
		super("+", "n1 n2 -- n3 Leave the arithmetic sum of n1 plus n2.");
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dict, edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput terminalOutput) {
		try {
			int n1 = ((Double) stack.pop()).intValue();
			int n2 = ((Double) stack.pop()).intValue();
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
