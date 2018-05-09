package edu.mccc.cos210.ds.fp.javaforth.words;

import java.util.NoSuchElementException;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

public class Divide extends ForthWordBase {
	public Divide() {
		super("/", "n1 n2 -- n3 Divide n1 by n2 and leave the quotient n3. n3  is  rounded toward zero.", false);
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dict) {
		try {
			int n2 = ((Double) stack.pop()).intValue();
			int n1 = ((Double) stack.pop()).intValue();
			stack.push(Double.valueOf((n1/n2)));
		}catch(NoSuchElementException e) {
			throw new RuntimeException("Stack underflow error on word /");
		}catch(ClassCastException e) {
			throw new RuntimeException("Typing error on /");
			
		}catch(ArithmeticException e) {
			throw new RuntimeException("Division by zero error");
		}catch(Exception e) {
			throw new RuntimeException("Unknown error on /");
		}
	}
}