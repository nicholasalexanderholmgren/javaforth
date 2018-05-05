package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.StringTokenizer;

import edu.mccc.cos210.ds.fp.javaforth.util.ByteUtils;

/**
 * Functional interface that takes a forthMachine and the name before the .dict of a dictionary file to be loaded and 
 * appends the contents of that dictionary file to the given forthMachine's dictionary.
 * @FunctionalInterface
 */
public interface DictionaryLoader {
	public static void loadDict(ForthMachine destination, String dictionaryFile) throws IOException {
		Collection<String> failedLines = new HashSet<>();
		BufferedReader br = new BufferedReader(new FileReader(new File(
				"DictionaryFiles/".concat(dictionaryFile.concat(".dict")))));
		while(br.ready()) {
			String currentLine = br.readLine();
			StringTokenizer tokens = new StringTokenizer(currentLine);
			String name = tokens.nextToken();
			destination.getDictionary().allocate(name);
			tokens.nextToken();
			String token = "";
			try {
				while(tokens.hasMoreTokens()) {
					 token = tokens.nextToken();
					if(!(token.equals(";") || token.equals("DPUSH"))) {
						Byte b = Forth79InstructionSet.convert(Instruction.valueOf(token));
						destination.putAtNextAddr(b);
						continue;
					}else {
						if (token.equals(";")) {
							destination.putAtNextAddr(Forth79InstructionSet.convert(Instruction.valueOf("RFROM")));
							destination.putAtNextAddr(Forth79InstructionSet.convert(Instruction.valueOf("JMP")));
							break;
						}
						if(token.equals("DPUSH")) {
							destination.putAtNextAddr(Forth79InstructionSet.convert(Instruction.valueOf("DPUSH")));
							Integer i = Integer.parseInt(tokens.nextToken());
							i = (int) (i%Math.pow(2, 16));
							destination.putAtNextAddr(Integer.valueOf(i/256).byteValue());
							destination.putAtNextAddr(Integer.valueOf(i%256).byteValue());
						}
						if(destination.getDictionary().contains(token)) {

						}
					}
				}
				StringBuilder definition = new StringBuilder();
				while(tokens.hasMoreTokens()) {
					definition.append(tokens.nextToken()+" ");
				}
				destination.getDictionary().addDefinition(name, definition.toString());
			}catch(IllegalArgumentException e) {
				if(destination.getDictionary().contains(token)) {
					destination.putAtNextAddr(Forth79InstructionSet.convert(Instruction.SUBJMP));
					Byte[] addr = ByteUtils.addrToBytes(destination.getDictionary().findAddr(token));
					destination.putAtNextAddr(addr[0]);
					destination.putAtNextAddr(addr[1]);
				}
				failedLines.add(currentLine);
			}
		}
		br.close();
	}
}
