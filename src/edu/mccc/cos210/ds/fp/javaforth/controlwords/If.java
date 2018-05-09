package edu.mccc.cos210.ds.fp.javaforth.controlwords;

import java.io.IOException;
import java.io.StreamTokenizer;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.DoublyLinkedList;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

public class If extends ForthControlWord {
	private DoublyLinkedList<ForthWordBase> codes = new DoublyLinkedList<>();
	// Contains only ForthWordBase, double and string.
	public If() {
		super("IF", "TODO");
	}
	public If build(StreamTokenizer tokenizer, ForthDictionary dictionary) throws IOException {
		If word = new If();
		
		return word;
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary) {
		if(this.codes == null) {
			throw new RuntimeException("This word is only valid in compile mode.");			
		}
		else {
			// TODO
		}
	}
}
