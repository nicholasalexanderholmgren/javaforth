package edu.mccc.cos210.ds.fp.javaforth.controlwords;

import java.io.IOException;
import java.io.StreamTokenizer;
import edu.mccc.cos210.ds.DoublyLinkedList;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

public class Colon extends ForthControlWord {
	private DoublyLinkedList<ForthWordBase> codes = new DoublyLinkedList<>(); 
	public Colon() {
		super(":", "A defining word executed in the form: : <name>  ...  ;", false);
	}
	public ForthControlWord build(StreamTokenizer tokenizer, ForthDictionary dictionary) throws IOException {
		Colon word = new Colon();
		Object nextCode;
		do {
			nextCode = ControlWordUtility.buildNext(tokenizer, dictionary);
//			codes.addLast(nextCode);
		} while (nextCode != null);
		return word;
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary) {
		if(codes == null) {
			super.throwCompileModeOnly();
		}
		
	}
}
