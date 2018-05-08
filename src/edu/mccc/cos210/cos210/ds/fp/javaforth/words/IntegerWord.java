package edu.mccc.cos210.ds.fp.javaforth.words;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

public final class IntegerWord extends ForthWordBase {
	private int value;
	public IntegerWord(String name, int value) {
		super(name, name, false);
		this.value = value;
	}
	public void execute(IStack<Object> stack, ForthDictionary dict) {
		stack.push(value);
	}
}
