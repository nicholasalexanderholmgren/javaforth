package edu.mccc.cos210.ds.fp.javaforth.machineModel;

public enum Instruction {
	//Arithmatic operations 00-0F
	ADD, SUB, DIV, MULT, MOD,
	//Arithmatic double operations 10-1F
	DADD, DSUB, DDIV, DMULT, DMOD,
	//logical operations 20-2F
	GREATER, LESS, EQL, AND, XOR,
	//return stack operations 30-3F
	RFETCH, RPUSH,  RFROM, 
	//data stack operations 40-4F
	DPUSH, ROLL, DPOP,
	//memory operations 50-5F
	CJMP, JMP,  FETCH, STORE, ALLOC, ALLOCSTRING, ALLOCVAR,
	//mode change 60-6F
	COMPILE, INTERP, 
	//output operations 70-7F
}
