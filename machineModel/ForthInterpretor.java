 package edu.mccc.cos210.ds.fp.javaforth.machineModel;

public class ForthInterpretor {
	private int debugStackheight;
	private String debugWord;
	private ForthStack stack;
	private ForthDictionary dict;
	public ForthInterpretor(ForthDictionary dictionary, ForthStack stack) {
		this.stack = stack;
		dict = dictionary;
	}
	public String getDebugWord() {
		return debugWord;
	}
	public void setDebugWord(String debugWord) {
		this.debugWord = debugWord;
	}
	public int getDebugStackheight() {
		return debugStackheight;
	}
	public void setDebugStackheight(int debugStackheight) {
		this.debugStackheight = debugStackheight;
	}
	public void interpretLine(String input) {
		
	}
	public void interpretFile(String input) {
		
	}
}
