package edu.mccc.cos210.ds.fp.javaforth.controlwords;

import java.io.IOException;
import java.io.StreamTokenizer;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;
import edu.mccc.cos210.ds.fp.javaforth.words.Minus;

public class DotQuote extends ForthControlWordBase {
	private StringBuilder content;
	public DotQuote() {
		super(".\"", "TODO");
	}
	@Override
	ForthWordBase build(StreamTokenizer tokenizer, ForthDictionary dictionary) throws IOException {
		DotQuote word = new DotQuote();
		int next = tokenizer.nextToken();
		while (next != StreamTokenizer.TT_EOF) {
			if (tokenizer.sval.endsWith("\"")) {
				return word;
			}
			if (tokenizer.sval != null) {
				content.append(tokenizer.sval);
			}
			next = tokenizer.nextToken();
		}
		throw new RuntimeException("dot-quote .\" not properly closed");
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary, ITerminalOutput terminalOutput) {
		if (content == null) {
			super.throwCompileModeOnly();
		}
		terminalOutput.writeLine(content.toString());
	}
}
