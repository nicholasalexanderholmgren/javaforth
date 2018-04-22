package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import java.util.ArrayList;
import java.util.Collection;
import edu.mccc.cos210.ds.fp.javaforth.util.IObserver;
import edu.mccc.cos210.ds.fp.javaforth.util.ISubject;

public class ForthMachine implements ISubject {
	private Collection<IObserver> listeners;
	private Object[] memory;
	private ForthStack stack;
	private ForthDictionary dict;
	private ForthInterpretor interp;
	private boolean changed;
	final int INITIAL_STACK_POINTER = (int)Math.pow(2,16)-1;
	final int INITIAL_DICT_POINTER = 0;
	final int INITIAL_INPUT_STREAM_POINTER = (int)Math.pow(2, 16)/3;
	public ForthMachine() {//JavaForth master) {
		//this.master = master;
		memory = new Object[(int)Math.pow(2,16)];

		stack = new ForthStack(memory, INITIAL_STACK_POINTER);
		dict = new ForthDictionary(memory,INITIAL_DICT_POINTER);
		listeners = new ArrayList<>();
		changed = false;
	}
	public ForthStack getStack() {
		return this.stack;
	}
	public ForthDictionary getDictionary() {
		return this.dict;
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
}
