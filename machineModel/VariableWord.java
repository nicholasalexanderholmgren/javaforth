package edu.mccc.cos210.ds.fp.javaforth.machineModel;

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

	@Override
	public byte[] evaluate(byte[] args) {
		return valueToByteArray();
	}
	public void setValue(int newValue) {
		value = newValue;
	}
	private byte[] valueToByteArray() {
		byte[] ans = new byte[2];
		ans [0] = (byte) ((value/256)%256);
		ans [1] = (byte) (value%256);
		return ans;
	}
}
