package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import java.util.StringTokenizer;

public class ForthInterpretor {
	private int debugStackheight;
	private String debugWord;
	private Status status;
	private ForthMachine machine;
	private boolean comment;
	StringTokenizer currentLineTokens;
	public ForthInterpretor(ForthMachine parent) {
		machine = parent;
		initDictionary();
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
	public void interpretLine(String input, boolean isLastLine) {
		currentLineTokens = new StringTokenizer(input); 
		while (currentLineTokens.hasMoreTokens()) {
			String currentToken = currentLineTokens.nextToken();
			if(machine.getDictionaryAsMap().containsKey(currentToken)) {
				 AbstractWord w = findWord(currentToken);
				continue;
			}else {
				if(currentToken.length()>3) {
					status = Status.ERROR;
				}
			}
		}
		if(isLastLine) {
			machine.setChanged();
			machine.notifyObservers();
		}
	}
	/**
	 * Method which takes a string containing line breaks and feeds them to the interpretLine method.
	 * @param String containing either \n or \r characters
	 */
	public void interpretFile(String input) {
		StringTokenizer s = new StringTokenizer(input, "\n\r");
		while(s.hasMoreTokens()) {
			String s1 = s.nextToken();
			interpretLine(s1,s.hasMoreTokens());
		}
		machine.setChanged();
		machine.notifyObservers();
	}
	/**
	 * Method for setting a flag in the interpreter as to whether conditional 
	 * debugging methods will be 
	 * 
	 */
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
	private abstract class NucleusWord extends AbstractWord {

		public NucleusWord(int numberOfArgument, boolean isPrimitive, boolean isImmediate) {
			super(numberOfArgument, true, isImmediate);
		}

		public NucleusWord(int numberOfArgument) {
			super (numberOfArgument, true, false);
		}

		@Override
		public String toString() {
			return "This word is defined in the Forth 79 Standard, it takes " + getNumberOfArguments() + ""
					+ "bytes as arguments.";
		}
	}
	
	private void initDictionary() {
		writeToDict(3);
		writeToDict('+');
		writeToDict(null);
		writeToDict(new NucleusWord(4) {

			@Override
			public byte[] evaluate(byte[] args) {
				byte n1a = popStack();
				byte n1b = popStack();
				byte n2a = popStack();
				byte n2b = popStack();
				int total = bytesToInt(n1a, n1b) +  bytesToInt(n2a, n2b);
				byte[] ans = new byte[2];
				ans [0] = (byte) ((total/256)%256);
				ans [1] = (byte) (total%256);
				return ans;
			}
			
		});
		writeToDict(3);
		writeToDict('-');
		writeToDict(null);
		writeToDict(new NucleusWord(4) {
			@Override
			public byte[] evaluate(byte[] args) {
				byte n1a = popStack();
				byte n1b = popStack();
				byte n2a = popStack();
				byte n2b = popStack();
				int total = bytesToInt(n1a, n1b) -  bytesToInt(n2a, n2b);
				byte[] ans = new byte[2];
				ans [0] = (byte) ((total/256)%256);
				ans [1] = (byte) (total%256);
				return ans;
			}
		});
		writeToDict(3);
		writeToDict('*');
		writeToDict(null);
		writeToDict(new NucleusWord(4) {
			@Override
			public byte[] evaluate(byte[] args) {
				byte n1a = popStack();
				byte n1b = popStack();
				byte n2a = popStack();
				byte n2b = popStack();
				int total = (bytesToInt(n1a, n1b) *  bytesToInt(n2a, n2b));
				if (total > Math.pow(2, 16)) {
					total = total%((int)Math.pow(2, 16));
				}
				byte[] ans = new byte[2];
				ans [0] = (byte) ((total/256)%256);
				ans [1] = (byte) (total%256);
				return ans;
			}
			
		});
		writeToDict(3);
		writeToDict('!');
		writeToDict(new NucleusWord(4){

			@Override
			public byte[] evaluate(byte[] args) {
				byte na = popStack();
				byte nb = popStack();
				int n = bytesToInt(na, nb);
				byte addra = popStack();
				byte addrb = popStack();
				int addr = bytesToAddr(addra,addrb);
				if(machine.getFromAddress(addr) instanceof VariableWord) {
					((VariableWord) machine.getFromAddress(addr)).setValue(n);
					return null;
				}
				machine.putAtAddress(addr, n);
				return null;
			} 
			
		});
		writeToDict(4);
		writeToDict('0');
		writeToDict('<');
		writeToDict(null);
		writeToDict(new NucleusWord(2){

			@Override
			public byte[] evaluate(byte[] args) {
				byte na = popStack();
				byte nb = popStack();
				int n = bytesToInt(na,nb);
				byte[] ans = new byte[1];
				if(n<0) {
					ans[0] = 1;
				}else {
					ans[0] = 0;
				}
				return ans;
			}
			
		});
		writeToDict(4);
		writeToDict('0');
		writeToDict('=');
		writeToDict(null);
		writeToDict(new NucleusWord(2){

			@Override
			public byte[] evaluate(byte[] args) {
				byte na = popStack();
				byte nb = popStack();
				int n = bytesToInt(na,nb);
				byte[] ans = new byte[1];
				if(n==0) {
					ans[0] = 1;
				}else {
					ans[0] = 0;
				}
				return ans;
			}
			
		});
		writeToDict(4);
		writeToDict('0');
		writeToDict('>');
		writeToDict(null);
		writeToDict(new NucleusWord(2){

			@Override
			public byte[] evaluate(byte[] args) {
				byte na = popStack();
				byte nb = popStack();
				int n = bytesToInt(na,nb);
				byte[] ans = new byte[1];
				if(n>0) {
					ans[0] = 1;
				}else {
					ans[0] = 0;
				}
				return ans;
			}
			
		});
		writeToDict(10);
		writeToDict("variable");
		writeToDict(new NucleusWord(0) {

			@Override
			public byte[] evaluate(byte[] args) {
				String vName = currentLineTokens.nextToken();
				writeToDict(vName.length());
				writeToDict(vName);
				writeToDict(new VariableWord(0));
				return null;
			}
		});
	}
	private byte popStack() {
		byte b  = (byte)machine.getFromAddress(machine.getStack().getCurrentPointer());
		machine.getStack().setCurrentPointer(machine.getStack().getCurrentPointer()+1);
		return b;
	}
	private void pushStack(byte b) {
		machine.putAtAddress(machine.getStack().getCurrentPointer(), b);
		machine.getStack().setCurrentPointer(machine.getStack().getCurrentPointer()-1);
	}
	private void writeToDict( Object o) {
		machine.putAtAddress(machine.getDictionary().getCurrentPointer(), o);
		machine.getDictionary().setCurrentPointer(machine.getDictionary().getCurrentPointer()+1);
	}
	private void writeToDict(String s) {
		for(int i =0; i< s.length(); i++) {
			writeToDict(s.charAt(i));
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
	private AbstractWord findWord(String name) {
		final int START = 0;
		int current = START;
		int end = machine.getDictionary().getCurrentPointer();
		while(current < end) {
			if((Integer)(machine.getFromAddress(current)) != name.length()) {
				current+=(Integer)(machine.getFromAddress(current));
				continue;
			}
			StringBuilder sb = new StringBuilder();
			current+=1;
			while(machine.getFromAddress(current) != null) {
				sb.append((char)(machine.getFromAddress(current)));
			}
			if(sb.toString().equals(name)) {
				return ((AbstractWord) (machine.getFromAddress(current+1)));
			}
			current+=1;
		}
		return null;
	}
}