package edu.mccc.cos210.ds.fp.javaforth.words;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

public class LessThan extends ForthWordBase {
	public LessThan() {
		super("<", "n1 n2 -- flag True if n1 is less than n2.", false);
	}
	@Override
	public void execute(IStack<Object> stack) {
	}
}