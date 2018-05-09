package edu.mccc.cos210.ds.fp.javaforth.words;

import java.util.NoSuchElementException;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

public class Equals extends ForthWordBase {
	public Equals() {
		super("=", "n1 n2 -- flag True if n1 is equal n2.", false);
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dict) {
		try {
			Object n1 = stack.pop();
			Object n2 = stack.pop();
			if(n1.equals(n2)) {
				stack.push(1.0);
			}else {
				stack.push(0.0);
			}
		}catch(NoSuchElementException e) {
			throw new RuntimeException("Stack underflow error on word =");
		}catch(Exception e) {
			throw new RuntimeException("Error on word =");
		}
	}
}
