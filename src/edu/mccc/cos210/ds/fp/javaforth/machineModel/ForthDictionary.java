package edu.mccc.cos210.ds.fp.javaforth.machineModel;

public class ForthDictionary extends LookUpTable {
	//private 
	public ForthDictionary(ForthMachine parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}
	private class DefinitionHolder{
		String name;
		String definition;
		public DefinitionHolder(String name, String definition) {
			this.name = name;
			this.definition = definition;
		}
		
	}
}
