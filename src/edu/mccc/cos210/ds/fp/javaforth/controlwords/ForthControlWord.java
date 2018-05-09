package edu.mccc.cos210.ds.fp.javaforth.controlwords;

import java.io.IOException;
import java.io.StreamTokenizer;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

public abstract class ForthControlWord extends ForthWordBase {
	public ForthControlWord(String name, String description, boolean immediate) {
		super(name, description, immediate);
	}
	abstract ForthControlWord build(StreamTokenizer tokenizer, ForthDictionary dictionary) throws IOException;
	protected void throwCompileModeOnly() {
		throw new RuntimeException(super.getName() + " can only be called during compile mode.");
	}
}
