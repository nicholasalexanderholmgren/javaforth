package edu.mccc.cos210.ds.fp.javaforth.controlwords;

import java.io.IOException;
import java.io.StreamTokenizer;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;

public class Comment extends ForthControlWordBase {
	public Comment() {
		super("(", " --  Used in the form: ( ccc) Accept  and ignore comment characters from the  input  stream, until  the  next  right parenthesis.   As  a  word,  the  left parenthesis  must be followed by one blank.   It may freely be used while executing or compiling.   An error condition exists if the input stream is exhausted before the right parenthesis.", false);
		// TODO Auto-generated constructor stub
	}
	@Override
	ForthWordBase build(StreamTokenizer tokenizer, ForthDictionary dictionary, boolean compiling) throws IOException {
		Comment word = new Comment();
		int next = tokenizer.nextToken();
		while (next != StreamTokenizer.TT_EOF) {
			if (tokenizer.sval != null) {
				if (tokenizer.sval.endsWith(")")) {
					return word;
				}
			}
			next = tokenizer.nextToken();
		}
		throw new RuntimeException("dot-quote .\" not properly closed");
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary, ITerminalOutput terminalOutput) {
	}
}
