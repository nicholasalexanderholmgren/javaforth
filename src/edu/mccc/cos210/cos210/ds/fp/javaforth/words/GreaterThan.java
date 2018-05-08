package edu.mccc.cos210.ds.fp.javaforth.words;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

public class GreaterThan extends ForthWordBase {
	public GreaterThan() {
		super(">", "n1 n2 -- flag True if n1 is greater than n2.", false);
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dict) {
	}
}