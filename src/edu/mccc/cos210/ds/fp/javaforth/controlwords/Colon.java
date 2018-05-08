package edu.mccc.cos210.ds.fp.javaforth.controlwords;

import java.io.IOException;
import java.io.StreamTokenizer;
import edu.mccc.cos210.ds.DoublyLinkedList;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

public class Colon extends ForthWordBase {
	private DoublyLinkedList<Object> codes = new DoublyLinkedList<>(); 
	public Colon() {
		super(":", "A defining word executed in the form: : <name>  ...  ;", false);
	}
	public void build(StreamTokenizer tokenizer, ForthDictionary dictionary) throws IOException {
		Object nextCode;
		do {
			nextCode = ControlWordUtility.buildNext(tokenizer, dictionary);
			codes.addLast(nextCode);
		} while (nextCode != null);
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary) {
	}
}
