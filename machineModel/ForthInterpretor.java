 package edu.mccc.cos210.ds.fp.javaforth.machineModel;

public class ForthInterpretor {
	private int debugStackheight;
	private String debugWord;
	private Status status;
	private ForthMachine machine;
	public ForthInterpretor(ForthMachine parent) {
		machine = parent;
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
	public void setDegbug(boolean debugState) {
		
	}
	public Status getStatus() {
		return status;
	}
	public static enum Status {
		SUCCESSUL("SUCCESS"), ERROR("ERROR");
		private String message;
		Status(String s) {
			message = s;
		}
		public String getMessage() { 
			return message;
		}
		public void setStatus(String newMessage) {
			message = newMessage;
		}
	}
}
