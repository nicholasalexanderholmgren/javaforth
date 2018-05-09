package edu.mccc.cos210.ds.fp.javaforth.controlwords;

import java.io.IOException;
import java.io.StreamTokenizer;
import edu.mccc.cos210.ds.DoublyLinkedList;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;

public class Else extends ForthControlWordBase {
	private DoublyLinkedList<ForthWordBase> codes;
	public Else() {
		super("ELSE", " --  Used in a colon-definition in the form: IF ... ELSE ... THEN ELSE executes after the true part following IF.   ELSE  forces execution  to skip till just after THEN.   It has no effect on the stack.  (see IF)", true);
	}
	@Override
	ForthControlWordBase build(StreamTokenizer tokenizer, ForthDictionary dictionary, boolean compiling) throws IOException {
		Else word = new Else();
		word.codes = new DoublyLinkedList<>();
		ForthWordBase nextWord = ControlWordUtility.buildNext(tokenizer, dictionary, compiling);
		while (nextWord != null) {
			if (nextWord instanceof Else) {
				throw new RuntimeException("Unexpected word ELSE.");
			}
			if (nextWord instanceof Then) {
				return word;
			}
			word.codes.addLast(nextWord);
			nextWord = ControlWordUtility.buildNext(tokenizer, dictionary, compiling);
		}
		return word;
	}
	public void execute(IStack<Object> stack, ForthDictionary dictionary, ITerminalOutput terminalOutput) {
		if (this.codes == null) {
			throw new RuntimeException("This word is only valid in compile mode.");
		} else {
			for (ForthWordBase word : codes) {
				word.execute(stack, dictionary, terminalOutput);
			}
		}
	}
}
