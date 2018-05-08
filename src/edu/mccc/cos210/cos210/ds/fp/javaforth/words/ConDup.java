package edu.mccc.cos210.ds.fp.javaforth.words;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;
/**
 * Javacode for the forth word ?dup. pops the top flag from the stack, then if it was true, executes dup.
 * @author Nicholas Holmgren
 *
 */
public class ConDup extends ForthWordBase {
	public ConDup() {
		super("?dup", "(flag n1 -- n1 n1) Conditionally duplicates the second from the top element of the stack",
				false);
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dict) {
		try {
			double flag;
			if(stack.peek() instanceof Double) {
				flag = (double) stack.pop();
			}else {
				throw new RuntimeException("Error, top of stack is not a number for conditional.");
			}
			if(flag != 0.0) {
				new Dup().execute(stack, dict);
			}
		}catch(Exception e) {
			throw new RuntimeException("Error on word ?Dup");
		}
		
	}
}
