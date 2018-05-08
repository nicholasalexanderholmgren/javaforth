package edu.mccc.cos210.ds.fp.javaforth.machineModel;


/**
 * Class handles translating instructions to bytes and bytes to instructions for the forth79 standard machine.
 */
public interface Forth79InstructionSet extends InstructionSet{
	/*
	 * 
	 */
	public static Instruction convert(Byte b) {
		for (Instruction instruction : Instruction.values()) {
			if(instruction.getByteCode() == b) {
				return instruction;
			}
		}
		throw new java.util.NoSuchElementException();
	}
	public static Byte convert(Instruction i) {
		return i.getByteCode();
	}
}
