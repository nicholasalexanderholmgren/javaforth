package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import java.util.List;

public class ForthWord {
	private List<Byte> compiledCode;
	private String source;
	public ForthWord(List<Byte> compiledCode, String sourceCode) {
		this.compiledCode = compiledCode;
		this.source = sourceCode;
	}
	public String getSourceCode() {
		return source;
	}
	public List<Byte> getCompiledCode(){
		return compiledCode;
	}
}
