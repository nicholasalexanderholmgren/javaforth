package edu.mccc.cos210.ds.fp.javaforth.controlwords;

import java.io.IOException;
import java.io.StreamTokenizer;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;
import edu.mccc.cos210.ds.fp.javaforth.words.Minus;

class ControlWordUtility {
	public static ForthWordBase buildNext(StreamTokenizer tokenizer, ForthDictionary dictionary, boolean compiling) throws IOException {
		int next = tokenizer.nextToken();
		while (next != StreamTokenizer.TT_EOF && tokenizer.sval == null) {
			next = tokenizer.nextToken();
		}
		if (next == StreamTokenizer.TT_EOF) {
			return null;
		}
		try {
			double nval = Double.parseDouble(tokenizer.sval);
			// Has to be a number.
			return new PutNumberOnStackWord(nval);
		} catch (NumberFormatException ex) {
			// Has to be a word.
			if (tokenizer.sval.equals("-")) {
				return new Minus();// Dirty hack for an issue.
			} else {
				ForthWordBase word = dictionary.getWord(tokenizer.sval);
				if (word == null) {
					throw new RuntimeException("Word not found. " + tokenizer.sval);
				} else {
					if (word.isCompileModeOnly() && !compiling) {
						throw new RuntimeException(word.getName() + " is only available in compile mode.");
					}
					if (word instanceof ForthControlWordBase) {
						word = ((ForthControlWordBase) word).build(tokenizer, dictionary, compiling);
					}
					return word;
				}
			}
		}
	}
}
