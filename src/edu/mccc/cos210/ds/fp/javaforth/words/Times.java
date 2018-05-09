package edu.mccc.cos210.ds.fp.javaforth.words;

import java.util.NoSuchElementException;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

public class Times extends ForthWordBase {
	public Times() {
		super("*", "n1 n2 -- n3 Leave the arithmetic product of n1 times n2.", false);
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dict) {
		try {
			int n1 = ((Double) stack.pop()).intValue();
			int n2 = ((Double) stack.pop()).intValue();
			stack.push(Double.valueOf((n1*n2)));
		}catch(NoSuchElementException e) {
			throw new RuntimeException("Stack underflow error on word *");
		}catch(ClassCastException e) {
			throw new RuntimeException("Typing error on *");
		}catch(Exception e) {
			throw new RuntimeException("Unknown error on *");
		}
	}
}
