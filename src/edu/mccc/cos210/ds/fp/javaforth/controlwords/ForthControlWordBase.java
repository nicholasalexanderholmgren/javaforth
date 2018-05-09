package edu.mccc.cos210.ds.fp.javaforth.controlwords;

import java.io.IOException;
import java.io.StreamTokenizer;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

public abstract class ForthControlWordBase extends ForthWordBase {
	public ForthControlWordBase(String name, String description) {
		super(name, description);
	}
	public ForthControlWordBase(String name, String description, boolean compileModeOnly) {
		super(name, description, compileModeOnly);
	}
	abstract ForthWordBase build(StreamTokenizer tokenizer, ForthDictionary dictionary, boolean compiling) throws IOException;
	protected void throwCompileModeOnly() {
		throw new RuntimeException(super.getName() + " can only be called during compile mode.");
	}
}