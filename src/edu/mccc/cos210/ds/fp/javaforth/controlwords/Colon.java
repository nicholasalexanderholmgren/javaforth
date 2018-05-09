package edu.mccc.cos210.ds.fp.javaforth.controlwords;

import java.io.IOException;
import java.io.StreamTokenizer;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

public class Colon extends ForthControlWordBase {
	private CompiledWord code;
	public Colon() {
		super(":", "A defining word executed in the form: : <name>  ...  ;");
	}
	@Override
	public ForthWordBase build(StreamTokenizer tokenizer, ForthDictionary dictionary, boolean compiling) throws IOException {
		do {
			if (tokenizer.nextToken() == StreamTokenizer.TT_EOF) {
				throw new RuntimeException("Unexpected end of file while compiling a word.");
			}
		} while (tokenizer.sval == null);
		Colon word = new Colon();
		word.code = new CompiledWord(tokenizer.sval, "");
		word.code.build(tokenizer, dictionary, true);
		return word;
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary, edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput terminalOutput) {
		if (this.code == null) {
			super.throwCompileModeOnly();
		} else {
			dictionary.addWord(this.code);
		}
	}
}
