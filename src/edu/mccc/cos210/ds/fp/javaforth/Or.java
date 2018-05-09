package edu.mccc.cos210.ds.fp.javaforth.words;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

public class Or extends ForthWordBase {
	public Or() {
		super("OR", "n1 n2 -- n3 Leave the bitwise inclusive-or of two numbers." , false);
	}
//	@Override
//	public void execute(IStack<Object> stack) {
//	}
}
