package edu.mccc.cos210.ds.fp.javaforth.machine_model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
/*
 * Definition 
 */
public class ForthDictionary extends LookUpTable {
	List<DefinitionHolder> sourceHolder;
	public ForthDictionary(ForthMachine parent) {
		super(parent);
		sourceHolder = new Vector<>();
	}
	public void addDefinition(String name, String definition) {
		sourceHolder.add(new DefinitionHolder(name, definition));
	}
	public String getDefinition(String name) {
		String s = null;
		for(DefinitionHolder d : sourceHolder) {
			if (d.getName().equals(name)) {
				return d.getDefinition();
			}
		}
		return s;
	}
	public Map<String, String> getDictionaryMap(){
		Map<String, String> map = new HashMap<>();
		for(DefinitionHolder d : sourceHolder) {
			map.put(d.getName(), d.getDefinition());
		}
		return map;
	}
	private class DefinitionHolder{
		private String name;
		private String definition;
		public DefinitionHolder(String name, String definition) {
			this.setName(name);
			this.setDefinition(definition);
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDefinition() {
			return definition;
		}
		public void setDefinition(String definition) {
			this.definition = definition;
		}
		public String toString() {
			return name + ": " +definition; 
		}
	}
}
