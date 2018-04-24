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

  //Should we make it interprety things
  //line by line or word by word?
  //Also should this take in a stack or
  //a string.

  //Might need to word with a
  //dataStack/variableStack/forthStack
	public void interpretLine(String input) {

	}

  //interprets entire forth file and updates
  //the memory segment accordingly.
  //should the file be saved to the memory?
  //or should the final output just be logged.
	public void interpretFile(String input) {

	}

  //line by line or word by word?
	public void setDegbug(boolean debugState) {

	}
  //What is inside of this?
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
