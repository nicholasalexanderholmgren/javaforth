package edu.mccc.cos210.ds.fp.javaforth.words;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

public class Negate extends ForthWordBase {
	public Negate() {
		super("NEGATE", "n -- -n Leave the two's complement of a number,  i.e.,  the difference of zero less n.", false);
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dict) {
		try {
			double n1 = (double) stack.pop();
			stack.push(Double.valueOf((n1*-1.0)));
		}catch(Exception e) {
			throw new RuntimeException("Error on word NEGATE");
		}
	}
}