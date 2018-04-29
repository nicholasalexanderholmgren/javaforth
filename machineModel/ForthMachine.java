package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import edu.mccc.cos210.ds.fp.javaforth.util.IObserver;
import edu.mccc.cos210.ds.fp.javaforth.util.ISubject;


public class ForthMachine implements ISubject {
	private Collection<IObserver> listeners;
	Object[] memory;
	private ForthStack stack;
	private ForthDictionary dict;
	private Map<String, AbstractWord> dictMap = new HashMap<String, AbstractWord>();
	private ForthInterpretor interp;
	private boolean changed;
	final int INITIAL_STACK_POINTER = (int)Math.pow(2,16)-65;
	final int INITIAL_DICT_POINTER = 0;
	final int INITIAL_INPUT_STREAM_POINTER = (int)Math.pow(2, 16)/3;
	final int PAD_POINTER = (int)Math.pow(2, 16)-1;
	public ForthMachine() {
		memory = new Object[(int)Math.pow(2,16)];
		dict = new ForthDictionary(memory,INITIAL_DICT_POINTER);
		interp = new ForthInterpretor(this);	
		stack = new ForthStack(memory , INITIAL_STACK_POINTER);
		listeners = new ArrayList<>();
		changed = false;
	}
	
	public ForthStack getStack() {		
		return this.stack;
	}
	public ForthDictionary getDictionary() {
		return this.dict;
	}
	//Will this be an array/sequence of characters
	//retrieved from the main memory segment?
	public int getPadPointer() {
		return PAD_POINTER;
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
	}
	public void setChanged() {
		changed = true;
	}

	/**
	 * Method used for objects outside of the machine to send string into the machine
	 * for interpretation. Strings passed in with this method will be written into memory
	 * starting from where INITIAL_INPUT_STREAM_POINTER points and continuing to greater memory addresses.
	 * @param String input - the string of input data to be processed
	 */
	public void interpret(String input) {
		interp.interpretFile(input);
		interp.interpretLine(input , true);
	}
	public Object getFromAddress(int address) {
		return memory[address];
	}
	public void putAtAddress(int address, Object entry) {
		memory[address] = entry;
		memory[address] = entry; 
	}
	//Stops the interpreter from running through
	//the forth code?
	/**
	 * Method that stops the interpreter from continuing. It can be expected that the GUI of this program
	 * will trigger this method if execution of an input has taken a sufficient amount of time that the 
	 * user suspects an infinite loop has occurred. 
	 * @return boolean true when the halt was successful, false when it was not.
	 */	
	public boolean halt() {
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

	}
	/**
	 * Method for setting an integer stack height on which the interpreter will pause in, in the middle of
	 * program execution, for Forth program debugging purposes.
	 * @param int height is the height of the stack the program will pause on.
	 */
	public void setDebugStackHeight(int height) {

	}

	/**
	 * Method for retrieving the stack data as a string. The stack contains only byte objects, so this string
	 * will be formatted into two digit hexadecimal numbers (00 - FF) separated by spaces.
	 * 
	 * @return String
	 */
	public String getStackAsString() {
		return null;
	}
	/**
	 * Method for retrieving the data in the dictionary memory segment, including both standard words and user-
	 * defined words. The dictionary is returned as a map specialized on type String to String so a user can 
	 * find the definition of a word using it as the key and the definition will be the value.
	 * @return Map<String,String>
	 */
	public Map<String, AbstractWord> getDictionaryAsMap(){
		
		int current = 0;
		int end = getDictionary().getCurrentPointer();
		while(current < end) {
			StringBuilder sb = new StringBuilder();
			current+=1;
			while(getFromAddress(current) != null) {
				sb.append((getFromAddress(current)));
				current ++;
			}
			current++;
			dictMap.put(sb.toString(), (AbstractWord) getFromAddress(current));
			current ++;
		}
		return dictMap;
	}
	/**
	 * Method for retrieving the status message from the machine upon code execution completion. 
	 * Status includes all program output heading for a live terminal, as well as if the program completed
	 * without hitting an error.
	 * @return String 
	 */
	public String getStatus() {
		return interp.getStatus().getMessage();
	}
}
