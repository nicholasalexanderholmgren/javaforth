package edu.mccc.cos210.ds.fp.javaforth.words;

import java.util.NoSuchElementException;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

public class Dup extends ForthWordBase {
	public Dup() {
		super("DUP", "(n1 -- n1 n1) Copies the top number of the stack and");
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dict, edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput terminalOutput) {
		try {
			Object temp = stack.pop();
			stack.push(temp);
			stack.push(temp);
		} catch (NoSuchElementException e) {
			throw new RuntimeException("Stack underflow error on word dup.");
		} catch (Exception e) {
			throw (new RuntimeException("Error on word dup."));
		}
	}
}
