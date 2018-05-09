package edu.mccc.cos210.ds.fp.javaforth.controlwords;

import java.io.IOException;
import java.io.StreamTokenizer;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;

public class J extends ForthLoopWordBase {
	public J() {
		super("J", "TODO");
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary, ITerminalOutput terminalOutput) {
		ForthLoopWordBase parent = super.getParentLoop();
		if (parent == null) {
			super.throwUnexpectedWord();
		}
		parent = parent.getParentLoop();
		if (parent == null) {
			super.throwUnexpectedWord();
		}
		stack.push(parent.getN2());
	}
	@Override
	ForthWordBase build(StreamTokenizer tokenizer, ForthDictionary dictionary, boolean compiling) throws IOException {
		// TODO Auto-generated method stub
		return new J();
	}
}
