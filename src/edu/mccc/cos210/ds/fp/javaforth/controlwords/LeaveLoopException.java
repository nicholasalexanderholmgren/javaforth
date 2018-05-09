package edu.mccc.cos210.ds.fp.javaforth.controlwords;

@SuppressWarnings("serial")
public class LeaveLoopException extends RuntimeException {
	public LeaveLoopException() {
		super("Unexpected word LEAVE in current context.");
	}
}
