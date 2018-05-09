package edu.mccc.cos210.ds.fp.javaforth.controlwords;

import java.io.IOException;
import java.io.StreamTokenizer;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;

public abstract class ForthControlWordBase extends ForthWordBase {
	public ForthControlWordBase(String name, String description) {
		super(name, description);
	}
	abstract ForthWordBase build(StreamTokenizer tokenizer, ForthDictionary dictionary) throws IOException;
	protected void throwCompileModeOnly() {
		throw new RuntimeException(super.getName() + " can only be called during compile mode.");
	}
}
