package edu.mccc.cos210.ds.fp.javaforth.words;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.NoSuchElementException;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.controlwords.If;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.GraphicsWordBase;

public class PaintLine extends GraphicsWordBase {
	public PaintLine() {
		super("PAINTLINE", "n1 n2 n3 n4 -- drawing pops 4 data values off of the stack and paints them." , false);
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dict) {
		try {
			
			int n1 = ((Double) stack.pop()).intValue();
			int n2 = ((Double) stack.pop()).intValue();
			int n3 = ((Double) stack.pop()).intValue();
			int n4 = ((Double) stack.pop()).intValue();			
			int[] points = {n1,n2,n3,n4};
			
		}catch(NoSuchElementException e){
			throw new RuntimeException("Stack underflow error on word OR.");
		}
	}
	public PaintLine build(StreamTokenizer tokenizer, ForthDictionary dictionary) throws IOException {
		return new PaintLine(tokenizer, dictionary);
	}
	
}
