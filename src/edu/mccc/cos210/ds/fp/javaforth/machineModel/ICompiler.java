package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @interface FunctionalInterface
 * @author Nicholas Holmgren
 *
 */
public interface ICompiler {
	/*
	* Compiler's sole function is to do compilation of colon definitions. It takes the forth machine as an argument so 
	* as to make subroutine calls correctly.
	*/
	public static ForthWord compile(ForthMachine machine, String terminator) {
		InputStream input = machine.getInputStream();
		StringBuilder source = new StringBuilder();
		String name = input.pull();
		source.append(name);
		String token = input.pull();
		List<Byte> compiledCode = new ArrayList<>();
		while(!token.equals(terminator)) {
			source.append(token+" ");
			if(machine.getDictionary().contains(token)) {
				
			} else {
				
			}
			token = input.pull();
		}
		ForthWord word = new ForthWord(name, compiledCode, source.toString());
		return word;
	}
}
