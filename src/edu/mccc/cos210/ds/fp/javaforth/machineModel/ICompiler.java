package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import java.util.ArrayList;
import java.util.List;
import edu.mccc.cos210.ds.fp.javaforth.util.ByteUtils;

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
		String token = input.pull();
		StringBuilder source = new StringBuilder();
		List<Byte> compiledCode = new ArrayList<>();
		while(!token.equals(terminator)) {
			source.append(token+" ");
			if(machine.getDictionary().contains(token)) {
				compiledCode.addAll(jumpToCompiledWord(token ,machine));
			} else {
				try {
					compiledCode.addAll(integerLiteral(Integer.parseInt(token)));
				}catch(Exception e) {
					
				}
			}
			token = input.pull();
		}
		compiledCode.add(Instruction.RETURN.getByteCode());
		ForthWord word = new ForthWord(compiledCode, source.toString());
		return word;
	}
	private static List<Byte> jumpToCompiledWord(String word , ForthMachine machine){
		List<Byte> compiledCode = new ArrayList<>();
		compiledCode.add(Instruction.SUBJMP.getByteCode());
		compiledCode.add((byte) (machine.getDictionary().findAddr(word)/256));
		compiledCode.add((byte) (machine.getDictionary().findAddr(word)%256));
		return compiledCode;
	}
	private static List<Byte> integerLiteral(Integer number){ 
		List<Byte> literal = new ArrayList<>();
		literal.add(Instruction.DPUSH.getByteCode());
		Byte[] intAsBytes = ByteUtils.intToBytes(number);
		literal.add(intAsBytes[0]);
		literal.add(intAsBytes[1]);
		return literal;
	}
}
