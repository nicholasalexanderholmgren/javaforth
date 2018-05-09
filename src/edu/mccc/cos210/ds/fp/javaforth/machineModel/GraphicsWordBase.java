package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import java.io.IOException;
import java.io.StreamTokenizer;
import edu.mccc.cos210.ds.fp.javaforth.controlwords.ForthControlWord;

public abstract class GraphicsWordBase extends ForthWordBase{
	public GraphicsWordBase(String name, String description, boolean immediate) {
		super(name, description, immediate);
	}
	abstract GraphicsWordBase build(StreamTokenizer tokenizer, ForthDictionary dictionary) throws IOException;
	protected void throwCompileModeOnly() {
		throw new RuntimeException(super.getName() + " can only be called during compile mode.");
	}
}
