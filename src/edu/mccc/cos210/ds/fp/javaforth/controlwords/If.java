package edu.mccc.cos210.ds.fp.javaforth.controlwords;

import java.io.IOException;
import java.io.StreamTokenizer;
import javax.management.RuntimeErrorException;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.DoublyLinkedList;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

public class If extends ForthControlWord {
	private DoublyLinkedList<Object> codeBlockForTrue = new DoublyLinkedList<>();
	private DoublyLinkedList<Object> codeBlockForFalse = new DoublyLinkedList<>();
	// Contains only ForthWordBase, double and string.
	public If() {
		super("IF", "TODO", false);
	}
	private If(StreamTokenizer tokenizer, ForthDictionary dictionary) throws IOException {
		super("", "", false);
		If word = new If();
		int next = tokenizer.nextToken();
		boolean buildingFalse = false;
		while (next != StreamTokenizer.TT_EOF) {
			if (next == '\"') {
				// A string in "".
				if (buildingFalse) {
					this.codeBlockForFalse.addLast(tokenizer.sval);
				} else {
					this.codeBlockForTrue.addLast(tokenizer.sval);
				}
			} else if (next == StreamTokenizer.TT_NUMBER) {
				if (buildingFalse) {
					this.codeBlockForFalse.addLast(tokenizer.nval);
				} else {
					this.codeBlockForTrue.addLast(tokenizer.nval);
				}
			} else {
				// Has to be a word.
				if (tokenizer.sval.toUpperCase().equals("ELSE")) {
					if (buildingFalse) {
						throw new RuntimeException();
					}
					buildingFalse = true;
				}
				if (tokenizer.sval.toUpperCase().equals("THEN")) {
					return;
				}
				ForthWordBase nextWord = dictionary.getWord(tokenizer.sval);
				if (buildingFalse) {
//					this.codeBlockForFalse.addLast(word);
				} else {
//					this.codeBlockForTrue.addLast(word);
				}
			}
		}
	}
	public If build(StreamTokenizer tokenizer, ForthDictionary dictionary) throws IOException {
		return new If(tokenizer, dictionary);
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary) {
		if(this.codeBlockForFalse == null || this.codeBlockForTrue == null) {
			throw new RuntimeException("This word is only valid in compile mode.");			
		}
		else {
			// TODO
		}
	}
}
