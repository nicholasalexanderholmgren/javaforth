package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import java.awt.EventQueue;
import edu.mccc.cos210.ds.Map;
import edu.mccc.cos210.ds.SinglyLinkedList;
import edu.mccc.cos210.ds.fp.javaforth.graphicswords.DrawLine;
import edu.mccc.cos210.ds.fp.javaforth.viewIde.GraphicsFrame;
import edu.mccc.cos210.ds.fp.javaforth.words.*;
import edu.mccc.cos210.ds.fp.javaforth.controlwords.*;

public class ForthDictionary {
	Map<String, ForthWordBase> dictionary = new Map<String, ForthWordBase>();
	SinglyLinkedList<IDictionaryUpdatedEventListener> dictionaryUpdatedEventListeners = new SinglyLinkedList<>();
	public ForthWordBase getWord(String name) {
		return this.dictionary.get(name.toUpperCase());
	}
	public void initRequiredWords() {
		ForthWordBase[] words = new ForthWordBase[] 
		{ 
			new ConDup(), 
			new Divide(), 
			new Dot(), 
			new Dup(), 
			new Equals(), 
			new False(), 
			new Fetch(), 
			new GreaterThan(), 
			new LessThan(), 
			new Minus(), 
			new Mod(), 
			new Negate(), 
			new BitwiseOr(), 
			new Plus(),
			new Sharp(), 
			new Sleep(),
			new Store(), 
			new Times(), 
			new True(), 
			new ZeroEquals(), 
			new ZeroLess(), 
			new ZeroMore(),
			// Control words
			new If(),
			new Else(),
			new Then(),
			new DotQuote(),
			new Colon(),
			new SemiColon(),
			new Comment(),
			new Loop(),
			new PlusLoop(),
			new Do(),
			new I(),
			new J(),
			new K(),
		};
		for (ForthWordBase word : words) {
			this.dictionary.put(word.getName(), word);
		}
	}
	public void initGraphicsWords() {
		GraphicsFrame frame = new GraphicsFrame();
		GraphicsWordBase[] words = new GraphicsWordBase[]
		{ 
			new DrawLine(frame),
		};
		for (ForthWordBase word : words) {
			this.dictionary.put(word.getName(), word);
		}
	}
	public void addWord(ForthWordBase word) {
		this.dictionary.put(word.getName(), word);
		this.dictionaryUpdatedEventListeners.forEach(l -> l.onDictionaryUpdated(this.dictionary));
	}
	public void addDictionaryUpdatedEventListener(IDictionaryUpdatedEventListener listener) {
		listener.onDictionaryUpdated(this.dictionary);
		this.dictionaryUpdatedEventListeners.addFirst(listener);
		EventQueue.invokeLater(() -> listener.onDictionaryUpdated(dictionary));
	}
}
