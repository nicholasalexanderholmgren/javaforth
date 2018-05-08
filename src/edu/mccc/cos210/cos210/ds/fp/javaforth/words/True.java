package edu.mccc.cos210.ds.fp.javaforth.words;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

public class True extends ForthWordBase {
	public True() {
		super("TRUE", "flag A non-zero value represents the true condition flag.", false);
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dict) {
	}
}