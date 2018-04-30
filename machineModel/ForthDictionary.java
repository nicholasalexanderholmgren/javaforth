package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import java.util.HashMap;
import java.util.Map;

public class ForthDictionary {
	private Map<String, Integer> addrLookupTable;
	public ForthDictionary() {
		addrLookupTable = new HashMap<>();
	}
	/**
	 * Checks to see if the dictionary contains a word already.
	 * @return boolean
	 * @param word being looked up
	 */
	public boolean contains(String word) {
		return addrLookupTable.keySet().contains(word.toUpperCase());
	}
	public Integer findAddr(String word) {
		if( contains(word)) {
			return addrLookupTable.get(word);
		}
		return 0;
	}
}
