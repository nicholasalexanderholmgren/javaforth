package edu.mccc.cos210.ds.fp.javaforth;

import java.awt.EventQueue;
import edu.mccc.cos210.ds.fp.javaforth.machine_model.ForthMachine;
import edu.mccc.cos210.ds.fp.javaforth.view_ide.IdeWindow;

public class JavaForth {
	private ForthMachine machine;
	private IdeWindow view;
	JavaForth() {
		this.machine = createForthMachine();
		this.view = createview_ide(machine);
	}
	public static IdeWindow createview_ide(ForthMachine machine) {
		return new IdeWindow(machine);
	}
	public ForthMachine createForthMachine() { 
		return new ForthMachine();
	}
	public static void main(String... args) {
		EventQueue.invokeLater(() ->new JavaForth());
	}
}
