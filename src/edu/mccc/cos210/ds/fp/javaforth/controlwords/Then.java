package edu.mccc.cos210.ds.fp.javaforth.controlwords;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthDictionary;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.ForthWordBase;

public class Then extends ForthWordBase {
	public Then() {
		super("THEN", "Used in a colon-definition in the form: IF ... ELSE ... THEN or IF ... THEN. THEN is the point where execution resumes after ELSE or IF (when no ELSE is present).");
	}
	@Override
	public void execute(IStack<Object> stack, ForthDictionary dictionary) {
	}
}
