package edu.mccc.cos210.ds.fp.javaforth.controlwords;

import java.io.IOException;
import java.io.StreamTokenizer;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.DoublyLinkedList;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;

public class If extends ForthControlWordBase {
	private DoublyLinkedList<ForthWordBase> codes = new DoublyLinkedList<>();
	private Else _else;
	// Contains only ForthWordBase, double and string.
	public If() {
		super("IF", "TODO");
	}
	public If build(StreamTokenizer tokenizer, ForthDictionary dictionary) throws IOException {
		If word = new If();
		ForthWordBase nextWord = ControlWordUtility.buildNext(tokenizer, dictionary);
		while (nextWord != null) {
			if (nextWord instanceof Else) {
				this._else = (Else) ((Else) nextWord).build(tokenizer, dictionary);
				return word;
			}
			if (nextWord instanceof Then) {
				return word;
			}
			this.codes.addLast(nextWord);
			nextWord = ControlWordUtility.buildNext(tokenizer, dictionary);
		}
		return word;
	}
	public void execute(IStack<Object> stack, ForthDictionary dictionary, ITerminalOutput terminalOutput) {
		if (this.codes == null) {
			throw new RuntimeException("This word is only valid in compile mode.");
		} else {
			double flag = (double) stack.pop();
			if (flag == 0) {
				this._else.execute(stack, dictionary, terminalOutput);
			} else {
				for (ForthWordBase word : codes) {
					word.execute(stack, dictionary, terminalOutput);
				}
			}
		}
	}
}
