package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.mccc.cos210.ds.fp.javaforth.util.IObserver;
import edu.mccc.cos210.ds.fp.javaforth.util.ISubject;


public class ForthMachine implements ISubject {
	private Set<IObserver> listeners;
	Byte[] memory;
	private ForthDictionary dict;
	private LookUpTable vars;
	//private Map<String, String> dictMap = new HashMap<String, String>();
	private boolean changed;
	private InputStream input;
	final int INITIAL_STACK_POINTER = (int)Math.pow(2,16)-65;
	final int INITIAL_DICT_POINTER = 0;
	final int INITIAL_INPUT_STREAM_POINTER = (int)Math.pow(2, 16)/3;
	final int PAD_POINTER = (int)Math.pow(2, 16)-1;
	private ForthStack dataStack;
	private ForthStack returnStack;
	private ForthInterpretor interp;
	private int nextAddr;
	private String output;
	public ForthMachine() {
		output = "";
		nextAddr = 1;
		input = new InputStream();
		memory = new Byte[(int)Math.pow(2,16)];
		dict = new ForthDictionary(this);
		vars = new LookUpTable(this);
		dataStack = new ForthStack();
		returnStack = new ForthStack();
		listeners = new HashSet<>();
		changed = false;
		initDictToForth79();
	}
	public InputStream getInputStream() {
		return input;
	}
	public ForthStack getDataStack() {		
		return this.dataStack;
	}
	public ForthStack getReturnStack() {
		return this.returnStack;
	}
	public ForthDictionary getDictionary() {
		if(dict == null) {
			dict = new ForthDictionary(this);
		}
		return this.dict;
	}
	public LookUpTable getVariables() {
		return this.vars;
	}
//	//Will this be an array/sequence of characters
//	//retrieved from the main memory segment?
	public int getPadPointer() {
		return PAD_POINTER;
	}
	/**
	 * Passes the input into the input stream so the interpreter can pick it up. Passing an empty string or no
	 * string will result in interpretation being attempted anyway, which effectively resumes the program if it
	 * was paused.
	 * @param String - input the string of input data to be passed into the inputStream.
	 */
	public void interpret(String input) {
		if(interp != null && interp.isRunning()) {
			return;
		}
		this.input.add(input);
		if(input.length() > 0) {
			interp = new ForthInterpretor(this);
		}
		if(interp != null) {
			new Thread(() -> interp.run()).start();
		}
	}
	public Byte getFromAddr(int address) {
		return memory[address];
	}
	public void putAtAddr(int address, Byte entry) {
		memory[address] = entry;
	}
	/**
	 * Places the supplied byte at the next available memory address, then returns the address where that byte
	 * was placed.
	 * @return Index of memory where the argument was added, should always be > 0 and < 2^16.
	 */
	public int putAtNextAddr(Byte entry) {
		putAtAddr(nextAddr, entry);
		int temp = nextAddr;
		nextAddr+=1;
		return temp;
	}
	public int getNextAddr() {
		return nextAddr;
	}
	/**
	 * Method that stops the interpreter from continuing and clears out the data stack, return stack, and 
	 * input stream. The program cannot resume after this method is called.
	 * @return boolean true when the halt was successful, false when it was not.
	 */	
	public boolean halt() {
		interp.stop();
		return true;
	}
	/**
	 * Method that pauses the interpreter, but does not empty the input stream or in any other way disrupt the 
	 * state of the machine, making it possible to resume.
	 * @return boolean true when the halt was successful, false when it was not.
	 */	
	public boolean pause() {
		interp.stop();
		return true;
	}
	/**
	 * Method enabling other objects to tell the forth machine whether the debugging parameter should be checked
	 * during code execution. When this is set to false, the interpreter will continue until it exhausts in input stream
	 * or halt() is called. When this is set to true, the interpreter will check after each word whether it should pause
	 * execution to permit the user to observe the state of the stack, dictionary, or other program outputs.
	 * @see halt()
	 * @param boolean mode is true when checks for debugging variables will be made.
	 */
	public void setDebugMode(boolean mode) {
		interp.setDegbug(mode);
	}
	/**
	 * Method for setting an integer stack height on which the interpreter will pause in, in the middle of
	 * program execution, for Forth program debugging purposes.
	 * @param int height is the height of the stack the program will pause on.
	 */
	public void setDebugStackHeight(int height) {
		interp.setDebugStackheight(height);
	}

	/**
	 * Method for retrieving the data stack as a string. The stack contains only byte objects, so this string
	 * will be formatted into two digit hexadecimal numbers (0x00 - 0xFF) separated by spaces.
	 * @return String
	 */
	public String getStackAsString() {
		return dataStack.toString();
	}
	/**
	 * Method retrieves each variable declared in the 
	 * @return Map<String,String>
	 */
	public Map<String, String> getDictionaryAsMap(){
		return null;
	}
	public void appendOutput(String output) {
		this.output += output;
	}
	/**
	 * Method for retrieving the status message from the machine upon code execution completion. 
	 * Status includes all program output heading for a live terminal, as well as if the program completed
	 * without hitting an error.
	 * @return String 
	 */
	public String getStatus() {
		return output;
	}
	@Override
	public void registerObserver(IObserver o) {
		listeners.add(o);
	}
	@Override
	public void removeObserver(IObserver o) {
		listeners.remove(o);
	}
	@Override
	public void notifyObservers() {
		if (changed) {
			for(IObserver o : listeners) {
				o.update(this);
			}
		}
		changed = false;
		output = "";
	}
	public void setChanged() {
		changed = true;
	}
	private void initDictToForth79() {
		try {
			DictionaryLoader.loadDict(this, "79Standard");
		} catch (Exception e) {
			this.output = "Dictionary loading error";
			this.setChanged();
			this.notifyObservers();
		}
	}
}