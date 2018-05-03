package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
  * Functional interface that takes a forthMachine and the name before the .dict of a dictionary file to be loaded and 
  * appends the contents of that dictionary file to the given forthMachine's dictionary.
  * @FunctionalInterface
  */
public interface DictionaryLoader {
	public static void loadDict(ForthMachine destination, String dictionaryFile) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(
				"DictionaryFiles/".concat(dictionaryFile.concat(".dict")))));
		while(br.ready()) {
			StringTokenizer tokens = new StringTokenizer(br.readLine());
			destination.getDictionary().allocate(tokens.nextToken());
			tokens.nextToken();
			while(tokens.hasMoreTokens()) {
				String token = tokens.nextToken();
				if(!(token.equals(";") || token.equals("DPUSH"))) {
					Byte b = Forth79InstructionSet.convert(Instruction.valueOf(token));
					destination.putAtNextAddr(b);
					continue;
				}else {
					if (token.equals(";")) {
						destination.putAtNextAddr(Forth79InstructionSet.convert(Instruction.valueOf("RFROM")));
						destination.putAtNextAddr(Forth79InstructionSet.convert(Instruction.valueOf("JMP")));
					}
					if(token.equals("DPUSH")) {
						destination.putAtNextAddr(Forth79InstructionSet.convert(Instruction.valueOf("DPUSH")));
						Integer i = Integer.parseInt(tokens.nextToken());
						i = (int) (i%Math.pow(2, 16));
						destination.putAtNextAddr(Integer.valueOf(i/256).byteValue());
						destination.putAtNextAddr(Integer.valueOf(i%256).byteValue());
					}
				}
				
			}
		}
		br.close();
	}
}
