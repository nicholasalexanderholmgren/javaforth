package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import javax.swing.JPanel;
import edu.mccc.cos210.ds.Map;
import edu.mccc.cos210.ds.fp.javaforth.words.*;

public class ForthDictionary {
	Map<String, ForthWordBase> dictionary = new Map<String, ForthWordBase>();
	public ForthWordBase getWord(String name) {
		return this.dictionary.get(name);
	}
	public void initRequiredWords() {
		ForthWordBase[] words = new ForthWordBase[] {
			new Store(),
		};
		for (ForthWordBase word : words) {
			this.dictionary.put(word.getName(), word);
		}
	}
	public void initGraphicsWords() {
		JPanel panel = new JPanel();// TODO...
		
	}
}
