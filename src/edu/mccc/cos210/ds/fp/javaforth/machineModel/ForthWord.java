package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import java.util.List;

public class ForthWord {
	private List<Byte> compiledCode;
	private String name;
	private String source;
	public ForthWord(String name, List<Byte> compiledCode, String sourceCode) {
		this.name = name;
		this.compiledCode = compiledCode;
		this.source = sourceCode;
	}
	public String getName() {
		return name;
	}
	public String getSourceCode() {
		return source;
	}
	public List<Byte> getCompiledCode(){
		return compiledCode;
	}
}
