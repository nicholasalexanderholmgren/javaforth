package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import java.awt.EventQueue;

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
			new ConDup(), new Divide(), new Dot(), new Dup(), new Equals(), new False(), new Fetch(), new GreaterThan(),
			new LessThan(), new Minus(), new Mod(), new Negate(), new BitwiseOr(), new Plus(), new Sharp(), new Store(), new Times(), new True(),
			new ZeroEquals(), new ZeroLess(), new ZeroMore(),
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
		listener.onDictionaryUpdated(this.dictionary);
		this.dictionaryUpdatedEventListeners.addFirst(listener);
		EventQueue.invokeLater(()-> listener.onDictionaryUpdated(dictionary) );
	}
}
