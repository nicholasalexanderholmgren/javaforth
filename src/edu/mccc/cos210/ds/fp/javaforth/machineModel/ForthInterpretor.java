package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import java.util.EmptyStackException;

public class ForthInterpretor implements Runnable{
	private int debugStackheight;
	private String debugWord;
	ForthMachine machine;
//	private boolean comment;
//	private boolean debugCondition;
//	private boolean debugMode;
	// The pointer for where the interpreter currently is getting instruction from in memory
	// is 0 when the interpreter is just moving data from the input stream to the data stack
	int instPointer;
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
	/**
	 * Main method of this class. Should be invoked when the the other parts of the machine are online.
	 * Other machine parts include data stack, return stack, instruction set look up, name lookup table, 
	 */
	public void run() {
		synchronized(inputStream) {
			while(! (inputStream.isEmpty() || haltFlag)){
				interpret(inputStream.pull());
			}
		}
		machine.appendOutput("ok");
		System.out.println(machine.getDataStack());
	}
	private void interpret(String token) {
		if(token == null) {
			return;
		}
		if(machine.getDictionary().contains(token)) {
			machine.getReturnStack().push(addrToBytes(instPointer));
			instPointer = machine.getDictionary().findAddr(token);
			try {
				while (instPointer != 0) {
					Byte byteCode = machine.getFromAddr(instPointer);
					switch(Forth79InstructionSet.convert(byteCode)) {
						case ADD:
							int n1 = bytesToInt(machine.getDataStack().pop(), machine.getDataStack().pop());
							int n2 = bytesToInt(machine.getDataStack().pop(), machine.getDataStack().pop());
							Byte[] ans = intToBytes(n1+n2);
							machine.getDataStack().push(ans);
							break;
						case JMP:
							instPointer = bytesToAddr(machine.getDataStack().pop(), machine.getDataStack().pop());
							break;
						case CJMP:
							if((bytesToInt(machine.getDataStack().pop(),machine.getDataStack().pop()) != 0)){
								instPointer = 
										bytesToAddr(machine.getDataStack().pop(), machine.getDataStack().pop());
							}
							break;
						case RFROM:
							machine.getDataStack().push(machine.getReturnStack().pop());
							machine.getDataStack().push(machine.getReturnStack().pop());
							break;
						default:
							break;
					}
					if(Forth79InstructionSet.convert(byteCode) != Instruction.JMP ||
							Forth79InstructionSet.convert(byteCode) != Instruction.CJMP) {
						instPointer += 1;
					}
				}
			}catch(EmptyStackException e) {
				machine.appendOutput("Stack underflow error on word " + token);
				stop();
			}
			return;
		}
		if(machine.getVariables().contains(token)) {
			Byte[] addr = intToBytes(machine.getVariables().findAddr(token));
			machine.getDataStack().push(addr[0]);
			machine.getDataStack().push(addr[1]);
			return;
		}
		try {
			int i = Integer.parseInt(token);
			machine.getDataStack().push(intToBytes(i));
			return;
		}catch(NumberFormatException e) {
			
		}
		if(token.matches("'[\\x00-\\x7F]'")) {
			machine.getDataStack().push((byte) token.charAt(1));
			return;
		}
		machine.appendOutput("ERROR on word "+token);
		stop();
	}
	public void stop() {
		haltFlag = true;
		inputStream.flush();
	}
	public void pause() {
		haltFlag = true;
	}
	/**
	 * Method for setting a flag in the interpreter as to whether conditional
	 * debugging methods will be
	 *
	 */
	public void setDegbug(boolean debugState) {

	}
	/**
	 * @param byte a is the leading half of the integer
	 * @param byte b is the trailing half of the integer
	 */
	private int bytesToInt(byte b, byte a) {
		int leadingDigits = 0;
		if(a<0) {
			leadingDigits = (int) (-Math.pow(2, 16) + (a%128)*256);
		}else {
			leadingDigits = a*256;
		}
		int trailingDigits = unsignByte(b);

		return leadingDigits + trailingDigits;
	}
	private Byte[] intToBytes(Integer n) {
		Byte[] ans = new Byte[2];
		if(n > Math.pow(2, 16)) {
			n = n%((int)Math.pow(2, 16));
		}
		Integer nA = n/256;
		Integer nB = n%256;
		ans[0] = nA.byteValue();
		ans[1] = nB.byteValue();
		return ans;
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
	private Byte[] addrToBytes(int addr) {
		addr = (int) (addr%Math.pow(2, 16));
		Byte[] ans = new Byte[2];
		ans[0] = (byte) (addr%256);
		ans[1] = (byte) (addr/256);
		return ans;
	}
	private int unsignByte(byte b) {
		if (b>=0) {
			return b;
		}else {
			return 256 +b;
		}
	}
}