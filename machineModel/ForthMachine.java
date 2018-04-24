package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import edu.mccc.cos210.ds.fp.javaforth.util.IObserver;
import edu.mccc.cos210.ds.fp.javaforth.util.ISubject;

public class ForthMachine implements ISubject {
	private Collection<IObserver> listeners;
	private Object[] memory;
	private ForthStack stack;
	private ForthDictionary dict;
	private ForthInterpretor interp;
	private boolean changed;
	final int INITIAL_STACK_POINTER = (int)Math.pow(2,16)-65;
	final int INITIAL_DICT_POINTER = 0;
	final int INITIAL_INPUT_STREAM_POINTER = (int)Math.pow(2, 16)/3;
	final int PAD_POINTER = (int)Math.pow(2, 16)-1;
	public ForthMachine() {//JavaForth master) {
		//this.master = master;

		//declares a large array of objects used
		//to store and simulate the memory used by
		//the FORTH code
		memory = new Object[(int)Math.pow(2,16)];

		//How will these be changing after they are
		//initialized?
		stack = new ForthStack(memory, INITIAL_STACK_POINTER);
		dict = new ForthDictionary(memory,INITIAL_DICT_POINTER);
		listeners = new ArrayList<>();

		//I'm presuming strings and memory data will
		//be passed to this from within this class
		//Does the interpreter class have access
		//to the memory segment.
		//Also how will be delimiting the memory?
		interp = new ForthInterpretor(this);
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

	//Where in the memory will input stream be saved
	//Is it going to be in the memory segment?
	public void interpret(String input) {

	}
	public Object getFromAddress(int address) {
		return null;
	}
	public void putAtAddress(int address, Object entry) {

	}
	//Stops the interpreter from running through
	//the forth code?
	public boolean halt() {
		return true;
	}

	//Needs to be able to increment through stack
	//operations or lines??
	public void setDebugMode(boolean mode) {

	}

	//I don't really understand what we'd be
	//setting here.
	public void setDebugStackHeight(int height) {

	}

	//Gets the current chosen stack as a
	//string. Can be done to any stack?
	public String getStackAsString() {
		return null;
	}
	//This map will contain the forth dictionary
	//according to the FORTH79 standards manual
	public Map<String,String> getDictionaryAsMap(){
		return null;
	}
	//What is meant by status here?
	public String getStatus() {
		return null;
	}
}
