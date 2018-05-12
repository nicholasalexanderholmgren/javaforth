package edu.mccc.cos210.ds.fp.javaforth.machine_model;

public enum Instruction {
	//Arithmatic operations 00-0F
	ADD(0x01), SUB(0x02), DIV(0x03), MULT(0x04), MOD(0x05),
	//Arithmatic double operations 10-1F
	DADD(0x10), DSUB(0x11), DDIV(0x12), DMULT(0x13),
	//logical operations 20-2F
	GREATER(0x20), LESS(0x21), EQL(0x22), AND(0x23), XOR(0x24),
	//return stack operations 30-3F
	RFETCH(0x30), RPUSH(0x31), RFROM(0x32), RETURN(0x33),
	//data stack operations 40-4F
	//Data Stack Push
	//Data Stack Push character
	//Roll data stack
	//Pop data Stack
	DPUSH(0x40), DPUSHC(0x41),  ROLL(0x42), DPOP(0x43),
	//memory operations 50-5F
	CJMP(0x50), JMP(0x51), SUBJMP(0x52), FETCH(0x53), ALLOC(0x54),
	STORE(0x55), ALLOCSTRING(0x56), ALLOCVAR(0x57),
	//mode change 60-6F
	COMPILE(0x60), INTERP(0x61), 
	//output operations 70-7F
	NUMOUT(0x70), CR(0x71), SPACE(0x72), CHAROUT(0x73);
	
	private final int byteCode;
	Instruction(int hexValue){
		this.byteCode = hexValue;
	}
	public Byte getByteCode() {
		return (byte) byteCode;
	}
}
