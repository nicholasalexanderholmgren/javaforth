package edu.mccc.cos210.ds.fp.javaforth.controlwords;

import java.io.IOException;
import java.io.StreamTokenizer;
import edu.mccc.cos210.ds.DoublyLinkedList;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;

public class Else extends ForthControlWord {
	private DoublyLinkedList<ForthWordBase> codes;
	public Else() {
		super("ELSE", "TODO");
	}
	@Override
	ForthControlWord build(StreamTokenizer tokenizer, ForthDictionary dictionary) throws IOException {
		Else word = new Else();
		word.codes = new DoublyLinkedList<>();
		ForthWordBase nextWord = ControlWordUtility.buildNext(tokenizer, dictionary);
		while (nextWord != null) {
			if (nextWord instanceof Else) {
				throw new RuntimeException("Unexpected word ELSE.");
			}
			if (nextWord instanceof Then) {
				return word;
			}
			word.codes.addLast(nextWord);
			nextWord = ControlWordUtility.buildNext(tokenizer, dictionary);
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
