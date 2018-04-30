package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import java.util.List;
import java.util.StringTokenizer;

public class ForthInterpretor implements Runnable{
	private int stringToInt;
	private int debugStackheight;
	private String debugWord;
	ForthMachine machine;
	private boolean comment;
	private boolean debugCondition;
	private boolean debugMode;
	int currentLineNumber;
	StringTokenizer currentLineTokens;
	private InputStream inputStream;
	boolean haltFlag;
	public ForthInterpretor(ForthMachine parent) {
		machine = parent;
		inputStream = machine.getInputStream();
		haltFlag = false;
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
	
	public boolean isInteger( String input )  {  
	   try {  
		  stringToInt = Integer.parseInt( input );  
	      return true;  
	   }  
	   catch( Exception e ) {  
	      return false;  
	   }  
	}
	/**
	 * Main method of this class. Should be invoked when the the other parts of the machine are online.
	 * Other machine parts include data stack, return stack, instruction set look up, name lookup table, 
	 */
	public void run() {
		synchronized(inputStream) {
			while(! (inputStream.isEmpty() && haltFlag)){
				interpret(inputStream.pull());
			}
		}
	}
	private void interpret(String token) {
		
	}
	public void stop() {
		haltFlag = true;
	}
	/**
	 * Method for setting a flag in the interpreter as to whether conditional
	 * debugging methods will be
	 *
	 */
	public void setDegbug(boolean debugState) {

	}
	private void pushStack(List<Byte> in) {
		for(Byte i : in) {
			machine.getStack().push(i);
		}
	}
	/**
	 * @param byte a is the leading half of the integer
	 * @param byte b is the trailing half of the integer
	 */
	private int bytesToInt(byte a, byte b) {
		int leadingDigits = 0;
		if(a<0) {
			leadingDigits = (int) (-Math.pow(2, 16) + (a%128)*256);
		}else {
			leadingDigits = a*256;
		}
		int trailingDigits = unsignByte(b);

		return leadingDigits + trailingDigits;
	}
	/**
	 * @param byte addra is the leading half of the address
	 * @param byte addrb is the trailing half of the address
	 */
	private int bytesToAddr(byte addra,byte addrb) {
		int leadingDigits = unsignByte(addra)*256;
		int trailingDigits = unsignByte(addrb);
		return leadingDigits + trailingDigits;
	}
	private int unsignByte(byte b) {
		if (b>0) {
			return b;
		}else {
			return 256 +b;
		}
	}
}