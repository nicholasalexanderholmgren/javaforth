package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import java.util.List;

/**
 * @interface FunctionalInterface
 * @author qulqu
 *
 */
public interface ICompiler {
	/*
	* Compiler's sole function is to do compilation of colon definitions. It takes the forth machine as an argument and
	* manipulates it to put the new word into the memory as compiled code.
	* @return returns false if the compilation fails.
	*/
	public static List<Byte> compile(ForthMachine machine, String terminator) {
		InputStream input = machine.getInputStream();
		//String token 
		return null;
	}
}
