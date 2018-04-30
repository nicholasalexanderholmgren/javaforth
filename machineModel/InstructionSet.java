package edu.mccc.cos210.ds.fp.javaforth.machineModel;
public interface InstructionSet {
	/**Takes a byte and returns an enumerator specifying which instruction it corresponds to. This does not carry with 
	 * it any instructions as to how to implement doing the instruction, just what instruction the byte refers to.
	 */
	public Instruction convert(Byte B);
}
