package edu.mccc.cos210.ds.fp.javaforth.words;

import java.util.NoSuchElementException;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

public class Plus extends ForthWordBase {
	public Plus() {
		super("+", "n1 n2 -- n3 Leave the arithmetic sum of n1 plus n2.", false);
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dict) {
		try {
			int n1 = (int) stack.pop();
			int n2 = (int) stack.pop();
			stack.push(Integer.valueOf((n1+n2)));
		}catch(NoSuchElementException e) {
			throw new RuntimeException("Stack underflow error on word +.");
		}catch(Exception e) {
			throw new RuntimeException("Error on word +.");
		}
	}
}
