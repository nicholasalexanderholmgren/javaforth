package edu.mccc.cos210.ds.fp.javaforth.controlwords;

import java.io.IOException;
import java.io.StreamTokenizer;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;

public class ThenWord extends ForthControlWord {
	public ThenWord(String name, String description, boolean immediate) {
		super(name, description, immediate);
		
	}

	@Override
	ForthControlWord build(StreamTokenizer tokenizer, ForthDictionary dictionary) throws IOException {
		throw new RuntimeException();
	}

	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary) {
	}
}
