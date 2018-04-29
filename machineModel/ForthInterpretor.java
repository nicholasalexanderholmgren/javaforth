package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ForthInterpretor {
	private int stringToInt;
	private int debugStackheight;
	private String debugWord;
	private Status status;
	ForthMachine machine;
	private boolean comment;
	private boolean debugCondition;
	private boolean debugMode;
	int currentLineNumber;
	StringTokenizer currentLineTokens;
	public ForthInterpretor(ForthMachine parent) {
		machine = parent;
		initDictionary();
		status = Status.SUCCESSUL;
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
	 *
	 */
	public void interpretLine(String input) {
		currentLineNumber++;
		currentLineTokens = new StringTokenizer(input);
		while (currentLineTokens.hasMoreTokens() && !debugCondition) {
			String currentToken = currentLineTokens.nextToken();
			if(machine.getDictionaryAsMap().containsKey(currentToken)) {
				AbstractWord w = findWord(currentToken);
				int argNumber = w.getNumberOfArguments();
				List<Integer> args = new ArrayList<Integer>();;
				try {
					for(int i = 0; i < argNumber; i++) {
						args.add(popStack());
					}
					List<Integer> result = w.evaluate(args);
					if(result !=null) {
						for(Integer i : result) {
							pushStack(result);
						}
					}
				}catch (IndexOutOfBoundsException e) {
					status = Status.ERROR;
					status.message += "Stack underflow error on word "+currentToken;
					break;
				}
				continue;
			} else {
				if(isInteger(currentToken) ) {
					machine.getStack().push(stringToInt);
				} else { 
					if (currentToken.length()>3) {
						status = Status.ERROR;
					}
				}
			}
			if (debugMode) { 
				if ( debugStackheight > 0 && debugStackheight < machine.getStack().stackHeight()) {
					debugCondition = true;
					break;
				}
				if (debugWord.equals(currentToken)) {
					debugCondition = true;
					break;
				}
			}
		}
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
	 * Method which takes a string, possibly containing line breaks and feeds them to the interpretLine method.
	 * @param String containing either \n or \r characters
	 */
	public void interpret(String input) {
		currentLineNumber = 0;
		StringTokenizer s = new StringTokenizer(input, "\n\r");
		while(s.hasMoreTokens() && status != Status.ERROR) {
			String s1 = s.nextToken();
			interpretLine(s1);
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
			return this + "\t" + message;
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
		final Object myNull = null;
		writeToDict(3);
		writeToDict('.');
		writeToDict(myNull);
		writeToDict(new InterpreterWord("." , 1) {
			@Override
			public List<Integer> evaluate(List<Integer> args) {
				status.message += args.get(0);
				return null;	
			}
		});
		writeToDict(3);
		writeToDict('+');
		writeToDict(myNull);
		writeToDict(new NucleusWord(2) {
			@Override
			public List<Integer> evaluate(List<Integer> args) {
				ArrayList<Integer> ans = new ArrayList<>();
				ans.add(args.get(0)+args.get(1));
				return ans;
			}
		});
		writeToDict(3);
		writeToDict('-');
		writeToDict(myNull);
		writeToDict(new NucleusWord(2) {
			@Override
			public List<Integer> evaluate(List<Integer> args) {
				ArrayList<Integer> ans = new ArrayList<>();
				ans.add(args.get(0)-args.get(1));
				return ans;
			}

		});
		writeToDict(3);
		writeToDict('*');
		writeToDict(myNull);
		writeToDict(new NucleusWord(2) {
			@Override
			public List<Integer> evaluate(List<Integer> args) {
				ArrayList<Integer> ans = new ArrayList<>();
				ans.add(args.get(1)*args.get(0));
				return ans;
			}
		});
		writeToDict(3);
		writeToDict('!');
		writeToDict(myNull);
		writeToDict(new NucleusWord(2){
			@Override
			public List<Integer> evaluate(List<Integer> args) {
				int n = args.get(0);
				int addr = args.get(1);
				if(machine.getFromAddress(addr) instanceof VariableWord) {
					((VariableWord) machine.getFromAddress(addr)).setValue(n);
					return null;
				}
				machine.putAtAddress(addr, n);
				return null;
			}
		});
		writeToDict(10);
		writeToDict("variable");
		writeToDict(new NucleusWord(0) {
			@Override
			public List<Integer> evaluate(List<Integer> args) {
				String vName = currentLineTokens.nextToken();
				int i = machine.getDictionary().getCurrentPointer();
				writeToDict(vName.length() + 2);
				writeToDict(vName);
				writeToDict(new VariableWord(0));
				pushStack(i);
				return null;
			}
		});
	}
	private int popStack() {
		return (Integer) machine.getStack().pop();
	}
	private void pushStack(List<Integer> in) {
		for(Integer i : in) {
			machine.getStack().push(i);
		}
	}
	private void pushStack(int in) {
		machine.getStack().push(in);
	}
	private void writeToDict( Object o) {
		
		machine.putAtAddress(machine.getDictionary().getCurrentPointer(), o);
		machine.getDictionary().setCurrentPointer(machine.getDictionary().getCurrentPointer()+1);
	}
//	private void writeToDict() {
//		
//	}
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
		if(machine.getDictionaryAsMap().containsKey(name)) {
			System.out.println("Found word " + name);
			return machine.getDictionaryAsMap().get(name);
		}
		return null;
	}
}
