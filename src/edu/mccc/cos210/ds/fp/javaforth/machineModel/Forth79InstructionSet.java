package edu.mccc.cos210.ds.fp.javaforth.machineModel;


/**
 * Class handles translating instructions to bytes and bytes to instructions for the forth79 standard machine.
 */
public interface Forth79InstructionSet extends InstructionSet{
	/*
	 * 
	 */
	public static Instruction convert(Byte b) {
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
				return Instruction.SUBJMP;
			case 0x53:
				return Instruction.FETCH;
			case 0x54:
				return Instruction.STORE;
			case 0x55:
				return Instruction.ALLOC;
			case 0x56:
				return Instruction.ALLOCSTRING;
			case 0x57:
				return Instruction.ALLOCVAR;
			case 0x60:
				return Instruction.COMPILE;
			case 0x61:
				return Instruction.INTERP;
			case 0x70:
				return Instruction.NUMOUT;
			default:
				return null;
		}
	}
	public static Byte convert(Instruction i) {
		switch(i) {
			case ADD:
				return 0x01;
			case SUB:
				return 0x02;
			case MULT:
				return 0x03;
			case DIV:
				return 0x04;
			case MOD:
				return 0x05;
			case DADD:
				return 0x10;
			case DSUB:
				return 0x11;
			case DMULT:
				return 0x12;
			case DDIV:
				return 0x13;
			case GREATER:
				return 0x20;
			case LESS:
				return 0x21;
			case EQL:
				return 0x22;
			case AND:
				return 0x23;
			case XOR:
				return 0x24;
			case RFETCH:
				return 0x30;
			case RPUSH:
				return 0x31;
			case RFROM:
				return 0x32;
			case DPUSH:
				return 0x40;
			case ROLL:
				return 0x41;
			case DPOP:
				return 0x42;
			case CJMP:
				return 0x50;
			case JMP:
				return 0x51;
			case SUBJMP:
				return 0x52;
			case FETCH:
				return 0x53;
			case STORE:
				return 0x54;
			case ALLOC:
				return 0x55;
			case ALLOCSTRING:
				return 0x56;
			case ALLOCVAR:
				return 0x57;
			case COMPILE:
				return 0x60;
			case INTERP:
				return 0x61;
			case NUMOUT:
				return 0x70;
			default:
				return 0;
		}
	}
}
