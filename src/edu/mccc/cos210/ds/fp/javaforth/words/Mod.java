package edu.mccc.cos210.ds.fp.javaforth.words;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

public class Mod extends ForthWordBase {
	public Mod() {
		super("MOD", "n1 n2 -- n3 Divide n1 by n2,  leaving the remainder n3, with the same sign as n1", false);
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dict) {
		try {
			int n1 = (int) stack.pop();
			int n2 = (int) stack.pop();
			stack.push(Integer.valueOf((n1%n2)));
		}catch(Exception e) {
			throw new RuntimeException("Error on word *");
		}
	}
}
