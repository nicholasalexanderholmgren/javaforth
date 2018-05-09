package edu.mccc.cos210.ds.fp.javaforth.controlwords;

import java.io.IOException;
import java.io.StreamTokenizer;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

public class Colon extends ForthControlWordBase {
	public Colon() {
		super(":", "A defining word executed in the form: : <name>  ...  ;");
	}
	@Override
	public ForthWordBase build(StreamTokenizer tokenizer, ForthDictionary dictionary, boolean compiling) throws IOException {
		while (tokenizer.sval == null) {
			if (tokenizer.nextToken() == StreamTokenizer.TT_EOF) {
				throw new RuntimeException("Unexpected end of file while compiling a word.");
			}
		}
		CompiledWord word = new CompiledWord(tokenizer.sval, "");
		word.build(tokenizer, dictionary, compiling);
		return word;
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary, edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput terminalOutput) {
		super.throwCompileModeOnly();
	}
}
