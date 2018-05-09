package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import java.awt.EventQueue;
import edu.mccc.cos210.ds.IMap;
import edu.mccc.cos210.ds.Map;
import edu.mccc.cos210.ds.SinglyLinkedList;
import edu.mccc.cos210.ds.fp.javaforth.graphicswords.*;
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
			new Abs(),
			new ConDup(), 
			new Depth(),
			new Divide(), 
			new Dot(), 
			new Dup(), 
			new Equals(), 
			new Emit(),
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
			//double words
			new DoubleMinus(),
			new DoublePlus(),
			// Control words aka words that look ahead. Orginally used to solve control structures.
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
			new IPrime(),
			new J(),
			new K(),
			new Leave(),
			new Forget(),
		};
		for (ForthWordBase word : words) {
			this.dictionary.put(word.getName(), word);
		}
	}
	public void initGraphicsWords() {
		GraphicsFrame frame = new GraphicsFrame();
		GraphicsWordBase[] words = new GraphicsWordBase[]
		{
			new ClearGraphics(frame),
			new DrawCubicCurve(frame),
			new DrawLine(frame),
			new DrawOval(frame),
			new DrawQuadCurve(frame),
			new FillOval(frame),
			new FillQuadCurve(frame),
			new FillRectangle(frame),
			new SetBackgroundColor(frame),
			new SetColor(frame),
			new SetStroke(frame),
		};
		for (ForthWordBase word : words) {
			this.dictionary.put(word.getName(), word);
		}
	}
	public void addWord(ForthWordBase word) {
		this.dictionary.put(word.getName(), word);
		this.dictionaryUpdatedEventListeners.forEach(l -> l.onDictionaryUpdated(this.dictionary));
	}
	public boolean removeWord(String name) {
		Map<String, ForthWordBase> oldDictionary = this.dictionary;
		this.dictionary = new Map<>();
		boolean success = false;
		for (IMap.Entry<String, ForthWordBase> entry : oldDictionary) {
			if (!entry.getKey().equals(name.toUpperCase())) {
				this.dictionary.put(entry.getKey(), entry.getValue());
			} else {
				success = true;
			}
		}
		if (success) {
			this.dictionaryUpdatedEventListeners.forEach(l -> l.onDictionaryUpdated(this.dictionary));
		}
		return success;
	}
	public void addDictionaryUpdatedEventListener(IDictionaryUpdatedEventListener listener) {
		listener.onDictionaryUpdated(this.dictionary);
		this.dictionaryUpdatedEventListeners.addFirst(listener);
		EventQueue.invokeLater(() -> listener.onDictionaryUpdated(dictionary));
	}
}
