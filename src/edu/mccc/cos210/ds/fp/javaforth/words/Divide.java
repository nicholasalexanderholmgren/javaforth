package edu.mccc.cos210.ds.fp.javaforth.words;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

public class Divide extends ForthWordBase {
	public Divide() {
		super("/", "n1 n2 -- n3 Divide n1 by n2 and leave the quotient n3. n3  is  rounded toward zero.", false);
	}
	@Override
	public void execute(IStack<Object> stack) {
	}
}