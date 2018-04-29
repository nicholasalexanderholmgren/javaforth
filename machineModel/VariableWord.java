package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import java.util.List;

public class VariableWord extends AbstractWord {
	int value;
	public VariableWord(int value) {
		super(0, false, false);
		this.value = value;
	}

	@Override
	public String toString() {
		return "This is a variable, its current value is "+value;
	}

	public int evaluate(int arg) {
		return groovy();
	}
	public void setValue(int newValue) {
		value = newValue;
	}
	private int groovy() {
		return value;
	}

	@Override
	public List<Integer> evaluate(List<Integer> args) {
		// TODO Auto-generated method stub
		return null;
	}
}
