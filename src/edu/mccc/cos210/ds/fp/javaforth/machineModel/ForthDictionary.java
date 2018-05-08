package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import javax.swing.JPanel;
import edu.mccc.cos210.ds.Map;
import edu.mccc.cos210.ds.SinglyLinkedList;
import edu.mccc.cos210.ds.fp.javaforth.words.*;

public class ForthDictionary {
	Map<String, ForthWordBase> dictionary = new Map<String, ForthWordBase>();
	SinglyLinkedList<IDictionaryUpdatedEventListener> dictionaryUpdatedEventListeners = new SinglyLinkedList<>();
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
	public void addWord(ForthWordBase word) {
		this.dictionary.put(word.getName(), word);
		this.dictionaryUpdatedEventListeners.forEach(l -> l.onDictionaryUpdated(this.dictionary));
	}
	public void addDictionaryUpdatedEventListener(IDictionaryUpdatedEventListener listener) {
		this.dictionaryUpdatedEventListeners.addFirst(listener);
	}
}
