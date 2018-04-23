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
		memory = new Object[(int)Math.pow(2,16)];
		stack = new ForthStack(memory, INITIAL_STACK_POINTER);
		dict = new ForthDictionary(memory,INITIAL_DICT_POINTER);
		listeners = new ArrayList<>();
		interp = new ForthInterpretor(this);
		changed = false;
	}
	public ForthStack getStack() {
		return this.stack;
	}
	public ForthDictionary getDictionary() {
		return this.dict;
	}
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
	public void interpret(String input) {
		
	}
	public Object getFromAddress(int address) {
		return null;
	}
	public void putAtAddress(int address, Object entry) { 
		
	}
	public boolean halt() {
		return true;
	}
	public void setDebugMode(boolean mode) {
		
	}
	public void setDebugStackHeight(int height) {
		
	}
	public String getStackAsString() {
		return null;
	}
	public Map<String,String> getDictionaryAsMap(){
		return null;
	}
	public String getStatus() {
		return null;
	}
}
