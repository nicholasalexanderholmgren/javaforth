package edu.mccc.cos210.ds.fp.javaforth.words;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

public class ZeroMore extends ForthWordBase {
	public ZeroMore() {
		super("0>", "n -- flag True if n is more than zero (positive)", false);
	}
	@Override
	public void execute(IStack<Object> stack) {
	}
}