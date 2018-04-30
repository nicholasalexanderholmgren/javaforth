package edu.mccc.cos210.ds.fp.javaforth.machineModel;


/**
 * Class that takes bytes and translates them into an enumerator for which instruction that byte correpsonds to
 * @interface FunctionalInterface
 */
public interface Forth79InstructionSet extends InstructionSet{
	/*
	 * 
	 */
	public default Instruction convert(Byte b) {
		switch(b.byteValue()) {
			case 0x00:
				return Instruction.ADD;
			case 0x01:
				return Instruction.SUB;
			case 0x02:
				return Instruction.MULT;
			case 0x03:
				return Instruction.DIV;
			case 0x04:
				return Instruction.MOD;
			case 0x05:
				return Instruction.ADD;
			case 0x06:
				return Instruction.ADD;
			case 0x07:
				return Instruction.ADD;
			default:
				return null;
		}
	}
	
}
