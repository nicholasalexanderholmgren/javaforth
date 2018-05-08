package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import java.util.StringTokenizer;
import edu.mccc.cos210.ds.SinglyLinkedList;
import edu.mccc.cos210.ds.fp.javaforth.util.IStackUpdatedEventListener;
import edu.mccc.cos210.ds.fp.javaforth.util.ObservableStack;

public class ForthMachine {
	SinglyLinkedList<IDictionaryUpdatedEventListener> dictionaryUpdatedEventListeners = new SinglyLinkedList<IDictionaryUpdatedEventListener>();
	SinglyLinkedList<ITerminalUpdatedEventListener> terminalUpdatedEventListener = new SinglyLinkedList<ITerminalUpdatedEventListener>();
	public void AddDictionaryUpdatedEventListener(IDictionaryUpdatedEventListener listener) {
		this.dictionaryUpdatedEventListeners.addFirst(listener);
	}
	public void AddStackUpdatedEventListener(IStackUpdatedEventListener listener) {
		this.stack.addStackUpdatedEventListener(listener);
	}
	public void AddTerminalUpdatedEventListener(ITerminalUpdatedEventListener listener) {
		this.terminalUpdatedEventListener.addFirst(listener);
	}
	private ObservableStack stack = new ObservableStack();
	public ForthMachine() {
	}
	/**
	 * Passes the input into the input stream so the interpreter can pick it up. Passing an empty string or no
	 * string will result in interpretation being attempted anyway, which effectively resumes the program if it
	 * was paused.
	 * @param String - input the string of input data to be passed into the inputStream.
	 */
	public void interpret(String input) {
		input = input.trim();
		StringTokenizer tokenizer = new StringTokenizer(input);
		while(tokenizer.hasMoreTokens()) {
			String tokenType = tokenizer.nextToken();
		}
		
		// TODO
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
}
