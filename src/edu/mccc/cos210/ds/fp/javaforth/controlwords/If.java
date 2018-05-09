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
		super("IF", "flag -- Used in a colon-definition in the form: flag  IF ... ELSE ... THEN   or flag  IF ... THEN If  flag is true,  the words following IF are executed and the words following ELSE are skipped.   The ELSE part is optional. If flag is false, words between IF and ELSE, or between IF and THEN  (when  no  ELSE is  used),  are  skipped.   IF-ELSE-THEN conditionals may be nested.", true);
	}
	public If build(StreamTokenizer tokenizer, ForthDictionary dictionary, boolean compiling) throws IOException {
		If word = new If();
		ForthWordBase nextWord = ControlWordUtility.buildNext(tokenizer, dictionary, compiling);
		while (nextWord != null) {
			if (nextWord instanceof Else) {
				if(word._else != null) {
					throw new RuntimeException("Unexpected word ELSE.");
				}
				word._else = (Else) nextWord;
				return word;
			}
			if(nextWord instanceof Then) {
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
