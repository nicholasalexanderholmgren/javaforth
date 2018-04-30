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
			case 0x01:
				return Instruction.ADD;
			case 0x02:
				return Instruction.SUB;
			case 0x03:
				return Instruction.MULT;
			case 0x04:
				return Instruction.DIV;
			case 0x05:
				return Instruction.MOD;
			case 0x10:
				return Instruction.DADD;
			case 0x11:
				return Instruction.DSUB;
			case 0x12:
				return Instruction.DMULT;
			case 0x13:
				return Instruction.DDIV;
			case 0x20:
				return Instruction.GREATER;
			case 0x21:
				return Instruction.LESS;
			case 0x22:
				return Instruction.EQL;
			case 0x23:
				return Instruction.AND;
			case 0x24:
				return Instruction.XOR;
			case 0x30:
				return Instruction.RFETCH;
			case 0x31:
				return Instruction.RPUSH;
			case 0x32:
				return Instruction.RFROM;
			case 0x40:
				return Instruction.DPUSH;
			case 0x41:
				return Instruction.ROLL;
			case 0x42:
				return Instruction.DPOP;
			case 0x50:
				return Instruction.CJMP;
			case 0x51:
				return Instruction.JMP;
			case 0x52:
				return Instruction.FETCH;
			case 0x53:
				return Instruction.STORE;
			case 0x54:
				return Instruction.ALLOC;
			case 0x55:
				return Instruction.ALLOCSTRING;
			case 0x56:
				return Instruction.ALLOCVAR;
			case 0x60:
				return Instruction.COMPILE;
			case 0x61:
				return Instruction.INTERP;
			default:
				return null;
		}
	}
	
}
