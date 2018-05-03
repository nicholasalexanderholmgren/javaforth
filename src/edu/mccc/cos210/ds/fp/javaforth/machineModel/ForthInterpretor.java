package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import java.util.ArrayList;
import java.util.EmptyStackException;

import edu.mccc.cos210.ds.fp.javaforth.util.ByteUtils;

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
				machine.setChanged();
				interpret(inputStream.pull());
			}
		}
		if(! haltFlag) {
			machine.appendOutput("ok");
		}
		machine.notifyObservers();
		System.out.println(machine.getDataStack());
	}
	private void interpret(String token) {
		if(token == null) {
			return;
		}
		if(machine.getDictionary().contains(token)) {
			machine.getReturnStack().push(ByteUtils.addrToBytes(instPointer));
			instPointer = machine.getDictionary().findAddr(token);
			try {
				ArrayList<Byte> temp = new ArrayList<>();
				while (!haltFlag && instPointer != 0) {
					Byte byteCode = machine.getFromAddr(instPointer);
					Byte[] ans;
					int n1, n2;
					switch(Forth79InstructionSet.convert(byteCode)) {
						case ADD:
							n1 = ByteUtils.bytesToInt(machine.getDataStack().pop(), machine.getDataStack().pop());
							n2 = ByteUtils.bytesToInt(machine.getDataStack().pop(), machine.getDataStack().pop());
							ans = ByteUtils.intToBytes(n1+n2);
							machine.getDataStack().push(ans);
							break;
						case SUB:
							n1 = ByteUtils.bytesToInt(machine.getDataStack().pop(), machine.getDataStack().pop());
							n2 = ByteUtils.bytesToInt(machine.getDataStack().pop(), machine.getDataStack().pop());
							ans = ByteUtils.intToBytes(n1-n2);
							machine.getDataStack().push(ans);
							break;
						case MULT:
							n1 = ByteUtils.bytesToInt(machine.getDataStack().pop(), machine.getDataStack().pop());
							n2 = ByteUtils.bytesToInt(machine.getDataStack().pop(), machine.getDataStack().pop());
							ans = ByteUtils.intToBytes(n1*n2);
							machine.getDataStack().push(ans);
							break;
						case DIV:
							n1 = ByteUtils.bytesToInt(machine.getDataStack().pop(), machine.getDataStack().pop());
							n2 = ByteUtils.bytesToInt(machine.getDataStack().pop(), machine.getDataStack().pop());
							ans = ByteUtils.intToBytes(n1/n2);
							machine.getDataStack().push(ans);
							break;
						case MOD:
							n1 = ByteUtils.bytesToInt(machine.getDataStack().pop(), machine.getDataStack().pop());
							n2 = ByteUtils.bytesToInt(machine.getDataStack().pop(), machine.getDataStack().pop());
							ans = ByteUtils.intToBytes(n1%n2);
							machine.getDataStack().push(ans);
							break;
						case JMP:
							instPointer = ByteUtils.bytesToAddr(
									machine.getDataStack().pop(), machine.getDataStack().pop());
							break;
						case CJMP:
							if((ByteUtils.bytesToInt(machine.getDataStack().pop(),machine.getDataStack().pop()) != 0)){
								instPointer = 
										ByteUtils.bytesToAddr(machine.getDataStack().pop(), 
												machine.getDataStack().pop());
							}
							break;
						case RFETCH:
							temp.add(machine.getReturnStack().pop());
							temp.add(machine.getReturnStack().pop());
							machine.getReturnStack().push(temp.get(1));
							machine.getReturnStack().push(temp.get(0));
							machine.getDataStack().push(temp.get(1));
							machine.getDataStack().push(temp.get(0));
							break;
						case RPUSH:
							temp.add(machine.getDataStack().pop());
							temp.add(machine.getDataStack().pop());
							machine.getReturnStack().push(temp.get(1));
							machine.getReturnStack().push(temp.get(0));
						case RFROM:
							temp.add(machine.getReturnStack().pop());
							temp.add(machine.getReturnStack().pop());
							machine.getDataStack().push(temp.get(1));
							machine.getDataStack().push(temp.get(0));
							break;
						case NUMOUT:
							temp.add(machine.getDataStack().pop());
							temp.add(machine.getDataStack().pop());
							machine.appendOutput(ByteUtils.bytesToInt(temp.get(0), temp.get(1)).toString()+ " ");
							break;
						case FETCH:
							temp.add(machine.getDataStack().pop());
							temp.add(machine.getDataStack().pop());
							break;
						default:
							break;
					}
					if(Forth79InstructionSet.convert(byteCode) != Instruction.JMP &&
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
			Byte[] addr = ByteUtils.intToBytes(machine.getVariables().findAddr(token));
			machine.getDataStack().push(addr[0]);
			machine.getDataStack().push(addr[1]);
			return;
		}
		try {
			int i = Integer.parseInt(token);
			machine.getDataStack().push(ByteUtils.intToBytes(i));
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
	
}