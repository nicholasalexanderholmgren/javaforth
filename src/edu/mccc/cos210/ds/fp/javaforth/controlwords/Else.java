package edu.mccc.cos210.ds.fp.javaforth.controlwords;

import java.io.IOException;
import java.io.StreamTokenizer;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;

public class Else extends ForthControlWord {
	public Else() {
		super("ELSE", "TODO");
	}
	@Override
	ForthControlWord build(StreamTokenizer tokenizer, ForthDictionary dictionary) throws IOException {
		return null;
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary) {
	}
}
