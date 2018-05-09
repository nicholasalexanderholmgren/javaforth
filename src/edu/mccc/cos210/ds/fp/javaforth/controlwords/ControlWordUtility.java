package edu.mccc.cos210.ds.fp.javaforth.controlwords;

import java.io.IOException;
import java.io.StreamTokenizer;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

class ControlWordUtility {
	public static ForthWordBase buildNext(StreamTokenizer tokenizer, ForthDictionary dictionary) throws IOException {
		int next = tokenizer.nextToken();
		boolean buildingFalse = false;
		if (next == StreamTokenizer.TT_EOF) {
			return null;
		}
		if (next == StreamTokenizer.TT_NUMBER) {
//			return tokenizer.nval;
		} else {
			// Has to be a word.
			ForthWordBase word = dictionary.getWord(tokenizer.sval);
			if (buildingFalse) {
				//this.codeBlockForFalse.addLast(word);
			} else {
//				this.codeBlockForTrue.addLast(word);
			}
		}
		throw new UnsupportedOperationException();
	}
}
