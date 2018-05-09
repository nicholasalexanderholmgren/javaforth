package edu.mccc.cos210.ds.fp.javaforth.words;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

public class Store extends ForthWordBase {

	public Store() {
		super("!", "n addr -- Store n at addr.", false);
	}

	@Override
	public void execute(IStack<Object> stack, ForthDictionary dict) {
	}
}
