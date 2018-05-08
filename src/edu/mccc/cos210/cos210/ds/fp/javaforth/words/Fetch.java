package edu.mccc.cos210.ds.fp.javaforth.words;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

public class Fetch extends ForthWordBase {
	public Fetch() {
		super("@", "addr -- n Leave on the stack the number contained at addr.", false);		
	}
	@Override
	public void execute(IStack<Object> stack) {
	}
}
