package edu.mccc.cos210.ds.fp.javaforth.controlwords;

import java.io.IOException;
import java.io.StreamTokenizer;
import edu.mccc.cos210.ds.DoublyLinkedList;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;

public class Do extends ForthLoopWordBase {
	private DoublyLinkedList<ForthWordBase> codes;
	public Do() {
		super("DO", "n1 n2 --  Used in a colon-definition: DO ... LOOP   or DO ... +LOOP Begin a loop which will terminate based on control parameters.");
	}
	@Override
	ForthWordBase build(StreamTokenizer tokenizer, ForthDictionary dictionary, boolean compiling) throws IOException {
		if (!compiling) {
			super.throwCompileModeOnly();
		}
		Do word = new Do();
		ForthWordBase nextWord = ControlWordUtility.buildNext(tokenizer, dictionary, compiling);
		while (nextWord != null) {
			if (nextWord instanceof Loop || nextWord instanceof PlusLoop) {
				word.codes.addLast(nextWord);
				return word;
			}
			word.codes.addLast(nextWord);
			nextWord = ControlWordUtility.buildNext(tokenizer, dictionary, compiling);
		}
		return word;
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary, ITerminalOutput terminalOutput) {
		if (codes == null) {
			super.throwCompileModeOnly();
		}
		super.setN2((double) stack.pop());
		super.setN1((double) stack.pop());
		while (super.getN2() < super.getN1()) {
			boolean loopWordHit = false;
			for (ForthWordBase forthWordBase : codes) {
				if (forthWordBase instanceof Do) {
					((Do) forthWordBase).setParentLoop(this);
				}
				if (forthWordBase instanceof Loop) {
					super.setN2(super.getN2() + 1);
					loopWordHit = true;
					break;
				}
				if (forthWordBase instanceof PlusLoop) {
					super.setN2(super.getN2() + (double) stack.pop());
					loopWordHit = true;
					break;
				}
				forthWordBase.execute(stack, dictionary, terminalOutput);
			}
			if (!loopWordHit) {
				throw new RuntimeException("Unexpected end of DO.");
			}
		}
	}
}
