package edu.mccc.cos210.ds.fp.javaforth.machineModel;

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
			while(! (inputStream.isEmpty() && haltFlag)){
				interpret(inputStream.pull());
			}
		}
	}
	private void interpret(String token) {
		if(machine.getDictionary().contains(token)) {
			instPointer = machine.getDictionary().findAddr(token);
			machine.getReturnStack().push((byte) 0);
			while (instPointer != 0) {
				Byte byteCode = machine.getFromAddr(instPointer);
				switch(Forth79InstructionSet.convert(byteCode)) {
					case ADD:
						int n1 = bytesToInt(machine.getDataStack().pop(), machine.getDataStack().pop());
						int n2 = bytesToInt(machine.getDataStack().pop(), machine.getDataStack().pop());
						byte[] ans = intToBytes(n1+n2);
						machine.getDataStack().push(ans[0]);
						machine.getDataStack().push(ans[1]);
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
					default:
						break;
				}
			}
			return;
		}
		if(machine.getVariables().contains(token)) {
			byte[] addr = intToBytes(machine.getVariables().findAddr(token));
			machine.getDataStack().push(addr[0]);
			machine.getDataStack().push(addr[1]);
			return;
		}
		try {
			int i = Integer.parseInt(token);
			byte[] val = intToBytes(i);
			machine.getDataStack().push(val[0]);
			machine.getDataStack().push(val[1]);
			return;
		}catch(NumberFormatException e) {
			
		}
		if(token.matches("'[\\x00-\\x7F]'")) {
			machine.getDataStack().push((byte) token.charAt(1));
			return;
		}
		stop();
	}
	public void stop() {
		haltFlag = true;
		inputStream.flush();
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
	private byte[] intToBytes(Integer n) {
		byte[] ans = new byte[2];
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
	private int unsignByte(byte b) {
		if (b>0) {
			return b;
		}else {
			return 256 +b;
		}
	}
}