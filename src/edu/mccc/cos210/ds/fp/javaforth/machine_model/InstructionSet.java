package edu.mccc.cos210.ds.fp.javaforth.machine_model;
public interface InstructionSet {
	/**Takes a byte and returns an enumerator specifying which instruction it corresponds to. T
	 * his does not carry with 
	 * it any information as to how to implement doing the instruction,
	 * just what instruction the byte refers to.
	 */
	public static byte convert(Byte B) {
		return 0;
	}
}
