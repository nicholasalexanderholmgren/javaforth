package edu.mccc.cos210.ds.fp.javaforth.controlwords;

import java.io.IOException;
import java.io.StreamTokenizer;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

public class Colon extends ForthControlWord {
	public Colon() {
		super(":", "A defining word executed in the form: : <name>  ...  ;");
	}
	@Override
	public ForthWordBase build(StreamTokenizer tokenizer, ForthDictionary dictionary) throws IOException {
		while (tokenizer.sval == null) {
			tokenizer.nextToken();
		}
		CompiledWord word = new CompiledWord(tokenizer.sval, "");
		word.build(tokenizer, dictionary);
		return word;
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary) {
		super.throwCompileModeOnly();
	}
}
