package edu.mccc.cos210.ds.fp.javaforth.controlwords;

import java.io.IOException;
import java.io.StreamTokenizer;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;

public class Forget extends ForthControlWordBase {
	public Forget() {
		super("FORGET", "Execute in the form: FORGET  <name> Delete  from  the dictionary <name> (which is in  the  CURRENT vocabulary)  and  all  words added  to  the  dictionary  after <name>,  regardless  of  their vocabulary.   Failure  to  find <name> in CURRENT or FORTH is an error condition.");
	}
	private Forget(String name) {
		super(name, "");
	}
	@Override
	ForthWordBase build(StreamTokenizer tokenizer, ForthDictionary dictionary, boolean compiling) throws IOException {
		do {
			if (tokenizer.nextToken() == StreamTokenizer.TT_EOF) {
				throw new RuntimeException("Unexpected end of file while compiling a word.");
			}
		} while (tokenizer.sval == null);
		Forget word = new Forget(tokenizer.sval);
		return word;
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary, ITerminalOutput terminalOutput) {
		if (super.getName().equals("FORGET")) {
			super.throwUnexpectedWord();
		}
		if (!dictionary.removeWord(super.getName())) {
			throw new RuntimeException("Element " + super.getName() + " was not found.");
		}
	}
}
