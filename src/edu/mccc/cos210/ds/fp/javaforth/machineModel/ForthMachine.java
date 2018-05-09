package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import java.io.StreamTokenizer;
import java.io.StringReader;
import edu.mccc.cos210.ds.SinglyLinkedList;
import edu.mccc.cos210.ds.fp.javaforth.controlwords.CompiledWord;
import edu.mccc.cos210.ds.fp.javaforth.util.IStackUpdatedEventListener;
/**
 * Class for performing forth computations. 
 * @author Jing-Chao Feng, Nicholas Holmgren, Ryan Hammound
 *
 */
public class ForthMachine {
	SinglyLinkedList<ITerminalUpdatedEventListener> terminalUpdatedEventListener = new SinglyLinkedList<>();
	SinglyLinkedList<IStackUpdatedEventListener> stackUpdatedEventListeners = new SinglyLinkedList<>();
	/**
	 * Adds the listener to the collection of dictionary update listeners, who are notified on dictionary update events.
	 * @param listener - the new listener
	 */
	public void AddDictionaryUpdatedEventListener(IDictionaryUpdatedEventListener listener) {
		this.dictionary.addDictionaryUpdatedEventListener(listener);
	}
	/**
	 * Adds the listener to the collection of stack event listeners, who are notified on stack update events.
	 * @param listener - the new listner 
	 */
	public void AddStackUpdatedEventListener(IStackUpdatedEventListener listener) {
		this.stackUpdatedEventListeners.addFirst(listener);
		this.stack.addStackUpdatedEventListener(listener);
	}
	/**
	 * Adds the listener to the collection of terminal event listeners, who are notified on terminal update events.
	 * @param listener - the new listner 
	 */
	public void AddTerminalUpdatedEventListener(ITerminalUpdatedEventListener listener) {
		this.terminalUpdatedEventListener.addFirst(listener);
	}
	private ObservableStack stack = new ObservableStack();
	private ForthDictionary dictionary = new ForthDictionary();
	private Object executingLock = new Object();
	private boolean pauseRequested;
	private Thread thread;
	public ForthMachine() {
		this.dictionary.initRequiredWords();
		this.dictionary.initGraphicsWords();
	}
	/**
	 * Passes the input into the input stream so the interpreter can pick it up. Passing an empty string or no
	 * string will result in interpretation being attempted anyway, which effectively resumes the program if it
	 * was paused.
	 * @param String - input the string of input data to be passed into the inputStream.
	 */
	public void interpret(String input, boolean isInCompileMode) {
		input = input.trim();
		StreamTokenizer tokenizer = new StreamTokenizer(new StringReader(input));
		tokenizer.resetSyntax();
		tokenizer.wordChars('!', 'z');
		Thread thread = new Thread(() -> {
			synchronized (executingLock) {
				try {
					CompiledWord word = new CompiledWord();
					word.build(tokenizer, dictionary, isInCompileMode);
					word.execute(stack, dictionary, s -> this.updateTerminal(s));
				} catch (Exception ex) {
					this.terminalUpdatedEventListener.iterator().forEachRemaining(l -> l.onTerminalUpdated(false, ex.getMessage()));
				} finally {
					// TODO: CanExecuteChanged event.
				}
			}
		});
		thread.run();
		this.thread = thread;
	}
	private void updateTerminal(String s) {
		this.terminalUpdatedEventListener.iterator().forEachRemaining(l -> l.onTerminalUpdated(false, s));
	}
	/**
	 * Method that stops the interpreter from continuing and clears out the data stack, return stack, and 
	 * input stream. The program cannot resume after this method is called.
	 * @return boolean true when the halt was successful, false when it was not.
	 */
	public boolean halt() {
		while (this.thread.isAlive()) {
			this.thread.interrupt();
		}
		return true;
	}
	/**
	 * Method that pauses the interpreter, but does not empty the input stream or in any other way disrupt the 
	 * state of the machine, making it possible to resume.
	 * @return boolean true when the halt was successful, false when it was not.
	 */
	public boolean pause() {
		return true;
	}
	public void unpause() {
		this.unpause();
	}
	private void reset() {
		this.pauseRequested = false;
//		this.stack = new ObservableStack();
//		for (IStackUpdatedEventListener listener : this.stackUpdatedEventListeners) {
//			this.stack.addStackUpdatedEventListener(listener);
//		}
//		this.dictionary = new ForthDictionary();
	}
}
