package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import edu.mccc.cos210.ds.fp.javaforth.util.ByteUtils;

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
	public static boolean compile(ForthMachine dest) {
		String name = dest.getInputStream().pull();
		StringBuilder definition = new StringBuilder();
		dest.getDictionary().allocate(name);
		String currentToken = "";
		Byte[] out = new Byte[2];
		currentToken = dest.getInputStream().pull();
		do{
			definition.append(currentToken);
			if(dest.getDictionary().contains(currentToken)) {
				out = ByteUtils.addrToBytes(dest.getDictionary().findAddr(currentToken));
				dest.putAtNextAddr(out[0]);
				dest.putAtNextAddr(out[1]);
				dest.putAtNextAddr(Forth79InstructionSet.convert(Instruction.DPUSH));
				dest.putAtNextAddr(Forth79InstructionSet.convert(Instruction.SUBJMP));
			}
			if(currentToken.matches("'[\\x00-\\x7F]'")) {
				dest.putAtNextAddr(Forth79InstructionSet.convert(Instruction.DPUSHC));
				dest.putAtNextAddr((byte) currentToken.charAt(1));
			}
			try {
				out = ByteUtils.intToBytes(Integer.parseInt(currentToken));
				dest.putAtNextAddr(Forth79InstructionSet.convert(Instruction.DPUSH));
				dest.putAtNextAddr(out[0]);
				dest.putAtNextAddr(out[1]);
			}catch (Exception e) {
			}
			currentToken = dest.getInputStream().pull();
		}while (!currentToken.equals(";"));
		dest.putAtNextAddr(Forth79InstructionSet.convert(Instruction.RFROM));
		dest.putAtNextAddr(Forth79InstructionSet.convert(Instruction.JMP));
		return true;
	}
}
