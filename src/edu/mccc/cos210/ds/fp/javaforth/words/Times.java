package edu.mccc.cos210.ds.fp.javaforth.words;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

public class Times extends ForthWordBase {
	public Times() {
		super("*", "n1 n2 -- n3 Leave the arithmetic product of n1 times n2.", false);
	}
	@Override
	public void execute(IStack<Object> stack) {
	}
}
