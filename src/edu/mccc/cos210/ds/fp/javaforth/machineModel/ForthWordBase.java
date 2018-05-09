package edu.mccc.cos210.ds.fp.javaforth.machineModel;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ITerminalOutput;

public abstract class ForthWordBase {
	private String name;
	private String description;
	private boolean compileModeOnly;
	public String getDescription() {
		return description;
	}
	/**
	 * Constructor for specifying a word's name, description, and whether it can be only used in compile mode.
	 * @param name - the name of the word as a string, note: this will be stored in upper case to comply with Forth's case
	 * insensitivity.
	 * @param description - the description of the word for use in a dictionary display, as a string.
	 * @param compileModeOnly - true if the word can exclusively be used in compile mode.
	 */
	public ForthWordBase(String name, String description, boolean compileModeOnly) {
		this(name, description);
		this.compileModeOnly = compileModeOnly;
	}
	/**
	 * Constructor that assumes compile mode is false and thus only requires the name and description fields
	 * @param name - the name of the word as a string, note: this will be stored in upper case to comply with Forth's case
	 * insensitivity.
	 * @param description - the description of the word for use in a dictionary display, as a string.
	 */
	public ForthWordBase(String name, String description) {
		super();
		if (name != null) {
			this.name = name.toUpperCase();
		}
		this.description = description;
	}
	/**
	 * Method for retrieving the name of this word.
	 * @return String - the name of the word.
	 */
	public String getName() {
		return name;
	}
	/**
	 * Method by which the word causes its change to the data in the forth machine. See class description for what changes a given
	 * word is meant to do.
	 * @param stack - The data stack from which values can be popped and pushed.
	 * @param dictionary - The forth dictionary to from which variables can be retrieved and new words can be written
	 * @param terminalOutput - The output listener into which this word writes.
	 */
	public abstract void execute(IStack<Object> stack, ForthDictionary dictionary, ITerminalOutput terminalOutput);
	public boolean isCompileModeOnly() {
		return compileModeOnly;
	}
	protected void throwUnexpectedWord() {
		throw new RuntimeException("Unexpected word " + name + " encountered.");
	}
}
