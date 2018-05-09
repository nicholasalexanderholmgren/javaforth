package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import java.awt.EventQueue;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.StringTokenizer;
import edu.mccc.cos210.ds.SinglyLinkedList;
import edu.mccc.cos210.ds.fp.javaforth.util.IStackUpdatedEventListener;
import edu.mccc.cos210.ds.fp.javaforth.util.ObservableStack;

public class ForthMachine {
	SinglyLinkedList<IDictionaryUpdatedEventListener> dictionaryUpdatedEventListeners = new SinglyLinkedList<>();
	SinglyLinkedList<ITerminalUpdatedEventListener> terminalUpdatedEventListener = new SinglyLinkedList<>();
	SinglyLinkedList<IStackUpdatedEventListener> stackUpdatedEventListeners = new SinglyLinkedList<>();
	public void AddDictionaryUpdatedEventListener(IDictionaryUpdatedEventListener listener) {
		this.dictionaryUpdatedEventListeners.addFirst(listener);
	}
	public void AddStackUpdatedEventListener(IStackUpdatedEventListener listener) {
		this.stackUpdatedEventListeners.addFirst(listener);
		this.stack.addStackUpdatedEventListener(listener);
	}
	public void AddTerminalUpdatedEventListener(ITerminalUpdatedEventListener listener) {
		this.terminalUpdatedEventListener.addFirst(listener);
	}
	private ObservableStack stack = new ObservableStack();
	private ForthDictionary dictionary = new ForthDictionary();
	private Object executingLock = new Object();
	private volatile boolean stopRequested = false;
	private boolean pauseRequested;
	public ForthMachine() {
		this.dictionary.initRequiredWords();
	}
	/**
	 * Passes the input into the input stream so the interpreter can pick it up. Passing an empty string or no
	 * string will result in interpretation being attempted anyway, which effectively resumes the program if it
	 * was paused.
	 * @param String - input the string of input data to be passed into the inputStream.
	 */
	public void interpret(String input) {
		input = input.trim();
		StreamTokenizer tokenizer = new StreamTokenizer(new StringReader(input));
		tokenizer.resetSyntax();
		tokenizer.wordChars('!', 'z');
		new Thread(() -> {
			synchronized (executingLock) {
				try {
					int next = tokenizer.nextToken();
					StringBuilder dotQuoteContent = null;
					while (next != StreamTokenizer.TT_EOF) {
						while (this.pauseRequested) {
							Thread.sleep(0);
						}
						if (this.stopRequested) {
							return;
						}
						int t = '\"';
						if (tokenizer.sval != null) {
							try {
								double nval = Double.parseDouble(tokenizer.sval);
								// Has to be a number.
								this.stack.push(nval);
							} catch (NumberFormatException ex) {
								if (dotQuoteContent != null) {
									dotQuoteContent.append(tokenizer.sval.substring(0, tokenizer.sval.length() - 2));
									if (tokenizer.sval.endsWith("\"")) {
										this.updateTerminal(dotQuoteContent.toString());
										dotQuoteContent = null;
									}
								} else {
									// Has to be a word.
									if (tokenizer.sval.equals(".\"")) {
										dotQuoteContent = new StringBuilder();
									} else {
										ForthWordBase word = this.dictionary.getWord(tokenizer.sval);
										if(word == null) {
											throw new RuntimeException("Word not found. " + tokenizer.sval);
										}
										word.execute(stack, dictionary);
										word.execute(stack, dictionary, s -> updateTerminal(s));
									}
								}
							}
						}
						next = tokenizer.nextToken();
					}
					if (dotQuoteContent != null) {
						throw new RuntimeException("dot-quote .\" not properly closed");
					}
				} catch (Exception ex) {
					this.terminalUpdatedEventListener.iterator().forEachRemaining(l -> l.onTerminalUpdated(false, ex.getMessage()));
				} finally {
					// TODO: CanExecuteChanged event.
				}
			}
		}).run();
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
	/**
	 * Method for retrieving the data stack as a string. The stack contains only byte objects, so this string
	 * will be formatted into two digit hexadecimal numbers (0x00 - 0xFF) separated by spaces.
	 * @return String
	 */
	public Iterable<String> getStackAsString() {
		throw new UnsupportedOperationException();
	}
	/**
	 * Method retrieves each variable declared in the 
	 * @return Map<String,String>
	 */
	public edu.mccc.cos210.ds.Map<String, String> getDictionaryAsMap() {
		throw new UnsupportedOperationException();
	}
	private void reset() {
		this.stopRequested = false;
		this.pauseRequested = false;
//		this.stack = new ObservableStack();
//		for (IStackUpdatedEventListener listener : this.stackUpdatedEventListeners) {
//			this.stack.addStackUpdatedEventListener(listener);
//		}
//		this.dictionary = new ForthDictionary();
	}
}
