package edu.mccc.cos210.ds.fp.javaforth.machine_model;

import java.util.HashMap;
import java.util.Map;

public class LookUpTable {
	private Map<String, Integer> addrLookupTable;
	private ForthMachine parent;
	public LookUpTable(ForthMachine parent) {
		addrLookupTable = new HashMap<>();
		this.parent = parent;
	}
	/**
	 * Checks to see if the dictionary contains a word already.
	 * @return boolean
	 * @param word being looked up
	 */
	public boolean contains(String word) {
		if(word == null) {
			return false;
		}
		return addrLookupTable.containsKey(word.toUpperCase());
	}
	public Integer findAddr(String word) {
		if( contains(word)) {
			return addrLookupTable.get(word);
		}
		return 0;
	}
	/**
	 *	Associates a word with the next free memory address in the parent ForthMachine
	 * @param word - the name of the new word to be allocated for.
	 */
	public void allocate(String word) {
		addrLookupTable.put(word, parent.getNextAddr());
	}
}
