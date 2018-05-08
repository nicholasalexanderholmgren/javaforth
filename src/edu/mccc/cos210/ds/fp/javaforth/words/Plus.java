package edu.mccc.cos210.ds.fp.javaforth.words;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

public class Plus extends ForthWordBase {
	public Plus() {
		super("+", "n1 n2 -- n3 Leave the arithmetic sum of n1 plus n2.", false);
	}
	@Override
	public void execute(IStack<Object> stack) {
	}
}
