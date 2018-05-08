package edu.mccc.cos210.ds.fp.javaforth.words;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

public class ZeroLess extends ForthWordBase {
	public ZeroLess() {
		super("0<", "n -- flag True if n is less than zero (negative)", false);
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dict) {
	}
}