package edu.mccc.cos210.ds.fp.javaforth.machineModel;

// One of the 3 data types that can be stored in the stack. It represent a name in the dictionary. Used as "addr" in Forth standard.
public class Symbol {
	private String name;
	public Symbol(String name) {
		super();
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
